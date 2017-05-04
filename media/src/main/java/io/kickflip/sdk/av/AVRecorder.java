package io.kickflip.sdk.av;

import java.io.IOException;

import io.kickflip.sdk.view.GLCameraView;

/**
 * Records an Audio / Video stream to disk.
 *
 * Example usage:
 * <ul>
 *     <li>AVRecorder recorder = new AVRecorder(mSessionConfig);</li>
 *     <li>recorder.setPreviewDisplay(mPreviewDisplay);</li>
 *     <li>recorder.startRecording();</li>
 *     <li>recorder.stopRecording();</li>
 *     <li>(Optional) recorder.reset(mNewSessionConfig);</li>
 *     <li>(Optional) recorder.startRecording();</li>
 *     <li>(Optional) recorder.stopRecording();</li>
 *     <li>recorder.release();</li>
 * </ul>
 * @hide
 */
public class AVRecorder {

    protected MediaControlVideo mCamEncoder;
    protected MediaControlAudio mMicEncoder;
    private SessionConfig mConfig;
    private boolean mIsRecording;

    public AVRecorder(SessionConfig config) throws IOException {
        init(config);
    }

    private void init(SessionConfig config) throws IOException {
        mCamEncoder = new MediaControlVideo(config);
        mMicEncoder = new MediaControlAudio(config);
        mConfig = config;
        mIsRecording = false;
    }

    public void setPreviewDisplay(GLCameraView display){
        mCamEncoder.setPreviewDisplay(display);
    }

    public void applyFilter(int filter){
        mCamEncoder.applyFilter(filter);
    }

    public void requestOtherCamera(){
        mCamEncoder.requestOtherCamera();
    }

    public void requestCamera(int camera){
        mCamEncoder.requestCamera(camera);
    }

    public void toggleFlash(){
        mCamEncoder.toggleFlashMode();
    }

    public void adjustVideoBitrate(int targetBitRate){
        mCamEncoder.adjustBitrate(targetBitRate);
    }

    public void signalVerticalVideo(FullFrameRect.SCREEN_ROTATION orientation) {
        mCamEncoder.signalVerticalVideo(orientation);

    }

    public void startRecording(){
        mIsRecording = true;
        mMicEncoder.startRecording();
        mCamEncoder.startRecording();

        System.out.println("................::  start");

    }

    public boolean isRecording(){
        return mIsRecording;
    }

    public void stopRecording(){
        mIsRecording = false;
        mMicEncoder.stopRecording();
        mCamEncoder.stopRecording();

        System.out.println("................::  end");
    }

    /**
     * Prepare for a subsequent recording. Must be called after {@link #stopRecording()}
     * and before {@link #release()}
     * @param config
     */
    public void reset(SessionConfig config) throws IOException {
        mCamEncoder.reset(config);
        mMicEncoder.reset(config);
        mConfig = config;
        mIsRecording = false;
    }

    /**
     * Release resources. Must be called after {@link #stopRecording()} After this call
     * this instance may no longer be used.
     */
    public void release() {
        mCamEncoder.release();
        // MediaControlAudio releases all it's resources when stopRecording is called
        // because it doesn't have any meaningful state
        // between recordings. It might someday if we decide to present
        // persistent audio volume meters etc.
        // Until then, we don't need to write MediaControlAudio.release()
    }

    public void onHostActivityPaused(){
        mCamEncoder.onHostActivityPaused();
    }

    public void onHostActivityResumed(){
        mCamEncoder.onHostActivityResumed();
    }
}
