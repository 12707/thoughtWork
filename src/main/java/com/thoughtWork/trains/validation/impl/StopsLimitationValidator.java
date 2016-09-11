package com.thoughtWork.trains.validation.impl;

import com.thoughtWork.trains.domain.RouteNode;
import com.thoughtWork.trains.domain.Trip;
import com.thoughtWork.trains.validation.IValidator;

import java.util.List;

import static com.thoughtWork.trains.domain.Town.buildTown;

/**
 * Created by Administrator on 2016/9/10 0010.
 */
public class StopsLimitationValidator implements IValidator<List<RouteNode>> {
	private Trip maximumStopsTrip = new Trip();

	private Trip exactStopsTrip = new Trip();

	public StopsLimitationValidator(){
		maximumStopsTrip.setStart(buildTown("C"));
		maximumStopsTrip.setEnd(buildTown("C"));
		exactStopsTrip.setStart(buildTown("A"));
		exactStopsTrip.setEnd(buildTown("C"));
	}

	@Override
	public boolean validate(List<RouteNode> routeNodes) {
		return false;
	}
}
