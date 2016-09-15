package com.thoughtWork.trains.core.impl;

import com.thoughtWork.trains.core.AbstractComputer;
import com.thoughtWork.trains.domain.Route;
import com.thoughtWork.trains.domain.RouteNode;
import com.thoughtWork.trains.domain.Trip;
import com.thoughtWork.trains.exception.NoSuchRouteException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/8 0008.
 */
public class DistanceComputer extends AbstractComputer<Integer, Route, Rule> {
	@Override
	public Integer compute(Route route) throws NoSuchRouteException {
		List<Trip> trips = new ArrayList<>();
		if (route != null && route.getTowns() != null && route.getTowns().size() > 1) {
			for (int i = 0; i < route.getTowns().size() - 1; i++) {
				Trip trip = new Trip();
				trip.setStart(route.getTowns().get(i));
				trip.setEnd(route.getTowns().get(i + 1));
				trips.add(trip);
			}
		}

		if (routeNodes != null
				&& trips.size() > 0
				&& routeNodes.stream().filter(routeNode -> trips.contains(routeNode.getTrip())).count() == trips.size()) {
			return routeNodes.stream().filter(routeNode -> trips.contains(routeNode.getTrip()))
					.map(RouteNode::getDistance)
					.mapToInt(distance -> distance.intValue()).sum();
		} else {
			throw new NoSuchRouteException();
		}
	}
}
