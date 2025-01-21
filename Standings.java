package com.quarterback.career;

import java.util.HashMap;
import java.util.Map;

public class Standings {
    private Map<Team, Integer> teamWins;
    private Map<Team, Integer> teamLosses;

    public Standings() {
        this.teamWins = new HashMap<>();
        this.teamLosses = new HashMap<>();
    }

    public void recordGameResult(Team winner, Team loser) {
        if (winner != null && loser != null) {
            teamWins.put(winner, teamWins.getOrDefault(winner, 0) + 1);
            teamLosses.put(loser, teamLosses.getOrDefault(loser, 0) + 1);
        } else if (winner == null && loser == null) {
            // Handle tie if necessary
        }
    }

    public int getWins(Team team) {
        return teamWins.getOrDefault(team, 0);
    }

    public int getLosses(Team team) {
        return teamLosses.getOrDefault(team, 0);
    }

    public void displayStandings() {
        System.out.println("Standings:");
        for (Team team : teamWins.keySet()) {
            System.out.println(team.getName() + " - Wins: " + teamWins.get(team) + ", Losses: " + teamLosses.getOrDefault(team, 0));
        }
    }
}
