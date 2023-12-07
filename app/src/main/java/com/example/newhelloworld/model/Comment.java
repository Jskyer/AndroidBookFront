package com.example.newhelloworld.model;

import java.util.Date;

public class Comment {
    private Integer comment_id;
    private Integer podcastId;
    private Integer commenter_id;
    private String comment_text;
    private Date comment_time;
    private Integer likeNum;
    public Comment(){}

    public Comment(Integer comment_id) {
        this.comment_id = comment_id;
    }

    public Integer getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
    }

    public Date getComment_time() {
        return comment_time;
    }

    public void setComment_time(Date comment_time) {
        this.comment_time = comment_time;
    }

    public String getComment_text() {
        return comment_text;
    }

    public void setComment_text(String comment_text) {
        this.comment_text = comment_text;
    }

    public Integer getPodcastId() {
        return podcastId;
    }

    public void setPodcastId(Integer podcastId) {
        this.podcastId = podcastId;
    }

    public Integer getCommenter_id() {
        return commenter_id;
    }

    public void setCommenter_id(Integer commenter_id) {
        this.commenter_id = commenter_id;
    }

    public Integer getComment_id() {
        return comment_id;
    }

    public void setComment_id(Integer comment_id) {
        this.comment_id = comment_id;
    }
}