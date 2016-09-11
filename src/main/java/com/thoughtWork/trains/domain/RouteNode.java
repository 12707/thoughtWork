package com.thoughtWork.trains.domain;

/**
 * Created by Administrator on 2016/9/8 0008.
 */
public class RouteNode {
	private Trip trip;

	private int distance;

	public Trip getTrip() {
		return trip;
	}

	public void setTrip(Trip trip) {
		this.trip = trip;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}
}
