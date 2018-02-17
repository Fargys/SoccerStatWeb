package command;

import controllers.ChampionshipController;

public class ExitCommand implements Command  {

    @Override
    public void execute() {
        ChampionshipController.exit();
    }
}
