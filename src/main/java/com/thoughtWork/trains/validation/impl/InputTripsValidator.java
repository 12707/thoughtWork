package com.thoughtWork.trains.validation.impl;

import com.thoughtWork.trains.domain.TripNode;
import com.thoughtWork.trains.exception.SameStartAndEndPointException;
import com.thoughtWork.trains.exception.ViolateUniqueRouteException;
import com.thoughtWork.trains.validation.IValidator;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Administrator on 2016/9/8 0008.
 */
@Component
public class InputTripsValidator implements IValidator<List<TripNode>> {
    public void validate(List<TripNode> tripNodes) {
        if (tripNodes != null && tripNodes.size() > 0) {
            if (tripNodes.stream().map(tripNode -> tripNode.getTrip()).distinct().count() != tripNodes.size()) {
                throw new ViolateUniqueRouteException();
            }

            tripNodes.stream().forEach(tripNode -> {
                if (tripNode.getTrip().getStart().equals(tripNode.getTrip().getEnd())) {
                    throw new SameStartAndEndPointException();
                }
            });
        }
    }
}
