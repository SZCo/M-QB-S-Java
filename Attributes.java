package com.quarterback.career;

import java.util.HashMap;
import java.util.Map;

public class Attributes {
    public enum AttributeType {
        THROW_POWER, THROW_ACCURACY, SPEED, AGILITY, AWARENESS,
        STRENGTH, STAMINA, TOUGHNESS, INTELLIGENCE, LEADERSHIP
    }

    public enum AttributeRating {
        F, D, C, B, A, S
    }

    private Map<AttributeType, AttributeRating> attributes;

    public Attributes() {
        attributes = new HashMap<>();
        for (AttributeType type : AttributeType.values()) {
            attributes.put(type, AttributeRating.F); // Initializing with lowest rating
        }
    }

    public void setAttribute(AttributeType type, AttributeRating rating) {
        attributes.put(type, rating);
    }

    public AttributeRating getAttribute(AttributeType type) {
        return attributes.get(type);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (AttributeType type : AttributeType.values()) {
            sb.append(type).append(": ").append(attributes.get(type)).append("\n");
        }
        return sb.toString();
    }
}
