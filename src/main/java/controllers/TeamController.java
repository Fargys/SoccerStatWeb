package controllers;

import DAO.FactoryDAO;
import entity.Team;
import java.util.List;
import other.Score;
import static other.ConsoleHelper.getSelectedTeam;
import static other.ConsoleHelper.writeEmptyLine;
import static other.ConsoleHelper.writeMessage;

public class TeamController {
    
    public static final int POINTS_FOR_WIN = 3;
    public static final int POINTS_FOR_DRAW = 1;
    public static final int POINTS_FOR_LOSS = 0;
    
    public static final int DEFAULT_STATISTIC_VALUE = 0;
    public static final int ONE_VALUE = 1;
    
    public static void create(Long champ_id, String creatingTeamName){
        Team creatingTeam = new Team();
            
        creatingTeam.setChampionship_id(champ_id);
        creatingTeam.setName(creatingTeamName);
            
        FactoryDAO.getInstance().getTeamDAO().createTeam(creatingTeam);
    }
    
    public static void print(Team teamForPrinting){
        
        String name = fixLength(teamForPrinting.getName(), 16, true);
        String games = fixLength(String.valueOf(teamForPrinting.getGames()), 4, false);
        String wins = fixLength(String.valueOf(teamForPrinting.getWins()), 4, false);
        String draws = fixLength(String.valueOf(teamForPrinting.getDraws()), 4, false);
        String losses = fixLength(String.valueOf(teamForPrinting.getLosses()), 4, false);
        String scores = fixLength(String.valueOf(teamForPrinting.getScored()), 6, false);
        String separator = ":";
        String missed = fixLength(String.valueOf(teamForPrinting.getMissed()), 5, true);
        String points = fixLength(String.valueOf(teamForPrinting.getPoints()), 4, false);
        
        String teamStatistic = name + games + wins + draws + losses + scores + separator + missed + points;
        
        writeMessage(teamStatistic);
    }
    
    public static void update(Team teamForUpdating){
        FactoryDAO.getInstance().getTeamDAO().updateTeam(teamForUpdating);
    }
    
    public static void delete(Team teamForRemoving){
        FactoryDAO.getInstance().getTeamDAO().deleteTeam(teamForRemoving);
                
        writeEmptyLine();
        writeMessage("The team was removed");
    }
    
    public static void clear(Team teamForClearing) {
        Team teamAfterClearing = teamForClearing;
        
        teamAfterClearing.setGames(DEFAULT_STATISTIC_VALUE);
        teamAfterClearing.setWins(DEFAULT_STATISTIC_VALUE);
        teamAfterClearing.setDraws(DEFAULT_STATISTIC_VALUE);
        teamAfterClearing.setLosses(DEFAULT_STATISTIC_VALUE);
        teamAfterClearing.setScored(DEFAULT_STATISTIC_VALUE);
        teamAfterClearing.setMissed(DEFAULT_STATISTIC_VALUE);
        teamAfterClearing.setPoints(DEFAULT_STATISTIC_VALUE);
        
        update(teamAfterClearing);
    }
    
    
    public static Team select(Long champ_id){
        List<Team> allTeamsOfChampionship = getAllTeams(champ_id);
        
        Team selectedTeam = getSelectedTeam(allTeamsOfChampionship);
        
        return selectedTeam;
    }
    
    
    public static List getAllTeams(Long champ_id) {
        return FactoryDAO.getInstance().getTeamDAO().getAllTeams(champ_id);
    }
    
    public static List getTeamsNames(Long champ_id) {
        return FactoryDAO.getInstance().getTeamDAO().getTeamsNames(champ_id);
    }
    
    public static Long getNumberOfTeams(Long champ_id) {
        return FactoryDAO.getInstance().getTeamDAO().getNumberOfTeams(champ_id);
    }
    
    
    public static void updateStatisticsAfterGame(Team homeTeam, Team guestTeam, Score scoreOfGames) {
        
        int goalsOfHomeTeam = scoreOfGames.getGoalsOfHomeTeam();
        int goalsOfGuestTeam = scoreOfGames.getGoalsOfGuestTeam();
        
        homeTeam.setGames(homeTeam.getGames() + ONE_VALUE);
        guestTeam.setGames(guestTeam.getGames() + ONE_VALUE);
        
        switch(scoreOfGames.getWinnerOfGame()) {
            
            case HOME_TEAM:
                win(homeTeam, goalsOfHomeTeam, goalsOfGuestTeam);
                loss(guestTeam, goalsOfGuestTeam, goalsOfHomeTeam);
                break;
                
            case GUEST_TEAM:
                win(guestTeam, goalsOfGuestTeam, goalsOfHomeTeam);
                loss(homeTeam, goalsOfHomeTeam, goalsOfGuestTeam);
                break;
                
            case NO_ONE:
                draw(homeTeam, goalsOfHomeTeam, goalsOfGuestTeam);
                draw(guestTeam, goalsOfGuestTeam, goalsOfHomeTeam);
                break;
                
            default:
                throw new IllegalArgumentException();
        }
        
        update(homeTeam);
        update(guestTeam);
    }
    
    private static void win(Team updatingTeam, int scored, int missed) {
        updatingTeam.setWins(updatingTeam.getWins() + ONE_VALUE);
        updatingTeam.setScored(updatingTeam.getScored() + scored);
        updatingTeam.setMissed(updatingTeam.getMissed() + missed);
        updatingTeam.setPoints(updatingTeam.getPoints() + POINTS_FOR_WIN);
        
    }
    
    private static void loss(Team updatingTeam, int scored, int missed) {
        updatingTeam.setLosses(updatingTeam.getLosses() + ONE_VALUE);
        updatingTeam.setScored(updatingTeam.getScored() + scored);
        updatingTeam.setMissed(updatingTeam.getMissed() + missed);
        updatingTeam.setPoints(updatingTeam.getPoints() + POINTS_FOR_LOSS);
    }
    
    private static void draw(Team updatingTeam, int scored, int missed) {
        updatingTeam.setDraws(updatingTeam.getDraws() + ONE_VALUE);
        updatingTeam.setScored(updatingTeam.getScored() + scored);
        updatingTeam.setMissed(updatingTeam.getMissed() + missed);
        updatingTeam.setPoints(updatingTeam.getPoints() + POINTS_FOR_DRAW);
    }
    
    
    /**
     * Method formats string by length
     * @param string String for formatting
     * @param length Length to which the blank characters are appended to the string
     * @param endString If this option is set to true - add the blank characters to the end of the string, otherwise in the beginning
     * @return formatted string
     */
    private static String fixLength(String string, int length, boolean endString) {
        
        StringBuilder sb = new StringBuilder();
        
        if(endString){
            
            sb.append(string);
            while(sb.toString().length() != length){
                sb.append(" ");
            }
            
        } else {
            
            int item = length - string.length();
            for(int i = 0; i < item; i++) {
                sb.append(" ");
            }
            sb.append(string);
            
        }
        return sb.toString();
    }
}
