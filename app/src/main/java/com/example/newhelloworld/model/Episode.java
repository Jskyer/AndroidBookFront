package com.example.newhelloworld.model;

import java.time.LocalDateTime;

//播放列表单集
public class Episode {
    private int id;
    private String title;
    private String uploader_name;
    private String poster;
    //总时长 s
    private int duration;
    private LocalDateTime dateTime;
    // 音频url
    private String podcast_path;
//    private String type;


//    非数据库存储, 上次播放到的位置 ms
    private int lastTime;

    public Episode(){

    }

    public Episode(int id, String title, String uploader_name, int duration, LocalDateTime dateTime, String podcast_path) {
        this(id, title, uploader_name, null, duration, dateTime, podcast_path, 0);
    }


    public Episode(int id, String title, String uploader_name, String poster, int duration, LocalDateTime dateTime, String podcast_path, int lastTime) {
        this.id = id;
        this.title = title;
        this.uploader_name = uploader_name;
        this.poster = poster;
        this.duration = duration;
        this.dateTime = dateTime;
        this.podcast_path = podcast_path;
        this.lastTime = lastTime;
    }

    @Override
    public String toString() {
        return "Episode{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", uploader_name='" + uploader_name + '\'' +
                ", poster='" + poster + '\'' +
                ", duration=" + duration +
                ", dateTime=" + dateTime +
                ", podcast_path='" + podcast_path + '\'' +
                ", lastTime=" + lastTime +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLastTime() {
        return lastTime;
    }

    public void setLastTime(int lastTime) {
        this.lastTime = lastTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUploader_name() {
        return uploader_name;
    }

    public void setUploader_name(String uploader_name) {
        this.uploader_name = uploader_name;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getPodcast_path() {
        return podcast_path;
    }

    public void setPodcast_path(String podcast_path) {
        this.podcast_path = podcast_path;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
