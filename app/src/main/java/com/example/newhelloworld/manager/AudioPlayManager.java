package com.example.newhelloworld.manager;

import android.media.MediaPlayer;
import android.util.Log;

import com.example.newhelloworld.model.Episode;
import com.example.newhelloworld.util.ResourceUtil;

import java.io.IOException;
import java.util.Locale;

// 单集播放页管理
public class AudioPlayManager {
    public static String TAG = "AudioPlayManager";

    private static AudioPlayManager instance;

    private AudioPlayManager(){
        player = new MediaPlayer();
//        lastTime = 0;
    }

//    private static class AudioPlayManagerHolder{
//        private static AudioPlayManager instance = new AudioPlayManager();
//    }

    public static AudioPlayManager getInstance(){
        if(instance == null){
            instance = new AudioPlayManager();
        }
        return instance;
    }
    // 单曲的播放状态
//    private int status;

    private MediaPlayer player;
    //    当前播放的单集
    private Episode curEpisode;
//    该单集对应的资源
//    private String uri;

    // 当前播放的单集  上次播放暂停、结束的位置
//    private int lastTime;
    // player 未调用setDataSource获得音频源
    private boolean isRelease = true;
    // curEpisode 是否正在播放
//    private boolean isPlaying = false;

    public void setCurEpisode(Episode episode){
//        if (episode == curEpisode){
//            Log.d(TAG, "setCurEpisode error");
//            return;
//        }
        //        有单曲是播放源，则reset
        if (curEpisode != null){
            int msec = player.getCurrentPosition();
            Log.d(TAG, curEpisode.getTitle() +" ,播放到位置：" + msec);
            curEpisode.setLastTime(msec);
            player.reset();
        }

        curEpisode = episode;
        try {
            player.setDataSource(ResourceUtil.getPodcastPath(curEpisode.getPodcast_path()));
            player.prepare();

//            player.setDataSource("/raw/wengu001.mp3");
//            player.prepareAsync();

            Log.d(TAG, "prepare ok");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        isRelease = false;
    }

//    public int getLastTime(){
//        return lastTime;
//    }

    public MediaPlayer getPlayer(){
        return player;
    }

    public Episode getCurEpisodeInPlay(){
        return curEpisode;
    }

    public void to_reset(){
        player.reset();
        curEpisode = null;
        isRelease = true;
    }


    // 释放player，在退出登录时
    public void releasePlayer(){
        if (player == null)return;

        player.stop();
        player.release();
        isRelease = true;

        curEpisode = null;

        instance = null;

        Log.d(TAG, "releasePlayer complete");
    }

//    播放按钮
    public void to_play(){
        if(isRelease || player.isPlaying() || curEpisode == null){
            Log.d(TAG, "to_play error");
            return;
        }

        //当前单集记录了上次播放到的位置
        if(curEpisode.getLastTime() != 0){
            player.seekTo(curEpisode.getLastTime());
        }

        player.start();
        Log.d(TAG, "to play");
//        isPlaying = true;

        // 设置seekbar
//        int seconds = player.getCurrentPosition() / 1000;
//        String cur = String.format(Locale.getDefault(), "%02d:%02d", seconds / 60, seconds % 60);
    }

    //重新设置curEpisode的lastTime
    public void setLastTime(int time){
        if(curEpisode == null){
            Log.d(TAG, "setLastTime error");
            return;
        }

        curEpisode.setLastTime(time);
    }

    //仅用于自动按模式播放下一首
    public void setModeNext(Episode next){
        player.reset();
        curEpisode = next;
        try {
            player.setDataSource(ResourceUtil.getPodcastPath(curEpisode.getPodcast_path()));
            player.prepare();

            Log.d(TAG, "prepare ok");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        isRelease = false;
    }

    // 暂停按钮
    public void to_pause(){
        if(isRelease || !player.isPlaying() || curEpisode == null){
            Log.d(TAG, "to_pause error");
            return;
        }

        player.pause();
        int msec = player.getCurrentPosition();
        curEpisode.setLastTime(msec);

        Log.d(TAG, "to pause");
        Log.d(TAG, curEpisode.getTitle() + " pause at " + msec);
//        isPlaying = false;
    }


//    单集播放结束
//    public void to_reset(){
//        try {
//            player.reset();
//            isPlaying = false;
//            isRelease = true;
////            player.setDataSource(getResources().openRawResourceFd(R.raw.song1));
////            player.prepare();
//
//        }catch (Exception e){
//            Log.d(TAG, "reset error: "+e.getMessage());
//        }
//
//        // 设置seekbar
//    }

//    根据mode重置episode
//    public void to_reset(Episode episode){
//        try {
//            isPlaying = false;
//            player.reset();
//            player.setDataSource(getResources().openRawResourceFd(R.raw.song1));
//            player.prepare();
//
//        }catch (Exception e){
//            e.printStackTrace();
//            Log.d("audio", "reset error: "+e.getMessage());
//        }
//
//    }



}
