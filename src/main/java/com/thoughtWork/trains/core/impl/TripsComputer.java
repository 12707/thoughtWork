package com.thoughtWork.trains.core.impl;

import com.thoughtWork.trains.core.AbstractComputer;
import com.thoughtWork.trains.domain.RouteNode;
import com.thoughtWork.trains.domain.Trip;
import com.thoughtWork.trains.exception.NoSuchRouteException;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created by Administrator on 2016/9/10 0010.
 */
public class TripsComputer extends AbstractComputer<Integer, Trip, Rule> {
    @Override
    public Integer compute(Trip trip, Rule rule) throws NoSuchRouteException {
        List<String> trips = new ArrayList<>();

        if (routeNodes != null) {
            StringBuilder stringBuilder = new StringBuilder();

            routeNodes.stream().filter(routeNode -> routeNode.getTrip().getStart().equals(trip.getStart()))
                    .filter(routeNode -> {
                        stringBuilder.append(trip.getStart().getId());
                        return lookup(rule, trip, trips, stringBuilder, routeNode, 1);
                    }).collect(toList());
        }

        return trips.size();
    }

    private boolean lookup(Rule rule, Trip originTrip, List<String> trips, StringBuilder tripBuilder, RouteNode routeNode, int stops) {
        routeNodes.stream().filter(routeNode1 -> routeNode1.getTrip().getStart().equals(routeNode.getTrip().getEnd()))
                .filter(routeNode1 -> {
                    tripBuilder.append(routeNode1.getTrip().getStart().getId());
                    if (routeNode1.getTrip().getEnd().equals(originTrip.getEnd())) {
                        tripBuilder.append(routeNode1.getTrip().getEnd().getId());
                        trips.add(tripBuilder.toString());
                    } else {
                        boolean isOver = false;

                        if (rule == Rule.MAXIMUM_STOPS_NUMBER) {
                            isOver = (stops > rule.retriveLimitedStops(originTrip));
                        } else if (rule == Rule.EXACT_STOPS_NUMBER) {
                            isOver = (stops == rule.retriveLimitedStops(originTrip));
                        } else {
                            isOver = (stops > Rule.DEFAULT.retriveLimitedStops(originTrip));
                        }

                        if (!isOver) {
                            lookup(rule, originTrip, trips, tripBuilder, routeNode1, stops + 1);
                        }
                    }
                    return true;
                }).collect(toList());
        return true;
    }
}
