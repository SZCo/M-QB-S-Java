package com.quarterback.career;

public class Coach {
    private String name;
    private char rating; // F to A grading scale

    public Coach() {
        this.name = "Default Coach";
        this.rating = 'C'; // Default rating
    }

    public Coach(String name, char rating) {
        this.name = name;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public char getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return name + " (Rating: " + rating + ")";
    }
}

