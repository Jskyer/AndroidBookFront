package com.example.newhelloworld.queryVO.podcast;

import com.example.newhelloworld.pojo.Podcast;
import com.example.newhelloworld.queryVO.Status;

import java.util.List;


public class GetPodcastResp {
    private Status status;
    private Podcast podcast;

    @Override
    public String toString() {
        return "GetPodcastResp{" +
                "status=" + status +
                ", podcast=" + podcast +
                '}';
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Podcast getPodcast() {
        return podcast;
    }

    public void setPodcast(Podcast podcast) {
        this.podcast = podcast;
    }

    public GetPodcastResp(Status status, Podcast podcast) {
        this.status = status;
        this.podcast = podcast;
    }
}
