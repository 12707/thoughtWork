package com.thoughtWork.trains.domain;

/**
 * A particular trip node contains a trip and another useful information, such as 'distance' between start and end in a trip.
 */
public class TripNode {
    private Trip trip;

    private int distance;

    public Trip getTrip() {
        return trip;
    }

    public int getDistance() {
        return distance;
    }

    private TripNode(Trip trip, int distance) {
        this.trip = Trip.Builder.builder()
                .withStart(trip.getStart())
                .withEnd(trip.getEnd())
                .build();
        this.distance = distance;
    }

    public static final class Builder {
        private Trip trip;
        private int distance;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder withTrip(Trip trip) {
            this.trip = trip;
            return this;
        }

        public Builder withDistance(int distance) {
            this.distance = distance;
            return this;
        }

        public TripNode build() {
            return new TripNode(trip, distance);
        }
    }
}
