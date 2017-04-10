package com.hifunki.funki.module.live.anchor;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.SurfaceTexture;
import android.opengl.GLES20;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.Surface;

import com.hifunki.funki.util.gpuimage.GPUImageBaseParamFilter;
import com.hifunki.funki.util.gpuimage.GPUImageBeautifyFilter;
import com.hifunki.funki.util.gpuimage.GPUImageCameraInputFilter;
import com.hifunki.funki.util.gpuimage.GPUImageSubareaFilter;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import jp.co.cyberagent.android.gpuimage.GPUImageAlphaBlendFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageExposureFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageFilterGroup;
import jp.co.cyberagent.android.gpuimage.GPUImageGrayscaleFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageSharpenFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageSketchFilter;
import jp.co.cyberagent.android.gpuimage.OpenGlUtils;
import jp.co.cyberagent.android.gpuimage.Rotation;
import jp.co.cyberagent.android.gpuimage.TextureRotationUtil;

/* Video Render Library */
public class GlVideoRender extends Thread {
	private static final String mTag = "GlVideoRender";

	private int mInWidth = 0;
	private int mInHeight = 0;
	private boolean mMirror = false;
	private boolean mFilterEn = false;

	private Object mLock = null;
	private Handler mHandler = null;

	private int[] mFbo = null;
	private int[] mFbTexture = null;

	private EglCore mEglCore = null;
	private OffscreenSurface mOffscreenSurface = null;
	private WindowSurface mViewSurface = null;
	private WindowSurface mEncSurface = null;

	private FloatBuffer mVerCoordArray = null;
	private FloatBuffer mTexCoordArray = null;

	private GPUImageCameraInputFilter mCameraInputFilter = null;
	private GPUImageFilter mDrawFilter = null;

	private GPUImageFilterGroup mFilterGroup = null;
	private GPUImageBaseParamFilter mBaseFilter = null;
	private GPUImageBeautifyFilter mBeautifyFilter = null;
	private GPUImageExposureFilter mExposureFilter = null;
	private GPUImageGrayscaleFilter mGrayscaleFilter = null;
	private GPUImageSharpenFilter mSharpenFilter = null;
	private GPUImageSketchFilter mSketchFilter = null;

	private GPUImageAlphaBlendFilter mOsdBlendFilter = null;
	private GPUImageSubareaFilter mSubareaFilter = null;

	public GlVideoRender(int inwidth, int inheight) {
		if (inwidth <= 0 || inheight <= 0) {
			throw new RuntimeException("Invalid parameters");
		}
		
		mInWidth = inwidth;
		mInHeight = inheight;

		mLock = new Object();
	}

