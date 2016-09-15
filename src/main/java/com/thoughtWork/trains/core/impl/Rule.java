package com.thoughtWork.trains.core.impl;

/**
 * Created by Administrator on 2016/9/10 0010.
 */
public enum Rule {
	C_C_MAXIMUM_STOPS_NUMBER(3),
	A_C_EXACT_STOPS_NUMBER(4),
	C_C_DISTANCE_LIMIT(30),
	DEFAULT(10);

	private int limit;

	Rule(int limit) {
		this.limit = limit;
	}

	public int getLimit() {
		return limit;
	}
}
