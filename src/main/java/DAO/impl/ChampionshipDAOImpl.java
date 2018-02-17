package DAO.impl;

import DAO.ChampionshipDAO;
import entity.Championship;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import other.HibernateUtil;

public class ChampionshipDAOImpl implements ChampionshipDAO {
    
    @Override
    public List getAllChampionships() {
        
        Session session = null;
        List<Championship> championships = new ArrayList<>();

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            championships = session.createCriteria(Championship.class).list();
        } catch (HibernateException e) {
            Logger.getLogger(ChampionshipDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return championships;
    }
    
    @Override
    public Championship getChampionshipByName(String champName){
        Session session = null;
        Championship champ = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Criteria champCriteria = session.createCriteria(Championship.class);
            champCriteria.add(Restrictions.eq("name", champName));
            champ = (Championship) champCriteria.uniqueResult();
            session.close();
        } catch (HibernateException e) {
            Logger.getLogger(ChampionshipDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return champ;
    }
    
    @Override   
    public Championship getChampionshipById(Long champ_id) {
        Session session = null;
        Championship champ = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            champ = (Championship) session.get(Championship.class, champ_id);
        } catch (HibernateException e) {
            Logger.getLogger(ChampionshipDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        
        return champ;
        
    }
    
    @Override
    public Long getNumberOfChampionships() {
        Session session = null;
        Long createdChampionships = 0L;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            
            Criteria champCriteria = session.createCriteria(Championship.class);
            Object result = champCriteria.setProjection(Projections.rowCount()).uniqueResult();
            createdChampionships = Long.parseLong(result.toString());
            
            session.close();
        } catch (HibernateException e) {
            Logger.getLogger(ChampionshipDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        
        return createdChampionships;
    }
    
    @Override
    public Long createChampionship(String championshipName){
        Session session = null;
        Championship champForCreating = new Championship();
        champForCreating.setName(championshipName);
        Long createdChampId = 0L;
        
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            createdChampId = (Long) session.save(champForCreating);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            Logger.getLogger(ChampionshipDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return createdChampId;
    } 
    
    @Override
    public void deleteChampionship(Championship champForRemoving){
        
        Session session = null;
       
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(champForRemoving);
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
