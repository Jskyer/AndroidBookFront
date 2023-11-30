package com.example.newhelloworld.pojo;


public class PodcastDo {
    private Integer podcastId;
    private Integer albumId;
    private Integer duration; // 时长
    private String title;
    private String podcastPoster;

    private String podcastPath;//音频文件路径

    private Integer views;//浏览量
    private Integer commentNum; // 评论量
    private Integer likeNum;

    private Integer uploaderId;
    private String uploaderName;
    private String createTime; // 上传时间
    private String updateTime;//更新时间

    @Override
    public String toString() {
        return "PodcastDo{" +
                "podcastId=" + podcastId +
                ", albumId=" + albumId +
                ", duration=" + duration +
                ", title='" + title + '\'' +
                ", podcastPoster='" + podcastPoster + '\'' +
                ", podcastPath='" + podcastPath + '\'' +
                ", views=" + views +
                ", commentNum=" + commentNum +
                ", likeNum=" + likeNum +
                ", uploaderId=" + uploaderId +
                ", uploaderName='" + uploaderName + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                '}';
    }

    public Integer getPodcastId() {
        return podcastId;
    }

    public void setPodcastId(Integer podcastId) {
        this.podcastId = podcastId;
    }

    public Integer getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPodcastPoster() {
        return podcastPoster;
    }

    public void setPodcastPoster(String podcastPoster) {
        this.podcastPoster = podcastPoster;
    }

    public String getPodcastPath() {
        return podcastPath;
    }

    public void setPodcastPath(String podcastPath) {
        this.podcastPath = podcastPath;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Integer getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

    public Integer getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
    }

    public Integer getUploaderId() {
        return uploaderId;
    }

    public void setUploaderId(Integer uploaderId) {
        this.uploaderId = uploaderId;
    }

    public String getUploaderName() {
        return uploaderName;
    }

    public void setUploaderName(String uploaderName) {
        this.uploaderName = uploaderName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public PodcastDo(Integer podcastId, Integer albumId, Integer duration, String title, String podcastPoster, String podcastPath, Integer views, Integer commentNum, Integer likeNum, Integer uploaderId, String uploaderName, String createTime, String updateTime) {
        this.podcastId = podcastId;
        this.albumId = albumId;
        this.duration = duration;
        this.title = title;
        this.podcastPoster = podcastPoster;
        this.podcastPath = podcastPath;
        this.views = views;
        this.commentNum = commentNum;
        this.likeNum = likeNum;
        this.uploaderId = uploaderId;
        this.uploaderName = uploaderName;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }
}
