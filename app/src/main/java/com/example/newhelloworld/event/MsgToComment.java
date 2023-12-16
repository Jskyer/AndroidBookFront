package com.example.newhelloworld.event;

public class MsgToComment {
    private int podcastId;

    @Override
    public String toString() {
        return "MsgToComment{" +
                "podcastId=" + podcastId +
                '}';
    }

    public int getPodcastId() {
        return podcastId;
    }

    public void setPodcastId(int podcastId) {
        this.podcastId = podcastId;
    }

    public MsgToComment(int podcastId) {
        this.podcastId = podcastId;
    }
}
