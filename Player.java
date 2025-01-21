package com.quarterback.career;

import java.util.Random;

public class Player {
    private String name;
    private int jerseyNumber;
    private int height; // in inches
    private int weight; // in pounds
    private Traits traits;
    private Attributes attributes;
    private int starRating; // For college recruitment

    // Stats tracking
    private int passingYards;
    private int passingTouchdowns;
    private int interceptions;
    private int rushingYards;
    private int rushingTouchdowns;

    // Year tracking
    private int year; // Freshman, Sophomore, Junior, Senior

    public Player(String name, int jerseyNumber) {
        this.name = name;
        this.jerseyNumber = jerseyNumber;
        this.height = generateRandomHeight();
        this.weight = generateRandomWeight();
        this.traits = new Traits();
        this.attributes = new Attributes();
        this.starRating = 0; // Initial star rating

        // Initialize stats
        this.passingYards = 0;
        this.passingTouchdowns = 0;
        this.interceptions = 0;
        this.rushingYards = 0;
        this.rushingTouchdowns = 0;

        // Initialize year
        this.year = 1; // Freshman
    }

    private int generateRandomHeight() {
        Random rand = new Random();
        return 68 + rand.nextInt(9); // Random height between 5'8" (68 inches) and 6'4" (76 inches)
    }

    private int generateRandomWeight() {
        Random rand = new Random();
        return 150 + rand.nextInt(51); // Random weight between 150 and 200 pounds
    }

    public void addPassingStats(int yards, int touchdowns, int interceptions) {
        this.passingYards += yards;
        this.passingTouchdowns += touchdowns;
        this.interceptions += interceptions;
    }

    public void addRushingStats(int yards, int touchdowns) {
        this.rushingYards += yards;
        this.rushingTouchdowns += touchdowns;
    }

    public void advanceYear() {
        this.year++;
    }

    public String getName() {
        return name;
    }

    public int getJerseyNumber() {
        return jerseyNumber;
    }

    public int getHeight() {
        return height;
    }

    public int getWeight() {
        return weight;
    }

    public Traits getTraits() {
        return traits;
    }

    public Attributes getAttributes() {
        return attributes;
    }

    public int getStarRating() {
        return starRating;
    }

    public void setStarRating(int starRating) {
        this.starRating = starRating;
    }

    public int getYear() {
        return year;
    }

    public void updateAttributesBasedOnPerformance(int performanceScore) {
        for (Attributes.AttributeType type : Attributes.AttributeType.values()) {
            Attributes.AttributeRating currentRating = attributes.getAttribute(type);
            Attributes.AttributeRating newRating = currentRating;

            if (performanceScore > 80) {
                newRating = improveRating(currentRating);
            } else if (performanceScore < 50) {
                newRating = degradeRating(currentRating);
            }

            attributes.setAttribute(type, newRating);
        }
    }

    private Attributes.AttributeRating improveRating(Attributes.AttributeRating rating) {
        switch (rating) {
            case F: return Attributes.AttributeRating.D;
            case D: return Attributes.AttributeRating.C;
            case C: return Attributes.AttributeRating.B;
            case B: return Attributes.AttributeRating.A;
            case A: return Attributes.AttributeRating.S;
            default: return rating;
        }
    }

    private Attributes.AttributeRating degradeRating(Attributes.AttributeRating rating) {
        switch (rating) {
            case S: return Attributes.AttributeRating.A;
            case A: return Attributes.AttributeRating.B;
            case B: return Attributes.AttributeRating.C;
            case C: return Attributes.AttributeRating.D;
            case D: return Attributes.AttributeRating.F;
            default: return rating;
        }
    }

    @Override
    public String toString() {
        return "Player: " + name + "\nJersey Number: " + jerseyNumber + "\nHeight: " + height + " inches\nWeight: " + weight + " pounds\n" +
                "Traits:\n" + traits + "\nAttributes:\n" + attributes + "\nStar Rating: " + starRating + "\nYear: " + year + "\n" +
                "Stats:\nPassing Yards: " + passingYards + "\nPassing Touchdowns: " + passingTouchdowns +
                "\nInterceptions: " + interceptions + "\nRushing Yards: " + rushingYards +
                "\nRushing Touchdowns: " + rushingTouchdowns;
    }
}
