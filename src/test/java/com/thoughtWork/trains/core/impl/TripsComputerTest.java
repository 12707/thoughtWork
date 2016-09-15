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

import static com.thoughtWork.trains.core.impl.RouteNodesUtil.initialize;
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
        routeNodes = initialize(graph);
        computer.prepareRoutesData(routeNodes);
    }

    @Test
    public void testCCTripCompute() throws NoSuchRouteException {
        Trip trip = new Trip();
        trip.setStart(Town.buildTown("C"));
        trip.setEnd(Town.buildTown("C"));
        int result = computer.compute(trip, Rule.C_C_MAXIMUM_STOPS_NUMBER);

        assertThat(result, is(2));

    }

    @Test
    public void testACTripCompute() throws NoSuchRouteException {
        Trip trip = new Trip();
        trip.setStart(Town.buildTown("A"));
        trip.setEnd(Town.buildTown("C"));
        int result = computer.compute(trip, Rule.A_C_EXACT_STOPS_NUMBER);

        assertThat(result, is(3));

    }
}
