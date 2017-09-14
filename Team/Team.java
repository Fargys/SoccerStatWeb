package Team;

import java.io.Serializable;

/**
 * Class describes soccer team
 */
public class Team implements Comparable, Serializable {
    private String name;
    private int games;
    private int wins;
    private int draw;
    private int defeats;
    private int scored;
    private int missed;
    private int difference;
    private int points;
    private CurrentForm currentForm;

    //Constructor
    public Team(String name) {
        this.name = name;
        this.games = 0;
        this.wins = 0;
        this.draw = 0;
        this.defeats = 0;
        this.scored = 0;
        this.missed = 0;
        this.difference = 0;
        this.points = 0;
        this.currentForm = new CurrentForm();
    }

    /**
     * Method for winner team
     * @param thisScore Scored goals
     * @param anotherScore Missed goals
     */
    public void win(int thisScore, int anotherScore){
        this.games++;
        this.wins++;
        this.scored += thisScore;
        this.missed += anotherScore;
        this.difference += (thisScore - anotherScore);
        this.points += 3;
        this.currentForm.newGame('+');
    }

    /**
     * Method for teams which have drawn
     * @param thisScore Scored goals for both team
     */
    public void draw(int thisScore){
        this.games++;
        this.draw++;
        this.scored += thisScore;
        this.missed += thisScore;
        this.points++;
        currentForm.newGame('=');
    }

    /**
     * Method for losing team
     * @param thisScore Scored goals
     * @param anotherScore Missed goals
     */
    public void lose(int thisScore, int anotherScore){
        this.games++;
        this.defeats++;
        this.scored += thisScore;
        this.missed += anotherScore;
        this.difference += (thisScore - anotherScore);
        currentForm.newGame('-');
    }

    /**
     * Method for zeroing data
     */
    public void cleanData() {
        games = wins = draw = defeats = scored = missed = difference = points = 0;
        currentForm = new CurrentForm();
    }

    /**
     * @return string in format "teamName  0 0 0 0 0-0 0 0 + = - + -".
     *         The string has fix length and equals 53 chars
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(fixLength(this.name, 16, true));
        sb.append(fixLength(String.valueOf(this.games), 3, false));
        sb.append(fixLength(String.valueOf(this.wins), 3, false));
        sb.append(fixLength(String.valueOf(this.draw), 3, false));
        sb.append(fixLength(String.valueOf(this.defeats), 3, false));
        sb.append(fixLength(String.valueOf(this.scored), 4, false));
        sb.append(":");
        sb.append(fixLength(String.valueOf(this.missed), 3, true));
        sb.append(fixLength(String.valueOf(this.difference), 4, false));
        sb.append(fixLength(String.valueOf(this.points), 3, false));
        sb.append(fixLength(String.valueOf(this.currentForm), 10, false));
        return sb.toString();
    }

    /**
     * In order of decreasing priority: points, wins, difference, scored, games
     */
    @Override
    public int compareTo(Object o) {
        Team team = (Team) o;

        if(this.points > team.points) return -1;
        else if(this.points < team.points) return 1;

        if(this.wins > team.wins) return -1;
        else if(this.wins < team.wins) return 1;

        if(this.difference > team.difference) return -1;
        else if(this.difference < team.difference) return 1;

        if(this.scored > team.scored) return -1;
        else if(this.scored < team.scored) return 1;

        if(this.games < team.games) return -1;
        else if(this.games > team.games) return 1;

        return 0;
    }

    /**
     * Method format string by length
     * @param string String for formatting
     * @param length Length to which to the string add empty chars
     * @param endString if true add to the end string, if false - to begin.
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
