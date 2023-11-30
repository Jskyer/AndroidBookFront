package com.example.newhelloworld.pojo;
/*编辑推荐*/

public class PodcastOffiRec {
    private Integer podcastId;
    private Integer albumId;

    private String title;
    private String podcastPoster;
    private String podcastPath;//音频文件路径
    private Integer views;//浏览量
    private Integer commentNum; // 评论量
    private String uploaderName;

    @Override
    public String toString() {
        return "PodcastOffiRec{" +
                "podcastId=" + podcastId +
                ", albumId=" + albumId +
                ", title='" + title + '\'' +
                ", podcastPoster='" + podcastPoster + '\'' +
                ", podcastPath='" + podcastPath + '\'' +
                ", views=" + views +
                ", commentNum=" + commentNum +
                ", uploaderName='" + uploaderName + '\'' +
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

    public String getUploaderName() {
        return uploaderName;
    }

    public void setUploaderName(String uploaderName) {
        this.uploaderName = uploaderName;
    }

    public PodcastOffiRec(Integer podcastId, Integer albumId, String title, String podcastPoster, String podcastPath, Integer views, Integer commentNum, String uploaderName) {
        this.podcastId = podcastId;
        this.albumId = albumId;
        this.title = title;
        this.podcastPoster = podcastPoster;
        this.podcastPath = podcastPath;
        this.views = views;
        this.commentNum = commentNum;
        this.uploaderName = uploaderName;
    }
}
