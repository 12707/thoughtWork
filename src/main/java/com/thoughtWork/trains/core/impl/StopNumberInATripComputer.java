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
public class StopNumberInATripComputer extends AbstractComputer<Long, Trip, Rule> {
	@Override
	public Long compute(Trip trip, Rule rule) throws NoSuchRouteException {
		long numberOfTrips = 0;
		List<String> trips = new ArrayList<>();

		if (routeNodes != null) {
			List<RouteNode> startRouteNodes = routeNodes.stream().filter(routeNode -> routeNode.getTrip().getStart().equals(trip.getStart())).collect(toList());
			List<RouteNode> endRouteNodes = routeNodes.stream().filter(routeNode -> routeNode.getTrip().getEnd().equals(trip.getEnd())).collect(toList());

			routeNodes.stream().filter(routeNode -> routeNode.getTrip().getStart().equals(trip.getStart()))
					.filter(routeNode -> {
						routeNodes.stream().
					})
		}

		return numberOfTrips;
	}

	private void lookup() {

	}
}
