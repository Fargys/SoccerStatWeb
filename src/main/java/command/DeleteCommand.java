package command;

import controllers.ChampionshipController;
import controllers.TeamController;
import entity.Team;
import static other.ConsoleHelper.chosenActionIsChampionship;
import static other.ConsoleHelper.writeEmptyLine;
import static other.ConsoleHelper.writeMessage;
import static controllers.ChampionshipController.areNotCreatedChampionships;
import static controllers.ChampionshipController.getCurrentChampionshipId;
import static controllers.TeamController.getNumberOfTeams;

public class DeleteCommand implements Command {
    
    @Override
    public void execute() {
        if(areNotCreatedChampionships()) return;
        if(chosenActionIsChampionship()) {
            ChampionshipController.delete();
        }
        else {
            ChampionshipController.select();
            Long currentChampionshipId = getCurrentChampionshipId();
            
            Long numberOfTeams = getNumberOfTeams(currentChampionshipId);
            
            if(numberOfTeams > 1) {
                Team selectedTeam = TeamController.select(currentChampionshipId);
                TeamController.delete(selectedTeam);
            } 
            if(numberOfTeams == 1) {
                writeEmptyLine();
                writeMessage("You can't remove the last team");
            }
        }
    }
}