package com.example.newhelloworld.queryVO.userInfo;

import com.example.newhelloworld.pojo.SubscribeInfo;
import com.example.newhelloworld.queryVO.Status;

import java.util.List;


public class GetSubscribeResp {
    private Status status;
    private Integer subscribe_num;
    private List<SubscribeInfo> subscribes;

    @Override
    public String toString() {
        return "GetSubscribeResp{" +
                "status=" + status +
                ", subscribe_num=" + subscribe_num +
                ", subscribes=" + subscribes +
                '}';
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Integer getSubscribe_num() {
        return subscribe_num;
    }

    public void setSubscribe_num(Integer subscribe_num) {
        this.subscribe_num = subscribe_num;
    }

    public List<SubscribeInfo> getSubscribes() {
        return subscribes;
    }

    public void setSubscribes(List<SubscribeInfo> subscribes) {
        this.subscribes = subscribes;
    }

    public GetSubscribeResp(Status status, Integer subscribe_num, List<SubscribeInfo> subscribes) {
        this.status = status;
        this.subscribe_num = subscribe_num;
        this.subscribes = subscribes;
    }
}
