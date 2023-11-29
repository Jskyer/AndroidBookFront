package com.example.newhelloworld.pojo;


public class PodcastRankPreview {
    private Integer podcastId;
    private Integer albumId;
    private String title;
    private String podcastPoster;
    private String podcastPath;//音频文件路径

    @Override
    public String toString() {
        return "PodcastRankPreview{" +
                "podcastId=" + podcastId +
                ", albumId=" + albumId +
                ", title='" + title + '\'' +
                ", podcastPoster='" + podcastPoster + '\'' +
                ", podcastPath='" + podcastPath + '\'' +
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

    public PodcastRankPreview(Integer podcastId, Integer albumId, String title, String podcastPoster, String podcastPath) {
        this.podcastId = podcastId;
        this.albumId = albumId;
        this.title = title;
        this.podcastPoster = podcastPoster;
        this.podcastPath = podcastPath;
    }
}
