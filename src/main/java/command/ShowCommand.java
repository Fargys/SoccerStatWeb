package command;

import controllers.ChampionshipController;
import static controllers.ChampionshipController.areNotCreatedChampionships;

public class ShowCommand implements Command {

    @Override
    public void execute() {
        if(areNotCreatedChampionships()) return;
        ChampionshipController.print();
    }
    
    
}
