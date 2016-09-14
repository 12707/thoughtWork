package com.thoughtWork.trains.core.impl;

import com.thoughtWork.trains.core.IComputer;
import com.thoughtWork.trains.domain.RouteNode;
import com.thoughtWork.trains.domain.Town;
import com.thoughtWork.trains.domain.Trip;
import com.thoughtWork.trains.exception.NoSuchRouteException;
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
 * Created by Nicolas_Wu on 9/14/2016.
 */
@RunWith(JUnit4.class)
public class TripsComputerTest {
    private IComputer<Integer, Trip, Rule> computer = new TripsComputer();

    private List<RouteNode> routeNodes = new ArrayList<>();

    private String graph = "AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7";

    @Before
    public void init() {
        initRouteNodes(graph);
        computer.prepareRoutesData(routeNodes);
    }

    @Test
    public void testCTripCompute() throws NoSuchRouteException {
        Trip trip = new Trip();
        trip.setStart(Town.buildTown("C"));
        trip.setEnd(Town.buildTown("C"));
        int result = computer.compute(trip, Rule.MAXIMUM_STOPS_NUMBER);

        assertThat(result, is(2));

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
