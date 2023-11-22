package com.example.newhelloworld.net;


import com.example.newhelloworld.queryVO.LoginResp;
import com.example.newhelloworld.queryVO.SendVerificationResp;

import java.util.Map;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface IRequest {
    @GET("/api/login/send_verification")
    Observable<SendVerificationResp> verify(@Query("email") String map);

    @POST("/api/login/signin")
    Observable<SendVerificationResp> register(@Body Map<String, String> map);

    @POST("/api/login/login")
    Observable<LoginResp> login(@Body Map<String, String> map);

    //    @Multipart
//    @POST("/file/upload")
//    Observable<Result> uploadAvatar(@Part MultipartBody.Part mediaFile);

}
