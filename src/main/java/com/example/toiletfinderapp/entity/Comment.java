package com.example.toiletfinderapp.entity;

import java.util.Date;

public class Comment {
    private Integer commentId;

    private String content;

    private Date submitTime;

    private Integer toiletId;

    private Integer rating;

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public Integer getToiletId() {
        return toiletId;
    }

    public void setToiletId(Integer toiletId) {
        this.toiletId = toiletId;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}