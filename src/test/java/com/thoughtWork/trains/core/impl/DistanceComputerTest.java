package com.thoughtWork.trains.core.impl;

import com.thoughtWork.trains.core.IComputer;
import com.thoughtWork.trains.domain.Route;
import com.thoughtWork.trains.domain.RouteNode;
import com.thoughtWork.trains.domain.Town;
import com.thoughtWork.trains.domain.Trip;
import com.thoughtWork.trains.exception.NoSuchRouteException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

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
		initRouteNodes(graph);
		computer.prepareRoutesData(routeNodes);
	}

	@Test
	public void testComputeDistanceWith2Nodes() throws NoSuchRouteException {
		initRoute("AD");
		Integer distance = computer.compute(route, null);
		assertThat(distance, is(5));
	}

	@Test
	public void testComputeDistanceWith3Nodes() throws NoSuchRouteException {
		initRoute("ABC");
		Integer distance = computer.compute(route, null);
		assertThat(distance, is(9));
	}

	@Test
	public void testComputeDistanceWith5Nodes() throws NoSuchRouteException {
		initRoute("AEBCD");
		Integer distance = computer.compute(route, null);
		assertThat(distance, is(22));
	}

	@Test(expected = NoSuchRouteException.class)
	public void testComputeDistanceWithException() throws NoSuchRouteException {
		initRoute("AED");
		computer.compute(route, null);
	}

	private void initRoute(String stops) {
		List<Town> towns = new ArrayList<>();
		for (int i = 0; i < stops.length(); i++) {
			Town town = new Town();
			town.setId(stops.substring(i,i+1));
			towns.add(town);
		}
		route.setTowns(towns);
	}

	private void initRouteNodes(String nodes) {
		StringTokenizer stringTokenizer = new StringTokenizer(nodes, ",");
		while (stringTokenizer.hasMoreTokens()) {
			String node = stringTokenizer.nextToken();

			Town town_start = new Town();
			town_start.setId(node.trim().substring(0, 1));
			Town town_end = new Town();
			town_end.setId(node.trim().substring(1, 2));

			Trip trip = new Trip();
			trip.setStart(town_start);
			trip.setEnd(town_end);

			RouteNode routeNode = new RouteNode();
			routeNode.setTrip(trip);
			routeNode.setDistance(Integer.valueOf(node.trim().substring(2, 3)));

			routeNodes.add(routeNode);
		}
	}
}
