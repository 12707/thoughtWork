package com.thoughtWork.trains.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * A route that includes the towns it passes through, i.e. a route A-B-C, 'A', 'B' and 'C' will be stored in a list.
 */
public class Route {
    private List<Town> towns;

    public List<Town> getTowns() {
        return towns;
    }

    private Route(List<Town> towns) {
        this.towns = Collections.unmodifiableList(towns);
    }

    public static final class Builder {
        private List<Town> towns;
        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder withTowns(List<Town> towns) {
            this.towns = towns;
            return this;
        }

        public Route build() {
            return new Route(this.towns);
        }
    }
}
