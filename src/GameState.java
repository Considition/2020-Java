import models.*;

import java.util.List;

public class GameState {
    public String gameId;
    public String mapName;
    public int maxTurns;
    public double maxTemp;
    public double minTemp;
    public int[][] map;
    public List<EnergyLevel> energyLevels;
    public List<BlueprintResidenceBuilding> availableResidenceBuildings;
    public List<BlueprintUtilityBuilding> availableUtilityBuildings;
    public List<Upgrade> availableUpgrades;
    public List<Effect> effects;
    public int turn;
    public double funds;
    public double totalCo2;
    public double totalHappiness;
    public double currentTemp;
    public double queueHappiness;
    public int housingQueue;
    public List<BuiltResidenceBuilding> residenceBuildings;
    public List<BuiltUtilityBuilding> utilityBuildings;
    public List<String> errors;
    public List<String> messages;

    public GameState(GameInfoResponse gameInfoResponse) {
        gameId = gameInfoResponse.gameId;
        mapName = gameInfoResponse.mapName;
        maxTurns = gameInfoResponse.maxTurns;
        maxTemp = gameInfoResponse.maxTemp;
        minTemp = gameInfoResponse.minTemp;
        map = gameInfoResponse.map;
        energyLevels = gameInfoResponse.energyLevels;
        availableResidenceBuildings = gameInfoResponse.availableResidenceBuildings;
        availableUtilityBuildings = gameInfoResponse.availableUtilityBuildings;
        availableUpgrades = gameInfoResponse.availableUpgrades;
        effects = gameInfoResponse.effects;
    }

    public void UpdateGameState(GameStateResponse gameStateResponse) {
        turn = gameStateResponse.turn;
        funds = gameStateResponse.funds;
        totalCo2 = gameStateResponse.totalCo2;
        totalHappiness = gameStateResponse.totalHappiness;
        currentTemp = gameStateResponse.currentTemp;
        queueHappiness = gameStateResponse.queueHappiness;
        housingQueue = gameStateResponse.housingQueue;
        residenceBuildings = gameStateResponse.residenceBuildings;
        utilityBuildings = gameStateResponse.utilityBuildings;
        errors = gameStateResponse.errors;
        messages = gameStateResponse.messages;
    }
}
