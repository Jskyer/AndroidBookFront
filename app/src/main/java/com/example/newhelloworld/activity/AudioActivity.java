package com.example.newhelloworld.activity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.newhelloworld.R;

import java.util.Locale;

public class AudioActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView btn_play;
    private ImageView btn_pre;
    private ImageView btn_next;
    private MediaPlayer player;
    private SeekBar seekBar;
    private TextView textCurTime;
    private TextView textEndTime;

    private boolean isRelease = true;

    private boolean isPlaying = false;

    private final int MSG_MEDIA_STOP = 0;
    private final int MSG_MEDIA_PLAY = 1;

    private Handler mhandler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            if(msg.what == MSG_MEDIA_PLAY){
                int seconds = player.getCurrentPosition() / 1000;
                seekBar.setProgress(seconds);
                Log.d("audio", "cur second: "+seconds);

                String cur = String.format(Locale.getDefault(), "%02d:%02d", seconds / 60, seconds % 60);
                textCurTime.setText(cur);
                Log.d("audio", "handler msg play, text modify");
            }else if(msg.what == MSG_MEDIA_STOP){
                Log.d("audio", "handler msg stop");
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.playbar);
        setContentView(R.layout.activity_playing);
        bindView();
        initPlayer();
        initListeners();
    }

    public void initPlayer(){
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
        if(isRelease){
            player = MediaPlayer.create(this, R.raw.song1);
            isRelease = false;

            int seconds = player.getDuration() / 1000;
            seekBar.setMax(seconds);

            String end = String.format(Locale.getDefault(), "%02d:%02d", seconds / 60, seconds % 60);
            textEndTime.setText(end);

            Log.d("audio", "duration " + seconds);
        }
    }


    private void bindView() {
        btn_play = findViewById(R.id.btn_play);
        btn_play.setOnClickListener(this);
        btn_pre = findViewById(R.id.btn_pre);
        btn_pre.setOnClickListener(this);
        btn_next = findViewById(R.id.btn_next);
        btn_next.setOnClickListener(this);

        textCurTime = findViewById(R.id.textCurTime);
        textEndTime = findViewById(R.id.textEndTime);

        seekBar = findViewById(R.id.seekbar);
    }

    public void initListeners() {
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.d("audio", "seekBar onProgressChanged");
//                seekBar.setProgress(progress);
//                Log.d("audio", "fromUser "+fromUser);
//                Log.d("audio", "progress "+progress);
//
//                if(fromUser){
//                    if(!isRelease){
//                        player.seekTo(progress * 1000);
//                    }
//                }
//
//                String cur = String.format(Locale.getDefault(), "%02d:%02d", progress / 60, progress % 60);
//                textCurTime.setText(cur);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Log.d("audio", "seekBar onStartTrackingTouch");
                if(isPlaying && player.isPlaying()){
                    to_pause();
                }
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.d("audio", "seekBar onStopTrackingTouch");
                player.seekTo(seekBar.getProgress() * 1000);
                to_play();
                new MyThread().start();
            }
        });

        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                Log.d("audio", "player onCompletion");
                to_reset();
            }
        });
    }

    public void to_play(){
        player.start();
        Log.d("audio", "to play");
        btn_play.setImageResource(R.drawable.btn_pause);
        isPlaying = true;

        int seconds = player.getCurrentPosition() / 1000;
        seekBar.setProgress(seconds);
        String cur = String.format(Locale.getDefault(), "%02d:%02d", seconds / 60, seconds % 60);
        textCurTime.setText(cur);
    }

    public void to_pause(){
        player.pause();
        Log.d("audio", "to pause");
        btn_play.setImageResource(R.drawable.btn_play);
        isPlaying = false;
    }

    public void to_reset(){
        try {
            seekBar.setProgress(0);
            isPlaying = false;
            btn_play.setImageResource(R.drawable.btn_play);
            player.reset();
            player.setDataSource(getResources().openRawResourceFd(R.raw.song1));
            player.prepare();

        }catch (Exception e){
            e.printStackTrace();
            Log.d("audio", "reset error: "+e.getMessage());
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
//        Log.d("click id:", id+"");

        if(id == R.id.btn_play){

            if(!isPlaying){
                to_play();
                new MyThread().start();
            }else{
                to_pause();
            }

        }else if(id == R.id.btn_pre){
            player.reset();

        }else if(id == R.id.btn_next){
            player.reset();
//            player.release();
//            isRelease = true;

//            btn_play.setEnabled(true);
//            btn_pre.setEnabled(false);
//            btn_next.setEnabled(false);
        }

    }

    class MyThread extends Thread{
        @Override
        public void run() {
            Log.d("audio", "thread start");

            if(!isPlaying || !player.isPlaying()){
                mhandler.sendEmptyMessage(MSG_MEDIA_STOP);
                Log.d("audio", "thread send empty");
                return;
            }

            while(isPlaying && player.isPlaying()){
                try {
                    Thread.sleep(1000);
                    Message message = new Message();
                    message.what = MSG_MEDIA_PLAY;
//                    message.arg1 = seekBar.getProgress();
                    mhandler.sendMessage(message);
                    Log.d("audio", "thread send msg success");
                }catch (Exception e){
                    Log.d("audio", "error: "+ e.getMessage());
                }
            }

        }
    }






}
