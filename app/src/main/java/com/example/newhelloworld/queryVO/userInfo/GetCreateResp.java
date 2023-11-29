package com.example.newhelloworld.queryVO.userInfo;

import com.example.newhelloworld.pojo.Album;
import com.example.newhelloworld.queryVO.Status;

import java.util.List;

public class GetCreateResp {
    private Status status;
    private Integer album_num;
    private List<Album> albums;

    @Override
    public String toString() {
        return "GetCreateResp{" +
                "status=" + status +
                ", album_num=" + album_num +
                ", albums=" + albums +
                '}';
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Integer getAlbum_num() {
        return album_num;
    }

    public void setAlbum_num(Integer album_num) {
        this.album_num = album_num;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

    public GetCreateResp(Status status, Integer album_num, List<Album> albums) {
        this.status = status;
        this.album_num = album_num;
        this.albums = albums;
    }
}
