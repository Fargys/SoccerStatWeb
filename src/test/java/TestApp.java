
import controllers.TeamController;
import static controllers.TeamController.POINTS_FOR_WIN;
import static controllers.TeamController.getTeamByName;
import static controllers.TeamController.updateTeam;
import entity.Championship;
import entity.Team;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import other.HibernateUtil;




public class TestApp {

    public static void main(String[] args) {
        System.out.println(getTeamByName("Loko"));
    }
    
    public static void win(String name, int thisScore, int thisMissed) {
        Team team = getTeamByName(name);
        System.out.println(team.getName());
        
        
        team.setGames(team.getGames() + 1);
        team.setWins(team.getWins() + 1);
        team.setScored(team.getScored() + thisScore);
        team.setMissed(team.getMissed() + thisMissed);
        team.setPoints(team.getPoints() + POINTS_FOR_WIN);
        
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
            System.out.println("TeamController.getTeamByName");
            Logger.getLogger(TeamController.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return result;
    }
    
}
