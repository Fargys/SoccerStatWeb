package Controllers;

import Helpers.ConsoleHelper;
import Helpers.JDBCHelper;
import Main.Main;
import Models.Table;
import Models.Team;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TableController {
    public static int currentChampId = 1;
    // JDBC variables for opening and managing connection
    Connection con = null;
    Statement stmt = null;
    ResultSet rs = null;
    
   //ready
    public void selectTableFromDataBase() {
        List<String> tablesNames = getAllTablesNames();
        String selectedTable = selectElement(tablesNames);
        currentChampId = getTableIdByName(selectedTable);
    }
    public void createTable(){
        String tableName = selectTableName();
        int numberOfTeams = selectNumberOfTeams();
        List<String> teamsNames = selectTeamsNames(numberOfTeams);
        saveTable(tableName, teamsNames);
    }   
    public void removeChampionship(){
        List<String> tablesNames = getAllTablesNames();
        String selectedTable = selectElement(tablesNames);
        removeSelectedChamp(selectedTable);
    }
    public void showChampionship(){
        List<Team> participants = getTeamsByChampId();
        printTeams(participants);
    }  
    public void change() {
        String home = selectTeam("Выберите команду хозяев");
        String guest = selectTeam("Выберите команду гостей");
        int[] score = selectScore("Введите итоговый результат в формате ?:?");
        makeChanges(home, guest, score[0], score[1]);
        System.out.println("Изменения внесены");
        System.out.println();
    }
    //ready
             
    
        // Methods assistant for selectTableFromDataBase()
    public List<String> getAllTablesNames() {
        List<String> result = new ArrayList<>();
        String query = "SELECT name FROM soccer.championship";

        try {
            con = JDBCHelper.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                result.add(rs.getString("name"));
            }
        } catch (SQLException ex) {
            System.out.println("TableController.getAllTablesNames(try)");
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(TableController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return result;
    }
    public static String selectElement(List<String> names) {
        System.out.println("Выберите таблицу");
        
        for(int i = 0; i < names.size(); i++){
            System.out.println(i + 1 + ". " + names.get(i));
        }
        
        int choice = ConsoleHelper.readInt(1, names.size());
        return names.get(choice - 1);
    }    
    public int getTableIdByName(String tableName) {
        String query = "SELECT id FROM soccer.championship WHERE name = '" + tableName + "'";
        int result = 0;

        try {
            con = JDBCHelper.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                result = rs.getInt("id");
            }

        } catch (SQLException ex) {
            System.out.println("TableController.getTableIdByName(try)");
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(TableController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return result;
    }
    
        // Methods assistant for createTable()
    public static String selectTableName(){
        System.out.println("Введите имя таблицы");
        return ConsoleHelper.readString();
    }
    public static int selectNumberOfTeams(){
        System.out.println("Введите количество участников турнира");
        return ConsoleHelper.readInt();
    }
    public static List<String> selectTeamsNames(int numberOfTeams) {
        System.out.println("Введите имена участников");
        List<String> result = new ArrayList<>();
        
        for(int i = 0; i < numberOfTeams; i++) {
            result.add(ConsoleHelper.readString());
        }
        return result;
    }
    public void saveTable(String tableName, List<String> teamsNames){
        createNewChampionship(tableName);
        int champ_id = getChampIdByName(tableName);
        createNewTeams(teamsNames, champ_id);
    }
    // Methods assistant for saveTable(String tableName, List<String> teamsNames)
    public void createNewChampionship(String tableName) {
        String query = "INSERT INTO soccer.championship (name) VALUES ('" + tableName + "')";

        try {
            con = JDBCHelper.getConnection();
            stmt = con.createStatement();
            stmt.executeUpdate(query);

        } catch (SQLException ex) {
            Logger.getLogger(TableController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(TableController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public int getChampIdByName(String tableName) {
        String query = "SELECT id FROM soccer.championship WHERE name = '" + tableName + "'";
        int result = 0;

        try {
            con = JDBCHelper.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            
            while (rs.next()) {
                result = rs.getInt("id");
            }
        } catch (SQLException ex) {
                System.out.println("TableController.getChampIdByName");
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(TableController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return result;
    }
    public void createNewTeams(List<String> teamsNames, int champ_id) {
        
        for(String str : teamsNames) {
            String query = "INSERT INTO soccer.team (name, championship_id) VALUES ('" + str + "', " + champ_id + ")";
            
            try {
            con = JDBCHelper.getConnection();
            stmt = con.createStatement();
            stmt.executeUpdate(query);

        } catch (SQLException ex) {
                System.out.println("TableController.createNewTeams");
            Logger.getLogger(TableController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
                try {
                    if (stmt != null) {
                        stmt.close();
                    }
                    if (rs != null) {
                        rs.close();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(JDBCHelper.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
        // Method assistant for removeChampionship()
    public void removeSelectedChamp(String selectedTable) {
        String query = "DELETE FROM soccer.championship WHERE name = '" + selectedTable + "'";

        try {
            con = JDBCHelper.getConnection();
            stmt = con.createStatement();
            stmt.executeUpdate(query);

        } catch (SQLException ex) {
            Logger.getLogger(TableController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(JDBCHelper.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
    
        // Methods assistants for showChampionship()
    private List<Team> getTeamsByChampId() {
        String query = "SELECT * FROM soccer.team WHERE championship_id = " + currentChampId + "";
        List<Team> participants = new ArrayList<>();
        Team team = null;

        try {
            con = JDBCHelper.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                team = new Team();
                team.setName(rs.getString("name"));
                team.setGames(rs.getInt("games"));
                team.setWins(rs.getInt("wins"));
                team.setDraws(rs.getInt("draws"));
                team.setLosses(rs.getInt("losses"));
                team.setScored(rs.getInt("scored"));
                team.setMissed(rs.getInt("missed"));
                team.setPoints(rs.getInt("points"));
                
                
               participants.add(team);
            }
        } catch (SQLException ex) {
            System.out.println("TableController.getTeamsByChampId() (try)");
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(TableController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return participants;
    }  
    private static void printTeams(List<Team> teams) {
       for(Team team : teams) {
            System.out.println(team.toString());
        }
    }
    
    // Methods assistant for change()
    public String selectTeam(String message) {
        System.out.println();
        System.out.println(message);
        
        List<Team> participants = getTeamsByChampId();
        
        for(int i = 0; i < participants.size(); i++){
            System.out.println(i + 1 + ". " + participants.get(i).toString());
        }

        int choice = ConsoleHelper.readInt();
        return participants.get(choice - 1).getName();
    }       
    public int[] selectScore(String message) {
        int[] result = new int[2];
        System.out.println(message);

        String[] array = ConsoleHelper.readString().split(":");
        result[0] = Integer.valueOf(array[0]);
        result[1] = Integer.valueOf(array[1]);

        return result;
    }       
    public void makeChanges(String homeTeam, String guestTeam, int homeScore, int guestScore){
        if (homeScore > guestScore) {
            TeamController.win(homeTeam, homeScore, guestScore);
            TeamController.loss(guestTeam, guestScore, homeScore);
        } else if (homeScore < guestScore) {
            TeamController.loss(homeTeam, homeScore, guestScore);
            TeamController.win(homeTeam, guestScore, homeScore);
        } else {
            TeamController.draw(homeTeam, homeScore, guestScore);
            TeamController.draw(guestTeam, guestScore, homeScore);
        }
    }
       
}
