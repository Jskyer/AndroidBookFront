package com.example.newhelloworld.event;

import com.example.newhelloworld.model.Episode;

public class MsgAddToAudioList {
    private Episode episode;

    public MsgAddToAudioList(Episode episode) {
        this.episode = episode;
    }

    public Episode getEpisode() {
        return episode;
    }

    public void setEpisode(Episode episode) {
        this.episode = episode;
    }

    @Override
    public String toString() {
        return "MsgAddToAudioList{" +
                "episode=" + episode +
                '}';
    }
}
