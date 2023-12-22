package com.example.newhelloworld.event;

import com.example.newhelloworld.pojo.PodcastDo;

import java.util.List;

public class MsgToSearchResult {
    private Integer pageNum;
    private Integer pageSize;
    private String likeString;

    private List<PodcastDo> podcasts;

    @Override
    public String toString() {
        return "MsgToSearchResult{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", likeString='" + likeString + '\'' +
                ", podcasts=" + podcasts +
                '}';
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getLikeString() {
        return likeString;
    }

    public void setLikeString(String likeString) {
        this.likeString = likeString;
    }

    public List<PodcastDo> getPodcasts() {
        return podcasts;
    }

    public void setPodcasts(List<PodcastDo> podcasts) {
        this.podcasts = podcasts;
    }

    public MsgToSearchResult(Integer pageNum, Integer pageSize, String likeString, List<PodcastDo> podcasts) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.likeString = likeString;
        this.podcasts = podcasts;
    }
}
