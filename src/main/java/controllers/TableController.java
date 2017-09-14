package controllers;


import entity.Championship;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import entity.Team;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import other.ConsoleHelper;
import other.HibernateUtil;

public class TableController {
    public static Long currentChampId = 1L;
    
    public void selectChampionship() {
        List<Championship> championshipsNames = getAllChampionships();
        Championship selectedChampionship = selectElement(championshipsNames);
        currentChampId = selectedChampionship.getId();
    }
    public void createChampionship(){
        String championshipName = selectChampionshipName();
        int numberOfTeams = selectNumberOfTeams();
        List<String> teamsNames = selectTeamsNames(numberOfTeams);
        saveTable(championshipName, teamsNames);
    } 
    public void showChampionship(){
        List<Team> teams = getTeamsByChampId();
        printTeams(teams);
    }  
    public void updateChampionship() {
        String home = selectTeam("Select home team");
        String guest = selectTeam("Select guest team");
        int[] score = selectScore("Enter result like ?:?");
        makeChanges(home, guest, score[0], score[1]);
        System.out.println("Changes made");
        System.out.println();
    }
    public void deleteChampionship(){
        List<Championship> championshipsNames = getAllChampionships();
        Championship selectedChampionship = selectElement(championshipsNames);
        removeSelectedChampionship(selectedChampionship);
    }
    
        // 1) Methods assistant for selectChampionship()
    public List<Championship> getAllChampionships() {
        Session session = null;
        List<Championship> champs = new ArrayList<>();

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            champs = session.createCriteria(Championship.class).list();
        } catch (HibernateException e) {
            Logger.getLogger(TableController.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return champs;
    }
    public Championship selectElement(List<Championship> champs) {
        System.out.println("Select championship");
        
        for(int i = 0; i < champs.size(); i++){
            System.out.println(i + 1 + ". " + champs.get(i).getName());
        }
        
        int choice = ConsoleHelper.readInt(1, champs.size());
        return champs.get(choice - 1);
    }    

        // 2) Methods assistant for createChampionship()
    public String selectChampionshipName(){
        System.out.println();
        System.out.println("Enter name of championship");
        return ConsoleHelper.readString();
    }
    public int selectNumberOfTeams(){
        System.out.println();
        System.out.println("Enter numbers of participants of championship");
        return ConsoleHelper.readInt();
    }
    public List<String> selectTeamsNames(int numberOfTeams) {
        System.out.println();
        System.out.println("Enter the names of the participants");
        List<String> result = new ArrayList<>();
        
        for(int i = 0; i < numberOfTeams; i++) {
            result.add(ConsoleHelper.readString());
        }
        return result;
    }
    public void saveTable(String tableName, List<String> teamsNames){
        createChampinship(tableName);
        Long champ_id = getChampIdByName(tableName);
        createTeams(teamsNames, champ_id);
    }
        // 2.1) Methods assistant for saveTable(String tableName, List<String> teamsNames)
    public void createChampinship(String champName){
        Session session = null;
        Championship champ = new Championship();
        champ.setName(champName);
        
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(champ);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            Logger.getLogger(TableController.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
    public Long getChampIdByName(String champName){
        Session session = null;
        Championship champ = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Criteria champCriteria = session.createCriteria(Championship.class);
            champCriteria.add(Restrictions.eq("name", champName));
            champ = (Championship) champCriteria.uniqueResult();
            session.close();
        } catch (HibernateException e) {
            Logger.getLogger(TableController.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return champ != null ? champ.getId() : -1;
    }
    public void createTeams(List<String> teamsNames, Long champ_id) {
        for(String teamName : teamsNames){
            createTeam(teamName, champ_id);
        }
    }
    public void createTeam(String teamName, Long champ_id) {
        Session session = null;
       
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Team team = new Team();
            team.setName(teamName);
            team.setChampionship_id(champ_id);
            session.save(team);

            session.getTransaction().commit();
        } catch (HibernateException e) {
            Logger.getLogger(TableController.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
    
         // 3) Methods assistants for showChampionship()
    private List<Team> getTeamsByChampId() {
        Session session = null;
        List<Team> result = null;
       
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(Team.class);
            result = criteria.add(Restrictions.like("championship_id", currentChampId)).list();
        } catch (HibernateException e) {
            Logger.getLogger(TableController.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return result;
    }  
    private void printTeams(List<Team> teams) {
        System.out.println();
       for(Team team : teams) {
            System.out.println(team.toString());
        }
    }
    
        // 4)Methods assistant for updateChampionship()
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
       
        // 5) Method assistant for deleteChampionship()
    public void removeSelectedChampionship(Championship selectedChampionship) {
        Session session = null;
       
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(selectedChampionship);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            Logger.getLogger(TableController.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
