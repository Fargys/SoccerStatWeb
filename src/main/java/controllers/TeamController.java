package controllers;

import entity.Team;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import other.HibernateUtil;

public class TeamController {
    // Constants
    public static final int POINTS_FOR_WIN = 3;
    public static final int POINTS_FOR_DRAW = 1;
    public static final int POINTS_FOR_LOSS = 0;
    
   // Win, draw, loss
    public static void win(String name, int thisScore, int thisMissed) {
        Team team = getTeamByName(name);
        
        team.setGames(team.getGames() + 1);
        team.setWins(team.getWins() + 1);
        team.setScored(team.getScored() + thisScore);
        team.setMissed(team.getMissed() + thisMissed);
        team.setPoints(team.getPoints() + POINTS_FOR_WIN);
        
        updateTeam(team);
    }
    public static void draw(String name, int thisScore, int thisMissed) {
        Team team = getTeamByName(name);
        
        team.setGames(team.getGames() + 1);
        team.setDraws(team.getDraws() + 1);
        team.setScored(team.getScored() + thisScore);
        team.setMissed(team.getMissed() + thisMissed);
        team.setPoints(team.getPoints() + POINTS_FOR_DRAW);
        
        updateTeam(team);
    }
    public static void loss(String name, int thisScore, int thisMissed) {
        Team team = getTeamByName(name);
        
        team.setGames(team.getGames() + 1);
        team.setLosses(team.getLosses() + 1);
        team.setScored(team.getScored() + thisScore);
        team.setMissed(team.getMissed() + thisMissed);
        team.setPoints(team.getPoints() + POINTS_FOR_LOSS);
        
        updateTeam(team);
    }

    public static Team getTeamByName(String teamName){
        Session session = null;
        Team result = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Criteria teamCriteria = session.createCriteria(Team.class);
            teamCriteria.add(Restrictions.eq("name", teamName));
            result = (Team) teamCriteria.uniqueResult();
            session.close();
        } catch (HibernateException e) {
            Logger.getLogger(TeamController.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return result;
    }
    public static void updateTeam(Team team) {
        Session session = null;
       
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(team);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            Logger.getLogger(TeamController.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}