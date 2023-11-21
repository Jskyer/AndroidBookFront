package com.example.newhelloworld.queryVO;

public class SendVerificationResp {
    private Status status;

    @Override
    public String toString() {
        return "SendVerificationResp{" +
                "status=" + status +
                '}';
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public SendVerificationResp(Status status) {
        this.status = status;
    }
}

