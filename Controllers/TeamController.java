package Controllers;

import Helpers.JDBCHelper;

public class TeamController {
    // Constants
    public static final int POINTS_FOR_WIN = 3;
    public static final int POINTS_FOR_DRAW = 1;
    public static final int POINTS_FOR_LOSS = 0;
    
   // Win, draw, loss
    public static void win(String name, int thisScore, int thisMissed) {
        addGames(name);
        addWins(name);
        addScored(name, thisScore);
        addMissed(name, thisMissed);
        addPoints(name, POINTS_FOR_WIN);
    }
    public static void draw(String name, int thisScore, int thisMissed) {
        addGames(name);
        addDraws(name);
        addScored(name, thisScore);
        addMissed(name, thisMissed);
        addPoints(name, POINTS_FOR_DRAW);
    }
    public static void loss(String name, int thisScore, int thisMissed) {
        addGames(name);
        addLosses(name);
        addScored(name, thisScore);
        addMissed(name, thisMissed);
        addPoints(name, POINTS_FOR_LOSS);
    }

    // Adds value to field by name of team
    private static void addGames(String name) {
        String query = "UPDATE team SET games = games + 1 WHERE name = '" + name + "'";
        JDBCHelper.executeUpdate(query);
    }
    private static void addWins(String name) {
        String query = "UPDATE team SET wins = wins + 1 WHERE name = '" + name + "'";
        JDBCHelper.executeUpdate(query);
    }
    private static void addDraws(String name) {
        String query = "UPDATE team SET draws = draws + 1 WHERE name = '" + name + "'";
        JDBCHelper.executeUpdate(query);
    }
    private static void addLosses(String name) {
        String query = "UPDATE team SET losses = losses + 1 WHERE name = '" + name + "'";
        JDBCHelper.executeUpdate(query);
    }
    
    private static void addScored(String name, int thisScore) {
        String query = "UPDATE team SET scored = scored + " + thisScore + " WHERE name = '" + name + "'";
        JDBCHelper.executeUpdate(query);
    }
    private static void addMissed(String name, int thisMissed) {
        String query = "UPDATE team SET missed = missed + " + thisMissed + " WHERE name = '" + name + "'";
        JDBCHelper.executeUpdate(query);
    }
    private static void addPoints(String name, int points) {
        String query = "UPDATE team SET points = points + " + points + " WHERE name = '" + name + "'";
        JDBCHelper.executeUpdate(query);
    }
    
}