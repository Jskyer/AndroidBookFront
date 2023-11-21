package com.example.newhelloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.newhelloworld.net.MyObserver;
import com.example.newhelloworld.net.MyRetrofitClient;
import com.example.newhelloworld.queryVO.LoginResp;
import com.example.newhelloworld.util.PreferenceUtil;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.rxjava3.core.Observable;

public class LoginActivity extends AppCompatActivity {
    private EditText emailView;
    private EditText pwdView;
    private Button loginButton;
    private Button signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 关联activity.xml
        setContentView(R.layout.activity_login);

        emailView = this.findViewById(R.id.EmailEdit);
        pwdView = this.findViewById(R.id.PassWordEdit);
        loginButton = this.findViewById(R.id.LoginButton);
        signUpButton = this.findViewById(R.id.SignUpButton);

        // 登录按钮监听器
        loginButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String email = emailView.getText().toString();
                        String pwd = pwdView.getText().toString();

                        Map<String, String> map = new HashMap<>();
                        map.put("email", email);
                        map.put("password", pwd);

                        Log.d("rxjava", email);
                        Log.d("rxjava", pwd);

                        login(map);

//                        String strUserName = userName.getText().toString().trim();
//                        String strPassWord = passWord.getText().toString().trim();
//                        // 判断如果用户名为"123456"密码为"123456"则登录成功
//                        if (strUserName.equals("123456") && strPassWord.equals("123456")) {
//                            Toast.makeText(LoginActivity.this, "登录成功！", Toast.LENGTH_SHORT).show();
//                        } else {
//                            Toast.makeText(LoginActivity.this, "请输入正确的用户名或密码！", Toast.LENGTH_SHORT).show();
//                        }
                    }
                }
        );

        // 注册按钮监听器
        signUpButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // 跳转到注册界面
                        Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                        startActivity(intent);
                    }
                }
        );

    }

    public void login(Map<String, String> map){
        MyRetrofitClient client = new MyRetrofitClient();

        Observable<LoginResp> register = client.login(map, new MyObserver<LoginResp>() {
            @Override
            public void onSuccss(LoginResp res) {
                Context context = getApplicationContext();
                PreferenceUtil.putString(context, PreferenceUtil.KEY_USER_ID, res.getUser_id());

//                String token = PreferenceUtil.getString(context, "token", null);
                Log.d("rxjava", "success");

                //跳转到首页

            }
        });

        Log.d("rxjava", "ok 123");
    }


}