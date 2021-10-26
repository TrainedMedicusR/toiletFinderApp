package com.example.toiletfinderapp.dao;

import com.example.toiletfinderapp.entity.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentMapper {
    int deleteByPrimaryKey(Integer commentId);
    int insert(Comment record);
    int insertSelective(Comment record);
    Comment selectByPrimaryKey(Integer commentId);
    int updateByPrimaryKeySelective(Comment record);
    int updateByPrimaryKey(Comment record);
    List<Comment> getAllComment();
    List<Comment> getAllCommentByTid(int toiletId);

}