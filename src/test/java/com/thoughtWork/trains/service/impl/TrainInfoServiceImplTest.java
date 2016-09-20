package com.thoughtWork.trains.service.impl;

import com.thoughtWork.trains.core.IComputer;
import com.thoughtWork.trains.core.TripRule;
import com.thoughtWork.trains.core.impl.DistanceComputer;
import com.thoughtWork.trains.core.impl.TripsComputer;
import com.thoughtWork.trains.domain.Route;
import com.thoughtWork.trains.domain.Trip;
import com.thoughtWork.trains.domain.TripNode;
import com.thoughtWork.trains.response.RouteDistanceResponse;
import com.thoughtWork.trains.service.ITrainInfoService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.List;

import static com.thoughtWork.trains.RouteNodesTestUtil.initialize;
import static org.hamcrest.core.Is.is;
import static org.springframework.util.Assert.notNull;

/**
 * Created by Administrator on 2016/9/19 0019.
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class TrainInfoServiceImplTest {
    @Autowired
    private ITrainInfoService trainInfoService;

    @Test
    public void testCalcRouteShortestDistance() {
        RouteDistanceResponse routeDistanceResponse = trainInfoService.calcRouteDistance("AC", true);
        notNull(routeDistanceResponse);
        Assert.assertThat(routeDistanceResponse.getDistance(), is(9));
    }

    @Configuration
    static class Config {
        public static String graph = "AB5, AB4, CD8, DC8, DE6, AD5, CE2, EB3, AE7";

        public static List<TripNode> tripNodes = initialize(graph);

        @Bean
        public ITrainInfoService trainInfoServiceBuilder() {
            return new TrainInfoServiceImpl();
        }

        @Bean
        public IComputer<Integer, Route, TripRule> getDistanceComputer() {
            IComputer<Integer, Route, TripRule> computer = new DistanceComputer();
            computer.prepareRoutesData(tripNodes);
            return computer;
        }

        @Bean
        public IComputer<Integer, Trip, TripRule> getTripComputer() {
            IComputer<Integer, Trip, TripRule> computer = new TripsComputer();
            computer.prepareRoutesData(tripNodes);
            return computer;
        }
    }
}
