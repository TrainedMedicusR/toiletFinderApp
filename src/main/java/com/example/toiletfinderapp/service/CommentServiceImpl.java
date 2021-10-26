package com.example.toiletfinderapp.service;

import com.example.toiletfinderapp.dao.CommentMapper;
import com.example.toiletfinderapp.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    CommentMapper commentMapper;


    @Override
    public void addComment(Comment comment) {
        commentMapper.insertSelective(comment);
    }

    @Override
    public void deleteCommentById(int id) {
        commentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Comment> getAllComment() {
        List<Comment> comments = commentMapper.getAllComment();
        return comments;
    }

    @Override
    public List<Comment> getAllCommentByTid(int tid) {
        return commentMapper.getAllCommentByTid(tid);
    }
}
