package com.example.newhelloworld.event;

public class MsgToAlbum {
    private int albumId;
    @Override
    public String toString(){
        return "MsgToComment{"+
                "albumId="+albumId+
                '}';

    }

    public int getAlbumId() {
        return albumId;
    }
    public void setAlbumId(int albumId){
        this.albumId=albumId;
    }
    public MsgToAlbum(int albumId){
        this.albumId=albumId;
    }
}
