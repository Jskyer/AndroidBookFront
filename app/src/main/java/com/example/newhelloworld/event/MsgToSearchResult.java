package com.example.newhelloworld.event;

import com.example.newhelloworld.pojo.PodcastDo;

import java.util.List;

public class MsgToSearchResult {
    private List<PodcastDo> podcasts;

    @Override
    public String toString() {
        return "MsgToSearchResult{" +
                "podcasts=" + podcasts +
                '}';
    }

    public List<PodcastDo> getPodcasts() {
        return podcasts;
    }

    public void setPodcasts(List<PodcastDo> podcasts) {
        this.podcasts = podcasts;
    }

    public MsgToSearchResult(List<PodcastDo> podcasts) {
        this.podcasts = podcasts;
    }
}
