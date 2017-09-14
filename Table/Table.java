package Table;

import ConsoleHelper.ConsoleHelper;
import Team.Team;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class describes table which applies in soccer tournament for
 * definitions the champions team. Each team must play with each other team.
 */
public class Table implements TableData, Serializable {
    private String name;
    private ArrayList<Team> participants;

    //---------------------------------------------------------
    // Котнейнер сохраненных данных
    public static List<String> saveData = new ArrayList<>();
    //---------------------------------------------------------

    // Constructors
    public Table(String name, String... nameOfTeam) {
        this.name = name;
        this.participants = new ArrayList<Team>();
        if (nameOfTeam.length > 0) {
            for (String team : nameOfTeam) {
                this.participants.add(new Team(team));
            }
        }
    }
    private Table(String name, List<String> nameOfTeam) {
        this.name = name;
        this.participants = new ArrayList<Team>();
        if (nameOfTeam.size() > 0) {
            for (String team : nameOfTeam) {
                this.participants.add(new Team(team));
            }
        }
    }

    // Getters
    ArrayList<Team> getParticipants() {
        return this.participants;
    }
    public String getName() {
        return this.name;
    }

    /**
     * Method prints table in console
     */
    @Override
    public void printTable() {
        System.out.println("\t" + name);
        for (int i = 0; i < participants.size(); i++) {
            System.out.println(i + 1 + ". " + participants.get(i));
        }
    }

    /**
     * Method changes table data
     */
    @Override
    public void change() {
        Team home = chooseTeam("Выберите команду хозяев");
        Team guest = chooseTeam("Выберите команду гостей");
        int[] score = chooseScore("Введите итоговый результат в формате ?:?");
        makeChanges(home, guest, score[0], score[1]);
        System.out.println("Изменения внесены");
        System.out.println();
    }

    /**
     * Method zeroing data of teams participating in the tournament
     */
    @Override
    public void cleanTable() {
        for (Team team : participants) {
            team.cleanData();
        }
    }

    /**
     * Method asks user to input name of table and names of teams.
     * Then method creates new table.
     */
    @Override
    public Table produceTable() {
        System.out.println("Введите название таблицы");
        String name = ConsoleHelper.readString();

        System.out.println("Введите название команд поочерёдно, по завершению введите exit");
        List<String> teamList = new ArrayList<>();
        String teamName = "";
        while (!(teamName = ConsoleHelper.readString()).equals("exit")) {
            teamList.add(teamName);
        }

        return new Table(name, teamList);
    }

    /**
     * Method prints to console a list of team and asks user choice the team.
     * @param str Message for user.
     * @return name of chosen team.
     */
    private Team chooseTeam(String str){
        System.out.println();
        System.out.println(str);
        for(int i = 0; i < participants.size(); i++){
            System.out.println(i + 1 + ". " + participants.get(i).toString());
        }

        int choice = ConsoleHelper.readInt();
        return participants.get(choice - 1);
    }

    /**
     * Method asks user to input result of game.
     * @param str Message for user.
     * @return array of scored goals. [0] - goals of home team, [1] - goals of guest team.
     */
    private int[] chooseScore(String str){
        int[] result = new int[2];
        System.out.println(str);

        String[] array = ConsoleHelper.readString().split(":");
        result[0] = Integer.valueOf(array[0]);
        result[1] = Integer.valueOf(array[1]);

        return result;
    }

    /**
     * Method updates data.
     */
    private void makeChanges(Team home, Team guest, int homeScore, int guestScore) {
        Team first = participants.get(participants.indexOf(home));
        Team second = participants.get(participants.indexOf(guest));

        if(homeScore > guestScore){
            first.win(homeScore, guestScore);
            second.lose(guestScore, homeScore);
        } else if (homeScore < guestScore){
            first.lose(homeScore, guestScore);
            second.win(guestScore, homeScore);
        } else {
            first.draw(homeScore);
            second.draw(homeScore);
        }

        Collections.sort(participants);
    }
}