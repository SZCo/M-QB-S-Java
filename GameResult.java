package com.quarterback.career;

public class GameResult {
    private Team team1;
    private Team team2;
    private int team1Score;
    private int team2Score;

    public GameResult(Team team1, Team team2, int team1Score, int team2Score) {
        this.team1 = team1;
        this.team2 = team2;
        this.team1Score = team1Score;
        this.team2Score = team2Score;
    }

    public Team getTeam1() {
        return team1;
    }

    public Team getTeam2() {
        return team2;
    }

    public int getTeam1Score() {
        return team1Score;
    }

    public int getTeam2Score() {
        return team2Score;
    }

    @Override
    public String toString() {
        return team1.getName() + " " + team1Score + " - " + team2Score + " " + team2.getName();
    }
}
