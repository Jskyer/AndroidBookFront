package com.example.newhelloworld.queryVO;


public class LoginResp {
    private Status status;
    private String user_id;
    private String user_avatar;
    private String user_name;
    private String access_token;
    private Long access_expire;

    public LoginResp(Status status, String user_id, String user_avatar, String user_name, String access_token, Long access_expire) {
        this.status = status;
        this.user_id = user_id;
        this.user_avatar = user_avatar;
        this.user_name = user_name;
        this.access_token = access_token;
        this.access_expire = access_expire;
    }

    @Override
    public String toString() {
        return "LoginResp{" +
                "status=" + status +
                ", user_id='" + user_id + '\'' +
                ", user_avatar='" + user_avatar + '\'' +
                ", user_name='" + user_name + '\'' +
                ", access_token='" + access_token + '\'' +
                ", access_expire=" + access_expire +
                '}';
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_avatar() {
        return user_avatar;
    }

    public void setUser_avatar(String user_avatar) {
        this.user_avatar = user_avatar;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public Long getAccess_expire() {
        return access_expire;
    }

    public void setAccess_expire(Long access_expire) {
        this.access_expire = access_expire;
    }
}
