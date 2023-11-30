package com.example.newhelloworld.queryVO.userInfo;

import com.example.newhelloworld.queryVO.Status;

public class IntegerResp {
    private Status status;
    private Integer num;

    @Override
    public String toString() {
        return "IntegerResp{" +
                "status=" + status +
                ", num=" + num +
                '}';
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public IntegerResp(Status status, Integer num) {
        this.status = status;
        this.num = num;
    }
}
