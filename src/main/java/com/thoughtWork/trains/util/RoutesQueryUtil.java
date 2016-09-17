package com.thoughtWork.trains.util;

import com.thoughtWork.trains.core.TripRule;
import com.thoughtWork.trains.domain.Town;
import com.thoughtWork.trains.domain.TripNode;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created by Administrator on 2016/9/15 0015.
 */
public class RoutesQueryUtil {

    /**
     * @param trips
     * @param routes
     * @param distance
     * @param tripRule
     * @param tripNodes
     * @param start
     * @param end
     */
    public static void lookup(List<List<Town>> trips, List<Town> routes, int distance, TripRule tripRule, List<TripNode> tripNodes, Town start, Town end) {
        //Initial start town with id, only add it when the first invocation.
        if (routes.isEmpty()) routes.add(start);

        //It doesn't matter what is returned, the basic idea is that generating route according to 'tripRule', which is going to be added into a list<string>.
        tripNodes.stream().filter(routeNode -> routeNode.getTrip().getStart().equals(start)).filter(childRouteNode -> {
            routes.add(childRouteNode.getTrip().getEnd());

            boolean isRecursive = false, isAdded = false;
            if (tripRule == TripRule.C_C_MAXIMUM_STOPS_NUMBER) {
                isRecursive = ((routes.size() - 1) < tripRule.getLimit());
                isAdded = childRouteNode.getTrip().getEnd().equals(end);
            } else if (tripRule == TripRule.A_C_EXACT_STOPS_NUMBER) {
                isRecursive = ((routes.size() - 1) != tripRule.getLimit());
                isAdded = !isRecursive && childRouteNode.getTrip().getEnd().equals(end);
            } else if (tripRule == TripRule.C_C_DISTANCE_LIMIT) {
                isRecursive = (distance + childRouteNode.getDistance()) < tripRule.getLimit();
                isAdded = childRouteNode.getTrip().getEnd().equals(end) && isRecursive;
            }else if(tripRule == TripRule.SHORTEST_DISTANCE){
                isRecursive = childRouteNode.getTrip().getEnd().equals(end) ? false : ((routes.size() - 1) < TripRule.DEFAULT.getLimit());
                isAdded = childRouteNode.getTrip().getEnd().equals(end);
            }else {
                isRecursive = ((routes.size() - 1) < TripRule.DEFAULT.getLimit());
                isAdded = childRouteNode.getTrip().getEnd().equals(end);
            }

            if (isAdded) {
                trips.add(new ArrayList(routes));
            }

            if (isRecursive) {
                lookup(trips, routes, distance + childRouteNode.getDistance(), tripRule, tripNodes, childRouteNode.getTrip().getEnd(), end);
            }

            if (routes.size() > 0) {
                routes.remove(routes.size() - 1);
            }

            return true;
        }).collect(toList());
    }
}
