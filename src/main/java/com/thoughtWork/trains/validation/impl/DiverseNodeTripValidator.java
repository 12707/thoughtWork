package com.thoughtWork.trains.validation.impl;

import com.thoughtWork.trains.domain.RouteNode;
import com.thoughtWork.trains.domain.Town;
import com.thoughtWork.trains.validation.IValidator;

import java.util.Optional;

import static org.apache.commons.lang3.StringUtils.EMPTY;


/**
 * Created by Administrator on 2016/9/8 0008.
 */
public class DiverseNodeTripValidator implements IValidator<RouteNode> {
	public boolean validate(RouteNode routeNode) {
		return Optional.ofNullable(routeNode)
				.map(RouteNode::getTrip)
				.map(trip -> Optional.ofNullable(trip.getStart())
						.map(Town::getId)
						.orElse(EMPTY)
						.equals(Optional.ofNullable(trip.getEnd())
								.map(Town::getId)
								.orElse(EMPTY)))
				.orElse(false);
	}
}
