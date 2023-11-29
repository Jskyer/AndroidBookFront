package com.example.newhelloworld.pojo;


public class PodcastUpdatePreview {
    private Integer podcastId;
    private Integer albumId;

    private String title;
    private String podcastPoster;

    private String podcastPath;//音频文件路径
    private String description;

    @Override
    public String toString() {
        return "PodcastUpdatePreview{" +
                "podcastId=" + podcastId +
                ", albumId=" + albumId +
                ", title='" + title + '\'' +
                ", podcastPoster='" + podcastPoster + '\'' +
                ", podcastPath='" + podcastPath + '\'' +
                ", description='" + description + '\'' +
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PodcastUpdatePreview(Integer podcastId, Integer albumId, String title, String podcastPoster, String podcastPath, String description) {
        this.podcastId = podcastId;
        this.albumId = albumId;
        this.title = title;
        this.podcastPoster = podcastPoster;
        this.podcastPath = podcastPath;
        this.description = description;
    }
}
