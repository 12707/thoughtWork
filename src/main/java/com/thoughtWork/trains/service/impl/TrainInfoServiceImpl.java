package com.thoughtWork.trains.service.impl;

import com.thoughtWork.trains.core.IComputer;
import com.thoughtWork.trains.core.TripRule;
import com.thoughtWork.trains.domain.Route;
import com.thoughtWork.trains.domain.Town;
import com.thoughtWork.trains.domain.Trip;
import com.thoughtWork.trains.response.RouteDistanceResponse;
import com.thoughtWork.trains.response.TripNumberResponse;
import com.thoughtWork.trains.service.ITrainInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by Administrator on 2016/9/18 0018.
 */

@Service
public class TrainInfoServiceImpl implements ITrainInfoService {
    @Autowired
    private IComputer<Integer, Route, TripRule> distanceComputer;

    @Autowired
    private IComputer<Integer, Trip, TripRule> tripsComputer;

    @Override
    public RouteDistanceResponse calcRouteDistance(String routes, boolean isShortest) {
        RouteDistanceResponse response = new RouteDistanceResponse();
        int distance;
        if (isShortest) {
            distance = distanceComputer.compute(generateRoute(routes), TripRule.SHORTEST_DISTANCE);
        } else {
            distance = distanceComputer.compute(generateRoute(routes), null);
        }
        response.setDistance(distance);
        return response;
    }

    @Override
    public TripNumberResponse calcTripNumber(String trip, boolean isDistanceRestriction) {
        TripNumberResponse response = new TripNumberResponse();
        int tripNumber;
        Trip tripObj = generateTrip(trip);
        TripRule tripRule = null;

        if(tripObj != null){
//            if(tripObj.getStart().equals())
        }

        if (isDistanceRestriction) {

//            tripNumber = tripsComputer.compute()
        } else {

        }
        return response;
    }

    private Route generateRoute(String routes) {
        StringTokenizer stringTokenizer = new StringTokenizer(routes, "-");
        List<Town> towns = new ArrayList();
        while (stringTokenizer.hasMoreTokens()) {
            towns.add(Town.Builder.builder().withId(stringTokenizer.nextToken()).build());
        }
        return Route.Builder.builder().withTowns(towns).build();
    }

    private Trip generateTrip(String trip) {
        return Trip.Builder.builder().build();
    }
}
