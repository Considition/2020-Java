package models;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GameInfoResponse{
    public String gameId ;
    public String mapName;
    public int maxTurns;
    public int[][] map;
    public List<EnergyLevel> energyLevels;
    public List<BlueprintResidenceBuilding> availableResidenceBuildings;
    public List<BlueprintUtilityBuilding> availableUtilityBuildings ;
    public double maxTemp;
    public double minTemp;
    public List<Upgrade> availableUpgrade;
    public List<Effect> effects;
}

