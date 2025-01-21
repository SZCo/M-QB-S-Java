package com.quarterback.career;

import java.util.ArrayList;
import java.util.List;

public class Season {
    private List<Team> teams;
    private List<GameResult> gameResults;
    private int currentWeek;
    private Player player;

    public Season(Player player) {
        this.teams = new ArrayList<>();
        this.gameResults = new ArrayList<>();
        this.currentWeek = 0;
        this.player = player;
    }

    public void addTeam(Team team) {
        teams.add(team);
    }

    public Team getNextOpponent(Team playerTeam) {
        for (Team team : teams) {
            if (!team.equals(playerTeam) && currentWeek % teams.size() == teams.indexOf(team)) {
                return team;
            }
        }
        return null;
    }

    public GameResult simulateNextWeek(Standings standings) {
        if (currentWeek >= teams.size() - 1) {
            return null; // Season complete
        }

        Team team1 = teams.get(currentWeek);
        Team team2 = teams.get((currentWeek + 1) % teams.size());
        GameResult result = GameSimulation.simulateGame(team1, team2, standings);
        gameResults.add(result);
        currentWeek++;
        return result;
    }

    public GameResult getPlayerGameResult(Team playerTeam) {
        for (GameResult result : gameResults) {
            if (result.getTeam1().equals(playerTeam) || result.getTeam2().equals(playerTeam)) {
                return result;
            }
        }
        return null;
    }

    public boolean isSeasonComplete() {
        return currentWeek >= teams.size() - 1;
    }

    public List<GameResult> getGameResults() {
        return gameResults;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Season Results:\n");
        for (GameResult result : gameResults) {
            sb.append(result).append("\n");
        }
        return sb.toString();
    }
}
