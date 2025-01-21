package com.quarterback.career;

import java.util.ArrayList;
import java.util.List;

public class MilestoneEvents {
    private List<String> events;

    public MilestoneEvents() {
        this.events = new ArrayList<>();
    }

    public void addEvent(String event) {
        events.add(event);
    }

    public List<String> getEvents() {
        return events;
    }

    public void simulateSigningDay(Player player, List<Team> collegeTeams) {
        StringBuilder sb = new StringBuilder();
        sb.append("Signing Day for ").append(player.getName()).append(":\n");
        boolean offerReceived = false;

        for (Team team : collegeTeams) {
            if (team.getPrestige() >= 'C' && player.getStarRating() >= 3) {
                sb.append("Offer from ").append(team.getName()).append("\n");
                offerReceived = true;
            }
        }

        if (!offerReceived) {
            sb.append("No offers received. Career ends.\n");
        }

        addEvent(sb.toString());
    }

    public void simulateDraftDay(Player player, List<Team> nflTeams) {
        StringBuilder sb = new StringBuilder();
        sb.append("Draft Day for ").append(player.getName()).append(":\n");
        boolean drafted = false;

        for (Team team : nflTeams) {
            if (player.getStarRating() >= 4) {
                sb.append(player.getName()).append(" drafted by ").append(team.getName()).append("\n");
                drafted = true;
                break;
            }
        }

        if (!drafted) {
            sb.append(player.getName()).append(" went undrafted.\n");
        }

        addEvent(sb.toString());
    }

    public void simulateAwardCeremony(Player player) {
        StringBuilder sb = new StringBuilder();
        sb.append("Award Ceremony for ").append(player.getName()).append(":\n");

        if (player.getStarRating() == 5) {
            sb.append(player.getName()).append(" wins the Heisman Trophy!\n");
        }

        addEvent(sb.toString());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Milestone Events:\n");
        for (String event : events) {
            sb.append(event).append("\n");
        }
        return sb.toString();
    }
}

