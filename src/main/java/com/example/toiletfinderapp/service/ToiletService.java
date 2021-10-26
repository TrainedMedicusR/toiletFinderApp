package com.example.toiletfinderapp.service;

import com.example.toiletfinderapp.entity.Toilet;

import java.util.List;

public interface ToiletService {

    List<Toilet> getAllToilets();

    List<Toilet> getAllDamagedToilets();

    List<Toilet> getBestToilet(double Longitude, double Latitude);

    Toilet getToiletByTid(int tid);

    int handleDamagedToilet(int toiletID, boolean is_damaged);

    int reportToilet(int toiletID);
}
