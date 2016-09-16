package com.thoughtWork.trains.core.impl;

import com.thoughtWork.trains.core.AbstractComputer;
import com.thoughtWork.trains.core.TripRule;
import com.thoughtWork.trains.domain.Town;
import com.thoughtWork.trains.domain.Trip;
import com.thoughtWork.trains.exception.NoSuchRouteException;

import java.util.ArrayList;
import java.util.List;

import static com.thoughtWork.trains.util.RoutesQueryUtil.lookup;

/**
 * Created by Administrator on 2016/9/10 0010.
 */
public class TripsComputer extends AbstractComputer<Integer, Trip, TripRule> {
    @Override
    public Integer compute(Trip trip, TripRule tripRule) throws NoSuchRouteException {
        List<List<Town>> trips = new ArrayList<>();

        if (tripNodes != null) {
            lookup(trips, new ArrayList(), 0, tripRule, this.tripNodes, trip.getStart(), trip.getEnd());
        }

        return trips.size();
    }
}
