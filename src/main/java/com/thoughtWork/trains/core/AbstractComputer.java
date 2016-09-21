package com.thoughtWork.trains.core;

import com.thoughtWork.trains.domain.TripNode;
import com.thoughtWork.trains.exception.NoSuchRouteException;
import com.thoughtWork.trains.validation.IValidator;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;

import static com.thoughtWork.trains.util.RouteNodesUtil.initialize;

/**
 * Created by Administrator on 2016/9/10 0010.
 */
public abstract class AbstractComputer<U, T, R> implements IComputer<U, T, R> {
	protected List<TripNode> tripNodes;

	@Autowired
	protected IValidator<List<TripNode>> validator;//Not initialized at the beginning

	public AbstractComputer() {
		if (tripNodes == null || tripNodes.isEmpty()) {
			prepareRoutesData("AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7");
		}
	}

	@Override
	public U compute(T t, R r) {
		throw new NoSuchRouteException();
	}

	@Override
	public U compute(T t) {
		return compute(t, null);
	}


	@Override
	public void prepareRoutesData(String graph) {
		List<TripNode> tripNodes = initialize(graph);

		if (validator != null) {
			validator.validate(tripNodes);
		}

		this.tripNodes = Collections.synchronizedList(tripNodes);
	}
}
