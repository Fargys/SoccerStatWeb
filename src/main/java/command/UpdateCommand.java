package command;

import entity.Team;
import java.util.List;
import controllers.ChampionshipController;
import static controllers.ChampionshipController.areNotCreatedChampionships;
import static controllers.ChampionshipController.getCurrentChampionshipId;
import controllers.TeamController;
import static controllers.TeamController.getAllTeams;
import static other.ConsoleHelper.chosenActionIsChampionship;
import static other.ConsoleHelper.getSelectedTeam;
import static other.ConsoleHelper.setNewDataForTeam;
import static other.ConsoleHelper.writeEmptyLine;
import static other.ConsoleHelper.writeMessage;


public class UpdateCommand implements Command {

    @Override
    public void execute() {
        if(areNotCreatedChampionships()) return;
        if(chosenActionIsChampionship()) ChampionshipController.update();
        else {
            ChampionshipController.select();
            Long currentChampionshipId = getCurrentChampionshipId();
            
            List<Team> teams = getAllTeams(currentChampionshipId);
            Team selectedTeam = getSelectedTeam(teams);
            
            Team updatedTeam = setNewDataForTeam(selectedTeam);
            
            TeamController.update(updatedTeam);
            
            writeEmptyLine();
            writeMessage("The changes were added");
        }
    }
}
