package com.example.newhelloworld.event;

import com.example.newhelloworld.model.Episode;

public class MsgPlaylistToMain {
    private Episode episode;

    private boolean isPlaying;

    public MsgPlaylistToMain(Episode episode, boolean isPlaying) {
        this.episode = episode;
        this.isPlaying = isPlaying;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }

    public Episode getEpisode() {
        return episode;
    }

    public void setEpisode(Episode episode) {
        this.episode = episode;
    }

    @Override
    public String toString() {
        return "MsgPlaylistToMain{" +
                "episode=" + episode +
                ", isPlaying=" + isPlaying +
                '}';
    }
}
