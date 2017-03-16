package com.hifunki.funki.net.http;

import android.os.Handler;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.ForwardingSink;
import okio.Okio;
import okio.Sink;

/**
 * Created by powyin on 2016/11/7.   代理加入上传进度监听
 */

public class ProgressDelegate extends RequestBody {

    private RequestBody mDelegate;
    private Callback<?> mListener;
    private Handler mHandler;

    public ProgressDelegate(Handler handler , RequestBody delegate, Callback<?> listener) {
        mHandler = handler;
        mDelegate = delegate;
        mListener = listener;
    }

    @Override
    public MediaType contentType() {
        return mDelegate.contentType();
    }

    @Override
    public long contentLength() {
        try {
            return mDelegate.contentLength();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public void writeTo(BufferedSink sink) throws IOException {
        CountingSink  mCountingSink = new CountingSink(sink);
        BufferedSink bufferedSink = Okio.buffer(mCountingSink);
        mDelegate.writeTo(bufferedSink);
        bufferedSink.flush();
    }

    private final class CountingSink extends ForwardingSink {
        private long bytesWritten = 0;
        private int currentProgress = 0;
        public CountingSink(Sink delegate) {
            super(delegate);
        }
        @Override
        public void write(Buffer source, long byteCount) throws IOException {
            long per = Math.max(1024,contentLength()/20);
            while (byteCount>0){
                long readCount = Math.min(per,byteCount);
                super.write(source, readCount);
                bytesWritten += readCount;
                int progress = (int) (100F * bytesWritten / contentLength());

                if(currentProgress!=progress){
                    currentProgress = progress;
                    mHandler.post(new Runnable() {
                        int local = currentProgress;
                        @Override
                        public void run() {
                            mListener.onProgress(local);
                        }
                    });
                }
                byteCount -= readCount;
            }
        }
    }
}



































