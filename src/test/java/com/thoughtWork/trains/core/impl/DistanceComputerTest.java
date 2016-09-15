package com.thoughtWork.trains.core.impl;

import com.thoughtWork.trains.core.IComputer;
import com.thoughtWork.trains.domain.Route;
import com.thoughtWork.trains.domain.RouteNode;
import com.thoughtWork.trains.domain.Town;
import com.thoughtWork.trains.exception.NoSuchRouteException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;

import static com.thoughtWork.trains.core.impl.RouteNodesUtil.initialize;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Administrator on 2016/9/10 0010.
 */

@RunWith(JUnit4.class)
public class DistanceComputerTest {
	private Route route = new Route();

	private List<RouteNode> routeNodes = new ArrayList<>();

	private IComputer<Integer, Route, Rule> computer = new DistanceComputer();

	private String graph = "AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7";

	@Before
	public void init() {
		routeNodes = initialize(graph);
		computer.prepareRoutesData(routeNodes);
	}

	@Test
	public void testComputeDistanceWith2Nodes() throws NoSuchRouteException {
		initRoute("AD");
		Integer distance = computer.compute(route);
		assertThat(distance, is(5));
	}

	@Test
	public void testComputeDistanceWith3Nodes() throws NoSuchRouteException {
		initRoute("ABC");
		Integer distance = computer.compute(route);
		assertThat(distance, is(9));
	}

	@Test
	public void testComputeDistanceWith5Nodes() throws NoSuchRouteException {
		initRoute("AEBCD");
		Integer distance = computer.compute(route);
		assertThat(distance, is(22));
	}

	@Test(expected = NoSuchRouteException.class)
	public void testComputeDistanceWithException() throws NoSuchRouteException {
		initRoute("AED");
		computer.compute(route);
	}

	private void initRoute(String stops) {
		List<Town> towns = new ArrayList<>();
		for (int i = 0; i < stops.length(); i++) {
			Town town = new Town();
			town.setId(stops.substring(i, i + 1));
			towns.add(town);
		}
		route.setTowns(towns);
	}
}
