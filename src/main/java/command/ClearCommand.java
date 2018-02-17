package command;

import controllers.ChampionshipController;
import controllers.TeamController;
import entity.Team;
import static other.ConsoleHelper.chosenActionIsChampionship;
import static controllers.ChampionshipController.areNotCreatedChampionships;
import static controllers.ChampionshipController.getCurrentChampionshipId;

public class ClearCommand implements Command {

    @Override
    public void execute() {
        if(areNotCreatedChampionships()) return;
        if(chosenActionIsChampionship()) ChampionshipController.clear();
        else {
            ChampionshipController.select();
            Team selectedTeam = TeamController.select(getCurrentChampionshipId());
            TeamController.clear(selectedTeam);
        }
    }
}
