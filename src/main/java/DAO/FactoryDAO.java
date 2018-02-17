package DAO;

import DAO.impl.ChampionshipDAOImpl;
import DAO.impl.TeamDAOImpl;

public class FactoryDAO {
  
  private static ChampionshipDAO championshipDAO = null;
  private static TeamDAO teamDAO = null;
  private static FactoryDAO instance = null;

  public static FactoryDAO getInstance(){
    if (instance == null){
      instance = new FactoryDAO();
    }
    return instance;
  }

  public ChampionshipDAO getChampionshipDAO(){
    if (championshipDAO == null){
      championshipDAO = new ChampionshipDAOImpl();
    }
    return championshipDAO;
  }

  public TeamDAO getTeamDAO(){
    if (teamDAO == null){
      teamDAO = new TeamDAOImpl();
    }
    return teamDAO;
  }
}