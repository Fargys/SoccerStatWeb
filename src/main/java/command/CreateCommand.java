package command;

import controllers.ChampionshipController;
import controllers.TeamController;
import java.util.List;
import static other.ConsoleHelper.chosenActionIsChampionship;
import static other.ConsoleHelper.readString;
import static other.ConsoleHelper.writeMessage;
import static other.ConsoleHelper.writeEmptyLine;
import static controllers.TeamController.getTeamsNames;
import static controllers.ChampionshipController.areNotCreatedChampionships;
import static controllers.ChampionshipController.getCurrentChampionshipId;

public class CreateCommand implements Command {

    @Override
    public void execute() {
        if(chosenActionIsChampionship()) ChampionshipController.create();
        else {
            if(areNotCreatedChampionships()) return;
            ChampionshipController.select();
            
            Long currenChampionshipId = getCurrentChampionshipId();
            List<String> teamsNamesForCheckingValidNameOfNewTeam = getTeamsNames(currenChampionshipId);
            
            writeMessage("Enter name of team:");
            writeEmptyLine();
            
            String creatingTeamName = readString();
            
            while(teamsNamesForCheckingValidNameOfNewTeam.contains(creatingTeamName)) {
                writeEmptyLine();
                writeMessage("This name has already been attached to this championship. Choose other name:");
                writeEmptyLine();
                creatingTeamName = readString();
            }
            
            TeamController.create(currenChampionshipId, creatingTeamName);
        }
    }
}
