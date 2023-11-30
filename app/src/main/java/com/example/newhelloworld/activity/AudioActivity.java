package com.example.newhelloworld.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.newhelloworld.R;
import com.example.newhelloworld.event.MsgAddToAudioList;
import com.example.newhelloworld.manager.AudioListManager;
import com.example.newhelloworld.model.Episode;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class AudioActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView btn_play;
    private ImageView btn_pre;
    private ImageView btn_next;
    private ImageView btn_second_back;
    private ImageView btn_second_forward;
    private ImageView btn_mode;
    private Button btn_comment;
    private ImageView btn_back;

    private ImageView posterView;

    //测试用
//    private MediaPlayer player;
    private AudioListManager audioListManager;
    private SeekBar seekBar;
    private TextView textCurTime;
    private TextView textEndTime;

    private TextView textTitleView;
    private TextView textUserNameView;

//    private boolean isRelease = true;

//    private boolean isPlaying = false;

    private final int MSG_MEDIA_STOP = 0;
    private final int MSG_MEDIA_PLAY = 1;

    private Timer timer;
    private TimerTask timerTask;

    //当前页展示的episode，不一定在播放
    private Episode curEpisode;

    //处理单集数据在播放页中的绑定
    private Handler mhandler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            if(msg.what == MSG_MEDIA_PLAY){

                //仅在一首结束，根据模式切换时执行，刷新播放页ui
                if(curEpisode != null && audioListManager.getCurEpisode() != null){
                    Episode newEpi = audioListManager.getCurEpisode();
                    if(curEpisode.getId() != newEpi.getId()){
                        textEndTime.setText(formatTimeToView(newEpi.getDuration()));
                        seekBar.setMax(newEpi.getDuration());

                        textTitleView.setText(newEpi.getTitle());
                        textUserNameView.setText(newEpi.getUploader_name());
//                    TODO
//                    posterView

                        curEpisode = newEpi;
                    }
                }

//                int seconds = player.getCurrentPosition() / 1000;
                int seconds = audioListManager.getCurPositionFromPlayer() / 1000;
                seekBar.setProgress(seconds);
                Log.d("audio", "cur second: "+seconds);

                String cur = String.format(Locale.getDefault(), "%02d:%02d", seconds / 60, seconds % 60);
                textCurTime.setText(cur);

                Log.d("audio", "handler msg play");
            }else if(msg.what == MSG_MEDIA_STOP){
                Log.d("audio", "handler msg stop");
            }
        }
    };


    //单集点击事件跳转到播放页：携带的数据
    //只有在主线程模式，才能更新ui组件
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onStickyEventReceived(MsgAddToAudioList msg){
        Episode episode = msg.getEpisode();
        if(episode == null)throw new RuntimeException();

        Log.d("audio", "onStickyEventReceived");

        textTitleView.setText(episode.getTitle());
        textUserNameView.setText(episode.getUploader_name());
        setModeView();
//        TODO
//        posterView.setImageResource();

        int seconds = episode.getDuration();
        seekBar.setMax(seconds);

        String end = String.format(Locale.getDefault(), "%02d:%02d", seconds / 60, seconds % 60);
        textEndTime.setText(end);

        //播放页点击过播放或暂停，退出播放页后重新打开，episode是listmanager中处于播放的，则恢复view的状态
        if(audioListManager.judgeIsCurInList(episode)){
            int curTime = 0;
            Log.d("audio", "judgeIsCurInList true");

            //音频仍在播放
            if(audioListManager.isPlayManagerPlaying()){
                Log.d("audio", "isPlayManagerPlaying true");

                btn_play.setImageResource(R.drawable.btn_pause);
                btn_play.setTag("btn_pause");
                curTime = audioListManager.getCurPositionFromPlayer() / 1000;

                publishProgress();
            }else{
                Log.d("audio", "isPlayManagerPlaying false");

                curTime = audioListManager.getCurLastTime() / 1000;
                String curFormat = String.format(Locale.getDefault(), "%02d:%02d", curTime / 60, curTime % 60);
                textCurTime.setText(curFormat);

                seekBar.setProgress(curTime);
            }

        }


        curEpisode = episode;
        Log.d("audio", "duration: " + seconds);

        EventBus.getDefault().removeStickyEvent(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing);

        bindView();
        initPlayer();
        initListeners();
        //设置进入、退出动画
        overridePendingTransition(R.anim.dialog_enter, R.anim.dialog_leave);
        //注册eventbus
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消注册
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void finish() {
        super.finish();
        stopPublishProgress();
        //退出动画
        overridePendingTransition(R.anim.dialog_enter, R.anim.dialog_leave);
    }

    public static void startAction(Context context){
        Intent intent = new Intent(context, AudioActivity.class);
        context.startActivity(intent);
    }


    public void initPlayer(){
        audioListManager = AudioListManager.getInstance();
//        player = new MediaPlayer();
//        try {
////            player.reset();
//            player.setDataSource("http://10.0.2.2:7070/song/song1.mp3");
//            player.prepareAsync();
////            player.prepare();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        //网络uri得到的音频，无法获取时长
//        if(isRelease){
//            isRelease = false;
//
//            Log.d("audio", ""+player.getDuration());
//            int seconds = player.getDuration() / 1000;
//            seekBar.setMax(seconds);
//
//            String end = String.format(Locale.getDefault(), "%02d:%02d", seconds / 60, seconds % 60);
//            textEndTime.setText(end);
//
//            Log.d("audio", "duration " + seconds);
//        }

//        try {
//            player = new MediaPlayer();
//            String mediaPath = "/raw/wengu001.mp3";
//            player.setDataSource(mediaPath);
//            player.prepare();
//            Log.d("audio", mediaPath);
//
//        }catch (Exception e){
//            Log.d("audio ex", e.getMessage());
//        }
//        if(isRelease){
//            player = MediaPlayer.create(this, R.raw.song1);
//            isRelease = false;
//
//            int seconds = player.getDuration() / 1000;
//            seekBar.setMax(seconds);
//
//            String end = String.format(Locale.getDefault(), "%02d:%02d", seconds / 60, seconds % 60);
//            textEndTime.setText(end);
//
//            Log.d("audio", "duration " + seconds);
//        }
    }


    private void bindView() {
        btn_play = findViewById(R.id.btn_playing);
        btn_play.setOnClickListener(this);

        btn_pre = findViewById(R.id.btn_pre);
        btn_pre.setOnClickListener(this);

        btn_next = findViewById(R.id.btn_next);
        btn_next.setOnClickListener(this);

        btn_second_back = findViewById(R.id.btn_second_back);
        btn_second_back.setOnClickListener(this);

        btn_second_forward = findViewById(R.id.btn_second_forward);
        btn_second_forward.setOnClickListener(this);

        btn_mode = findViewById(R.id.play_mode);
        btn_mode.setOnClickListener(this);

        btn_comment = findViewById(R.id.comment);
        btn_comment.setOnClickListener(this);

        btn_back = findViewById(R.id.go_down);
        btn_back.setOnClickListener(this);

        posterView = findViewById(R.id.img_url);
        textTitleView = findViewById(R.id.play_title);
        textUserNameView = findViewById(R.id.play_username);

//        if (textTitleView == null) Log.d("eventbus", "null");
//        Log.d("eventbus", textTitleView.getText().toString());

        textCurTime = findViewById(R.id.textCurTime);
        textEndTime = findViewById(R.id.textEndTime);

        seekBar = findViewById(R.id.seekbar);
    }

    public void initListeners() {
        seekBar.setOnSeekBarChangeListener(new SeekBarMoveListener(audioListManager));

//        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                Log.d("audio", "seekBar onProgressChanged");
////                seekBar.setProgress(progress);
////                Log.d("audio", "fromUser "+fromUser);
////                Log.d("audio", "progress "+progress);
////
////                if(fromUser){
////                    if(!isRelease){
////                        player.seekTo(progress * 1000);
////                    }
////                }
////
////                String cur = String.format(Locale.getDefault(), "%02d:%02d", progress / 60, progress % 60);
////                textCurTime.setText(cur);
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//                Log.d("audio", "seekBar onStartTrackingTouch");
//                if(isPlaying && player.isPlaying()){
//                    to_pause();
//                }
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//                Log.d("audio", "seekBar onStopTrackingTouch");
//                player.seekTo(seekBar.getProgress() * 1000);
//                to_play();
//                new MyThread().start();
//            }
//        });

//        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//            @Override
//            public void onCompletion(MediaPlayer mediaPlayer) {
//                Log.d("audio", "player onCompletion");
//                to_reset();
//            }
//        });
    }

//    public void to_play(){
//        player.start();
//        Log.d("audio", "to play");
//        btn_play.setImageResource(R.drawable.btn_pause);
//        isPlaying = true;
//
//        int seconds = player.getCurrentPosition() / 1000;
//        seekBar.setProgress(seconds);
//        String cur = String.format(Locale.getDefault(), "%02d:%02d", seconds / 60, seconds % 60);
//        textCurTime.setText(cur);
//    }
//
//    public void to_pause(){
//        player.pause();
//        Log.d("audio", "to pause");
//        btn_play.setImageResource(R.drawable.btn_play);
//        isPlaying = false;
//    }

//    public void to_reset(){
//        try {
//            seekBar.setProgress(0);
//            isPlaying = false;
//            btn_play.setImageResource(R.drawable.btn_play);
//            player.reset();
//            player.setDataSource(getResources().openRawResourceFd(R.raw.song1));
//            player.prepare();
//
//        }catch (Exception e){
//            e.printStackTrace();
//            Log.d("audio", "reset error: "+e.getMessage());
//        }
//    }

    public void publishProgress(){
        timerTask = new TimerTask() {
            @Override
            public void run() {
                if(!audioListManager.isPlayManagerPlaying()){
                    mhandler.sendEmptyMessage(MSG_MEDIA_STOP);
                    Log.d("audio", "timer error: empty");
                    return;
                }

                Message message = new Message();
                message.what = MSG_MEDIA_PLAY;
                mhandler.sendMessage(message);
                Log.d("audio", "timer send msg success");
            }
        };
        timer = new Timer();
        timer.schedule(timerTask, 0, 1000);
    }

    public void stopPublishProgress(){
        if(timerTask != null){
            timerTask.cancel();
            timerTask = null;
        }

        if(timer != null){
            timer.cancel();
            timer = null;
        }
    }

    public void stopAndPublish(){
        stopPublishProgress();
        publishProgress();
    }

    // second 单位 s
    public String formatTimeToView(int second){
        return String.format(Locale.getDefault(), "%02d:%02d", second / 60, second % 60);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if(id == R.id.btn_playing){
            Log.d("audio", "btn_playing");

            if(btn_play.getTag().equals("btn_play")){
                Log.d("audio", "to play");

                btn_play.setImageResource(R.drawable.btn_pause);
                audioListManager.play(curEpisode);
                publishProgress();

                btn_play.setTag("btn_pause");

            }else{
                Log.d("audio", "to pause");

                btn_play.setImageResource(R.drawable.btn_play);
                audioListManager.pause();
                stopPublishProgress();

                btn_play.setTag("btn_play");
            }


//            if(!audioListManager.isPlayManagerPlaying()){
//                Log.d("audio", "to play");
//
//                btn_play.setImageResource(R.drawable.btn_pause);
//                //TODO 测试用
////                player.start();
//                audioListManager.play(curEpisode);
//                publishProgress();
//
//
//
////                new MyThread().start();
//
////                int seconds = player.getCurrentPosition() / 1000;
////                seekBar.setProgress(seconds);
////                String cur = String.format(Locale.getDefault(), "%02d:%02d", seconds / 60, seconds % 60);
////                textCurTime.setText(cur);
//
////                to_play();
//            }else{
//                Log.d("audio", "to pause");
//
//                btn_play.setImageResource(R.drawable.btn_play);
//                audioListManager.pause();
////                player.pause();
//                stopPublishProgress();
//
//            }

        }else if(id == R.id.btn_pre){
            Log.d("audio", "btn_pre");

            Episode res = audioListManager.click_pre(curEpisode);
            if (res == null) Toast.makeText(this, "到头啦~", Toast.LENGTH_SHORT).show();
            else{
                if(timer != null || timerTask != null)stopPublishProgress();

                btn_play.setImageResource(R.drawable.btn_pause);
                btn_play.setTag("btn_pause");

                //TODO 改变当前显示的单集ui
//                posterView
                textTitleView.setText(res.getTitle());
                textUserNameView.setText(res.getUploader_name());

                textEndTime.setText(formatTimeToView(res.getDuration()));
                seekBar.setMax(res.getDuration());

                curEpisode = res;

                publishProgress();
            }

        }else if(id == R.id.btn_next){
            Log.d("audio", "btn_next");

            Episode res = audioListManager.click_next(curEpisode);
            if (res == null) Toast.makeText(this, "到底啦~", Toast.LENGTH_SHORT).show();
            else{
                if(timer != null || timerTask != null)stopPublishProgress();

                btn_play.setImageResource(R.drawable.btn_pause);
                btn_play.setTag("btn_pause");
                //TODO 改变当前显示的单集ui
//                posterView
                textTitleView.setText(res.getTitle());
                textUserNameView.setText(res.getUploader_name());

                textEndTime.setText(formatTimeToView(res.getDuration()));
                seekBar.setMax(res.getDuration());

                curEpisode = res;


                publishProgress();
            }

        }else if(id == R.id.go_down){
            finish();
            Log.d("audio", "finish activity");
        }else if (id == R.id.btn_second_back){
            Log.d("audio", "btn_second_back");
            audioListManager.secondBack(curEpisode);

            btn_play.setImageResource(R.drawable.btn_pause);
            btn_play.setTag("btn_pause");

            stopAndPublish();
            
        } else if (id == R.id.btn_second_forward) {
            Log.d("audio", "btn_second_forward");
            audioListManager.secondForward(curEpisode);

            btn_play.setImageResource(R.drawable.btn_pause);
            btn_play.setTag("btn_pause");

            stopAndPublish();
            
        } else if (id == R.id.play_mode) {
            Log.d("audio", "play_mode");
            audioListManager.changeMode();
            setModeView();

        } else if (id == R.id.comment) {
            Log.d("audio", "comment");

        }

    }

    public void setModeView(){
        if(audioListManager.getMode() == AudioListManager.MODE_LIST_LOOP){
            btn_mode.setImageResource(R.drawable.playmode_listloop);
        }else if (audioListManager.getMode() == AudioListManager.MODE_SINGLE){
            btn_mode.setImageResource(R.drawable.playmode_single);
        }else if (audioListManager.getMode() == AudioListManager.MODE_RANDOM){
            btn_mode.setImageResource(R.drawable.playmode_random);
        }
    }

    //region 内部类
    public final class SeekBarMoveListener implements SeekBar.OnSeekBarChangeListener{
        private AudioListManager audioListManager;

        public SeekBarMoveListener(AudioListManager audioListManager) {
            this.audioListManager = audioListManager;
        }

        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            Log.d("audio", "seekBar onProgressChanged");
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            Log.d("audio", "seekBar onStartTrackingTouch");
            audioListManager.startTrackingTouch(curEpisode);

            stopPublishProgress();
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            Log.d("audio", "seekBar onStopTrackingTouch");

            btn_play.setImageResource(R.drawable.btn_pause);
            btn_play.setTag("btn_pause");
            audioListManager.stopTrackingTouch(seekBar.getProgress() * 1000);
            publishProgress();
//            new MyThread().start();
        }
    }



//    class MyThread extends Thread{
//        @Override
//        public void run() {
//            Log.d("audio", "thread start");
//
//            if(!audioListManager.existPlayingItem()){
//                mhandler.sendEmptyMessage(MSG_MEDIA_STOP);
//                Log.d("audio", "thread send empty");
//                return;
//            }
//
//            while(audioListManager.existPlayingItem()){
//                try {
//                    Thread.sleep(1000);
//                    Message message = new Message();
//                    message.what = MSG_MEDIA_PLAY;
////                    message.arg1 = seekBar.getProgress();
//                    mhandler.sendMessage(message);
//                    Log.d("audio", "thread send msg success");
//                }catch (Exception e){
//                    Log.d("audio", "thread error: "+ e.getMessage());
//                }
//            }
//
//        }
//    }






}
