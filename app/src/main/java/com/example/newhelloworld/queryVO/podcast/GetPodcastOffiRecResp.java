package com.example.newhelloworld.queryVO.podcast;


import com.example.newhelloworld.pojo.PodcastOffiRec;
import com.example.newhelloworld.queryVO.Status;

import java.util.List;


public class GetPodcastOffiRecResp {
    private Status status;
    private List<PodcastOffiRec> podcasts;

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

    public List<PodcastOffiRec> getPodcasts() {
        return podcasts;
    }

    public void setPodcasts(List<PodcastOffiRec> podcasts) {
        this.podcasts = podcasts;
    }

    public GetPodcastOffiRecResp(Status status, List<PodcastOffiRec> podcasts) {
        this.status = status;
        this.podcasts = podcasts;
    }
}
