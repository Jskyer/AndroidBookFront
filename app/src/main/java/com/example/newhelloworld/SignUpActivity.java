package com.example.newhelloworld;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.newhelloworld.net.MyObserver;
import com.example.newhelloworld.net.MyRetrofitClient;
import com.example.newhelloworld.queryVO.SendVerificationResp;
import com.example.newhelloworld.queryVO.Status;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.rxjava3.core.Observable;

public class SignUpActivity extends AppCompatActivity {
    private EditText userNameView;
    private EditText emailView;
    private EditText pwdView;
    private EditText codeView;

    private Button verifyButton;
    private Button signUpButton;

    private ImageButton loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        userNameView = this.findViewById(R.id.UserNameEdit);
        emailView = this.findViewById(R.id.EmailEdit);
        pwdView = this.findViewById(R.id.PassWordAgainEdit);
        codeView = this.findViewById(R.id.VerifyEdit);

        verifyButton = this.findViewById(R.id.VerifyButton);
        signUpButton = this.findViewById(R.id.SignUpButton);
        loginButton = this.findViewById(R.id.ReturnLogin);


        verifyButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String email = emailView.getText().toString();
                        if(StringUtils.isBlank(email)){
                            Toast.makeText(SignUpActivity.this, "邮箱为空", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        Log.d("rxjava", email);
                        getVerification(email);

//                        String strUserName = userName.getText().toString().trim();
//                        String strPassWord = passWord.getText().toString().trim();
//                        String strPassWordAgain = passWordAgain.getText().toString().trim();
//                        String strPhoneNumber = email.getText().toString().trim();
//                        //注册格式粗检
//                        if (strUserName.length() > 10) {
//                            Toast.makeText(SignUpActivity.this, "用户名长度必须小于10！", Toast.LENGTH_SHORT).show();
//                        } else if (strUserName.length() < 4) {
//                            Toast.makeText(SignUpActivity.this, "用户名长度必须大于4！", Toast.LENGTH_SHORT).show();
//                        } else if (strPassWord.length() > 16) {
//                            Toast.makeText(SignUpActivity.this, "密码长度必须小于16！", Toast.LENGTH_SHORT).show();
//                        } else if (strPassWord.length() < 6) {
//                            Toast.makeText(SignUpActivity.this, "密码长度必须大于6！", Toast.LENGTH_SHORT).show();
//                        } else if (!strPassWord.equals(strPassWordAgain)) {
//                            Toast.makeText(SignUpActivity.this, "两次密码输入不一致！", Toast.LENGTH_SHORT).show();
//                        } else if (!strPhoneNumber.contains("@")) {
//                            Toast.makeText(SignUpActivity.this, "邮箱格式不正确！", Toast.LENGTH_SHORT).show();
//                        } else {
//                            Toast.makeText(SignUpActivity.this, "注册成功！", Toast.LENGTH_SHORT).show();
//                            // 跳转到登录界面
//                            Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
//                            startActivity(intent);
//                        }
                    }
                }
        );


        signUpButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String username = userNameView.getText().toString();
                        String email = emailView.getText().toString();
                        String code = codeView.getText().toString();
                        String pwd = pwdView.getText().toString();

                        if(StringUtils.isBlank(username) || StringUtils.isBlank(email) ||
                                StringUtils.isEmpty(pwd) || StringUtils.isEmpty(code)){
                            Toast.makeText(SignUpActivity.this, "用户名、邮箱、验证码、密码不能为空", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        Map<String, String> map = new HashMap<>();
                        map.put("username", username);
                        map.put("email", email);
                        map.put("verification", code);
                        map.put("password", pwd);


                        Log.d("rxjava", username);
                        Log.d("rxjava", email);
                        Log.d("rxjava", code);
                        Log.d("rxjava", pwd);

                        register(map);
                    }
                }
        );

        loginButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // 跳转到注册界面
                        Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                }
        );

    }

    public void getVerification(String email){
        MyRetrofitClient client = new MyRetrofitClient();

        Observable<SendVerificationResp> register = client.verify(email, new MyObserver<SendVerificationResp>() {
            @Override
            public void onSuccss(SendVerificationResp res) {
                Status status = res.getStatus();
                if(status.getCode() != 200){
                    Toast.makeText(SignUpActivity.this, status.getMsg(), Toast.LENGTH_SHORT).show();
                    return;
                }

                Log.d("rxjava", "success");
            }
        });

        Log.d("rxjava", "ok 123");
    }

    public void register(Map<String, String> map){
        MyRetrofitClient client = new MyRetrofitClient();

        Observable<SendVerificationResp> register = client.register(map, new MyObserver<SendVerificationResp>() {
            @Override
            public void onSuccss(SendVerificationResp res) {
//                Context context = getApplicationContext();
//                PreferenceUtil.putString(context, "token", "12345");
//                String token = PreferenceUtil.getString(context, "token", null);
                Status status = res.getStatus();
                if(status.getCode() != 200){
                    Toast.makeText(SignUpActivity.this, status.getMsg(), Toast.LENGTH_SHORT).show();
                    return;
                }

                Log.d("rxjava", "success");

                // 跳转到登录界面,销毁自己
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                Toast.makeText(SignUpActivity.this, "注册失败，请重试", Toast.LENGTH_SHORT).show();

            }
        });

        Log.d("rxjava", "ok 123");
    }


}

