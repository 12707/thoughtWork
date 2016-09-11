package com.thoughtWork.trains.core.impl;

import com.thoughtWork.trains.domain.Trip;

/**
 * Created by Administrator on 2016/9/10 0010.
 */
public enum Rule {
	MAXIMUM_STOPS_NUMBER("C", "C", 5),
	EXACT_STOPS_NUMBER("A", "C", 4),
	DEFAULT("", "", 10);

	private String StartTownID;
	private String endTownID;
	private int stops;

	Rule(String StartTownID, String endTownID, int stops) {
		this.StartTownID = StartTownID;
		this.endTownID = endTownID;
		this.stops = stops;
	}

	public int retriveLimitedStops(Trip trip) {
		if (trip.getStart().getId().equals(StartTownID) && trip.getEnd().getId().equals(endTownID)) {
			return stops;
		} else {
			return DEFAULT.stops;
		}
	}
}
