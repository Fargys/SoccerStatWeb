package Team;

import java.io.Serializable;

/**
 * Class for mapping data about 5 last games
 */
class CurrentForm implements Serializable {
    private char[] fiveLastGames;

    //Constructor
    CurrentForm() {
        fiveLastGames = new char[5];
        for(char ch : fiveLastGames){
            ch = ' ';
        }
    }

    /**
     * Method rewrites data of 5 last games with considering the last game
     * @param lastResult result of last game
     */
    void newGame(char lastResult) {
        fiveLastGames[4] = fiveLastGames[3];
        fiveLastGames[3] = fiveLastGames[2];
        fiveLastGames[2] = fiveLastGames[1];
        fiveLastGames[1] = fiveLastGames[0];
        fiveLastGames[0] = lastResult;
    }

    /**
     * @return String in format "x x x x x", 'x' may be equals: '+', '-', '=' or ' '
     */
    @Override
    public String toString() {
        String result = String.valueOf(fiveLastGames[0]);
        for (int i = 1; i < 5; i++) {
            result += (" " + fiveLastGames[i]);
        }
        return result;
    }
}
