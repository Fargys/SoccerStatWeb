package other;

public class Score {
    private int goalsOfHomeTeam;
    private int goalsOfGuestTeam;
    private Winner winnerOfGame;

    public Score(int goalsOfHomeTeam, int goalsOfGuestTeam) {
        this.goalsOfHomeTeam = goalsOfHomeTeam;
        this.goalsOfGuestTeam = goalsOfGuestTeam;
        
        if(goalsOfHomeTeam > goalsOfGuestTeam) winnerOfGame = Winner.HOME_TEAM;
        if(goalsOfHomeTeam < goalsOfGuestTeam) winnerOfGame = Winner.GUEST_TEAM;
        if(goalsOfHomeTeam == goalsOfGuestTeam) winnerOfGame = Winner.NO_ONE;
    }

    public int getGoalsOfHomeTeam() {
        return goalsOfHomeTeam;
    }

    public void setGoalsOfHomeTeam(int goalsOfHomeTeam) {
        this.goalsOfHomeTeam = goalsOfHomeTeam;
    }

    public int getGoalsOfGuestTeam() {
        return goalsOfGuestTeam;
    }

    public void setGoalsOfGuestTeam(int goalsOfGuestTeam) {
        this.goalsOfGuestTeam = goalsOfGuestTeam;
    }

    public Winner getWinnerOfGame() {
        return winnerOfGame;
    }

    public void setWinnerOfGame(Winner winnerOfGame) {
        this.winnerOfGame = winnerOfGame;
    }
    
    
    public static enum Winner {
        HOME_TEAM,
        GUEST_TEAM,
        NO_ONE;
    }
}