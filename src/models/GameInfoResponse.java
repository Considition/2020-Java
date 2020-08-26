package models;

import java.util.List;

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
    public List<Upgrade> availableUpgrades;
    public List<Effect> effects;
}

