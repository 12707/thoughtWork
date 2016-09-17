package com.thoughtWork.trains.core;

import com.thoughtWork.trains.domain.TripNode;
import com.thoughtWork.trains.exception.NoSuchRouteException;

import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on 2016/9/10 0010.
 */
public abstract class AbstractComputer<U, T, R> implements IComputer<U, T, R> {
	protected List<TripNode> tripNodes;

	@Override
	public void prepareRoutesData(List<TripNode> tripNodes) {
		this.tripNodes = Collections.unmodifiableList(tripNodes);
	}

	@Override
	public U compute(T t, R r) {
		throw new NoSuchRouteException();
	}

	@Override
	public U compute(T t) {
		return compute(t, null);
	}
}
