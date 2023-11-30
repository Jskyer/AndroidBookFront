package com.example.newhelloworld.pojo;

public class LikeInfo {
    private Integer comment_id;
    private Integer user_id;

    @Override
    public String toString() {
        return "LikeInfo{" +
                "comment_id=" + comment_id +
                ", user_id=" + user_id +
                '}';
    }

    public Integer getComment_id() {
        return comment_id;
    }

    public void setComment_id(Integer comment_id) {
        this.comment_id = comment_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public LikeInfo(Integer comment_id, Integer user_id) {
        this.comment_id = comment_id;
        this.user_id = user_id;
    }
}
