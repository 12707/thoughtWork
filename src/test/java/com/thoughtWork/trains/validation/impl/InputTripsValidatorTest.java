package com.thoughtWork.trains.validation.impl;

import com.thoughtWork.trains.domain.TripNode;
import com.thoughtWork.trains.exception.SameStartAndEndPointException;
import com.thoughtWork.trains.exception.ViolateUniqueRouteException;
import com.thoughtWork.trains.validation.IValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static com.thoughtWork.trains.RouteNodesTestUtil.initialize;

/**
 * Created by Administrator on 2016/9/18 0018.
 */
@RunWith(JUnit4.class)
public class InputTripsValidatorTest {
    private IValidator<List<TripNode>> validator = new InputTripsValidator();

    @Test(expected = ViolateUniqueRouteException.class)
    public void testDuplicatedRoutes() {
        String graph = "AB5, AB4, CD8, DC8, DE6, AD5, CE2, EB3, AE7";
        List<TripNode> tripNodes = initialize(graph);
        validator.validate(tripNodes);
    }

    @Test(expected = SameStartAndEndPointException.class)
    public void testSameStartAndEndPoint() {
        String graph = "AB5, AA4, CD8, DC8, DE6, AD5, CE2, EB3, AE7";
        List<TripNode> tripNodes = initialize(graph);
        validator.validate(tripNodes);
    }

    @Test
    public void testUniqueRoute() {
        String graph = "AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7";
        List<TripNode> tripNodes = initialize(graph);
        validator.validate(tripNodes);
    }
}
