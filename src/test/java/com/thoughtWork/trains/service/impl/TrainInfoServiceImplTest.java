package com.thoughtWork.trains.service.impl;

import com.thoughtWork.trains.core.IComputer;
import com.thoughtWork.trains.core.TripRule;
import com.thoughtWork.trains.core.impl.DistanceComputer;
import com.thoughtWork.trains.core.impl.TripsComputer;
import com.thoughtWork.trains.domain.Route;
import com.thoughtWork.trains.domain.Trip;
import com.thoughtWork.trains.domain.TripNode;
import com.thoughtWork.trains.service.ITrainInfoService;
import com.thoughtWork.trains.validation.IValidator;
import com.thoughtWork.trains.validation.impl.InputTripsValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.springframework.util.Assert.notNull;

/**
 * Created by Administrator on 2016/9/19 0019.
 */
@RunWith(SpringRunner.class)
public class TrainInfoServiceImplTest {
    @Autowired
    private ITrainInfoService trainInfoService;

    @Test
    public void testCalcRouteACShortestDistance() {
        String routeDistanceResponse = trainInfoService.calcRouteDistance("A-C", true);
        notNull(routeDistanceResponse);
        assertThat(routeDistanceResponse, is("9"));
    }

    @Test
    public void testCalcRouteBBShortestDistance() {
        String routeDistanceResponse = trainInfoService.calcRouteDistance("B-B", true);
        notNull(routeDistanceResponse);
        assertThat(routeDistanceResponse, is("9"));
    }

    @Test
    public void testCalcRouteDistance() {
        String routeDistanceResponse = trainInfoService.calcRouteDistance("A-E-B-C-D", false);
        notNull(routeDistanceResponse);
        assertThat(routeDistanceResponse, is("22"));
    }

    @Test
    public void testCalcCCTripNumberNonDistanceRestriction() {
        String response = trainInfoService.calcTripNumber("CC", "maximum", 3);
        notNull(response);
        assertThat(response, is("2"));
    }

    @Test
    public void testCalcACTripNumberNonDistanceRestriction() {
        String response = trainInfoService.calcTripNumber("AC", "EXACT", 4);
        notNull(response);
        assertThat(response, is("3"));
    }

    @Test
    public void testCalcCCTripNumberWithDistanceRestriction() {
        String response = trainInfoService.calcTripNumber("CC", "LESS", 30);
        notNull(response);
        assertThat(response, is("7"));
    }

    @TestConfiguration
    static class Config {
        @Bean
        public IComputer<Integer, Route, TripRule> getDistanceComputer() {
            return new DistanceComputer();
        }

        @Bean
        public IComputer<Integer, Trip, TripRule> getTripComputer() {
            return new TripsComputer();
        }

        @Bean
        public ITrainInfoService trainInfoServiceBuilder() {
            return new TrainInfoServiceImpl();
        }

        @Bean
        public IValidator<List<TripNode>> getValidator() {
            return new InputTripsValidator();
        }

    }
}
