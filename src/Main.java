//TODO install com.google.gson

import models.BlueprintResidenceBuilding;
import models.BuiltResidenceBuilding;
import models.Position;

public class Main {
    private static String apiKey = "";                              // TODO: Enter your API key here
    // The different map names can be found on considition.com/rules
    private static String map = "training1";                        // TODO: Enter which map to play. If left empty, the map "training1" will be selected.
    private GameLayer gameLayer;

    Main() {
        gameLayer = new GameLayer(apiKey);
        String gameId = gameLayer.newGame(map);
        System.out.println("Starting game: " + gameId);
        gameLayer.startGame(gameId);
        while (gameLayer.getState().turn < gameLayer.getState().maxTurns) {
            takeTurn(gameId);
        }
        System.out.println("Done with game: " + gameId);
        System.out.println("Final score was: " + gameLayer.getScore(gameId));
    }

    private void takeTurn(String gameId) {
        // TODO Implement you own artificial intelligence here
        // TODO Take on action per turn until the game ends.
        // TODO The following is a short example of how to use the StarterKit
        GameState state = gameLayer.getState();

        if (state.residenceBuildings.size() < 1) {
            int x = 0;
            int y = 0;
            for (int i=0;i<state.map.length; i++) {
                for (int j=0; j<state.map[i].length; j++) {
                    if (state.map[i][j] == 0) {
                        x = i;
                        y = j;
                        break;
                    }
                }
            }
            gameLayer.startBuild(state.availableResidenceBuildings.get(0).buildingName, new Position(x, y), gameId);
        } else {
            BuiltResidenceBuilding residence = state.residenceBuildings.get(0);
            if (state.residenceBuildings.get(0).buildProgress < 100) {
                gameLayer.build(residence.position);
            } else if (residence.health < 50) {
                gameLayer.maintenance(residence.position, gameId);
            } else if (residence.temperature < 18) {
                BlueprintResidenceBuilding blueprint = gameLayer.getResidenceBlueprint(residence.buildingName);
                double energy = blueprint.baseEnergyNeed +
                        (residence.temperature - state.currentTemp) * blueprint.emissivity / 1.0 +
                        0.5 - residence.currentPop * 0.04;
                gameLayer.adjustEnergy(residence.position, energy, gameId);
            } else if (residence.temperature > 24) {
                BlueprintResidenceBuilding blueprint = gameLayer.getResidenceBlueprint(residence.buildingName);
                double energy = blueprint.baseEnergyNeed +
                        (residence.temperature - state.currentTemp) * blueprint.emissivity / 1.0 -
                        0.5 - residence.currentPop * 0.04;
                gameLayer.adjustEnergy(residence.position, energy, gameId);
            } else if (!residence.effects.contains(state.availableUpgrades.get(0).name)) {
                gameLayer.buyUpgrade(residence.position, state.availableUpgrades.get(0).name, gameId);
            } else {
                gameLayer.doWait(gameId);
            }
        }
        for (int i=0; i<gameLayer.getState().messages.size(); i++) {
            System.out.println(gameLayer.getState().messages.get(i));
        }
        for (int i=0; i<gameLayer.getState().errors.size(); i++) {
            System.out.println("Error: " + gameLayer.getState().errors.get(i));
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}
