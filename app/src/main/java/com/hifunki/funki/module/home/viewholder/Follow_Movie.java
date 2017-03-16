package com.hifunki.funki.module.home.viewholder;

import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

import com.hifunki.funki.R;
import com.hifunki.funki.net.back.Post;
import com.powyin.scroll.adapter.AdapterDelegate;
import com.powyin.scroll.adapter.PowViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 在此写用途
 *
 * @author yinhaoxiang
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.home.viewholder.Follow_Movie.java
 * @link
 * @since 2017-03-16 10:04:04
 */
public class Follow_Movie extends PowViewHolder<Post> {
    public Follow_Movie(Activity activity, ViewGroup viewGroup) {
        super(activity, viewGroup);
        ButterKnife.bind(this,mItemView);
    }



    @BindView(R.id.media_actions)
    MediaController mediaController;
    @BindView(R.id.video_view)
    VideoView videoView;



    @OnClick({
            R.id.play
    })
    void onClick(View view){
        switch (view.getId()){
            case R.id.play:


                videoView.setVideoURI(Uri.parse("http://203.205.148.145/vcloud.tc.qq.com/o0013etzwjw.m1301.mp4?sha=40247A10CC638A8F4AA87F1EE8A36E8EF9F0C1AB&vkey=3BC01E406F6EB67D358C6A52A6E3C92975E520EA46FEF1EC56DDD4F07070B46FFD465876C45DB164155FE81D2A9F7901565F96C3C655A2DB8189BB99D1CD8FD5F23E9361EB7EF49BD1F7A8BC9006F4756A6E40E0513D7021D2EA3E4778032E555FB1255523B8AE3EFB1D8D4AB818C712&ocid=521322250"));

                videoView.requestFocus();
                videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        videoView.start();

                    }
                });
                videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                    @Override
                    public boolean onError(MediaPlayer mp, int what, int extra) {
                        return false;
                    }
                });




                mediaController.setAnchorView(videoView);
            //    mediaController.setKeepScreenOn(true);
                mediaController.setMediaPlayer(new MediaController.MediaPlayerControl() {
                    @Override
                    public void start() {

                    }

                    @Override
                    public void pause() {

                    }

                    @Override
                    public int getDuration() {
                        return 0;
                    }

                    @Override
                    public int getCurrentPosition() {
                        return 0;
                    }

                    @Override
                    public void seekTo(int pos) {

                    }

                    @Override
                    public boolean isPlaying() {
                        return false;
                    }

                    @Override
                    public int getBufferPercentage() {
                        return 0;
                    }

                    @Override
                    public boolean canPause() {
                        return false;
                    }

                    @Override
                    public boolean canSeekBackward() {
                        return false;
                    }

                    @Override
                    public boolean canSeekForward() {
                        return false;
                    }

                    @Override
                    public int getAudioSessionId() {
                        return 0;
                    }
                });






                break;
        }
    }

    @Override
    protected int getItemViewRes() {
        return R.layout.viewholder_post_movie;
    }

    @Override
    protected boolean acceptData(Post data) {
        return data!=null && data.type==3;
    }

    @Override
    public void loadData(AdapterDelegate<? super Post> multipleAdapter, Post data, int postion) {



    }
}
