package com.thoughtWork.trains.service;

import com.thoughtWork.trains.response.RouteDistanceResponse;
import com.thoughtWork.trains.response.TripNumberResponse;

/**
 * Created by Administrator on 2016/9/18 0018.
 */
public interface ITrainInfoService {
    RouteDistanceResponse calcRouteDistance(String routes, boolean isShortest);

    TripNumberResponse calcTripNumber(String trip, boolean isDistanceRestriction);
}
