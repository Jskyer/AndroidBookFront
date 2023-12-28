package com.example.newhelloworld.manager;

import android.media.MediaPlayer;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SeekBar;

import com.example.newhelloworld.event.AudioCompleteListener;
import com.example.newhelloworld.model.Episode;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// 播放列表管理
public class AudioListManager {
    public static String TAG = "AudioListManager";
    public static int MODE_LIST_LOOP = 0; //列表循环
    public static int MODE_SINGLE = 1; //单曲循环
    public static int MODE_RANDOM = 2; //随机

    private static AudioListManager instance;

    private AudioListManager(){
        audioList = new ArrayList<>();
        audioPlayManager = AudioPlayManager.getInstance();
        curPosition = -1;
        mode = 0;
        random = new Random();
        setListeners();
    }

//    private static class AudioListManagerHolder{
//        private static AudioListManager instance = new AudioListManager();
//    }

    public static AudioListManager getInstance(){
        if(instance == null){
            instance = new AudioListManager();
        }
        return instance;
    }

    private AudioPlayManager audioPlayManager;
    //播放列表
    private List<Episode> audioList;

//    当前正处于播放或暂停的单集在播放列表中的位置
//    -1 没有处于该状态的，即不存在一个list中episode作为参数传给play调用
    private int curPosition;

    // 当前列表的播放mode
    private int mode;

    // 随机数生成
    private Random random;

    //用于单集播放结束，按模式切换时，设置MainActivity中的laybottom
    private AudioCompleteListener audioCompleteListener;

    //仅在一首结束，根据模式切换，刷新播放页ui时调用
    public Episode getCurEpisode(){
        if(curPosition == -1)return null;
        return audioList.get(curPosition);
    }

    public int getCurpos(){
        return curPosition;
    }

    public List<Episode> getList(){
        return audioList;
    }

    public int getCurPositionFromPlayer(){
        return audioPlayManager.getPlayer().getCurrentPosition();
    }

    public boolean isPlayManagerPlaying(){
        return audioPlayManager.getPlayer().isPlaying();
    }

    public boolean isCurInListPlaying(Episode episode){
        if (episode == null)throw new RuntimeException();

        Episode curEpisodeInPlay = audioPlayManager.getCurEpisodeInPlay();
        if(curEpisodeInPlay == null)return false;

        return episode.getId() == curEpisodeInPlay.getId();
    }

    public boolean judgeInList(Episode episode){
        return getPositionInList(episode) != -1;
    }

    public int getCurLastTime(){
        return audioList.get(curPosition).getLastTime();
    }

//    public AudioPlayManager getAudioPlayManager(){
//        return audioPlayManager;
//    }

    //开始拖动seekbar调用
    public void startTrackingTouch(Episode episode) {
        int pos = getPositionInList(episode);
        //未加入列表，直接加入音频源
        if (pos == -1){
            throw new RuntimeException();
//            audioPlayManager.setCurEpisode(episode);
//            return;
        }

        if(pos != curPosition){
            curPosition = pos;
            audioPlayManager.setCurEpisode(episode);
        }else {
            if(isPlayManagerPlaying())audioPlayManager.to_pause();
        }
    }

    //结束拖动seekbar调用
    public void stopTrackingTouch(int time){
        audioPlayManager.setLastTime(time);
        audioPlayManager.to_play();
    }

