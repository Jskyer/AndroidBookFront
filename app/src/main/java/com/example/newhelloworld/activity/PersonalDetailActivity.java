package com.example.newhelloworld.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.bumptech.glide.Glide;
import com.example.newhelloworld.MyApplication;
import com.example.newhelloworld.R;
import com.example.newhelloworld.databinding.PersonalDetailLayoutBinding;
import com.example.newhelloworld.event.MsgChangeAvatar;
import com.example.newhelloworld.net.MyObserver;
import com.example.newhelloworld.net.MyRetrofitClient;
import com.example.newhelloworld.queryVO.Status;
import com.example.newhelloworld.queryVO.signIn.ResetPassResp;
import com.example.newhelloworld.queryVO.userInfo.IntegerResp;
import com.example.newhelloworld.util.DialogUtil;
import com.example.newhelloworld.util.PreferenceUtil;
import com.example.newhelloworld.util.ResourceUtil;
import com.google.android.material.button.MaterialButton;

import org.apache.commons.lang3.StringUtils;
import org.greenrobot.eventbus.EventBus;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class PersonalDetailActivity extends ViewBindingActivity<PersonalDetailLayoutBinding> {
    public static final String TAG = "PersonalDetailActivity";

    //与系统requestCode对应
    private final int CHOOSE_PHOTE = 2;

    private boolean canEdit = false;

    private MyRetrofitClient client;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        client = new MyRetrofitClient();
        bindPersonalInfo();
        setListeners();

    }

    //绑定个人信息
    public void bindPersonalInfo(){
        //订阅数
        requestSubscribe();

        //绑定头像
        String avatarPath = PreferenceUtil.getString(this, PreferenceUtil.KEY_USER_AVATAR, null);
        if (avatarPath != null){
            Glide.with(this)
                    .load(ResourceUtil.getUserAvatarPath(avatarPath))
                    .centerCrop()
                    .into(binding.avatar);
        }

        //绑定名字
        String name = PreferenceUtil.getString(this, PreferenceUtil.KEY_USER_NAME);
        if(name != null){
            binding.username.setText(name);
        }


        //简介
        String intro = PreferenceUtil.getString(this, PreferenceUtil.KEY_USER_INTRO);
        if (intro != null){
            binding.introduction.setText(intro);
        }

    }


    public void setListeners(){
        binding.btnAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View inflate = getLayoutInflater().inflate(R.layout.dialog_upload_img, null);

                MaterialButton btnUpload  = inflate.findViewById(R.id.uploadImg_person);
                btnUpload.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                            Log.d(TAG, "permit: "+ Environment.isExternalStorageManager());

                            if(Environment.isExternalStorageManager()){
                                openImgService();
                            }else{
                                Log.d(TAG, "need permission");

                                //开启dialog，由dialog跳转去申请权限
                                AlertDialog dialogPermit = DialogUtil.permissionDialog(PersonalDetailActivity.this);
                                dialogPermit.show();
                            }
                        }else{
                            Log.d(TAG, "sdk < 30");
                        }
                    }
                });

                AlertDialog dialog = new AlertDialog.Builder(PersonalDetailActivity.this)
                        .setView(inflate)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {//添加"Yes"按钮
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Log.d(TAG, "open");
                            }
                        }).create();

                dialog.show();
            }
        });

        binding.introductionEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!canEdit){
                    canEdit = true;
                    binding.introduction.setEnabled(true);
                    Toast.makeText(PersonalDetailActivity.this, "开启编辑,再点击以完成编辑", Toast.LENGTH_SHORT).show();
                }else{
                    canEdit = false;
                    binding.introduction.setEnabled(false);

                    String val = binding.introduction.getText().toString();
                    PreferenceUtil.putString(PersonalDetailActivity.this, PreferenceUtil.KEY_USER_INTRO, val);

                    Toast.makeText(PersonalDetailActivity.this, "完成编辑啦~", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void requestSubscribe(){
        client.getSubscribeNumPreviews(new MyObserver<IntegerResp>() {
            @Override
            public void onSuccss(IntegerResp integerResp) {
                Status status = integerResp.getStatus();
                Log.d(TAG, status.getMsg());

                if(status.getCode() == 200){
//                    binding.subscribeNum.setText(integerResp.getNum().toString());
                    binding.subscribeNum.setText(String.valueOf(integerResp.getNum()));
                }

            }
        });
    }



    /**
     *  打开相册选图片
     *
     */
    public void openImgService(){
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent, CHOOSE_PHOTE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d(TAG, "onActivityResult");

        if(requestCode == CHOOSE_PHOTE){
            if(resultCode == RESULT_OK){
                Uri uri = data.getData();
                String path = uri.getLastPathSegment();
                path = path.substring(path.indexOf(":")+1);

                Log.d(TAG, "path: "+path);

                String imagePath = "/storage/emulated/0/"+path;
                Log.d(TAG, "img path: "+imagePath);

                File file = new File(imagePath);
                if(file == null) Log.d("photo", "img error null ");
                Log.d(TAG, "exist: " + file.exists());
                Log.d(TAG, "exist: " + file.getName());

                if(file != null){
                    uploadRequest(file);
                    Log.d(TAG, "success");
                }
            }else{
                Log.d(TAG, "resultCode not ok");
            }
        }
    }

    public void uploadRequest(File file){
        RequestBody fileBody = RequestBody.Companion.create(file, MediaType.parse("image/*"));
        MultipartBody.Part multipartBody = MultipartBody.Part.createFormData("file", file.getName(), fileBody);

        client.uploadAvatar(multipartBody, new MyObserver<ResetPassResp>() {
            @Override
            public void onSuccss(ResetPassResp resp) {
                Status status = resp.getStatus();
                Log.d(TAG, status.getMsg());

                if(status.getCode() == 200){
                    Toast.makeText(PersonalDetailActivity.this, "上传成功", Toast.LENGTH_SHORT).show();

                    //保存到pref
                    String avatarPath = status.getMsg();
                    PreferenceUtil.putString(PersonalDetailActivity.this, PreferenceUtil.KEY_USER_AVATAR, avatarPath);

                    //显示到detail页
                    Glide.with(PersonalDetailActivity.this)
                            .load(ResourceUtil.getUserAvatarPath(avatarPath))
                            .centerCrop()
                            .into(binding.avatar);

                    EventBus.getDefault().postSticky(new MsgChangeAvatar(avatarPath));

                }else{
                    Toast.makeText(PersonalDetailActivity.this, "上传失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public static void startAction(Context context){
        Intent intent = new Intent(context, PersonalDetailActivity.class);
        context.startActivity(intent);
    }
}
