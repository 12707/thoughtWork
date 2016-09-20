package com.thoughtWork.trains.service;

/**
 * Created by Administrator on 2016/9/18 0018.
 */
public interface ITrainInfoService {
    String calcRouteDistance(String routes, boolean isShortest);

    //TripNumberResponse calcTripNumber(String trip, boolean isDistanceRestriction);

    String calcTripNumber(String trip, String rule, int limit);
}
