package com.example.newhelloworld.event;

import com.example.newhelloworld.model.Episode;

public class MsgAudioToMain {
    private Episode episode;

    private boolean isPlaying;

    public MsgAudioToMain(Episode episode, boolean isPlaying) {
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
        return "MsgAudioToMain{" +
                "episode=" + episode +
                ", isPlaying=" + isPlaying +
                '}';
    }
}
