package com.hifunki.funki.util.gpuimage;

import android.graphics.SurfaceTexture;
import android.opengl.GLES11Ext;
import android.opengl.GLES20;

import jp.co.cyberagent.android.gpuimage.GPUImageFilter;
import jp.co.cyberagent.android.gpuimage.OpenGlUtils;

public class GPUImageCameraInputFilter extends GPUImageFilter {
	public static final String EXT_TEXTURE_INPUT_FRAGMENT_SHADER = ""
			+ "#extension GL_OES_EGL_image_external : require\n"
			+ "\n"
			+ "varying highp vec2 textureCoordinate;\n"
			+ " \n"
			+ "uniform samplerExternalOES inputImageTexture;\n"
			+ " \n"
			+ "void main()\n"
			+ "{\n"
			+ "     gl_FragColor = texture2D(inputImageTexture, textureCoordinate);\n"
			+ "}";

	private int mInputTextureId;
	private SurfaceTexture mInputSurfaceTexture;

	public GPUImageCameraInputFilter() {
		super(NO_FILTER_VERTEX_SHADER, EXT_TEXTURE_INPUT_FRAGMENT_SHADER);
		mInputTextureId = OpenGlUtils.NO_TEXTURE;
	}

	@Override
	public void onInit() {
		super.onInit();

		int[] textureid = new int[1];
		GLES20.glGenTextures(1, textureid, 0);
		mInputTextureId = textureid[0];

		GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
		GLES20.glBindTexture(GLES11Ext.GL_TEXTURE_EXTERNAL_OES, mInputTextureId);
		GLES20.glTexParameterf(GLES11Ext.GL_TEXTURE_EXTERNAL_OES,
				GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_NEAREST);
		GLES20.glTexParameterf(GLES11Ext.GL_TEXTURE_EXTERNAL_OES,
				GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR);
		GLES20.glTexParameteri(GLES11Ext.GL_TEXTURE_EXTERNAL_OES,
				GLES20.GL_TEXTURE_WRAP_S, GLES20.GL_CLAMP_TO_EDGE);
		GLES20.glTexParameteri(GLES11Ext.GL_TEXTURE_EXTERNAL_OES,
				GLES20.GL_TEXTURE_WRAP_T, GLES20.GL_CLAMP_TO_EDGE);
		GLES20.glBindTexture(GLES11Ext.GL_TEXTURE_EXTERNAL_OES, 0);
		
		mInputSurfaceTexture = new SurfaceTexture(mInputTextureId);
	}

	@Override
	protected void onDrawArraysPre() {
		super.onDrawArraysPre();

		GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
		GLES20.glBindTexture(GLES11Ext.GL_TEXTURE_EXTERNAL_OES, mInputTextureId);
		GLES20.glUniform1i(mGLUniformTexture, 0);
	}

	@Override
	public void onDestroy() {
		mInputSurfaceTexture.release();
		mInputSurfaceTexture = null;
		
		GLES20.glBindTexture(GLES11Ext.GL_TEXTURE_EXTERNAL_OES, 0);

		int[] textureid = new int[1];
		textureid[0] = mInputTextureId;
		GLES20.glDeleteTextures(1, textureid, 0);

		mInputTextureId = OpenGlUtils.NO_TEXTURE;
		super.onDestroy();
	}

	public SurfaceTexture getInputSurfaceTexture() {
		return mInputSurfaceTexture;
	}
}
