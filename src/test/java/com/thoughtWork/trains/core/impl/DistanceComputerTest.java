package com.thoughtWork.trains.core.impl;

import com.thoughtWork.trains.core.IComputer;
import com.thoughtWork.trains.core.TripRule;
import com.thoughtWork.trains.domain.Route;
import com.thoughtWork.trains.domain.Town;
import com.thoughtWork.trains.exception.NoSuchRouteException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Administrator on 2016/9/10 0010.
 */

@RunWith(JUnit4.class)
public class DistanceComputerTest {
	private Route route;

	private IComputer<Integer, Route, TripRule> computer = new DistanceComputer();

	private String graph = "AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7";

	@Before
	public void init() {
		computer.prepareRoutesData(graph);
	}

	@Test
	public void testComputeDistanceWith2Nodes() {
		initRoute("AD");
		Integer distance = computer.compute(route);
		assertThat(distance, is(5));
	}

	@Test
	public void testComputeDistanceWith3Nodes() {
		initRoute("ABC");
		Integer distance = computer.compute(route);
		assertThat(distance, is(9));
	}

	@Test
	public void testComputeDistanceWith5Nodes() {
		initRoute("AEBCD");
		Integer distance = computer.compute(route);
		assertThat(distance, is(22));
	}

	@Test(expected = NoSuchRouteException.class)
	public void testComputeDistanceWithException() {
		initRoute("AED");
		computer.compute(route);
	}

	@Test
	public void testACShortestDistanceCompute() {
		initRoute("AC");
		Integer distance = computer.compute(route, TripRule.SHORTEST_DISTANCE);
		assertThat(distance, is(9));
	}

	@Test
	public void testBBShortestDistanceCompute() {
		initRoute("BB");
		Integer distance = computer.compute(route, TripRule.SHORTEST_DISTANCE);
		assertThat(distance, is(9));
	}

	private void initRoute(String stops) {
		List<Town> towns = new ArrayList<>();
		for (int i = 0; i < stops.length(); i++) {
			towns.add(Town.Builder.builder()
					.withId(stops.substring(i, i + 1))
					.build());
		}
		route = Route.Builder.builder().withTowns(towns).build();
	}
}
