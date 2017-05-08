package io.media.av;

import android.media.MediaCodec;
import android.media.MediaFormat;
import android.media.MediaMuxer;
import android.os.Build;
import android.util.Log;


import java.io.IOException;
import java.nio.ByteBuffer;


/**
 * Base Muxer class for interaction with MediaCodec based
 * encoders
 *
 * @hide
 */
public class Muxer {
    public  enum FORMAT {MPEG4, HLS}
    private final int mExpectedNumTracks = 2;           // TODO: Make this configurable?

    protected FORMAT mFormat;
    protected String mOutputPath;
    protected int mNumTracks;
    protected int mNumTracksFinished;
    protected long mFirstPts;
    protected long mLastPts[];


    private static final String TAG = "AndroidMuxer";
    private static final boolean VERBOSE = false;

    private MediaMuxer mMuxer;
    private boolean mStarted;


    protected Muxer(String outputPath, FORMAT format) {
        Log.i(TAG, "Created muxer for output: " + outputPath);
        mOutputPath = outputPath;
        mFormat = format;
        mNumTracks = 0;
        mNumTracksFinished = 0;
        mFirstPts = 0;
        mLastPts = new long[mExpectedNumTracks];
        for (int i = 0; i < mLastPts.length; i++) {
            mLastPts[i] = 0;
        }

        try {
            switch (format) {
                case MPEG4:
                    mMuxer = new MediaMuxer(outputPath, MediaMuxer.OutputFormat.MUXER_OUTPUT_MPEG_4);
                    break;
                default:
                    throw new IllegalArgumentException("Unrecognized format!");
            }
        } catch (IOException e) {
            throw new RuntimeException("MediaMuxer creation failed", e);
        }
        mStarted = false;


    }


    public String getOutputPath() {
        return mOutputPath;
    }


    public int addTrack(MediaFormat trackFormat) {
        mNumTracks++;

        if (mStarted)
            throw new RuntimeException("format changed twice");
        int track = mMuxer.addTrack(trackFormat);

        if (allTracksAdded()) {
            start();
        }
        return track;
    }


    public void onEncoderReleased(int trackIndex) {
    }

    public void release() {
        mMuxer.release();
    }

    public boolean isStarted() {
        return mStarted;
    }


    /**
     * Write the MediaCodec output buffer. This method <b>must</b>
     * be overridden by subclasses to release encodedData, transferring
     * ownership back to encoder, by calling encoder.releaseOutputBuffer(bufferIndex, false);
     *
     * @param trackIndex
     * @param encodedData
     * @param bufferInfo
     */
    public void writeSampleData(MediaCodec encoder, int trackIndex, int bufferIndex, ByteBuffer encodedData, MediaCodec.BufferInfo bufferInfo) {
        if ((bufferInfo.flags & MediaCodec.BUFFER_FLAG_END_OF_STREAM) != 0) {
            signalEndOfTrack();
        }

        if ((bufferInfo.flags & MediaCodec.BUFFER_FLAG_CODEC_CONFIG) != 0) {
            // MediaMuxer gets the codec config info via the addTrack command
            if (VERBOSE) Log.d(TAG, "ignoring BUFFER_FLAG_CODEC_CONFIG");
            encoder.releaseOutputBuffer(bufferIndex, false);
            return;
        }

        if (bufferInfo.size == 0) {
            if (VERBOSE) Log.d(TAG, "ignoring zero size buffer");
            encoder.releaseOutputBuffer(bufferIndex, false);
            return;
        }

        if (!mStarted) {
            Log.e(TAG, "writeSampleData called before muxer started. Ignoring packet. Track index: " + trackIndex + " tracks added: " + mNumTracks);
            encoder.releaseOutputBuffer(bufferIndex, false);
            return;
        }

        bufferInfo.presentationTimeUs = getNextRelativePts(bufferInfo.presentationTimeUs, trackIndex);

        mMuxer.writeSampleData(trackIndex, encodedData, bufferInfo);

        encoder.releaseOutputBuffer(bufferIndex, false);

        if (allTracksFinished()) {
            stop();
        }
    }


    protected void start() {
        mMuxer.start();
        mStarted = true;
    }

    protected void stop() {
        mMuxer.stop();
        mStarted = false;
    }


    public void forceStop() {
        stop();
    }

    protected boolean allTracksFinished() {
        return (mNumTracks == mNumTracksFinished);
    }

    protected boolean allTracksAdded() {
        return (mNumTracks == mExpectedNumTracks);
    }

    /**
     * Muxer will call this itself if it detects BUFFER_FLAG_END_OF_STREAM
     * in writeSampleData.
     */
    protected void signalEndOfTrack() {
        mNumTracksFinished++;
    }

    /**
     * Does this Muxer's format require
     * copying and buffering encoder output buffers.
     * Generally speaking, is the output a Socket or File?
     *
     * @return
     */
    protected boolean formatRequiresBuffering() {
        if (Build.VERSION.SDK_INT >= 21) return true;

        switch (mFormat) {
            case HLS:
                return false;
            default:
                return false;
        }
    }

    /**
     * Return a relative pts given an absolute pts and trackIndex.
     * <p>
     * This method advances the state of the Muxer, and must only
     * be called once per call to {@link #writeSampleData(android.media.MediaCodec, int, int, java.nio.ByteBuffer, android.media.MediaCodec.BufferInfo)}.
     */
    protected long getNextRelativePts(long absPts, int trackIndex) {
        if (mFirstPts == 0) {
            mFirstPts = absPts;
            return 0;
        }
        return getSafePts(absPts - mFirstPts, trackIndex);
    }

    /**
     * Sometimes packets with non-increasing pts are dequeued from the MediaCodec output buffer.
     * This method ensures that a crash won't occur due to non monotonically increasing packet timestamp.
     */
    private long getSafePts(long pts, int trackIndex) {
        if (mLastPts[trackIndex] >= pts) {
            // Enforce a non-zero minimum spacing
            // between pts
            mLastPts[trackIndex] += 9643;
            return mLastPts[trackIndex];
        }
        mLastPts[trackIndex] = pts;
        return pts;
    }





}
