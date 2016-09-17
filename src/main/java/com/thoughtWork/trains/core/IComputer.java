package com.thoughtWork.trains.core;

import com.thoughtWork.trains.domain.TripNode;

import java.util.List;

/**
 * Created by Administrator on 2016/9/8 0008.
 */
public interface IComputer<U, T, R> {
	U compute(T t, R r);

	U compute(T t);

	void prepareRoutesData(List<TripNode> tripNodes);
}
