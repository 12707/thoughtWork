package com.thoughtWork.trains.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Created by Administrator on 2016/9/8 0008.
 */
public class Trip {
	private Town start;

	private Town end;

	public Town getStart() {
		return start;
	}

	public void setStart(Town start) {
		this.start = start;
	}

	public Town getEnd() {
		return end;
	}

	public void setEnd(Town end) {
		this.end = end;
	}

	public Trip(Town start, Town end){
		this.start = start;
		this.end = end;
	}

	public Trip(){}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;

		if (o == null || getClass() != o.getClass()) return false;

		Trip trip = (Trip) o;

		return new EqualsBuilder()
				.append(start, trip.start)
				.append(end, trip.end)
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37)
				.append(start)
				.append(end)
				.toHashCode();
	}
}
