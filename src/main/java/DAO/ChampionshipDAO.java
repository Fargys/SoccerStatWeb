package DAO;

import entity.Championship;
import java.util.List;

public interface ChampionshipDAO {
    public List getAllChampionships();
    public Championship getChampionshipByName(String champName);
    public Championship getChampionshipById(Long champ_id);
    public Long createChampionship(String championshipName);
    public void deleteChampionship(Championship champForRemoving);
    public Long getNumberOfChampionships();
}
