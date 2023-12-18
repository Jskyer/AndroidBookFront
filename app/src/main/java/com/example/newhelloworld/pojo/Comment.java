package com.example.newhelloworld.pojo;

import java.util.Date;

public class Comment {
    private Integer comment_id;
    private Integer podcastId;
    private Integer commenter_id;
    private String comment_text;
    private Date comment_time;
    private Integer likeNum;

    public Comment() {

    }

    @Override
    public String toString() {
        return "Comment{" +
                "comment_id=" + comment_id +
                ", podcastId=" + podcastId +
                ", commenter_id=" + commenter_id +
                ", comment_text='" + comment_text + '\'' +
                ", comment_time=" + comment_time +
                ", likeNum=" + likeNum +
                '}';
    }

    public Integer getComment_id() {
        return comment_id;
    }

    public void setComment_id(Integer comment_id) {
        this.comment_id = comment_id;
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

    public String getComment_text() {
        return comment_text;
    }

    public void setComment_text(String comment_text) {
        this.comment_text = comment_text;
    }

    public Date getComment_time() {
        return comment_time;
    }

    public void setComment_time(Date comment_time) {
        this.comment_time = comment_time;
    }

    public Integer getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
    }

    public Comment(Integer comment_id, Integer podcastId, Integer commenter_id, String comment_text, Date comment_time, Integer likeNum) {
        this.comment_id = comment_id;
        this.podcastId = podcastId;
        this.commenter_id = commenter_id;
        this.comment_text = comment_text;
        this.comment_time = comment_time;
        this.likeNum = likeNum;
    }
}
