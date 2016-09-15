package com.thoughtWork.trains.core;

import com.thoughtWork.trains.domain.RouteNode;
import com.thoughtWork.trains.exception.NoSuchRouteException;

import java.util.List;

/**
 * Created by Administrator on 2016/9/8 0008.
 */
public interface IComputer<U, T, R> {
	U compute(T t, R r) throws NoSuchRouteException;

	U compute(T t) throws NoSuchRouteException;

	void prepareRoutesData(List<RouteNode> routeNodes);
}
