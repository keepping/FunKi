package com.hifunki.funki.module.dynamic.post.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.SurfaceTexture;
import android.media.MediaCodec;
import android.media.MediaMuxer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Surface;
import android.view.View;

import com.hifunki.funki.R;
import com.hifunki.funki.util.FileUtils;

import java.util.ArrayList;
import java.util.List;

import io.media.RecodeUtil;
import io.media.av.AVRecorder;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/**
 * Created by pow on 2017/5/5.
 */

public class VideoEditActivity extends AppCompatActivity {

    private static String FILENAMES = "FILENAMES";

    public static void startActivity(Context context, ArrayList<String> fileNames) {
        Intent intent = new Intent(context, VideoEditActivity.class);
        intent.putStringArrayListExtra(FILENAMES, fileNames);
        context.startActivity(intent);
    }

    private List<String> fileLists;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_edit_video);

    }

    void getIntentData() {
        Intent intent = getIntent();
        if(!intent.hasExtra(FILENAMES)) return;

        fileLists = intent.getStringArrayListExtra(FILENAMES);

    }

    void onClick(View view){

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {


//                    MediaCodec codec = MediaCodec.createByCodecName("video/avc");
//
//                    Surface surface = codec.createInputSurface();
//
//                    codec.start();
//
//
//                    MediaMuxer mediaMuxer = new MediaMuxer(FileUtils.getRandomFilePath(VideoEditActivity.this,".mp4",false).getAbsolutePath() , MediaMuxer.OutputFormat.MUXER_OUTPUT_MPEG_4);
//
//
//
//
//                    mediaMuxer.addTrack()
//
//
//                    final IjkMediaPlayer ijkMediaPlayer = new IjkMediaPlayer();
//
//                    ijkMediaPlayer.setSurface(surface);
//
//
//                    ijkMediaPlayer.setOnPreparedListener(new IMediaPlayer.OnPreparedListener() {
//                        @Override
//                        public void onPrepared(IMediaPlayer iMediaPlayer) {
//                            ijkMediaPlayer.start();
//                        }
//                    });
//
//                    ijkMediaPlayer.setOnCompletionListener(new IMediaPlayer.OnCompletionListener() {
//                        @Override
//                        public void onCompletion(IMediaPlayer iMediaPlayer) {
//                            ijkMediaPlayer.reset();
//                            ijkMediaPlayer.setDataSource(getApplicationContext(), Uri.fromFile(null));
//                        }
//                    });
//
//                    ijkMediaPlayer.start();
//
//                }catch (Exception e){
//                    e.printStackTrace();
//                }



//
//
//            }
//        }).start();
//


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
