package com.thoughtWork.trains.util;

import com.thoughtWork.trains.domain.Town;
import com.thoughtWork.trains.domain.Trip;
import com.thoughtWork.trains.domain.TripNode;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by Administrator on 2016/9/21 0021.
 */
public class RouteNodesUtil {
    public static List<TripNode> initialize(String nodes){
        List<TripNode> tripNodes = new ArrayList<>();
        StringTokenizer stringTokenizer = new StringTokenizer(nodes, ",");
        while (stringTokenizer.hasMoreTokens()) {
            String node = stringTokenizer.nextToken();
            tripNodes.add(TripNode.Builder.builder()
                    .withTrip(Trip.Builder.builder()
                            .withStart(Town.Builder.builder()
                                    .withId(node.trim().substring(0, 1))
                                    .build())
                            .withEnd(Town.Builder.builder()
                                    .withId(node.trim().substring(1, 2))
                                    .build())
                            .build())
                    .withDistance(Integer.valueOf(node.trim().substring(2, 3)))
                    .build());
        }
        return tripNodes;
    }
}
