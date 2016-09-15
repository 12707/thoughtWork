package com.thoughtWork.trains.core.impl;

import com.thoughtWork.trains.domain.RouteNode;
import com.thoughtWork.trains.domain.Town;
import com.thoughtWork.trains.domain.Trip;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by Administrator on 2016/9/14 0014.
 */
public class RouteNodesUtil {
	public static List<RouteNode> initialize(String nodes){
		List<RouteNode> routeNodes = new ArrayList<>();
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
		return routeNodes;
	}
}
