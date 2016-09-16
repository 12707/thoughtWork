package com.thoughtWork.trains.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * A definition of a town, including id, name, description, etc.
 * It is better to customize equals and hashCode methods so that it can be used in comparison as expected.
 */
public class Town {
    private String id;

    public String getId() {
        return id;
    }

    private Town(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Town town = (Town) o;

        return new EqualsBuilder()
                .append(id, town.id)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "Town{" +
                "id='" + id + '\'' +
                '}';
    }

    public static final class Builder {
        private String id;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Town build() {
            return new Town(id);
        }
    }
}
