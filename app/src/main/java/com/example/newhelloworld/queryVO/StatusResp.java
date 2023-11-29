package com.example.newhelloworld.queryVO;


public class StatusResp {
    private Status status;

    @Override
    public String toString() {
        return "StatusResp{" +
                "status=" + status +
                '}';
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public StatusResp(Status status) {
        this.status = status;
    }
}
