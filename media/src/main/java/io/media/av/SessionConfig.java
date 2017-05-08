package io.media.av;

import android.os.Environment;

import java.io.File;
import java.util.Map;
import java.util.UUID;




/**
 * Configuration information for a Broadcasting or Recording session.
 * Includes meta data, video + audio encoding
 * and muxing parameters
 */
public class SessionConfig {

    private final VideoEncoderConfig mVideoConfig;
    private final AudioEncoderConfig mAudioConfig;

    private Muxer mMuxer;

    private boolean mIsAdaptiveBitrate;

    private int mHlsSegmentDuration;


    private SessionConfig(VideoEncoderConfig videoConfig, AudioEncoderConfig audioConfig) {
        mVideoConfig = videoConfig;
        mAudioConfig = audioConfig;
    }

    public Muxer getMuxer() {
        return mMuxer;
    }

    private void setMuxer(Muxer mMuxer){
        this.mMuxer = mMuxer;
    }



    public String getOutputFile() {
        return mMuxer.getOutputPath();
    }

    public int getTotalBitrate() {
        return mVideoConfig.getBitRate() + mAudioConfig.getBitrate();
    }

    public int getVideoWidth() {
        return mVideoConfig.getWidth();
    }

    public int getVideoHeight() {
        return mVideoConfig.getHeight();
    }

    public int getVideoBitrate() {
        return mVideoConfig.getBitRate();
    }

    public int getNumAudioChannels() {
        return mAudioConfig.getNumChannels();
    }

    public int getAudioBitrate() {
        return mAudioConfig.getBitrate();
    }

    public int getAudioSamplerate() {
        return mAudioConfig.getSampleRate();
    }



    public boolean isAdaptiveBitrate() {
        return mIsAdaptiveBitrate;
    }


    public int getHlsSegmentDuration() {
        return mHlsSegmentDuration;
    }

    public void setUseAdaptiveBitrate(boolean useAdaptiveBit) {
        mIsAdaptiveBitrate = useAdaptiveBit;
    }




    public static class Builder {
        private int mWidth;
        private int mHeight;
        private int mVideoBitrate;

        private int mAudioSamplerate;
        private int mAudioBitrate;
        private int mNumAudioChannels;


        private String mOutPutFile;

        private boolean mPrivate;


        private Map mExtraInfo;

        private int mHlsSegmentDuration;

        /**
         * Configure a SessionConfig quickly with intelligent path interpretation.
         * Valid inputs are "/path/to/name.m3u8", "/path/to/name.mp4"
         * <p/>
         * For file-based outputs (.m3u8, .mp4) the file structure is managed
         * by a recording UUID.
         * <p/>
         * Given an absolute file-based outputLocation like:
         * <p/>
         * /sdcard/test.m3u8
         * <p/>
         * the output will be available in:
         * <p/>
         * /sdcard/<UUID>/test.m3u8
         * /sdcard/<UUID>/test0.ts
         * /sdcard/<UUID>/test1.ts
         * ...
         * <p/>
         * You can query the final outputLocation after building with
         * SessionConfig.getOutputPath()
         *
         */
        public Builder() {
            setAVDefaults();
            setMetaDefaults();
        }


        private void setAVDefaults() {
            mWidth = 1280;
            mHeight = 720;
            mVideoBitrate = 2 * 1000 * 1000;

            mAudioSamplerate = 44100;
            mAudioBitrate = 96 * 1000;
            mNumAudioChannels = 1;
        }

        private void setMetaDefaults() {
            mPrivate = false;
            mHlsSegmentDuration = 10;
        }



        public Builder withPrivateVisibility(boolean isPrivate) {
            mPrivate = isPrivate;
            return this;
        }


        public Builder withExtraInfo(Map extraInfo) {
            mExtraInfo = extraInfo;
            return this;
        }


        public Builder withVideoResolution(int width, int height) {
            mWidth = width;
            mHeight = height;
            return this;
        }

        public Builder withVideoBitrate(int bitrate) {
            mVideoBitrate = bitrate;
            return this;
        }

        public Builder withAudioSamplerate(int samplerate) {
            mAudioSamplerate = samplerate;
            return this;
        }

        public Builder withAudioBitrate(int bitrate) {
            mAudioBitrate = bitrate;
            return this;
        }

        public Builder withAudioChannels(int numChannels) {
         //   checkArgument(numChannels == 0 || numChannels == 1);
            mNumAudioChannels = numChannels;
            return this;
        }

        public Builder withOutFilePath(String recodeFile){
            mOutPutFile = recodeFile;
            return this;
        }


        public SessionConfig build() {
            SessionConfig config = new SessionConfig(
                    new VideoEncoderConfig(mWidth, mHeight, mVideoBitrate),
                    new AudioEncoderConfig(mNumAudioChannels, mAudioSamplerate, mAudioBitrate));

            config.setMuxer(new Muxer(mOutPutFile, Muxer.FORMAT.MPEG4));

            return config;
        }


    }
}
