package com.thoughtWork.trains.core.impl;

import com.thoughtWork.trains.core.AbstractComputer;
import com.thoughtWork.trains.domain.Trip;
import com.thoughtWork.trains.exception.NoSuchRouteException;

import java.util.ArrayList;
import java.util.List;

import static com.thoughtWork.trains.util.RoutesQueryUtil.lookup;

/**
 * Created by Administrator on 2016/9/15 0015.
 */
public class RoutesComputer extends AbstractComputer<Integer, Trip, Rule> {
	@Override
	public Integer compute(Trip trip, Rule rule) throws NoSuchRouteException {
		List<String> trips = new ArrayList<>();

		if (routeNodes != null) {
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(trip.getStart().getId());
			lookup(this.routeNodes, rule, trips, trip.getStart(), trip.getEnd(), stringBuilder);
		}

		return trips.size();
	}
}
