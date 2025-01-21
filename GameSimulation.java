package com.quarterback.career;

import java.util.Random;

public class GameSimulation {
    public static GameResult simulateGame(Team team1, Team team2, Standings standings) {
        Random rand = new Random();
        int team1Advantage = getTeamAdvantage(team1);
        int team2Advantage = getTeamAdvantage(team2);

        int team1Score = Math.max(0, rand.nextInt(31) + team1Advantage); // Ensure realistic scores
        int team2Score = Math.max(0, rand.nextInt(31) + team2Advantage); // Ensure realistic scores

        if (team1Score > team2Score) {
            standings.recordGameResult(team1, team2);
        } else if (team2Score > team1Score) {
            standings.recordGameResult(team2, team1);
        } else {
            // Handle tie
            standings.recordGameResult(null, null);
        }

        return new GameResult(team1, team2, team1Score, team2Score);
    }

    private static int getTeamAdvantage(Team team) {
        // Simple advantage calculation based on prestige and coach rating
        int prestigeAdvantage = (team.getPrestige() - 'F') * 3;
        int coachAdvantage = (team.getHeadCoach().getRating() - 'F') * 2;
        return prestigeAdvantage + coachAdvantage;
    }
}
