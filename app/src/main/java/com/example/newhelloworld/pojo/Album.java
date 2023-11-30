package com.example.newhelloworld.pojo;


public class Album {
    private Integer album_id;
    private String album_name;
    private String description;
    private String upload_time;
    private Integer subscribe_number;
    private String upload_id;
    private String album_poster;
    private String is_private;
    private String type;

    @Override
    public String toString() {
        return "Album{" +
                "album_id=" + album_id +
                ", album_name='" + album_name + '\'' +
                ", description='" + description + '\'' +
                ", upload_time='" + upload_time + '\'' +
                ", subscribe_number=" + subscribe_number +
                ", upload_id='" + upload_id + '\'' +
                ", album_poster='" + album_poster + '\'' +
                ", is_private='" + is_private + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    public Integer getAlbum_id() {
        return album_id;
    }

    public void setAlbum_id(Integer album_id) {
        this.album_id = album_id;
    }

    public String getAlbum_name() {
        return album_name;
    }

    public void setAlbum_name(String album_name) {
        this.album_name = album_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUpload_time() {
        return upload_time;
    }

    public void setUpload_time(String upload_time) {
        this.upload_time = upload_time;
    }

    public Integer getSubscribe_number() {
        return subscribe_number;
    }

    public void setSubscribe_number(Integer subscribe_number) {
        this.subscribe_number = subscribe_number;
    }

    public String getUpload_id() {
        return upload_id;
    }

    public void setUpload_id(String upload_id) {
        this.upload_id = upload_id;
    }

    public String getAlbum_poster() {
        return album_poster;
    }

    public void setAlbum_poster(String album_poster) {
        this.album_poster = album_poster;
    }

    public String getIs_private() {
        return is_private;
    }

    public void setIs_private(String is_private) {
        this.is_private = is_private;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Album(Integer album_id, String album_name, String description, String upload_time, Integer subscribe_number, String upload_id, String album_poster, String is_private, String type) {
        this.album_id = album_id;
        this.album_name = album_name;
        this.description = description;
        this.upload_time = upload_time;
        this.subscribe_number = subscribe_number;
        this.upload_id = upload_id;
        this.album_poster = album_poster;
        this.is_private = is_private;
        this.type = type;
    }
}
