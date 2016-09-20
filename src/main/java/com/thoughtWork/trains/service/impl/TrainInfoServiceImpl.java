package com.thoughtWork.trains.service.impl;

import com.thoughtWork.trains.core.IComputer;
import com.thoughtWork.trains.core.TripRule;
import com.thoughtWork.trains.domain.Route;
import com.thoughtWork.trains.domain.Town;
import com.thoughtWork.trains.domain.Trip;
import com.thoughtWork.trains.exception.NoSuchRouteException;
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
    public String calcRouteDistance(String routes, boolean isShortest) {
        TripRule tripRule = null;
        if (isShortest) {
            tripRule = TripRule.SHORTEST_DISTANCE;
        }
        StringBuilder result = new StringBuilder();
        try {
            result.append(distanceComputer.compute(generateRoute(routes), tripRule));
        } catch (NoSuchRouteException e) {
            result.append(e.getClass().getName());
        }
        return result.toString();
    }
//
//    @Override
//    public TripNumberResponse calcTripNumber(String trip, boolean isDistanceRestriction) {
//        Trip tripObj = generateTrip(trip);
//        TripRule tripRule = generateTripRule(tripObj, isDistanceRestriction);
//        int tripNumber = tripsComputer.compute(tripObj, tripRule);
//        TripNumberResponse response = new TripNumberResponse();
//        response.setTripNumber(tripNumber);
//        return response;
//    }

    @Override
    public String calcTripNumber(String trip, String rule, int limit) {
        Trip tripObj = generateTrip(trip);
        TripRule tripRule = generateTripRule(rule, limit);
        StringBuilder result = new StringBuilder();
        result.append(tripsComputer.compute(tripObj, tripRule));
        return result.toString();
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
        return Trip.Builder.builder()
                .withStart(Town.Builder.builder().withId(trip.substring(0, 1)).build())
                .withEnd(Town.Builder.builder().withId(trip.substring(1, 2)).build())
                .build();
    }
//
//    private TripRule generateTripRule(Trip trip, boolean isDistanceRestriction) {
//        TripRule result;
//        Town aTown = Town.Builder.builder().withId("A").build();
//        Town cTown = Town.Builder.builder().withId("C").build();
//        if (cTown.equals(trip.getStart()) && cTown.equals(trip.getEnd())) {
//            if (isDistanceRestriction) {
//                result = TripRule.C_C_DISTANCE_LIMIT;
//            } else {
//                result = TripRule.C_C_MAXIMUM_STOPS_NUMBER;
//            }
//        } else if (aTown.equals(trip.getStart()) && cTown.equals(trip.getEnd())) {
//            result = TripRule.A_C_EXACT_STOPS_NUMBER;
//        } else {
//            result = TripRule.DEFAULT;
//        }
//        return result;
//    }

    private TripRule generateTripRule(String rule, int limit) {
        TripRule result;
        try {
            result = TripRule.valueOf(rule.toUpperCase());
            result.setLimit(limit);
        } catch (Exception e) {
            result = TripRule.DEFAULT;
        }
        return result;
    }
}
