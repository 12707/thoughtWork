package com.thoughtWork.trains.core.impl;

import com.thoughtWork.trains.core.IComputer;
import com.thoughtWork.trains.core.TripRule;
import com.thoughtWork.trains.domain.Town;
import com.thoughtWork.trains.domain.Trip;
import com.thoughtWork.trains.domain.TripNode;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;

import static com.thoughtWork.trains.RouteNodesTestUtil.initialize;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Nicolas_Wu on 9/14/2016.
 */
@RunWith(JUnit4.class)
public class TripsComputerTest {
	private IComputer<Integer, Trip, TripRule> computer = new TripsComputer();

	private List<TripNode> tripNodes = new ArrayList<>();

	private String graph = "AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7";

	@Before
	public void init() {
		tripNodes = initialize(graph);
		computer.prepareRoutesData(tripNodes);
	}

	@Test
	public void testCCTripCompute() {
		int result = computer.compute(Trip.Builder.builder()
				.withStart(Town.Builder.builder().withId("C").build())
				.withEnd(Town.Builder.builder().withId("C").build())
				.build(), TripRule.C_C_MAXIMUM_STOPS_NUMBER);

		assertThat(result, is(2));

	}

	@Test
	public void testACTripCompute() {
		int result = computer.compute(Trip.Builder.builder()
				.withStart(Town.Builder.builder().withId("A").build())
				.withEnd(Town.Builder.builder().withId("C").build())
				.build(), TripRule.A_C_EXACT_STOPS_NUMBER);

		assertThat(result, is(3));
	}

	@Test
	public void testCCTripLimitedDistanceIn30Compute() {
		int result = computer.compute(Trip.Builder.builder()
				.withStart(Town.Builder.builder().withId("C").build())
				.withEnd(Town.Builder.builder().withId("C").build())
				.build(), TripRule.C_C_DISTANCE_LIMIT);

		assertThat(result, is(7));
	}
}
