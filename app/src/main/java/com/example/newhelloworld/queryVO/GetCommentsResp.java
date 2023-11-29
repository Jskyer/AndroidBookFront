package com.example.newhelloworld.queryVO;

import com.example.newhelloworld.pojo.Comment;

import java.util.List;


public class GetCommentsResp {
    private Status status;
    private Integer commentNum;
    private List<Comment> comments;

    @Override
    public String toString() {
        return "GetCommentsResp{" +
                "status=" + status +
                ", commentNum=" + commentNum +
                ", comments=" + comments +
                '}';
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Integer getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public GetCommentsResp(Status status, Integer commentNum, List<Comment> comments) {
        this.status = status;
        this.commentNum = commentNum;
        this.comments = comments;
    }
}
