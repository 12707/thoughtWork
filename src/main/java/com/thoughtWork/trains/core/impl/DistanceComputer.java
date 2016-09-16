package com.thoughtWork.trains.core.impl;

import com.thoughtWork.trains.core.AbstractComputer;
import com.thoughtWork.trains.core.TripRule;
import com.thoughtWork.trains.domain.Route;
import com.thoughtWork.trains.domain.Trip;
import com.thoughtWork.trains.domain.TripNode;
import com.thoughtWork.trains.exception.NoSuchRouteException;

import java.util.Collections;
import java.util.List;

/**
 * Calculate the distance according to the route.
 */
public class DistanceComputer extends AbstractComputer<Integer, Route, TripRule> {
    @Override
    public Integer compute(Route route, TripRule rule) throws NoSuchRouteException {
        List<Trip> trips = generateTrips(route);
        return calcalateDistance(trips);
    }

    private List<Trip> generateTrips(Route route) {
        List<Trip> trips = Collections.EMPTY_LIST;
        if (route != null
                && route.getTowns() != null
                && route.getTowns().size() > 1) {

            for (int i = 0; i < route.getTowns().size() - 1; i++) {
                trips.add(Trip.Builder.builder()
                        .withStart(route.getTowns().get(i))
                        .withEnd(route.getTowns().get(i + 1))
                        .build());
            }
        }

        return trips;
    }

    private int calcalateDistance(List<Trip> trips) {
        if (tripNodes != null
                && trips.size() > 0
                && tripNodes.stream().filter(routeNode -> trips.contains(routeNode.getTrip())).count() == trips.size()) {
            return tripNodes.stream().filter(routeNode -> trips.contains(routeNode.getTrip()))
                    .map(TripNode::getDistance)
                    .mapToInt(distance -> distance.intValue()).sum();
        } else {
            throw new NoSuchRouteException();
        }
    }
}
