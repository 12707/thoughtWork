package com.thoughtWork.trains.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * It contains start point and end point of a journey.
 */
public class Trip {
	private Town start;

	private Town end;

	private Trip(Town start, Town end) {
		this.start = ((start == null) ? start : Town.Builder.builder().withId(start.getId()).build());
		this.end = ((end == null) ? end : Town.Builder.builder().withId(end.getId()).build());
	}

	public Town getStart() {
		return start;
	}

	public Town getEnd() {
		return end;
	}

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

	@Override
	public String toString() {
		return "Trip{" +
				"start=" + start +
				", end=" + end +
				'}';
	}

	public static final class Builder {
		private Town start;
		private Town end;

		private Builder() {
		}

		public static Builder builder() {
			return new Builder();
		}

		public Builder withStart(Town start) {
			this.start = start;
			return this;
		}

		public Builder withEnd(Town end) {
			this.end = end;
			return this;
		}

		public Trip build() {
			return new Trip(start, end);
		}
	}
}
