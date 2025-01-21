package com.quarterback.career;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get user input for player's name and jersey number
        System.out.print("Enter player's name: ");
        String playerName = scanner.nextLine();

        System.out.print("Enter player's jersey number: ");
        int jerseyNumber = scanner.nextInt();
        scanner.nextLine(); // consume newline

        // Create the player
        Player player = new Player(playerName, jerseyNumber);

        // Get user input for player's traits
        selectPlayerTraits(scanner, player);

        // Initialize high school, college, and NFL teams
        List<Team> highSchoolTeams = initializeHighSchoolTeams();
        List<Team> collegeTeams = initializeCollegeTeams();
        List<Team> nflTeams = initializeNFLTeams();

        // Assign player to a high school team
        Team playerTeam = assignPlayerToTeam(player, highSchoolTeams);
        System.out.println("You have been assigned to: " + playerTeam.getName() + " in the " + playerTeam.getConference() + " conference.");

        // Simulate high school seasons
        for (int year = 1; year <= 4; year++) {
            player.advanceYear();
            System.out.println("Year: " + player.getYear());
            System.out.println("The season for your " + getYearName(player.getYear()) + " year is starting!");

            // Create and simulate high school season week by week
            Season highSchoolSeason = new Season(player);
            Standings highSchoolStandings = new Standings();
            for (Team team : highSchoolTeams) {
                highSchoolSeason.addTeam(team);
            }

            boolean seasonComplete = false;
            while (!seasonComplete) {
                System.out.println("Menu:");
                System.out.println("1. Simulate Next Week");
                System.out.println("2. View Standings");
                System.out.println("3. View Player Stats");
                System.out.println("4. View Season Results");
                System.out.println("5. Quit");

                System.out.print("Enter choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // consume newline

                switch (choice) {
                    case 1:
                        Team opponent = highSchoolSeason.getNextOpponent(playerTeam);
                        if (opponent != null) {
                            System.out.println("Your opponent for this week is: " + opponent.getName());
                        } else {
                            System.out.println("No opponent found for this week.");
                        }

                        GameResult result = highSchoolSeason.simulateNextWeek(highSchoolStandings);
                        GameResult playerGameResult = highSchoolSeason.getPlayerGameResult(playerTeam);
                        if (playerGameResult != null) {
                            System.out.println("Game result: " + playerGameResult);
                        }

                        if (highSchoolSeason.isSeasonComplete()) {
                            seasonComplete = true;
                            System.out.println("Season Complete!");
                        }
                        break;
                    case 2:
                        highSchoolStandings.displayStandings();
                        break;
                    case 3:
                        System.out.println(player);
                        break;
                    case 4:
                        System.out.println(highSchoolSeason);
                        break;
                    case 5:
                        seasonComplete = true;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }

            // Determine player's star rating based on performance
            determinePlayerStarRating(player, highSchoolStandings, playerTeam);
        }

        // Initialize milestone events and simulate signing day
        MilestoneEvents milestoneEvents = new MilestoneEvents();
        milestoneEvents.simulateSigningDay(player, collegeTeams);
        System.out.println(milestoneEvents);

        // Simulate college season if player gets signed
        if (player.getStarRating() > 0) {
            Team collegeTeam = assignPlayerToTeam(player, collegeTeams);
            Season collegeSeason = new Season(player);
            Standings collegeStandings = new Standings();
            for (Team team : collegeTeams) {
                collegeSeason.addTeam(team);
            }

            boolean collegeSeasonComplete = false;
            while (!collegeSeasonComplete) {
                System.out.println("Menu:");
                System.out.println("1. Simulate Next Week");
                System.out.println("2. View Standings");
                System.out.println("3. View Player Stats");
                System.out.println("4. View Season Results");
                System.out.println("5. Quit");

                System.out.print("Enter choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // consume newline

                switch (choice) {
                    case 1:
                        Team opponent = collegeSeason.getNextOpponent(playerTeam);
                        if (opponent != null) {
                            System.out.println("Your opponent for this week is: " + opponent.getName());
                        } else {
                            System.out.println("No opponent found for this week.");
                        }

                        GameResult result = collegeSeason.simulateNextWeek(collegeStandings);
                        GameResult playerGameResult = collegeSeason.getPlayerGameResult(playerTeam);
                        if (playerGameResult != null) {
                            System.out.println("Game result: " + playerGameResult);
                        }

                        if (collegeSeason.isSeasonComplete()) {
                            collegeSeasonComplete = true;
                            System.out.println("Season Complete!");
                        }
                        break;
                    case 2:
                        collegeStandings.displayStandings();
                        break;
                    case 3:
                        System.out.println(player);
                        break;
                    case 4:
                        System.out.println(collegeSeason);
                        break;
                    case 5:
                        collegeSeasonComplete = true;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }

            // Simulate draft day
            milestoneEvents.simulateDraftDay(player, nflTeams);
            System.out.println(milestoneEvents);

            // Simulate award ceremony
            milestoneEvents.simulateAwardCeremony(player);
            System.out.println(milestoneEvents);
        }

        scanner.close();
    }

    private static void selectPlayerTraits(Scanner scanner, Player player) {
        // Define pools of traits
        List<String> goodMentalTraits = List.of("Clutch", "Leader", "Resilient", "Calm", "Focused",
                "Smart", "Quick Learner", "Strategic", "Adaptable",
                "Confident", "Hardworking", "Composed", "Disciplined",
                "Inspirational", "Tough");
        List<String> badMentalTraits = List.of("Nervous", "Inconsistent", "Reckless", "Overconfident",
                "Easily Distracted", "Impulsive", "Lazy", "Selfish",
                "Moody", "Stubborn");

        // Select 3 good mental traits
        System.out.println("Select 3 good mental traits:");
        for (int i = 0; i < goodMentalTraits.size(); i++) {
            System.out.println((i + 1) + ". " + goodMentalTraits.get(i));
        }
        for (int i = 0; i < 3; i++) {
            System.out.print("Enter choice " + (i + 1) + ": ");
            int choice = scanner.nextInt();
            player.getTraits().addTrait(Traits.TraitType.GOOD_MENTAL, goodMentalTraits.get(choice - 1), Traits.TraitMultiplier.GREAT);
        }

        // Select 2 bad mental traits
        System.out.println("Select 2 bad mental traits:");
        for (int i = 0; i < badMentalTraits.size(); i++) {
            System.out.println((i + 1) + ". " + badMentalTraits.get(i));
        }
        for (int i = 0; i < 2; i++) {
            System.out.print("Enter choice " + (i + 1) + ": ");
            int choice = scanner.nextInt();
            player.getTraits().addTrait(Traits.TraitType.BAD_MENTAL, badMentalTraits.get(choice - 1), Traits.TraitMultiplier.BASIC);
        }
        scanner.nextLine(); // consume newline
    }

    private static List<Team> initializeHighSchoolTeams() {
        List<Team> teams = new ArrayList<>();
        // Add 50 real-life high school teams (example: using Pioneer League teams and others)
        teams.add(new Team("Torrance High", "Pioneer League", 'B'));
        teams.add(new Team("West High", "Pioneer League", 'A'));
        teams.add(new Team("North High", "Pioneer League", 'C'));
        teams.add(new Team("South High", "Pioneer League", 'B'));
        teams.add(new Team("Mira Costa", "Pioneer League", 'A'));
        teams.add(new Team("Redondo Union", "Pioneer League", 'A'));
        teams.add(new Team("Palos Verdes", "Pioneer League", 'B'));
        teams.add(new Team("San Pedro", "Pioneer League", 'B'));
        teams.add(new Team("Carson", "Pioneer League", 'C'));
        teams.add(new Team("Narbonne", "Pioneer League", 'B'));

        // Bay League
        teams.add(new Team("El Segundo High", "Bay League", 'B'));
        teams.add(new Team("Lawndale High", "Bay League", 'A'));
        teams.add(new Team("Leuzinger High", "Bay League", 'B'));
        teams.add(new Team("Hawthorne High", "Bay League", 'C'));
        teams.add(new Team("Inglewood High", "Bay League", 'B'));
        teams.add(new Team("Santa Monica High", "Bay League", 'B'));
        teams.add(new Team("Beverly Hills High", "Bay League", 'B'));
        teams.add(new Team("Culver City High", "Bay League", 'A'));
        teams.add(new Team("Malibu High", "Bay League", 'C'));
        teams.add(new Team("Morningside High", "Bay League", 'C'));

        // Ocean League
        teams.add(new Team("El Segundo High", "Ocean League", 'B'));
        teams.add(new Team("Santa Monica High", "Ocean League", 'B'));
        teams.add(new Team("Beverly Hills High", "Ocean League", 'B'));
        teams.add(new Team("Culver City High", "Ocean League", 'A'));
        teams.add(new Team("Malibu High", "Ocean League", 'C'));
        teams.add(new Team("Morningside High", "Ocean League", 'C'));
        teams.add(new Team("Westchester High", "Ocean League", 'B'));
        teams.add(new Team("Hamilton High", "Ocean League", 'B'));
        teams.add(new Team("Hawthorne High", "Ocean League", 'C'));
        teams.add(new Team("Leuzinger High", "Ocean League", 'B'));

        // Del Rey League
        teams.add(new Team("St. Bernard High", "Del Rey League", 'B'));
        teams.add(new Team("Bishop Montgomery High", "Del Rey League", 'A'));
        teams.add(new Team("Serra High", "Del Rey League", 'A'));
        teams.add(new Team("Cathedral High", "Del Rey League", 'B'));
        teams.add(new Team("La Salle High", "Del Rey League", 'B'));
        teams.add(new Team("St. Paul High", "Del Rey League", 'B'));
        teams.add(new Team("Cantwell-Sacred Heart of Mary High", "Del Rey League", 'C'));
        teams.add(new Team("Verbum Dei High", "Del Rey League", 'C'));
        teams.add(new Team("Salesian High", "Del Rey League", 'B'));
        teams.add(new Team("St. Monica High", "Del Rey League", 'C'));

        // Mission League
        teams.add(new Team("Alemany High", "Mission League", 'A'));
        teams.add(new Team("Bishop Amat High", "Mission League", 'A'));
        teams.add(new Team("Chaminade High", "Mission League", 'A'));
        teams.add(new Team("Crespi High", "Mission League", 'B'));
        teams.add(new Team("Loyola High", "Mission League", 'B'));
        teams.add(new Team("Notre Dame High", "Mission League", 'B'));
        teams.add(new Team("St. Francis High", "Mission League", 'B'));
        teams.add(new Team("St. Paul High", "Mission League", 'B'));
        teams.add(new Team("Paraclete High", "Mission League", 'B'));
        teams.add(new Team("Cathedral High", "Mission League", 'B'));

        return teams;
    }

    private static List<Team> initializeCollegeTeams() {
        List<Team> teams = new ArrayList<>();
        // Add 10 real-life FBS teams (only a subset is shown here for brevity)
        teams.add(new Team("Alabama", "SEC", 'A'));
        teams.add(new Team("Clemson", "ACC", 'A'));
        teams.add(new Team("Ohio State", "Big Ten", 'A'));
        teams.add(new Team("Oklahoma", "Big 12", 'A'));
        teams.add(new Team("Oregon", "Pac-12", 'A'));
        teams.add(new Team("LSU", "SEC", 'A'));
        teams.add(new Team("Michigan", "Big Ten", 'A'));
        teams.add(new Team("Texas", "Big 12", 'A'));
        teams.add(new Team("Notre Dame", "Independent", 'A'));
        teams.add(new Team("Georgia", "SEC", 'A'));
        return teams;
    }

    private static List<Team> initializeNFLTeams() {
        List<Team> teams = new ArrayList<>();
        // Add 10 real-life NFL teams
        teams.add(new Team("Arizona Cardinals", "NFC", 'B'));
        teams.add(new Team("Atlanta Falcons", "NFC", 'B'));
        teams.add(new Team("Baltimore Ravens", "AFC", 'A'));
        teams.add(new Team("Buffalo Bills", "AFC", 'A'));
        teams.add(new Team("Carolina Panthers", "NFC", 'B'));
        teams.add(new Team("Chicago Bears", "NFC", 'B'));
        teams.add(new Team("Cincinnati Bengals", "AFC", 'B'));
        teams.add(new Team("Cleveland Browns", "AFC", 'B'));
        teams.add(new Team("Dallas Cowboys", "NFC", 'B'));
        teams.add(new Team("Denver Broncos", "AFC", 'B'));
        return teams;
    }

    private static Team assignPlayerToTeam(Player player, List<Team> teams) {
        // Assign player to a random team
        int randomIndex = (int) (Math.random() * teams.size());
        Team team = teams.get(randomIndex);
        team.addPlayer(player);
        return team;
    }

    private static void determinePlayerStarRating(Player player, Standings standings, Team playerTeam) {
        // Simple logic to determine player's star rating based on team's performance
        int wins = standings.getWins(playerTeam);
        if (wins >= 10) {
            player.setStarRating(5);
        } else if (wins >= 8) {
            player.setStarRating(4);
        } else if (wins >= 6) {
            player.setStarRating(3);
        } else if (wins >= 4) {
            player.setStarRating(2);
        } else if (wins >= 2) {
            player.setStarRating(1);
        } else {
            player.setStarRating(0);
        }
    }

    private static String getYearName(int year) {
        switch (year) {
            case 1:
                return "freshman";
            case 2:
                return "sophomore";
            case 3:
                return "junior";
            case 4:
                return "senior";
            default:
                return "unknown";
        }
    }
}
