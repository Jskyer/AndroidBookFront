package com.example.newhelloworld.util;

public class ResourceUtil {

//    public static String getUrlAbsolutePath(String url){
//        return "http://10.0.2.2:7070" + url; //本地测试
////        return "http://39.101.66.253:8080" + url; //服务器音频url前缀
//    }

    //user头像
    public static String getUserAvatarPath(String url){
//        return "http://10.0.2.2:7070/img/" + url; //本地测试
        return "http://139.224.194.55:8080/avatar/" + url; //服务器url前缀
    }

    //podcast封面
    public static String getPodcastPosterPath(String url){
        return "http://139.224.194.55:8080/album/" + url; //服务器url前缀
    }

    //podcast音频
    public static String getPodcastPath(String url){
        return "http://139.224.194.55:8080/audio/" + url; //服务器url前缀
    }

}
