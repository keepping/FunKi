package com.hifunki.funki.util.gpuimage;

import android.annotation.SuppressLint;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import jp.co.cyberagent.android.gpuimage.GPUImageFilter;

/*
 * a filter for main texture's subarea
 * */
public class GPUImageSubareaFilter extends GPUImageFilter {
	private float[] mRectArea;	//{x,y,w,h}
	private FloatBuffer mAreaCubeBuffer;
	private FloatBuffer mAreaTextureBuffer;
	private GPUImageFilter mAreaFilter;
	
	public GPUImageSubareaFilter () {
		this(new float[]{0.0f, 0.0f, 1.0f, 1.0f});
	}
	
	/**
	 *@param rectArea[]{x,y,w,h} 0.0f~1.0f and x+w<=1.0f y+h<=1.0f
	 */
	public GPUImageSubareaFilter (float []rectArea) {
		super();
		setRectArea(rectArea);
	}
	
	private void setRectAreaCoordBuffer (final FloatBuffer srcCubeBuffer, FloatBuffer dstCubeBuffer, final float[] rectArea) {
		float sx, sy, sw, sh;
		float dx, dy, dw, dh;
		
		sx = srcCubeBuffer.get(0);
		sy = srcCubeBuffer.get(1);
		sw = srcCubeBuffer.get(2) - srcCubeBuffer.get(0);
		sh = srcCubeBuffer.get(5) - srcCubeBuffer.get(1);
		
		dx = rectArea[0] * sw + sx;
		dy = rectArea[1] * sh + sy;
		dw = rectArea[2] * sw;
		dh = rectArea[3] * sh;
		
		dstCubeBuffer.put(0, dx);
		dstCubeBuffer.put(1, dy);
		dstCubeBuffer.put(2, dx+dw);
		dstCubeBuffer.put(3, dy);
		dstCubeBuffer.put(4, dx);
		dstCubeBuffer.put(5, dy+dh);
		dstCubeBuffer.put(6, dx+dw);
		dstCubeBuffer.put(7, dy+dh);
	}
	
	@Override
    public void onOutputSizeChanged(final int width, final int height) {
        super.onOutputSizeChanged(width, height);
        if (mAreaFilter != null) {
        	mAreaFilter.onOutputSizeChanged(width, height);
        }
    }

    @SuppressLint("WrongCall")
	@Override
    public void onDraw (final int textureId, final FloatBuffer cubeBuffer,
    		final FloatBuffer textureBuffer)
    {
    	if (!isInitialized()) {
    		return;
    	}
    	
    	super.onDraw(textureId, cubeBuffer, textureBuffer);

    	if (mRectArea != null) {
    		setRectAreaCoordBuffer(cubeBuffer, mAreaCubeBuffer, mRectArea);
    		setRectAreaCoordBuffer(textureBuffer, mAreaTextureBuffer, mRectArea);
    	}
    	
        mAreaCubeBuffer.position(0);
        mAreaTextureBuffer.position(0);
        
        if (mAreaFilter != null) {
        	mAreaFilter.onDraw(textureId, mAreaCubeBuffer, mAreaTextureBuffer);
        } else {
        	super.onDraw(textureId, mAreaCubeBuffer, mAreaTextureBuffer);
        }
	}

	/**
	 *@param rectArea[]{x,y,w,h} 0.0f~1.0f and x+w<=1.0f y+h<=1.0f
	 *@note onDraw's cubeBuffer and textureBuffer must be rectangle
	 */
	public void setRectArea (float []rectArea) {
		if (rectArea != null) {
			mRectArea = rectArea;
			mAreaCubeBuffer = ByteBuffer.allocateDirect(4 * 2 * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
			mAreaTextureBuffer = ByteBuffer.allocateDirect(4 * 2 * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
		}
	}
	
	/**
	 *@param areaCubeBuffer draw destination Vertex coord
	 *@param areaTextureBuffer capture source Texture coord
	 */
	public void setAreaCoordBuffer (FloatBuffer areaCubeBuffer, FloatBuffer areaTextureBuffer) {
		if (areaCubeBuffer != null && areaTextureBuffer != null) {
			mAreaCubeBuffer = areaCubeBuffer;
			mAreaTextureBuffer = areaTextureBuffer;
			mRectArea = null;
		}
	}
	
	/**
	 *@param a filter of processing area texture
	 */
	public void setAreaFilter (GPUImageFilter areaFilter) {
		mAreaFilter = areaFilter;
	}
}