    //点击单集后，添加到listmanager
    public void addData(Episode episode){
        Log.d(TAG, "addData");
        if(episode == null){
            throw new RuntimeException();
        }

        int id = episode.getId();
        //检测是否重复插入
        boolean repeatFlag = false;
        for(int i = 0; i < audioList.size(); i++){
            if(audioList.get(i).getId() == id){
                if(curPosition != -1){
                    if(curPosition == i){
                        //当前播放的被重复插入，则把原来的移到头
                        Episode origin = audioList.remove(i);
                        audioList.add(0, origin);
                        curPosition = 0;

//                        audioPlayManager.to_reset();

                    }else if(curPosition > i){
                        audioList.remove(i);
                        audioList.add(0, episode);
//                        curPosition--;
                    }else{
                        audioList.remove(i);
                        audioList.add(0, episode);
                        curPosition++;
                    }

                }else{
                    audioList.remove(i);
                    audioList.add(0, episode);

                }

                repeatFlag = true;
                break;
            }
        }

        if(repeatFlag)return;

        audioList.add(0, episode);

//        if(curPosition != -1){
//            curPosition++;
//            if(curPosition >= 8){
//                // 列表超过8个，正在播放的在最后，删除正播放单集的前一个
//                audioList.remove(7);
//                curPosition = 7;
//
////                audioPlayManager.to_reset();
////                curPosition = -1;
//            }
//        }

        //维护list长度最大为8，防止ui显示错误
        int size = audioList.size();
        if(size > 8){
            if(curPosition == -1){
                for(int i = 8; i < size; i++){
                    audioList.remove(i);
                }

            }else {
                if(curPosition < 7){
                    audioList.remove(8);
                    curPosition++;
                }else{
                    if(isPlayManagerPlaying() && isCurInListPlaying(audioList.get(8))){
                        audioList.remove(7);
                    }else{
                        audioList.remove(8);

                        curPosition = -1;
                        audioPlayManager.to_reset();
                    }
                }

            }


//            for(int i = 8; i < size; i++){
//                audioList.remove(i);
//            }
        }else{
            if(curPosition != -1){
                curPosition++;
            }
        }

        Log.d(TAG, "addData: curPosition = "+curPosition);
//        curPosition = 0;
//        audioPlayManager.setCurEpisode(audioList.get(curPosition));
//        audioPlayManager.to_play();
    }

    // position为列表中的位置，不是数据库相关的id
    public void removeData(int position){
        Log.d(TAG, "removeData");

        int size = audioList.size();
        if(size == 0 || position >= size){
            Log.d(TAG, "removeData error");
            return;
        }

        audioList.remove(position);

        //删除的是当前播放单集
        if(curPosition == position){
            curPosition = -1;
            audioPlayManager.to_reset();
//            if(curPosition == size - 1){
//                curPosition--;
//            }
            //重新设置playmanager的播放源
//            audioPlayManager.setCurEpisode(audioList.get(curPosition));
        }else {
            if(position < curPosition){
                curPosition--;
            }
        }

    }

    private int getPositionInList(Episode episode){
        int ret = -1;
        for(int i = 0; i < audioList.size(); i++){
            if(audioList.get(i).getId() == episode.getId()){
                ret = i;
                break;
            }
        }
        return ret;
    }

    //监听播放列表RecyclerView的播放button
//    public class PlayItemOnCLickListener implements AdapterView.OnItemClickListener {
//
//        @Override
//        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//            Episode episode = audioList.get(curPosition);
//            episode.setLastTime(audioPlayManager.getLastTime());
//            //获取点击的列表的中音乐的位置，赋值给当前播放音乐
//            curPosition = position;
//
//            //令暂停的进度为0（即为从头播放）
//            audioPlayManager.setLastTime(0);
//            audioPlayManager.setCurEpisode(audioList.get(position));
//            //播放
//            audioPlayManager.to_play();
//        }
//    }

    // position为播放列表中的位置，不是数据库相关的id，也不是单集列表的位置
    //点击播放按钮
    public void play(Episode episode){
        if(episode == null){
            throw new RuntimeException();
        }

        int position = getPositionInList(episode);
        if (position == -1){
            Log.d(TAG, "play error: episode is not in list");
            return;
        }

        if(curPosition != position){
            curPosition = position;
            Log.d(TAG, "curPosition: " + curPosition);

            audioPlayManager.setCurEpisode(audioList.get(curPosition));
        }

        audioPlayManager.to_play();
    }

//    public void play_in_list(int position){
//        curPosition = position;
//        audioPlayManager.setCurEpisode(audioList.get(position));
//        audioPlayManager.to_play();
//    }

    //点击暂停按钮
    public void pause(){
        if(!audioPlayManager.getPlayer().isPlaying()){
            Log.d(TAG, "pause error");
            return;
        }

//        curPosition = -1;
        audioPlayManager.to_pause();
    }

    //快退10秒
    public void secondBack(Episode episode) {
        int pos = getPositionInList(episode);
        if(pos == -1){
            Log.d(TAG, "secondBack pos -1");
            throw new RuntimeException();
        }

        if(pos != curPosition){
            audioPlayManager.setCurEpisode(episode);
            curPosition = pos;
        }else {
            if(isPlayManagerPlaying())audioPlayManager.to_pause();
        }

        MediaPlayer player = audioPlayManager.getPlayer();
        if(player == null){
            Log.d(TAG, "secondBack player null");
            throw new RuntimeException();
        }

        int curPos = player.getCurrentPosition() - 10000;
        if(curPos <= 0)curPos = 0;

        player.seekTo(curPos);
        player.start();
        Log.d(TAG, "secondBack ok");
    }

