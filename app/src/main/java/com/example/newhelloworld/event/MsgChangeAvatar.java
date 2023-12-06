package com.example.newhelloworld.event;

//PersonalDetailActivity中上传头像成功后发送事件
public class MsgChangeAvatar {
    private String path;

    public MsgChangeAvatar(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "MsgChangeAvatar{" +
                "path='" + path + '\'' +
                '}';
    }
}
