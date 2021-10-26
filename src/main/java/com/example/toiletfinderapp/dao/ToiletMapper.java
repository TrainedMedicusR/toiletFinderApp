package com.example.toiletfinderapp.dao;

import com.example.toiletfinderapp.entity.Toilet;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToiletMapper {
    int deleteByPrimaryKey(Integer toiletId);

    int insert(Toilet record);

    int insertSelective(Toilet record);

    Toilet selectByPrimaryKey(Integer toiletId);

    int updateByPrimaryKeySelective(Toilet record);

    int updateByPrimaryKey(Toilet record);

    List<Toilet> getAllToilets();

    List<Toilet> getAllDamagedToilets();

}