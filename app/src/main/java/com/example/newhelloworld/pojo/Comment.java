package com.example.newhelloworld.pojo;

import java.util.Date;

public class Comment {
    private Integer commentId;
    private Integer podcastId;
    private Integer commenter_id;
    private String comment_text;
    private Date commentTime;
    private Integer likeNum;

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", podcastId=" + podcastId +
                ", commenter_id=" + commenter_id +
                ", comment_text='" + comment_text + '\'' +
                ", commentTime=" + commentTime +
                ", likeNum=" + likeNum +
                '}';
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
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

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }

    public Integer getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
    }

    public Comment(Integer commentId, Integer podcastId, Integer commenter_id, String comment_text, Date commentTime, Integer likeNum) {
        this.commentId = commentId;
        this.podcastId = podcastId;
        this.commenter_id = commenter_id;
        this.comment_text = comment_text;
        this.commentTime = commentTime;
        this.likeNum = likeNum;
    }
}
