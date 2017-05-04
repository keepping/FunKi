package io.kickflip.sdk.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import io.kickflip.sdk.av.MediaControlVideo;

/**
 * Special GLSurfaceView for use with MediaControlVideo
 * The tight coupling here allows richer touch interaction
 */
public class GLCameraEncoderView extends GLCameraView {
    private static final String TAG = "GLCameraEncoderView";

    protected MediaControlVideo mMediaControlVideo;

    public GLCameraEncoderView(Context context) {
        super(context);
    }

    public GLCameraEncoderView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setCameraEncoder(MediaControlVideo encoder){
        mMediaControlVideo = encoder;
        setCamera(mMediaControlVideo.getCamera());
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if(mScaleGestureDetector != null){
            mScaleGestureDetector.onTouchEvent(ev);
        }
        if(mMediaControlVideo != null && ev.getPointerCount() == 1 && (ev.getAction() == MotionEvent.ACTION_MOVE)){
            mMediaControlVideo.handleCameraPreviewTouchEvent(ev);
        }else if(mMediaControlVideo != null && ev.getPointerCount() == 1 && (ev.getAction() == MotionEvent.ACTION_DOWN)){
            mMediaControlVideo.handleCameraPreviewTouchEvent(ev);
        }
        return true;
    }


}
