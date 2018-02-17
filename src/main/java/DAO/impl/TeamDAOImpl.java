package DAO.impl;

import DAO.TeamDAO;
import entity.Team;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import other.HibernateUtil;

public class TeamDAOImpl implements TeamDAO {
    
    @Override
    public List getAllTeams(Long champ_id) {
        Session session = null;
        List<Team> result = new ArrayList<>();
       
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(Team.class);
            result = criteria.add(Restrictions.like("championship_id", champ_id)).list();
        } catch (HibernateException e) {
            Logger.getLogger(ChampionshipDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        
        if(result.size() > 1) Collections.sort(result);
        
        return result;
    }  

    @Override
    public Long getNumberOfTeams(Long champ_id) {
        Session session = null;
        Long numberOfTeams = 0L;
        
        String SQLQuery = "SELECT count(team_id) FROM Team WHERE championship_id = :championship_id";
    
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
      
            Query query = session.createQuery(SQLQuery);
            query.setParameter("championship_id", champ_id);
            numberOfTeams = (Long) query.uniqueResult();
            
            session.getTransaction().commit();
        } finally {
            if (session != null && session.isOpen()) {
            session.close();
            }
        }
        
        return numberOfTeams;
    }

    @Override
    public List getTeamsNames(Long champ_id) {
        Session session = null;
        List<String> teamsNames = new ArrayList();
        
        String SQLQuery = "SELECT name FROM Team WHERE championship_id = :championship_id";
        
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
      
            Query query = session.createQuery(SQLQuery);
            query.setParameter("championship_id", champ_id);
            teamsNames = query.list();
            
            session.getTransaction().commit();
        } finally {
            if (session != null && session.isOpen()) {
            session.close();
            }
        }
        
        return teamsNames;
    }
    
    @Override
    public Team getTeamByName(String teamName){
        Session session = null;
        Team result = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Criteria teamCriteria = session.createCriteria(Team.class);
            teamCriteria.add(Restrictions.eq("name", teamName));
            result = (Team) teamCriteria.uniqueResult();
            session.close();
        } catch (HibernateException e) {
            Logger.getLogger(TeamDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return result;
    }
    
    @Override
    public void createTeam(Team teamForCreating) {
        Session session = null;
       
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(teamForCreating);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            Logger.getLogger(ChampionshipDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public void updateTeam(Team teamForUpdating) {
        Session session = null;
       
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(teamForUpdating);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            Logger.getLogger(TeamDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public void deleteTeam(Team teamForRemoving) {
        Session session = null;
       
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(teamForRemoving);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            Logger.getLogger(ChampionshipDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
    
}