package com.example.newhelloworld.event;

public class MsgToCategory {
    private String type;


    @Override
    public String toString() {
        return "MsgToCategory{" +
                "type='" + type + '\'' +
                '}';
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public MsgToCategory(String type) {
        this.type = type;
    }
}
