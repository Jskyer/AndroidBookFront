package com.example.newhelloworld.queryVO.podcast;

import com.example.newhelloworld.pojo.PodcastRankPreview;
import com.example.newhelloworld.queryVO.Status;

import java.util.List;


public class GetPodcastRankPreviewResp {
    private Status status;
    private List<PodcastRankPreview> podcasts;

    @Override
    public String toString() {
        return "GetPodcastRankPreviewResp{" +
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

    public List<PodcastRankPreview> getPodcasts() {
        return podcasts;
    }

    public void setPodcasts(List<PodcastRankPreview> podcasts) {
        this.podcasts = podcasts;
    }

    public GetPodcastRankPreviewResp(Status status, List<PodcastRankPreview> podcasts) {
        this.status = status;
        this.podcasts = podcasts;
    }
}
