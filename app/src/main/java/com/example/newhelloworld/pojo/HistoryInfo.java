package com.example.newhelloworld.pojo;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.util.Date;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class HistoryInfo {
    @Id(autoincrement = true)
    private Long history_id;//history_id

    private Integer podcast_id;
    private String views;
    private String podcast_poster;
    private String title;
    private Date update_time;
    private Date create_time;
    private String podcast_path;
    private Integer uploader_id;
    private String uploader_name;
    private String duration;
    private Integer album_id;
    private Integer like_num;
    private Integer comment_num;


    private Integer userId;
    private String album_name;
    private String podcast_name;
    private Date datetime;

    @Generated(hash = 774391409)
    public HistoryInfo(Long history_id, Integer podcast_id, String views,
            String podcast_poster, String title, Date update_time, Date create_time,
            String podcast_path, Integer uploader_id, String uploader_name,
            String duration, Integer album_id, Integer like_num,
            Integer comment_num, Integer userId, String album_name,
            String podcast_name, Date datetime) {
        this.history_id = history_id;
        this.podcast_id = podcast_id;
        this.views = views;
        this.podcast_poster = podcast_poster;
        this.title = title;
        this.update_time = update_time;
        this.create_time = create_time;
        this.podcast_path = podcast_path;
        this.uploader_id = uploader_id;
        this.uploader_name = uploader_name;
        this.duration = duration;
        this.album_id = album_id;
        this.like_num = like_num;
        this.comment_num = comment_num;
        this.userId = userId;
        this.album_name = album_name;
        this.podcast_name = podcast_name;
        this.datetime = datetime;
    }

    @Generated(hash = 1690888989)
    public HistoryInfo() {
    }

    @Override
    public String toString() {
        return "HistoryInfo{" +
                "history_id=" + history_id +
                ", podcast_id=" + podcast_id +
                ", views='" + views + '\'' +
                ", podcast_poster='" + podcast_poster + '\'' +
                ", title='" + title + '\'' +
                ", update_time=" + update_time +
                ", create_time=" + create_time +
                ", podcast_path='" + podcast_path + '\'' +
                ", uploader_id=" + uploader_id +
                ", uploader_name='" + uploader_name + '\'' +
                ", duration='" + duration + '\'' +
                ", album_id=" + album_id +
                ", like_num=" + like_num +
                ", comment_num=" + comment_num +
                ", userId=" + userId +
                ", album_name='" + album_name + '\'' +
                ", podcast_name='" + podcast_name + '\'' +
                ", datetime=" + datetime +
                '}';
    }

    public Long getHistory_id() {
        return history_id;
    }

    public void setHistory_id(Long history_id) {
        this.history_id = history_id;
    }

    public Integer getPodcast_id() {
        return podcast_id;
    }

    public void setPodcast_id(Integer podcast_id) {
        this.podcast_id = podcast_id;
    }

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }

    public String getPodcast_poster() {
        return podcast_poster;
    }

    public void setPodcast_poster(String podcast_poster) {
        this.podcast_poster = podcast_poster;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public String getPodcast_path() {
        return podcast_path;
    }

    public void setPodcast_path(String podcast_path) {
        this.podcast_path = podcast_path;
    }

    public Integer getUploader_id() {
        return uploader_id;
    }

    public void setUploader_id(Integer uploader_id) {
        this.uploader_id = uploader_id;
    }

    public String getUploader_name() {
        return uploader_name;
    }

    public void setUploader_name(String uploader_name) {
        this.uploader_name = uploader_name;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Integer getAlbum_id() {
        return album_id;
    }

    public void setAlbum_id(Integer album_id) {
        this.album_id = album_id;
    }

    public Integer getLike_num() {
        return like_num;
    }

    public void setLike_num(Integer like_num) {
        this.like_num = like_num;
    }

    public Integer getComment_num() {
        return comment_num;
    }

    public void setComment_num(Integer comment_num) {
        this.comment_num = comment_num;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getAlbum_name() {
        return album_name;
    }

    public void setAlbum_name(String album_name) {
        this.album_name = album_name;
    }

    public String getPodcast_name() {
        return podcast_name;
    }

    public void setPodcast_name(String podcast_name) {
        this.podcast_name = podcast_name;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }
}
