package com.example.newhelloworld.queryVO.userInfo;

import com.example.newhelloworld.pojo.HistoryInfo;
import com.example.newhelloworld.queryVO.Status;

import java.util.List;

public class GetHistoryResp {
    private Status status;
    private Integer history_num;
    private List<HistoryInfo> historys;

    @Override
    public String toString() {
        return "GetHistoryResp{" +
                "status=" + status +
                ", history_num=" + history_num +
                ", historys=" + historys +
                '}';
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Integer getHistory_num() {
        return history_num;
    }

    public void setHistory_num(Integer history_num) {
        this.history_num = history_num;
    }

    public List<HistoryInfo> getHistorys() {
        return historys;
    }

    public void setHistorys(List<HistoryInfo> historys) {
        this.historys = historys;
    }

    public GetHistoryResp(Status status, Integer history_num, List<HistoryInfo> historys) {
        this.status = status;
        this.history_num = history_num;
        this.historys = historys;
    }
}
