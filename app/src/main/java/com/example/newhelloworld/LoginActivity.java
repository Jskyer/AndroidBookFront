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

import com.example.newhelloworld.manager.MyActivityManager;
import com.example.newhelloworld.net.MyObserver;
import com.example.newhelloworld.net.MyRetrofitClient;
import com.example.newhelloworld.queryVO.LoginResp;
import com.example.newhelloworld.util.PreferenceUtil;

import org.apache.commons.lang3.StringUtils;

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
        setContentView(R.layout.activity_login);

        //加入管理器，方便登录后销毁
        MyActivityManager.getInstance().add(this);

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

                        if(StringUtils.isBlank(email) || StringUtils.isEmpty(pwd)){
                            Toast.makeText(LoginActivity.this, "邮箱或密码为空", Toast.LENGTH_SHORT).show();
                            return;
                        }


                        Log.d("rxjava", email);
                        Log.d("rxjava", pwd);

                        Map<String, String> map = new HashMap<>();
                        map.put("email", email);
                        map.put("password", pwd);
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
            //登录成功
            @Override
            public void onSuccss(LoginResp res) {
                Context context = getApplicationContext();
                PreferenceUtil.putString(context, PreferenceUtil.KEY_USER_ID, res.getUser_id());
//                PreferenceUtil.putString(context, PreferenceUtil.KEY_USER_NAME, res.getUser_name());
//                PreferenceUtil.putString(context, PreferenceUtil.KEY_USER_TOKEN, res.getAccess_token());

                Log.d("rxjava", "success");

                //跳转到首页,销毁自己
                MyActivityManager.getInstance().finishAll();
                startActivity(new Intent(LoginActivity.this, MainActivity.class));

            }
            //登录失败
            @Override
            public void onError(Throwable e) {
                super.onError(e);
                Toast.makeText(LoginActivity.this, "邮箱或密码错误", Toast.LENGTH_SHORT).show();
            }
        });

        Log.d("rxjava", "ok 123");
    }


}