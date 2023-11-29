package com.example.newhelloworld.queryVO.podcast;

import com.example.newhelloworld.pojo.PodcastUpdatePreview;
import com.example.newhelloworld.queryVO.Status;

import java.util.List;

public class GetPodcastUpdatePreviewResp {
    private Status status;
    private List<PodcastUpdatePreview> podcasts;

    @Override
    public String toString() {
        return "GetPodcastUpdatePreviewResp{" +
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

    public List<PodcastUpdatePreview> getPodcasts() {
        return podcasts;
    }

    public void setPodcasts(List<PodcastUpdatePreview> podcasts) {
        this.podcasts = podcasts;
    }

    public GetPodcastUpdatePreviewResp(Status status, List<PodcastUpdatePreview> podcasts) {
        this.status = status;
        this.podcasts = podcasts;
    }
}
