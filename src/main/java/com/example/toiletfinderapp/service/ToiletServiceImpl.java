package com.example.toiletfinderapp.service;

import com.example.toiletfinderapp.dao.ToiletMapper;
import com.example.toiletfinderapp.entity.Toilet;
import com.example.toiletfinderapp.util.DistanceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class ToiletServiceImpl implements ToiletService{

    @Autowired
    ToiletMapper toiletMapper;

    @Override
    public List<Toilet> getAllToilets() {
        return toiletMapper.getAllToilets();
    }

    @Override
    public List<Toilet> getBestToilet(double Longitude, double Latitude) {
        List<Toilet> toilets = getAllToilets();
        toilets.sort(new Comparator<Toilet>() {
            @Override
            public int compare(Toilet t1, Toilet t2) {
                double distance1 = DistanceUtil.GetDistance(Longitude, Latitude, t1.getLongitude(), t1.getLatitude());
                double distance2 = DistanceUtil.GetDistance(Longitude, Latitude, t2.getLongitude(), t2.getLatitude());
                return (int) (distance1 - distance2);
            }

        });
        List<Toilet> rs = new ArrayList<>(5);
        for (int i = 0; i < 5; i++) {
            rs.add(toilets.get(i));
        }
        return rs;
    }

    @Override
    public Toilet getToiletByTid(int tid) {
        return toiletMapper.selectByPrimaryKey(tid);
    }

    @Override
    public List<Toilet> getAllDamagedToilets() {
        return toiletMapper.getAllDamagedToilets();
    }

    @Override
    public int handleDamagedToilet(int toiletID, boolean is_damaged) {
        Toilet toilet = new Toilet();
        toilet.setToiletId(toiletID);
        toilet.setIsDamage(is_damaged);
        return toiletMapper.updateByPrimaryKeySelective(toilet);
    }

    @Override
    public int reportToilet(int toiletID) {
        Toilet toilet = new Toilet();
        toilet.setToiletId(toiletID);
        toilet.setIsDamage(true);
        return toiletMapper.updateByPrimaryKeySelective(toilet);
    }
}
