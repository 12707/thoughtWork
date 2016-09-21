package com.thoughtWork.trains.core.impl;

import com.thoughtWork.trains.core.AbstractComputer;
import com.thoughtWork.trains.core.TripRule;
import com.thoughtWork.trains.domain.Route;
import com.thoughtWork.trains.domain.Town;
import com.thoughtWork.trains.domain.Trip;
import com.thoughtWork.trains.domain.TripNode;
import com.thoughtWork.trains.exception.NoSuchRouteException;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.thoughtWork.trains.util.RoutesQueryUtil.lookup;

/**
 * Calculate the distance according to the route.
 */
@Component
public class DistanceComputer extends AbstractComputer<Integer, Route, TripRule> {
    @Override
    public Integer compute(Route route, TripRule tripRule) {
        List<Trip> trips = generateTrips(route);
        int result;
        if (tripRule == TripRule.SHORTEST_DISTANCE) {
            result = calculateShortestDistance(trips, tripRule);
        } else {
            result = calculateDistance(trips);
        }
        return result;
    }

    private List<Trip> generateTrips(Route route) {
        List<Trip> trips = new ArrayList<>();
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

    private int calculateShortestDistance(List<Trip> trips, TripRule tripRule) {
        List<List<Town>> tripsShortestDistance = new ArrayList<>();
        lookup(tripsShortestDistance, new ArrayList(), 0, tripRule, this.tripNodes,
                trips.stream().findFirst().orElse(Trip.Builder.builder().build()).getStart(),
                trips.stream().findFirst().orElse(Trip.Builder.builder().build()).getEnd());
        int result = tripsShortestDistance.stream()
                .map(towns -> generateTrips(Route.Builder.builder().withTowns(towns).build()))
                .mapToInt(tripList -> calculateDistance(tripList))
                .min()
                .orElse(-1);
        return result;
    }

    private int calculateDistance(List<Trip> trips) {
        if (tripNodes != null
                && trips.size() > 0) {
            trips.stream().forEach(trip -> {
                if (tripNodes.stream().filter(tripNode -> tripNode.getTrip().equals(trip)).count() == 0) {
                    throw new NoSuchRouteException(trips.toString());
                }
            });

            return tripNodes.stream().filter(routeNode -> trips.contains(routeNode.getTrip()))
                    .map(TripNode::getDistance)
                    .mapToInt(distance -> distance.intValue()).sum();
        } else {
            throw new NoSuchRouteException(trips.toString());
        }
    }
}
