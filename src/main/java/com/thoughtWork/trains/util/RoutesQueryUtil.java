package com.thoughtWork.trains.util;

import com.thoughtWork.trains.core.impl.Rule;
import com.thoughtWork.trains.domain.RouteNode;
import com.thoughtWork.trains.domain.Town;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created by Administrator on 2016/9/15 0015.
 */
public class RoutesQueryUtil {

	public static void lookup(List<RouteNode> routeNodes, Rule rule, List<String> trips, Town start, Town end, StringBuilder route) {
		routeNodes.stream().filter(routeNode -> routeNode.getTrip().getStart().equals(start)).filter(childRouteNode -> {
			route.append(childRouteNode.getTrip().getEnd().getId());

			boolean isRecursive = false;
			if (rule == Rule.C_C_MAXIMUM_STOPS_NUMBER || rule == Rule.C_C_DISTANCE_LIMIT) {
				isRecursive = ((route.length() - 1) < rule.getLimit()) && !childRouteNode.getTrip().getEnd().equals(end);
			} else if (rule == Rule.A_C_EXACT_STOPS_NUMBER) {
				isRecursive = ((route.length() - 1) != rule.getLimit());
			} else {
				isRecursive = ((route.length() - 1) < Rule.DEFAULT.getLimit()) && !childRouteNode.getTrip().getEnd().equals(end);
			}

			if (isRecursive) {
				lookup(routeNodes, rule, trips, childRouteNode.getTrip().getEnd(), end, route);
			} else {
				if (childRouteNode.getTrip().getEnd().equals(end)) {
					trips.add(route.toString());
				}
			}

			if (route.length() > 0) {
				route.deleteCharAt(route.length() - 1);
			}

			return true;
		}).collect(toList());
	}
}
