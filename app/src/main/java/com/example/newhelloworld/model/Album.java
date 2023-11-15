package com.example.newhelloworld.model;

import java.time.LocalDateTime;

public class Album {

    private int id;
    private String title;
    private String description;
    private String img;
    private LocalDateTime createTime;
    private int count;

    public Album(String title, LocalDateTime createTime, int count) {
        this.title = title;
        this.createTime = createTime;
        this.count = count;
    }

    public Album(int id, String title, String description, String img, LocalDateTime createTime, int count) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.img = img;
        this.createTime = createTime;
        this.count = count;
    }

    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", img='" + img + '\'' +
                ", createTime=" + createTime +
                ", count=" + count +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
