package com.example.newhelloworld.event;

public class MsgModeChange {
    private int mode;

    @Override
    public String toString() {
        return "MsgModeChange{" +
                "mode=" + mode +
                '}';
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public MsgModeChange(int mode) {
        this.mode = mode;
    }
}
