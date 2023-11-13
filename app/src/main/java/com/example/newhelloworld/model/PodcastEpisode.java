package com.example.newhelloworld.model;

import java.time.LocalDateTime;

public class PodcastEpisode {
    private String title;
    private String content;
    private int leftTime;
    private LocalDateTime dateTime;

    public PodcastEpisode(String title, String content, int leftTime, LocalDateTime dateTime) {
        this.title = title;
        this.content = content;
        this.leftTime = leftTime;
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "History{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", leftTime=" + leftTime +
                ", dateTime=" + dateTime +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLeftTime() {
        return leftTime;
    }

    public void setLeftTime(int leftTime) {
        this.leftTime = leftTime;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
