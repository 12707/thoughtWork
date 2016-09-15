package com.thoughtWork.trains.core;

import com.thoughtWork.trains.domain.RouteNode;
import com.thoughtWork.trains.exception.NoSuchRouteException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/10 0010.
 */
public abstract class AbstractComputer<U, T, R> implements IComputer<U, T, R> {
	protected List<RouteNode> routeNodes;

	@Override
	public void prepareRoutesData(List<RouteNode> routeNodes) {
		if (routeNodes != null) {
			this.routeNodes = new ArrayList();
			routeNodes.stream().forEach(routeNode -> this.routeNodes.add(routeNode));
		}
	}

	@Override
	public U compute(T t, R r) throws NoSuchRouteException {
		throw new NoSuchRouteException();
	}

	@Override
	public U compute(T t) throws NoSuchRouteException {
		return compute(t, null);
	}
}
