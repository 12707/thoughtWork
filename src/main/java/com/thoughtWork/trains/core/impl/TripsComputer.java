package com.thoughtWork.trains.core.impl;

import com.thoughtWork.trains.core.AbstractComputer;
import com.thoughtWork.trains.core.TripRule;
import com.thoughtWork.trains.domain.Town;
import com.thoughtWork.trains.domain.Trip;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.thoughtWork.trains.util.RoutesQueryUtil.lookup;

/**
 * Created by Administrator on 2016/9/10 0010.
 */
@Component
public class TripsComputer extends AbstractComputer<Integer, Trip, TripRule> {

	public TripsComputer(ApplicationArguments arguments) {
		super(arguments);
	}

	public TripsComputer() {
	}

	@Override
	public Integer compute(Trip trip, TripRule tripRule) {
		List<List<Town>> trips = new ArrayList<>();

		if (tripNodes != null) {
			lookup(trips, new ArrayList(), 0, tripRule, this.tripNodes, trip.getStart(), trip.getEnd());
		}

		return trips.size();
	}
}
