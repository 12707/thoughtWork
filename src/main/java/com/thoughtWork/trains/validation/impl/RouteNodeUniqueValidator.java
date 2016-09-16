package com.thoughtWork.trains.validation.impl;

import com.thoughtWork.trains.domain.TripNode;
import com.thoughtWork.trains.domain.Town;
import com.thoughtWork.trains.domain.Trip;
import com.thoughtWork.trains.validation.IValidator;

import java.util.List;
import java.util.Optional;

import static org.apache.commons.lang3.StringUtils.EMPTY;

/**
 * Created by Administrator on 2016/9/8 0008.
 */
public class RouteNodeUniqueValidator implements IValidator<List<TripNode>> {
	public boolean validate(List<TripNode> tripNodes) {
		if (tripNodes != null && tripNodes.size() > 0) {
			return tripNodes.stream().map(routeNode -> {
				Optional<Trip> tripOptional = Optional.ofNullable(routeNode.getTrip());
				return tripOptional.map(Trip::getStart).map(Town::getId).orElse(EMPTY) + tripOptional.map(Trip::getEnd).map(Town::getId).orElse(EMPTY);
			}).count() == tripNodes.size();
		}
		return false;
	}
}
