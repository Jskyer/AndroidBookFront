package com.example.newhelloworld.queryVO.album;

import com.example.newhelloworld.pojo.Album;
import com.example.newhelloworld.pojo.PodcastDo;
import com.example.newhelloworld.queryVO.Status;

import java.util.List;


public class GetAlbumInfoResp {
    private Status status;
    private Integer podcast_num;
    private Album album;
    private List<PodcastDo> podcasts;

    @Override
    public String toString() {
        return "GetAlbumInfoResp{" +
                "status=" + status +
                ", podcast_num=" + podcast_num +
                ", album=" + album +
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

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public List<PodcastDo> getPodcasts() {
        return podcasts;
    }

    public void setPodcasts(List<PodcastDo> podcasts) {
        this.podcasts = podcasts;
    }

    public GetAlbumInfoResp(Status status, Integer podcast_num, Album album, List<PodcastDo> podcasts) {
        this.status = status;
        this.podcast_num = podcast_num;
        this.album = album;
        this.podcasts = podcasts;
    }
}
