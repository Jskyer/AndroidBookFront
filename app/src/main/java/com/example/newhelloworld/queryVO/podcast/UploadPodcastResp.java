package com.example.newhelloworld.queryVO.podcast;


import com.example.newhelloworld.queryVO.Status;

import java.util.List;


public class UploadPodcastResp {
    private Status status;

    @Override
    public String toString() {
        return "UploadPodcastResp{" +
                "status=" + status +
                '}';
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public UploadPodcastResp(Status status) {
        this.status = status;
    }
}
