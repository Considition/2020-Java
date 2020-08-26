import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import models.*;

public class GameLayer {
	private String apiKey;
	private GameState gameState;
	private static final Gson gson;
	static {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gson = gsonBuilder.create();
	}

	public GameLayer(String apiKey) {
		this.apiKey = apiKey;
	}

	public GameState getState() {
		return gameState;
	}

	/**
	 * Creates a new game.
	 * @param map Specify which map to play on
	 * @return The id for the new game
	 */
	public String newGame(String map) {
		String mapName = "{\"mapName\":" + map + "}";
		gameState = new GameState(Api.newGame(apiKey, mapName));
		return gameState.gameId;
	}

	/**
	 * Creates a new game.
	 */
	public void newGame() {
		gameState = new GameState(Api.newGame(apiKey, ""));
	}

	/**
	 * Starts a new game.
	 * @param gameId specify which game to start.
	 */
	public void startGame(String gameId) {
		gameState.UpdateGameState(Api.startGame(apiKey, gameId));
	}

	/**
	 * Starts a new game.
	 */
	public void startGame() {
		startGame(null);
	}

	/**
	 * Place a foundation.
	 * @param buildingName specify which building.
	 * @param position specify where to put the building.
	 * @param gameId The Id of the game to perform the action on
	 */
	public void startBuild(String buildingName, Position position, String gameId) {
		String pos = gson.toJson((position), Position.class);
		String foundation = "{\"position\": " + pos + ",\"buildingName\": " + buildingName + "}";
		gameState.UpdateGameState(Api.startBuild(apiKey, foundation, gameId));
	}

	/**
	 * Place a foundation.
	 * @param buildingName specify which building.
	 * @param pos specify where to put the building.
	 */
	public void startBuild(String buildingName, Position pos) {
		startBuild(buildingName, pos, null);
	}

	/**
	 * Makes one build action.
	 * @param pos specify where to build.
	 * @param gameId The Id of the game to perform the action on
	 */
	public void build(Position pos, String gameId) {
		String position = gson.toJson((pos), Position.class);
		position = "{\"position\":" + position + "}";
		gameState.UpdateGameState(Api.build(apiKey, position, gameId));
	}
	/**
	 * Makes one build action.
	 * @param pos specify where to build.
	 */
	public void build(Position pos) {
		build(pos, null);
	}
	
	/**
	 * Waits one tick.
	 * @param gameId The Id of the game to perform the action on
	 */
	public void doWait(String gameId) {
		gameState.UpdateGameState(Api.wait(apiKey, gameId));
	}

	/**
	 * Waits one tick.
	 */
	public void doait() {
		doWait(null);
	}

	/**
	 * Adjusts the energy on the specific buildings.
	 * @param position specify where the buildings are.
	 * @param value specify how much energy each building gets respectively.
	 * @param gameId The Id of the game to perform the action on
	 */
	public void adjustEnergy (Position position, double value, String gameId) {
		String positionJson = gson.toJson((position) );
		String valueJson = gson.toJson(value);
		
		String body = "{ \"position\":" + positionJson + " ,\"value\":" + valueJson+"}";
		gameState.UpdateGameState(Api.adjustEnergy(apiKey, body, gameId));
	}

	/**
	 * Adjusts the energy on the specific buildings.
	 * @param position specify where the buildings are.
	 * @param value specify how much energy each building gets respectively.
	 */
	public void adjustEnergy (Position position, double value) {
		adjustEnergy(position, value);
	}

	/**
	 * Increases the building's health points.
	 * @param position specify where the building is.
	 * @param gameId The Id of the game to perform the action on
	 */
	public void maintenance(Position position, String gameId) {
		String pos = gson.toJson((position), Position.class);
		pos = "{\"position\":" + pos + "}";
		gameState.UpdateGameState(Api.maintenance(apiKey, pos, gameId));
	}

	/**
	 * Increases the building's health points.
	 * @param position specify where the building is.
	 */
	public void maintenance(Position position) {
		maintenance(position, null);
	}