	private void glesStart() {
		mEglCore = new EglCore(null, EglCore.FLAG_RECORDABLE);
		mOffscreenSurface = new OffscreenSurface(mEglCore, mInWidth, mInHeight);
		mOffscreenSurface.makeCurrent();

		Log.d(mTag, "GL_RENDERER: " + GLES20.glGetString(GLES20.GL_RENDERER));
		Log.d(mTag, "GL_VENDOR: " + GLES20.glGetString(GLES20.GL_VENDOR));
		Log.d(mTag, "GL_VERSION: " + GLES20.glGetString(GLES20.GL_VERSION));
		Log.d(mTag, "GL_EXTENSIONS: " + GLES20.glGetString(GLES20.GL_EXTENSIONS));

		// GLES20.glEnable(GLES20.GL_DEPTH_TEST);
		GLES20.glEnable(GLES20.GL_CULL_FACE);
		GLES20.glCullFace(GLES20.GL_BACK);
		// GLES20.glEnable(GLES20.GL_SCISSOR_TEST);

		GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		GLES20.glClearDepthf(1.0f);

		mVerCoordArray = ByteBuffer.allocateDirect(TextureRotationUtil.TEXTURE_NO_ROTATION.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
		mTexCoordArray = ByteBuffer.allocateDirect(TextureRotationUtil.TEXTURE_NO_ROTATION.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();

		mFbTexture = new int[2];
		GLES20.glGenTextures(2, mFbTexture, 0);

		GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
		GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, mFbTexture[0]);
		GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_S, GLES20.GL_CLAMP_TO_EDGE);
		GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_T, GLES20.GL_CLAMP_TO_EDGE);
		GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_NEAREST);
		GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR);
		GLES20.glTexImage2D(GLES20.GL_TEXTURE_2D, 0, GLES20.GL_RGBA, mInWidth, mInHeight, 0, GLES20.GL_RGBA, GLES20.GL_UNSIGNED_BYTE, null);
		GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
		GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, mFbTexture[1]);
		GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_S, GLES20.GL_CLAMP_TO_EDGE);
		GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_T, GLES20.GL_CLAMP_TO_EDGE);
		GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_NEAREST);
		GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR);
		GLES20.glTexImage2D(GLES20.GL_TEXTURE_2D, 0, GLES20.GL_RGBA, mInWidth, mInHeight, 0, GLES20.GL_RGBA, GLES20.GL_UNSIGNED_BYTE, null);

		mFbo = new int[1];
		GLES20.glGenFramebuffers(1, mFbo, 0);
		GLES20.glBindFramebuffer(GLES20.GL_FRAMEBUFFER, mFbo[0]);
		GLES20.glFramebufferTexture2D(GLES20.GL_FRAMEBUFFER, GLES20.GL_COLOR_ATTACHMENT0, GLES20.GL_TEXTURE_2D, mFbTexture[0], 0);

		if (GLES20.glCheckFramebufferStatus(GLES20.GL_FRAMEBUFFER) != GLES20.GL_FRAMEBUFFER_COMPLETE) {
			Log.e(mTag, "glCheckFramebufferStatus " + GLES20.glGetString(GLES20.glGetError()));
		} else {
			Log.d(mTag, "glCheckFramebufferStatus success");
		}

		GLES20.glBindFramebuffer(GLES20.GL_FRAMEBUFFER, 0);

		mCameraInputFilter = new GPUImageCameraInputFilter();
		mCameraInputFilter.init();

		mDrawFilter = new GPUImageFilter();
		mDrawFilter.init();

		mBaseFilter = new GPUImageBaseParamFilter();
		mExposureFilter = new GPUImageExposureFilter();
		mBeautifyFilter = new GPUImageBeautifyFilter(mInWidth, mInHeight);
		mGrayscaleFilter = new GPUImageGrayscaleFilter();
		mSharpenFilter = new GPUImageSharpenFilter();
		mSketchFilter = new GPUImageSketchFilter();

		mOsdBlendFilter = new GPUImageAlphaBlendFilter();
		mSubareaFilter = new GPUImageSubareaFilter();

		mFilterGroup = new GPUImageFilterGroup();
		mFilterGroup.addFilter(mSubareaFilter);
		mFilterGroup.addFilter(mBaseFilter);
		mFilterGroup.addFilter(mBeautifyFilter);
		// mFilterGroup.addFilter(mExposureFilter);
		// mFilterGroup.addFilter(mGrayscaleFilter);
		// mFilterGroup.addFilter(mSharpenFilter);
		// mFilterGroup.addFilter(mSketchFilter);
		// mFilterGroup.addFilter(m_transformFilter);

		mFilterGroup.init();

		mOsdBlendFilter.init();
		mOsdBlendFilter.setRotation(Rotation.NORMAL, false, true);
		mOsdBlendFilter.setMix(1.0f);
		mSubareaFilter.setRectArea(new float[] { 0.80f, 0.05f, 0.10f, 0.17f });
		mSubareaFilter.setAreaFilter(mOsdBlendFilter);

		mBaseFilter.setBrightness(0.0f); // 0.0f
		mBaseFilter.setContrast(1.0f); // 1.0f
		mBaseFilter.setSaturation(1.0f); // 1.0f
		mExposureFilter.setExposure(0.0f); // 1.0f
		mSharpenFilter.setSharpness(0.0f); // 0.0f

		mFilterGroup.onOutputSizeChanged(mInWidth, mInHeight);
	}

	@SuppressLint("WrongCall")
	private void glesDrawView() {
		if (mEglCore == null || mOffscreenSurface == null) {
			throw new RuntimeException("GlImageRender not prepared");
		}
		
		final float vercoord[] = GPUImageFilter.VERTEX_CUBE_NORMAL;
		final float texcoord[] = TextureRotationUtil.getRotation(Rotation.NORMAL, false, false);
		final float texcoord_flip[] = TextureRotationUtil.getRotation(Rotation.NORMAL, false, true);
		final float texcoord_flip_mirror[] = TextureRotationUtil.getRotation(Rotation.NORMAL, true, true);
		final float texcoord_rotated270[] = TextureRotationUtil.getRotation(Rotation.ROTATION_270, false, false);

		mOffscreenSurface.makeCurrent();

		mCameraInputFilter.getInputSurfaceTexture().updateTexImage();
		long timestamp_ns = mCameraInputFilter.getInputSurfaceTexture().getTimestamp();

		// ԭʼͼ������
		mVerCoordArray.position(0);
		mTexCoordArray.position(0);
		mVerCoordArray.put(vercoord).position(0);
		if (mMirror)
			mTexCoordArray.put(texcoord_flip_mirror).position(0);
		else
			mTexCoordArray.put(texcoord_flip).position(0);

		GLES20.glBindFramebuffer(GLES20.GL_FRAMEBUFFER, mFbo[0]);
		GLES20.glFramebufferTexture2D(GLES20.GL_FRAMEBUFFER, GLES20.GL_COLOR_ATTACHMENT0, GLES20.GL_TEXTURE_2D, mFbTexture[0], 0);
		GLES20.glViewport(0, 0, mInWidth, mInHeight);
		GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
		mCameraInputFilter.onOutputSizeChanged(mInWidth, mInHeight);
		mCameraInputFilter.onDraw(OpenGlUtils.NO_TEXTURE, mVerCoordArray, mTexCoordArray);

		int src_tex = mFbTexture[0], dst_tex = mFbTexture[1], tmp;

		// m_brightnessFilter.setBrightness(0.0f); //0.0f
		// m_contrastFilter.setContrast(1.2f); //1.2f
		// m_saturationFilter.setSaturation(1.0f); //1.0f
		// mExposureFilter.setExposure(1.0f); //1.0f
		// mBeautifyFilter.setBeautifyLevel(0); // 0
		// mSharpenFilter.setSharpness(0.0f); //0.0f
		// mGrayscaleFilter;
		// mSketchFilter;

		if (mFilterEn && mFilterGroup.getMergedFilters() != null && mFilterGroup.getMergedFilters().size() > 0) {
			// Filter ��Ч
			mVerCoordArray.position(0);
			mTexCoordArray.position(0);
			mVerCoordArray.put(vercoord).position(0);
			mTexCoordArray.put(texcoord).position(0);

			GLES20.glBindFramebuffer(GLES20.GL_FRAMEBUFFER, mFbo[0]);
			GLES20.glFramebufferTexture2D(GLES20.GL_FRAMEBUFFER, GLES20.GL_COLOR_ATTACHMENT0, GLES20.GL_TEXTURE_2D, dst_tex, 0);
			GLES20.glViewport(0, 0, mInWidth, mInHeight);
			GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
			mFilterGroup.onDraw(src_tex, mVerCoordArray, mTexCoordArray);
			tmp = src_tex;
			src_tex = dst_tex;
			dst_tex = tmp;
		}
		
		// draw view ��ʾ
		if (mViewSurface != null) {
			mViewSurface.makeCurrent();

			int vWidth = mViewSurface.getWidth();
			int vHeight = mViewSurface.getHeight();
	
			mVerCoordArray.position(0);
			mTexCoordArray.position(0);
			mVerCoordArray.put(vercoord).position(0);
			mTexCoordArray.put(texcoord_rotated270).position(0);
	
			GLES20.glBindFramebuffer(GLES20.GL_FRAMEBUFFER, 0);
			GLES20.glViewport(0, 0, vWidth, vHeight);
			GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
			mDrawFilter.onOutputSizeChanged(vWidth, vHeight);
			mDrawFilter.onDraw(src_tex, mVerCoordArray, mTexCoordArray);
			
			mViewSurface.setPresentationTime(timestamp_ns);
			mViewSurface.swapBuffers();
		}

		// draw enc ����
		if (mEncSurface != null) {
			mEncSurface.makeCurrent();

			int eWidth = mEncSurface.getWidth();
			int eHeight = mEncSurface.getHeight();
	
			mVerCoordArray.position(0);
			mTexCoordArray.position(0);
			mVerCoordArray.put(vercoord).position(0);
			mTexCoordArray.put(texcoord).position(0);
	
			GLES20.glBindFramebuffer(GLES20.GL_FRAMEBUFFER, 0);
			GLES20.glViewport(0, 0, eWidth, eHeight);
			GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
			mDrawFilter.onOutputSizeChanged(eWidth, eHeight);
			mDrawFilter.onDraw(src_tex, mVerCoordArray, mTexCoordArray);
			
			mEncSurface.setPresentationTime(timestamp_ns);
			mEncSurface.swapBuffers();
		}
		
		mOffscreenSurface.makeCurrent();
	}

	private void glesStop() {
		mOsdBlendFilter.destroy();
		
		mFilterGroup.destroy();
		mDrawFilter.destroy();
		mCameraInputFilter.destroy();
		mFilterGroup = null;
		mDrawFilter = null;
		mCameraInputFilter = null;

		mBaseFilter = null;
		mExposureFilter = null;
		mBeautifyFilter = null;
		mSharpenFilter = null;
		mGrayscaleFilter = null;
		mSketchFilter = null;
		mOsdBlendFilter = null;
		mSubareaFilter = null;

		GLES20.glBindFramebuffer(GLES20.GL_FRAMEBUFFER, 0);
		GLES20.glDeleteTextures(2, mFbTexture, 0);
		GLES20.glDeleteFramebuffers(1, mFbo, 0);
		mFbTexture = null;
		mFbo = null;

		if (mEncSurface != null) {
			mEncSurface.release();
			mEncSurface = null;
		}
		if (mViewSurface != null) {
			mViewSurface.release();
			mViewSurface = null;			
		}
		
		mOffscreenSurface.release();
		mEglCore.release();
		mOffscreenSurface = null;
		mEglCore = null;
	}

	private static final int GL_RENDER_SET_VIEW_SURFACE = 1;
	private static final int GL_RENDER_SET_ENC_SURFACE = 2;
	private static final int GL_RENDER_DRAW_FRAME = 3;
	private static final int GL_RENDER_QUIT = 4;

	public void run() {
		glesStart();

		Looper.prepare();
		mHandler = new Handler() {
			long prevTime = System.currentTimeMillis();
			long frameCnt = 0;

			public void handleMessage(Message msg) {
				switch (msg.what) {
				case GL_RENDER_SET_VIEW_SURFACE:
					if (mViewSurface != null) {
						mViewSurface.release();
						mViewSurface = null;
					}
					if (msg.obj != null && msg.obj instanceof Surface) {
						mViewSurface = new WindowSurface(mEglCore, (Surface)msg.obj, false);
					}
					break;
					
				case GL_RENDER_SET_ENC_SURFACE:
					if (mEncSurface != null) {
						mEncSurface.release();
						mEncSurface = null;
					}
					if (msg.obj != null && msg.obj instanceof Surface) {
						mEncSurface = new WindowSurface(mEglCore, (Surface)msg.obj, false);
					}
					break;
					
				case GL_RENDER_DRAW_FRAME:
					long ts0 = System.currentTimeMillis();
					glesDrawView();
					long ts1 = System.currentTimeMillis();
					frameCnt++;
					if (System.currentTimeMillis() >= prevTime + 1000) {
						Log.d(mTag, "size: " + mInWidth + "x" + mInHeight + " fps: " + frameCnt + " (" + (ts1 - ts0) + "ms)");
						prevTime += 1000;
						frameCnt = 0;
					}
					break;
					
				case GL_RENDER_QUIT:
					Looper.myLooper().quit();
					break;
				}
			}
		};

		Log.i(mTag, "initialize success!");
		synchronized (mLock) {
			mLock.notify();
		}

		Looper.loop();
		glesStop();
	}

	public void prepare() {
		start();
		synchronized (mLock) {
			try {
				mLock.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public SurfaceTexture getInputSurfaceTexture() {
		return mCameraInputFilter.getInputSurfaceTexture();
	}

	public void setMirror(boolean mirror) {
		mMirror = mirror;
	}
	
	public void setViewSurface (Surface surface) {
		mHandler.sendMessage(Message.obtain(mHandler, GL_RENDER_SET_VIEW_SURFACE, surface));
	}
	
	public void setEncSurface (Surface surface) {
		mHandler.sendMessage(Message.obtain(mHandler, GL_RENDER_SET_ENC_SURFACE, surface));
	}

	public void setFilterEnable(boolean en) {
		mFilterEn = en;
	}

	public void setBeautifyLevel(int level) {
		level = level < 0 ? 0 : (level > 4 ? 4 : level);
		mBeautifyFilter.setBeautifyLevel(level); // 0
	}
	
	//0~100
	public void setSaturation(int sat) {
		sat = sat < 0 ? 0 : (sat > 100 ? 100 : sat);
		mBaseFilter.setSaturation(((float)sat) / 50.0f);
	}

	public void setOsdBmp(Bitmap bmp) {
		mOsdBlendFilter.setBitmap(bmp);
	}

	public void drawFrame() {
		mHandler.sendMessage(Message.obtain(mHandler, GL_RENDER_DRAW_FRAME));
	}

	public void release() {
		mHandler.sendMessage(Message.obtain(mHandler, GL_RENDER_QUIT));
		try {
			this.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
