package com.thoughtWork.trains.domain;

import java.util.List;

/**
 * Created by Administrator on 2016/9/8 0008.
 */
public class Route {
	private List<Town> towns;

	public List<Town> getTowns() {
		return towns;
	}

	public void setTowns(List<Town> towns) {
		this.towns = towns;
	}
}
