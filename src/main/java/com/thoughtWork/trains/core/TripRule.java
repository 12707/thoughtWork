package com.thoughtWork.trains.core;

/**
 * A series of rules related to different trips, which could be used in Computer implementation.
 */
public enum TripRule {
	C_C_MAXIMUM_STOPS_NUMBER(3),
	A_C_EXACT_STOPS_NUMBER(4),
	C_C_DISTANCE_LIMIT(30),
	SHORTEST_DISTANCE(0),
	DEFAULT(10),
	MAXIMUM(0),
	EXACT(0),
	LESS(0);

	private int limit;

	TripRule(int limit) {
		this.limit = limit;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}
}
