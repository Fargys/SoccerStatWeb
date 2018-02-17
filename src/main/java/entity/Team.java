package entity;

import java.io.Serializable;

public class Team implements Comparable, Serializable {
    private Long id;
    private String name;
    private int games;
    private int wins;
    private int draws;
    private int losses;
    private int scored;
    private int missed;
    private int points;
    private Long championship_id;

    public Team() {
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }
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
    public Long getChampionship_id() {
        return championship_id;
    }
    public void setChampionship_id(Long championship) {
        this.championship_id = championship;
    }

    @Override
    public int compareTo(Object o) {
        Team team = (Team) o;

        if(this.points > team.points) return -1;
        else if(this.points < team.points) return 1;

        if(this.wins > team.wins) return -1;
        else if(this.wins < team.wins) return 1;
        
        
        int thisDifference = this.scored - this.missed;
        int teamDifference = team.scored - team.missed;
        

        if(thisDifference > teamDifference) return -1;
        else if(thisDifference < teamDifference) return 1;

        if(this.games < team.games) return -1;
        else if(this.games > team.games) return 1;

        return 0;
    }

}
