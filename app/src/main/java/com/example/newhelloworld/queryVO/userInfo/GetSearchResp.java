package com.example.newhelloworld.queryVO.userInfo;

import com.example.newhelloworld.pojo.PodcastDo;
import com.example.newhelloworld.queryVO.Status;

import java.util.List;

public class GetSearchResp {
    private Status status;
    private Integer podcast_num;
    private List<PodcastDo> podcasts;

    @Override
    public String toString() {
        return "GetSearchResp{" +
                "status=" + status +
                ", podcast_num=" + podcast_num +
                ", podcasts=" + podcasts +
                '}';
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Integer getPodcast_num() {
        return podcast_num;
    }

    public void setPodcast_num(Integer podcast_num) {
        this.podcast_num = podcast_num;
    }

    public List<PodcastDo> getPodcasts() {
        return podcasts;
    }

    public void setPodcasts(List<PodcastDo> podcasts) {
        this.podcasts = podcasts;
    }

    public GetSearchResp(Status status, Integer podcast_num, List<PodcastDo> podcasts) {
        this.status = status;
        this.podcast_num = podcast_num;
        this.podcasts = podcasts;
    }
}
