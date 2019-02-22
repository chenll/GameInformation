package com.game.mcw.gameinformation.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.game.mcw.gameinformation.R;

import org.yczbj.ycvideoplayerlib.OnVideoBackListener;
import org.yczbj.ycvideoplayerlib.VideoClarity;
import org.yczbj.ycvideoplayerlib.VideoPlayer;
import org.yczbj.ycvideoplayerlib.VideoPlayerController;

import java.util.ArrayList;
import java.util.List;


public class VideoPlayerTmView extends FrameLayout {
    private static String imageUri = "http://imgsrc.baidu.com/image/c0%3Dshijue%2C0%2C0%2C245%2C40/sign=304dee3ab299a9012f38537575fc600e/91529822720e0cf3f8b77cd50046f21fbe09aa5f.jpg";
    private static String videoUri = "http://play.g3proxy.lecloud.com/vod/v2/MjUxLzE2LzgvbGV0di11dHMvMTQvdmVyXzAwXzIyLTExMDc2NDEzODctYXZjLTE5OTgxOS1hYWMtNDgwMDAtNTI2MTEwLTE3MDg3NjEzLWY1OGY2YzM1NjkwZTA2ZGFmYjg2MTVlYzc5MjEyZjU4LTE0OTg1NTc2ODY4MjMubXA0?b=259&mmsid=65565355&tm=1499247143&key=f0eadb4f30c404d49ff8ebad673d3742&platid=3&splatid=345&playid=0&tss=no&vtype=21&cvid=2026135183914&payff=0&pip=08cc52f8b09acd3eff8bf31688ddeced&format=0&sign=mb&dname=mobile&expect=1&tag=mobile&xformat=super";

    private VideoPlayer videoPlayer;
    private OnCallBackListener onCallBackListener;

    public VideoPlayerTmView(@NonNull Context context) {
        super(context);
    }

    public VideoPlayerTmView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.layout_video_player_content_tm, this, true);
        videoPlayer = findViewById(R.id.video_player);
    }

    //    public void setVideoPlayerVm(ThoughtMoveStudyVm vm) {
//        videoPlayer.setPlayerType(VideoPlayer.TYPE_IJK); // IjkPlayer or MediaPlayer
//        VideoPlayerController controller = new VideoPlayerController(getContext());
//        if (vm != null && vm.model != null) {
//            controller.setTitle("思维挪移学习");
//            controller.setClarity(getClarites(verifyVideoUrl(vm.model.get().getVideo())), 0);
//            String imgUrl = getFirstFrame(vm.model.get().getVideo());
//            if (!TextUtils.isEmpty(imgUrl)) {
//                ImageViewBindingAdapter.loadRectImage(controller.imageView(), null, imgUrl);
//            }
//        }
//        controller.setOnVideoBackListener(new OnVideoBackListener() {
//            @Override
//            public void onBackClick() {
//                if (onCallBackListener != null) {
//                    onCallBackListener.onCallBack();
//                }
//            }
//        });
//        videoPlayer.setController(controller);
////        videoPlayer.enterFullScreen();//直接进入全屏模式
//        videoPlayer.start();//开始播放
//    }

    public void setVideoPlayerVmWithNoTitle() {
        videoPlayer.setPlayerType(VideoPlayer.TYPE_IJK); // IjkPlayer or MediaPlayer
        VideoPlayerController controller = new VideoPlayerController(getContext());
        controller.setTitle("1111");
        controller.setClarity(getClarites(verifyVideoUrl(videoUri)), 0);
        String imgUrl = getFirstFrame(videoUri);
        if (!TextUtils.isEmpty(imgUrl)) {
            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .placeholder(null);
            Glide.with(getContext()).load(imgUrl)
                    .apply(options)
                    .into(controller.imageView());
        }
        controller.setOnVideoBackListener(new OnVideoBackListener() {
            @Override
            public void onBackClick() {
                if (onCallBackListener != null) {
                    onCallBackListener.onCallBack();
                }
            }
        });
        videoPlayer.setController(controller);
//        videoPlayer.enterFullScreen();//直接进入全屏模式
        videoPlayer.start();//开始播放
    }

    public void setVideoPlayerVm() {
        videoPlayer.setPlayerType(VideoPlayer.TYPE_IJK); // IjkPlayer or MediaPlayer
        VideoPlayerController controller = new VideoPlayerController(getContext());
        controller.setTitle("1111");
        controller.setClarity(getClarites(verifyVideoUrl(videoUri)), 0);
        String imgUrl = getFirstFrame(videoUri);
        if (!TextUtils.isEmpty(imgUrl)) {
            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .placeholder(null);
            Glide.with(getContext()).load(imgUrl)
                    .apply(options)
                    .into(controller.imageView());
        }
        controller.setOnVideoBackListener(new OnVideoBackListener() {
            @Override
            public void onBackClick() {
                if (onCallBackListener != null) {
                    onCallBackListener.onCallBack();
                }
            }
        });
        videoPlayer.setController(controller);
//        videoPlayer.enterFullScreen();//直接进入全屏模式
        videoPlayer.start();//开始播放
    }

    public List<VideoClarity> getClarites(String video) {
        List<VideoClarity> clarities = new ArrayList<>();
        if (TextUtils.isEmpty(video)) {
            video = videoUri;
        }
        clarities.add(new VideoClarity("标清", "270P", video));
        clarities.add(new VideoClarity("高清", "480P", video));
        clarities.add(new VideoClarity("超清", "720P", video));
        clarities.add(new VideoClarity("蓝光", "1080P", video));
        return clarities;
    }

    public void onPause() {
        videoPlayer.pause();
    }

    public void onDestroy() {
        videoPlayer.release();
    }

    public interface OnCallBackListener {
        void onCallBack();
    }

    public void setOnCallBackListener(OnCallBackListener onCallBackListener) {
        this.onCallBackListener = onCallBackListener;
    }

    private String verifyVideoUrl(String video) {
        String url = null;
        if (!TextUtils.isEmpty(video)) {
            if (video.contains("https")) {
                url = video.replace("https", "http");
                return url;
            }
            return video;
        }
        return videoUri;
    }

    //七牛云截取第一针：https://developer.qiniu.com/dora/manual/1313/video-frame-thumbnails-vframe
    private String getFirstFrame(String videoUri) {//https://qnyevent.rockyenglish.com/T1DAY1.mp4?vframe/jpg/offset/0
        String firstFrame = "";
        if (!TextUtils.isEmpty(videoUri)) {
            firstFrame = videoUri + "?vframe/jpg/offset/0";
        }
        return firstFrame;
    }
}
