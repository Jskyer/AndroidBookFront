package com.example.newhelloworld.event;

public class LogoutMsg {
    private boolean flag;

    @Override
    public String toString() {
        return "LogoutMsg{" +
                "flag=" + flag +
                '}';
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public LogoutMsg(boolean flag) {
        this.flag = flag;
    }
}
