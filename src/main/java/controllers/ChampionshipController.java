package controllers;

import DAO.FactoryDAO;
import entity.Championship;
import entity.Team;
import java.util.List;
import java.util.Objects;
import other.Score;
import static other.ConsoleHelper.getChampionshipName;
import static other.ConsoleHelper.writeEmptyLine;
import static other.ConsoleHelper.writeMessage;
import static other.ConsoleHelper.getNumberOfParticipants;
import static other.ConsoleHelper.getParticipantsNames;
import static other.ConsoleHelper.printChampionship;
import static other.ConsoleHelper.getSelectedTeam;
import static other.ConsoleHelper.getScore;
import static other.ConsoleHelper.writeGoodBye;
import static other.ConsoleHelper.getSelectedChampionship;


public class ChampionshipController {
    private static Long currentChampionshipId;

    public static Long getCurrentChampionshipId() {
        return currentChampionshipId;
    }
    
            
    public static void create() {
        String creatingChampName = getChampionshipName();
        
        while(nameAlreadyExsits(creatingChampName)) {
            writeEmptyLine();
            writeMessage("You already have created championship with this name. Choose a different name.");
            
            creatingChampName = getChampionshipName();
        }
        
        Long createdChampId = FactoryDAO.getInstance().getChampionshipDAO().createChampionship(creatingChampName);
        
        int numberOfParticipans = getNumberOfParticipants();
        
        List<String> participants = getParticipantsNames(numberOfParticipans);
        
        for(String name : participants) {
            TeamController.create(createdChampId, name);
        }
        
        writeEmptyLine();
        writeMessage("The championship was created");
    }
                                                    
    public static void print() {
        select();
        
        Championship printingChamp = FactoryDAO.getInstance().getChampionshipDAO().getChampionshipById(currentChampionshipId);
        List<Team> teamsForPrinting = TeamController.getAllTeams(currentChampionshipId);
        
        printChampionship(printingChamp.getName(), teamsForPrinting);
    }
    
    public static void update() {
        select();
        
        List<Team> teamsOfUpdatingChamp = TeamController.getAllTeams(currentChampionshipId);
        
        Team homeTeam = getSelectedTeam(teamsOfUpdatingChamp);
        Team guestTeam = getSelectedTeam(teamsOfUpdatingChamp);
        
        while(Objects.equals(homeTeam.getId(), guestTeam.getId())) {
            writeEmptyLine();
            writeMessage("You chose the same team. Choose another team");
            guestTeam = getSelectedTeam(teamsOfUpdatingChamp);
        }
        
        Score score = getScore();
        
        TeamController.updateStatisticsAfterGame(homeTeam, guestTeam, score);
        
        writeEmptyLine();
        writeMessage("The changes were added");
    }
    
    public static void delete() {
        select();
        
        Championship champForRemoving = FactoryDAO.getInstance().getChampionshipDAO().getChampionshipById(currentChampionshipId);
        
        FactoryDAO.getInstance().getChampionshipDAO().deleteChampionship(champForRemoving);
        
        writeEmptyLine();
        writeMessage("The champioship was removed");
    }
    
    public static void clear() {
        select();
        
        List<Team> teamsForClearing = TeamController.getAllTeams(currentChampionshipId);
        
        for(Team teamForClearing : teamsForClearing) {
            TeamController.clear(teamForClearing);
        }
        
        writeEmptyLine();
        writeMessage("The champioship was cleared");
    }
    
    public static void exit() {
        writeGoodBye();
        System.exit(0);
    }
    
    
    public static void select() {
        List<Championship> createdChamps = FactoryDAO.getInstance().getChampionshipDAO().getAllChampionships();
                
        Championship selectedChamp = getSelectedChampionship(createdChamps);
        currentChampionshipId = selectedChamp.getId();
    }
    
    
    public static Boolean areNotCreatedChampionships() {
        Long numberOfChampionship = FactoryDAO.getInstance().getChampionshipDAO().getNumberOfChampionships();
        
        if(numberOfChampionship == 0) {
            writeEmptyLine();
            writeMessage("No championship has been created");
            writeEmptyLine();
            return true;
        }
        
        return false;
    }
    
    private static Boolean nameAlreadyExsits(String champName) {
        Championship createdChampionship = FactoryDAO.getInstance().getChampionshipDAO().getChampionshipByName(champName);
        
        return createdChampionship != null;
    }
}
