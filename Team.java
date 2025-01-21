package com.quarterback.career;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private String name;
    private String conference;
    private char prestige; // F to A grading scale
    private List<Player> roster;
    private Coach headCoach;

    public Team(String name, String conference, char prestige) {
        this.name = name;
        this.conference = conference;
        this.prestige = prestige;
        this.roster = new ArrayList<>();
        this.headCoach = new Coach(); // Assuming a default coach is assigned
    }

    public String getName() {
        return name;
    }

    public String getConference() {
        return conference;
    }

    public char getPrestige() {
        return prestige;
    }

    public List<Player> getRoster() {
        return roster;
    }

    public void addPlayer(Player player) {
        roster.add(player);
    }

    public Coach getHeadCoach() {
        return headCoach;
    }

    public void setHeadCoach(Coach headCoach) {
        this.headCoach = headCoach;
    }

    @Override
    public String toString() {
        return "Team: " + name + "\nConference: " + conference + "\nPrestige: " + prestige + "\nHead Coach: " + headCoach + "\nRoster: " + roster.size() + " players";
    }
}
