package DAO;

import entity.Team;
import java.util.List;

public interface TeamDAO {
    public List getAllTeams(Long champ_id);
    public Long getNumberOfTeams(Long champ_id);
    public List getTeamsNames(Long champ_id);
    public Team getTeamByName(String teamName);
    public void createTeam(Team teamForCreating);
    public void updateTeam(Team teamForUpdating);
    public void deleteTeam(Team teamForRemoving);
}
