package com.example.newhelloworld.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.acrcloud.rec.ACRCloudClient;
import com.acrcloud.rec.ACRCloudConfig;
import com.acrcloud.rec.ACRCloudResult;
import com.acrcloud.rec.IACRCloudListener;
import com.acrcloud.rec.IACRCloudPartnerDeviceInfo;
import com.acrcloud.rec.IACRCloudRadioMetadataListener;
import com.acrcloud.rec.utils.ACRCloudLogger;
import com.example.newhelloworld.R;
import com.example.newhelloworld.manager.MyActivityManager;
import com.example.newhelloworld.views.RippleAnimationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class RecognizeActivity extends AppCompatActivity implements IACRCloudListener, IACRCloudRadioMetadataListener {
    private final static String TAG = "RecognizeActivity";

    private ImageView imageView;
    private ImageButton bck;
    private RippleAnimationView rippleAnimationView;
    private TextView songName;
    private TextView singer;
    /*识别*/
    private boolean mProcessing = false;
    private boolean initState = false;
    private boolean mAutoRecognizing = false;
    private MediaPlayer mediaPlayer = new MediaPlayer();
    private boolean isPlaying = false;

    private String path = "";

    private long startTime = 0;
    private long stopTime = 0;

    private final int PRINT_MSG = 1001;

    private ACRCloudConfig mConfig = null;
    private ACRCloudClient mClient = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recognize);

        MyActivityManager.getInstance().add(this);

        path = Environment.getExternalStorageDirectory().toString()
                + "/acrcloud";
        Log.e(TAG, path);
        File file = new File(path);
        if(!file.exists()){
            file.mkdirs();
        }
        imageView=(ImageView) findViewById(R.id.recognize);
        songName=(TextView)findViewById(R.id.songName);
        singer=(TextView)findViewById(R.id.singer);
        bck=(ImageButton) findViewById(R.id.back_recog);

        rippleAnimationView=(RippleAnimationView) findViewById(R.id.layout_RippleAnimation);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rippleAnimationView.isRippleRunning()){
                    rippleAnimationView.stopRippleAnimation();
                    cancel();
                    Log.d(TAG,"stop");
                }else{
                    rippleAnimationView.startRippleAnimation();
                    start();

                    Log.d(TAG,"start");
                }
            }
        });
        bck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        verifyPermissions();
        this.mConfig = new ACRCloudConfig();

        this.mConfig.acrcloudListener = this;
        this.mConfig.context = this;

        // Please create project in "http://console.acrcloud.cn/service/avr".
        this.mConfig.host =
                "identify-cn-north-1.acrcloud.cn";
        this.mConfig.accessKey = "220d5c5aafbd25d4ca3c86a2c75a8ae9";
        this.mConfig.accessSecret = "jkB43vihPX0R6MlJngFvLFBDlIiPEWs0PvzbNnq8";

        // auto recognize access key
        this.mConfig.hostAuto =
                "identify-cn-north-1.acrcloud.cn";
        this.mConfig.accessKeyAuto = "220d5c5aafbd25d4ca3c86a2c75a8ae9";
        this.mConfig.accessSecretAuto = "jkB43vihPX0R6MlJngFvLFBDlIiPEWs0PvzbNnq8";

        this.mConfig.recorderConfig.rate = 8000;
        this.mConfig.recorderConfig.channels = 1;

        this.mConfig.acrcloudPartnerDeviceInfo = new IACRCloudPartnerDeviceInfo() {
            @Override
            public String getGPS() {
                return null;
            }

            @Override
            public String getRadioFrequency() {
                return null;
            }

            @Override
            public String getDeviceId() {
                return "";
            }

            @Override
            public String getDeviceModel() {
                return null;
            }
        };

        // If you do not need volume callback, you set it false.
        this.mConfig.recorderConfig.isVolumeCallback = true;

        this.mClient = new ACRCloudClient();
        ACRCloudLogger.setLog(true);

        this.initState = this.mClient.initWithConfig(this.mConfig);
    }


    @Override
    public void onResult(ACRCloudResult results) {
        this.reset();
        String result = results.getResult();

        String songname="\n";
        String singerName="\n";

        try {
            JSONObject j = new JSONObject(result);
            JSONObject j1 = j.getJSONObject("status");
            int j2 = j1.getInt("code");
            Log.d(TAG, String.valueOf(j2));
            if(j2 == 0){
                JSONObject metadata = j.getJSONObject("metadata");
                //
                if (metadata.has("humming")) {
                    JSONArray musics = metadata.getJSONArray("humming");

                        JSONObject tt = (JSONObject) musics.get(0);
                        songname = "歌名:"+tt.getString("title");
                        JSONArray artistt = tt.getJSONArray("artists");
                        JSONObject art = (JSONObject) artistt.get(0);
                        singerName = "歌手:"+art.getString("name");

                }

            }else{
                songname=result;
                singerName="";
            }
        } catch (JSONException e) {
            songname=result;
            singerName="";
            e.printStackTrace();
        }

        songName.setText(songname);
        singer.setText(singerName);
        startTime = System.currentTimeMillis();

    }

    @Override
    public void onVolumeChanged(double v) {
/*        long time = (System.currentTimeMillis() - startTime) / 1000;
        mVolume.setText(getResources().getString(R.string.volume) + volume + "\n\nTime: " + time + " s");*/

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.e("MainActivity", "release");
        if (this.mClient != null) {
            this.mClient.release();
            this.initState = false;
            this.mClient = null;
        }
    }

    @Override
    public void onRadioMetadataResult(String s) {

    }

    public void start() {
        String str = this.getString(R.string.suss);
        if (!mAutoRecognizing) {
            mAutoRecognizing = true;
            if (this.mClient == null || !this.mClient.runAutoRecognize()) {
                mAutoRecognizing = true;
                str = this.getString(R.string.error);
            }
        }
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }
    public void cancel() {
        String str = this.getString(R.string.suss);
        if (mAutoRecognizing) {
            mAutoRecognizing = false;
            this.mClient.cancelAutoRecognize();
            str = this.getString(R.string.error);
        }
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }
    public void reset() {
        songName.setText("歌名：");
        singer.setText("歌手：");
        mProcessing = false;
    }
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS = {
            Manifest.permission.ACCESS_NETWORK_STATE,
            Manifest.permission.ACCESS_WIFI_STATE,
            Manifest.permission.INTERNET,
            Manifest.permission.RECORD_AUDIO
    };
    public void verifyPermissions() {
        for (int i=0; i<PERMISSIONS.length; i++) {
            int permission = ActivityCompat.checkSelfPermission(this, PERMISSIONS[i]);
            if (permission != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, PERMISSIONS,
                        REQUEST_EXTERNAL_STORAGE);
                break;
            }
        }
    }
    public void requestRadioMetadata() {
        String lat = "39.98";
        String lng = "116.29";
        List<String> freq = new ArrayList<>();
        freq.add("88.7");
        if (!this.mClient.requestRadioMetadataAsyn(lat, lng, freq,
                ACRCloudConfig.RadioType.FM, this)) {
            String str = this.getString(R.string.error);
            Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
        }
    }
    public static void startAction(Context context){
        Intent intent = new Intent(context, RecognizeActivity.class);
        context.startActivity(intent);
    }

}
