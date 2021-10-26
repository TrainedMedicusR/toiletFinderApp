package com.example.toiletfinderapp.service;

import com.example.toiletfinderapp.entity.Comment;

import java.util.List;

public interface CommentService {

    void addComment(Comment comment);

    void deleteCommentById(int id);

    List<Comment> getAllComment();

    List<Comment> getAllCommentByTid(int tid);

}
