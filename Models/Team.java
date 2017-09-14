package Models;

public class Team implements Comparable {
    //fields
    private String name;
    private int games;
    private int wins;
    private int draws;
    private int losses;
    private int scored;
    private int missed;
    private int points;
    private long championship_id;

    //constructor
    public Team() {
    }

    //getters and setters
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getGames() {
        return games;
    }
    public void setGames(int games) {
        this.games = games;
    }
    public int getWins() {
        return wins;
    }
    public void setWins(int wins) {
        this.wins = wins;
    }
    public int getDraws() {
        return draws;
    }
    public void setDraws(int draws) {
        this.draws = draws;
    }
    public int getLosses() {
        return losses;
    }
    public void setLosses(int losses) {
        this.losses = losses;
    }
    public int getScored() {
        return scored;
    }
    public void setScored(int scored) {
        this.scored = scored;
    }
    public int getMissed() {
        return missed;
    }
    public void setMissed(int missed) {
        this.missed = missed;
    }
    public int getPoints() {
        return points;
    }
    public void setPoints(int points) {
        this.points = points;
    }
    public long getChampionship_id() {
        return championship_id;
    }
    public void setChampionship_id(long championship_id) {
        this.championship_id = championship_id;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(fixLength(this.name, 16, true));
        sb.append(fixLength(String.valueOf(this.games), 3, false));
        sb.append(fixLength(String.valueOf(this.wins), 3, false));
        sb.append(fixLength(String.valueOf(this.draws), 3, false));
        sb.append(fixLength(String.valueOf(this.losses), 3, false));
        sb.append(fixLength(String.valueOf(this.scored), 4, false));
        sb.append(":");
        sb.append(fixLength(String.valueOf(this.missed), 3, true));
        sb.append(fixLength(String.valueOf(this.points), 3, false));
        sb.toString();
        return sb.toString();
    }

    /**
     * Method formats string by length
     * @param string String for formatting
     * @param length Length to which to the string add empty chars
     * @param endString if true - add to the end string, if false - to begin.
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

    @Override
    public int compareTo(Object o) {
        Team team = (Team) o;

        if(this.points > team.points) return -1;
        else if(this.points < team.points) return 1;

        if(this.wins > team.wins) return -1;
        else if(this.wins < team.wins) return 1;

        if(this.scored > team.scored) return -1;
        else if(this.scored < team.scored) return 1;

        if(this.games < team.games) return -1;
        else if(this.games > team.games) return 1;

        return 0;
    }

}
