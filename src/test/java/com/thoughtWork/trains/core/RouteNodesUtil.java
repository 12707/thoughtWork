package com.thoughtWork.trains.core;

import com.thoughtWork.trains.domain.TripNode;
import com.thoughtWork.trains.domain.Town;
import com.thoughtWork.trains.domain.Trip;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by Administrator on 2016/9/14 0014.
 */
public class RouteNodesUtil {
	public static List<TripNode> initialize(String nodes){
		List<TripNode> tripNodes = new ArrayList<>();
		StringTokenizer stringTokenizer = new StringTokenizer(nodes, ",");
		while (stringTokenizer.hasMoreTokens()) {
			String node = stringTokenizer.nextToken();

			Town town_start = new Town();
			town_start.setId(node.trim().substring(0, 1));
			Town town_end = new Town();
			town_end.setId(node.trim().substring(1, 2));

			Trip trip = new Builder().createTrip();
			trip.setStart(town_start);
			trip.setEnd(town_end);

			TripNode tripNode = new TripNode();
			tripNode.setTrip(trip);
			tripNode.setDistance(Integer.valueOf(node.trim().substring(2, 3)));

			tripNodes.add(tripNode);
		}
		return tripNodes;
	}
}
