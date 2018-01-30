package com.bulesky.vlcdemo;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import org.videolan.libvlc.IVLCVout;
import org.videolan.libvlc.LibVLC;
import org.videolan.libvlc.Media;
import org.videolan.libvlc.MediaPlayer;

import java.util.ArrayList;

public class MainActivity extends Activity {
    private SurfaceView surfaceView;
    private SurfaceHolder surfaceHolder;
    private LibVLC libVLC;
    private MediaPlayer mediaPlayer;
    private Media media;
    private IVLCVout ivlcVout;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        surfaceView = findViewById(R.id.surfaceView);
        initPlayer();
    }

    private void initPlayer() {
        ArrayList<String> options = new ArrayList<>();
        options.add("--aout=opensles");
        options.add("--audio-time-stretch");
        options.add("-vvv");
        libVLC = new LibVLC(MainActivity.this, options);
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.setKeepScreenOn(true);
        surfaceHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {

            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {

            }
        });

        mediaPlayer = new MediaPlayer(libVLC);

        //media = new Media(libvlc, Uri.parse("http://live.hkstv.hk.lxdns.com/live/hks/playlist.m3u8"));

        // take live555 as RTSP server
//        http://1.82.215.22/sportsts.tc.qq.com/AfuAm_g3KiKHSj48wOj45hHpixBmlzcvmLIUYT35yc8k/AVb0caw7p2UuHSIUNDr5reaI7eehTbqB8cnxU_bs8ppA7KwACu5ogkB5fRKTje45aYTkhZY8XBwkAhDIqxWivkFmRTQmHZd_upKjWKkvGWHx7_fNXGU7yg/00_o0025oha9qn.321002.1.ts?index=0&start=0&end=8320&brs=0&bre=977035&ver=4
//        http://1.82.215.22/sportsts.tc.qq.com/AfuAm_g3KiKHSj48wOj45hHpixBmlzcvmLIUYT35yc8k/AVb0caw7p2UuHSIUNDr5reaI7eehTbqB8cnxU_bs8ppA7KwACu5ogkB5fRKTje45aYTkhZY8XBwkAhDIqxWivkFmRTQmHZd_upKjWKkvGWHx7_fNXGU7yg/01_o0025oha9qn.321002.1.ts?index=1&start=8320&end=20240&brs=977036&bre=2526719&ver=4
//        http://117.34.59.13/sportsts.tc.qq.com/A9FC4J3caGY6YPZYSqceRbEYi_tUSYEGNgnXM8bszEJw/vKbXO00e_ncFHBRsBYvs1aVBohr1UuOh9FQ6OvmYWzgUUZhQ6S42NExzJ0oDAAUCcOpvOP70lgSmHNqWV9oa7KUw1XBvWi6XKzV4fi748ZeV8Je2ZZ-8kQ/03_s0025ly7sbs.321002.1.ts?index=3&start=32240&end=44200&brs=4235828&bre=5902823&ver=4
//        http://117.34.59.13/sportsts.tc.qq.com/A9FC4J3caGY6YPZYSqceRbEYi_tUSYEGNgnXM8bszEJw/vKbXO00e_ncFHBRsBYvs1aVBohr1UuOh9FQ6OvmYWzgUUZhQ6S42NExzJ0oDAAUCcOpvOP70lgSmHNqWV9oa7KUw1XBvWi6XKzV4fi748ZeV8Je2ZZ-8kQ/04_s0025ly7sbs.321002.1.ts?index=4&start=44200&end=56200&brs=5902824&bre=7407387&ver=4
//        http://117.34.59.13/sportsts.tc.qq.com/A9FC4J3caGY6YPZYSqceRbEYi_tUSYEGNgnXM8bszEJw/vKbXO00e_ncFHBRsBYvs1aVBohr1UuOh9FQ6OvmYWzgUUZhQ6S42NExzJ0oDAAUCcOpvOP70lgSmHNqWV9oa7KUw1XBvWi6XKzV4fi748ZeV8Je2ZZ-8kQ/012_s0025ly7sbs.321002.1.ts?index=12&start=140200&end=152200&brs=18334888&bre=19798091&ver=4

        media = new Media(libVLC, Uri.parse("http://1.82.215.22/sportsts.tc.qq.com/AfuAm_g3KiKHSj48wOj45hHpixBmlzcvmLIUYT35yc8k/AVb0caw7p2UuHSIUNDr5reaI7eehTbqB8cnxU_bs8ppA7KwACu5ogkB5fRKTje45aYTkhZY8XBwkAhDIqxWivkFmRTQmHZd_upKjWKkvGWHx7_fNXGU7yg/02_o0025oha9qn.321002.1.ts?index=2&start=20240&end=32240&brs=2526720&bre=4238647&ver=4"));
        mediaPlayer.setMedia(media);

        ivlcVout = mediaPlayer.getVLCVout();
        ivlcVout.setVideoView(surfaceView);
        ivlcVout.attachViews();
        ivlcVout.addCallback(new IVLCVout.Callback() {
            @Override
            public void onSurfacesCreated(IVLCVout vlcVout) {
                int sw = getWindow().getDecorView().getWidth();
                int sh = getWindow().getDecorView().getHeight();

                if (sw * sh == 0) {
                    Log.e(TAG, "Invalid surface size");
                    return;
                }

                mediaPlayer.getVLCVout().setWindowSize(sw, sh);
                mediaPlayer.setAspectRatio("16:9");
                mediaPlayer.setScale(0);
            }

            @Override
            public void onSurfacesDestroyed(IVLCVout vlcVout) {

            }
        });

        mediaPlayer.play();
    }
}
