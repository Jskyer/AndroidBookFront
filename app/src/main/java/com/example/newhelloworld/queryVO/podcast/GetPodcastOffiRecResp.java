package com.example.newhelloworld.queryVO.podcast;


import com.example.newhelloworld.pojo.Podcast;
import com.example.newhelloworld.pojo.PodcastOffiRec;
import com.example.newhelloworld.queryVO.Status;

import java.util.List;


public class GetPodcastOffiRecResp {
    private Status status;
    private List<Podcast> podcasts;

    @Override
    public String toString() {
        return "GetPodcastOffiRecResp{" +
                "status=" + status +
                ", podcasts=" + podcasts +
                '}';
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Podcast> getPodcasts() {
        return podcasts;
    }

    public void setPodcasts(List<Podcast> podcasts) {
        this.podcasts = podcasts;
    }

    public GetPodcastOffiRecResp(Status status, List<Podcast> podcasts) {
        this.status = status;
        this.podcasts = podcasts;
    }
}