    //快进10秒
    public void secondForward(Episode episode) {
        int pos = getPositionInList(episode);
        if(pos == -1){
            Log.d(TAG, "secondForward pos -1");
            throw new RuntimeException();
        }

        if(pos != curPosition){
            audioPlayManager.setCurEpisode(episode);
            curPosition = pos;
        }else {
            if(isPlayManagerPlaying())audioPlayManager.to_pause();
        }

        MediaPlayer player = audioPlayManager.getPlayer();
        if(player == null){
            Log.d(TAG, "secondForward player null");
            throw new RuntimeException();
        }

        int curPos = player.getCurrentPosition() + 10000;

        player.seekTo(curPos);
        player.start();
        Log.d(TAG, "secondForward ok");
    }

    public void release(){
        audioPlayManager.releasePlayer();
        instance = null;
    }

//    public void reset(){
//        audioPlayManager.to_reset();
//    }

    // 上下集是否到边界，返回上下集的episode对象
    // 点击上一首并播放
    public Episode click_pre(Episode episode){
        if(episode == null){
            throw new RuntimeException();
        }

        int position = getPositionInList(episode);
        if(position - 1 < 0){
            Log.d(TAG, "the first song");
            return null;
        }

//        audioPlayManager.to_reset();
        curPosition = position - 1;
        Episode ans = audioList.get(curPosition);
        audioPlayManager.setCurEpisode(ans);
        ans.setLastTime(0);
        audioPlayManager.to_play();
        return ans;
    }

    // 点击下一首并播放
    public Episode click_next(Episode episode){
        if(episode == null){
            throw new RuntimeException();
        }

        int position = getPositionInList(episode);
        int size = audioList.size();
        if(position + 1 >= size){
            Log.d(TAG, "the last song");
            return null;
        }

//        audioPlayManager.to_reset();
        curPosition = position + 1;
        Episode ans = audioList.get(curPosition);
        audioPlayManager.setCurEpisode(ans);
        ans.setLastTime(0);
        audioPlayManager.to_play();
        return ans;
    }

    public void setListeners(){
        MediaPlayer player = audioPlayManager.getPlayer();
        if(player == null)Log.d(TAG, "setListeners error");
        player.setOnCompletionListener(new EpisodeCompletionListener());
    }

    private final class EpisodeCompletionListener implements MediaPlayer.OnCompletionListener{

        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            mode_next_song();
        }
    }

//    public final class SeekBarMoveListener implements SeekBar.OnSeekBarChangeListener{
//
//        @Override
//        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
//            Log.d("audio", "seekBar onProgressChanged");
//        }
//
//        @Override
//        public void onStartTrackingTouch(SeekBar seekBar) {
//            Log.d("audio", "seekBar onStartTrackingTouch");
//            if(curPosition != -1){
//                audioPlayManager.to_pause();
//            }
//        }
//
//        @Override
//        public void onStopTrackingTouch(SeekBar seekBar) {
//            Log.d("audio", "seekBar onStopTrackingTouch");
//            audioPlayManager.setLastTime(seekBar.getProgress() * 1000);
//            audioPlayManager.to_play();
////            new AudioActivity.MyThread().start();
//        }
//    }

    public void setCompleteNext(AudioCompleteListener listener){
        audioCompleteListener = listener;
    }


    // 当前播放的单集播放结束，按照mode播放
    public void mode_next_song(){
        if(mode == AudioListManager.MODE_LIST_LOOP){
            Log.d(TAG, "MODE_LIST_LOOP");
            curPosition = (curPosition + 1) % audioList.size();

        }else if(mode == AudioListManager.MODE_SINGLE){
            Log.d(TAG, "MODE_SINGLE");

        }else if(mode == AudioListManager.MODE_RANDOM){
            Log.d(TAG, "MODE_RANDOM");

            if (audioList.size() != 1){
                int newPos = random.nextInt(audioList.size());
                while(newPos == curPosition){
                    newPos = random.nextInt(audioList.size());
                }
                curPosition = newPos;
            }

        }

        Episode next = audioList.get(curPosition);
        audioPlayManager.setLastTime(0);
        audioPlayManager.setModeNext(next);
        audioPlayManager.setLastTime(0);
        audioPlayManager.to_play();

        audioCompleteListener.onCompleteForNext(next);
    }

    public int getMode(){
        return mode;
    }

    //点击模式切换按钮
    public void changeMode(){
        mode = (mode + 1) % 3;
    }


}
