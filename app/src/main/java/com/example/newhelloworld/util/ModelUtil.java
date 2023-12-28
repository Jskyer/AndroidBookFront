package com.example.newhelloworld.util;

import com.example.newhelloworld.model.Episode;
import com.example.newhelloworld.pojo.HistoryInfo;
import com.example.newhelloworld.pojo.Podcast;
import com.example.newhelloworld.pojo.PodcastDo;
import com.example.newhelloworld.pojo.SubscribeInfo;

public class ModelUtil {
    //后端Podcast pojo 转为 AudioListManager需要的Episode
    public static Episode transEpisode(Podcast pod){
        Episode episode = new Episode();

        episode.setId(pod.getPodcastId());
        episode.setTitle(pod.getTitle());
        episode.setUploader_name(pod.getUploaderName());
        episode.setPoster(pod.getPodcastPoster());
        episode.setDuration(pod.getDuration());
        episode.setPodcast_path(pod.getPodcastPath());

        episode.setLastTime(0);
        return episode;
    }

    public static Episode transEpisode(HistoryInfo info){
        Episode episode = new Episode();

        episode.setId(info.getPodcast_id());
        episode.setTitle(info.getTitle());
        episode.setUploader_name(info.getUploader_name());
        episode.setPoster(info.getPodcast_poster());
        episode.setDuration(Integer.parseInt(info.getDuration()));
        episode.setPodcast_path(info.getPodcast_path());

        episode.setLastTime(0);
        return episode;
    }

    public static Episode transEpisode(PodcastDo pod) {
        Episode episode = new Episode();

        episode.setId(pod.getPodcastId());
        episode.setTitle(pod.getTitle());
        episode.setUploader_name(pod.getUploaderName());
        episode.setPoster(pod.getPodcastPoster());
        episode.setDuration(pod.getDuration());
        episode.setPodcast_path(pod.getPodcastPath());

        episode.setLastTime(0);
        return episode;
    }


    public static Episode transEpisode(SubscribeInfo info){
        Episode episode = new Episode();

        episode.setId(info.getPodcast_id());
        episode.setTitle(info.getTitle());
        episode.setUploader_name(info.getUploader_name());
        episode.setPoster(info.getPodcast_poster());
        episode.setDuration(Integer.parseInt(info.getDuration()));
        episode.setPodcast_path(info.getPodcast_path());

        episode.setLastTime(0);
        return episode;
    }

}
