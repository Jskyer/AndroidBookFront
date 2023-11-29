package com.example.newhelloworld.queryVO.signIn;


import com.example.newhelloworld.queryVO.Status;

public class ResetPassResp {
    private Status status;

    @Override
    public String toString() {
        return "ResetPassResp{" +
                "status=" + status +
                '}';
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public ResetPassResp(Status status) {
        this.status = status;
    }
}