	/**
	 * Buys an upgrade on the specific building.
	 * @param position specify where the building is.
	 * @param upgrade specify which upgrade to buy.
	 * @param gameId The Id of the game to perform the action on
	 */
	public void buyUpgrade(Position position, String upgrade, String gameId) {
		String pos = " \"position\":" + gson.toJson(position);
		String body ="{"+ pos + ",\"upgradeAction\":" + gson.toJson(upgrade)+"}";
		gameState.UpdateGameState(Api.buyUpgrade(apiKey, body, gameId));
	}

	/**
	 * Buys an upgrade on the specific building.
	 * @param position specify where the building is.
	 * @param upgrade specify which upgrade to buy.
	 */
	public void buyUpgrade(Position position, String upgrade) {
		buyUpgrade(position, upgrade, null);
	}

	/**
	 * Demolishes a building.
	 * @param position specify where the building is.
	 * @param gameId The Id of the game to perform the action on
	 */
	public void demolish(Position position, String gameId) {
		String pos = gson.toJson((position), Position.class);
		pos = "{\"position\":" + pos + "}";
		gameState.UpdateGameState(Api.demolish(apiKey, pos, gameId));
	}

	/**
	 * Demolishes a building.
	 * @param position specify where the building is.
	 */
	public void demolish(Position position) {
		demolish(position, null);
	}

	/**
	 * Ends a game prematurely.
	 */
	public void endGame(String gameId) {
		Api.endGame(apiKey,gameId);
	}

	/**
	 * Ends the game prematurely.
	 */
	public void endGame() {
		endGame(null);
	}

	/**
	 * Gets the score for the specified game.
	 * @param gameId which game to check.
	 */
	public ScoreResponse getScore(String gameId) {
		return Api.getScore(apiKey, gameId);
	}

	/**
	 * Gets the score for the latest game.
	 */
	public ScoreResponse getScore() {
		return getScore(null);
	}

	/**
	 * Gets the game info from an already created game and creates a new state.
	 * @param gameId which game to check
	 */
	public void getNewGameInfo(String gameId) {
		gameState = new GameState(Api.getGameInfo(apiKey, gameId));
	}

	/**
	 * Gets the game state from an already created game and updates the state.
	 * @param gameId which game to check
	 */
	public void getNewGameState(String gameId) {
		gameState.UpdateGameState(Api.getGameState(apiKey, gameId));
	}

	/**
	 * Gets the blueprint matching the building name.
	 * @param buildingName The name to match
	 * @return A blueprint of a building or null if no such building exists
	 */
	public BlueprintBuilding getBlueprint(String buildingName) {
		BlueprintResidenceBuilding blueprintResidenceBuilding = getResidenceBlueprint(buildingName);
		if (blueprintResidenceBuilding != null) return blueprintResidenceBuilding;
		return getUtilityBlueprint(buildingName);
	}

	/**
	 * Gets the blueprint matching the building name.
	 * @param buildingName The name to match
	 * @return A blueprint of a building or null if no such building exists
	 */
	public BlueprintResidenceBuilding getResidenceBlueprint(String buildingName) {
		for (BlueprintResidenceBuilding blueprint: gameState.availableResidenceBuildings) {
			if (blueprint.buildingName.equals(buildingName))
				return blueprint;
		}
		return null;
	}

	/**
	 * Gets the blueprint matching the building name.
	 * @param buildingName The name to match
	 * @return A blueprint of a building or null if no such building exists
	 */
	public BlueprintUtilityBuilding getUtilityBlueprint(String buildingName) {
		for (BlueprintUtilityBuilding blueprint: gameState.availableUtilityBuildings) {
			if (blueprint.buildingName.equals(buildingName))
				return blueprint;
		}
		return null;
	}

	/**
	 * Gets the effect matching the effect name
	 * @param effectName The name to match
	 * @return An effect or null if no such effect exists
	 */
	public Effect getEffect(String effectName) {
		for (Effect effect: gameState.effects) {
			if (effect.name.equals(effectName))
				return effect;
		}
		return null;
	}
}
