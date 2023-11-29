package com.example.newhelloworld.util;

public class ResourceUtil {
    public static String getUrlAbsolutePath(String url){
//        return "http://10.0.2.2:7070" + url; //本地测试
        return "http://39.101.66.253:8080" + url; //服务器音频url前缀
    }
}
