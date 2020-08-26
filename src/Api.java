import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import models.GameInfoResponse;
import models.GameStateResponse;
import models.GamesResponse;
import models.ScoreResponse;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.IOException;

public class Api {
    private static final String BasePath = "https://game.considition.com/api/game/";
	private static final Gson gson;
	static {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gson = gsonBuilder.create();
	}

    public static GameInfoResponse newGame(String apiKey, String map) {
        try {
            URL url = new URL(BasePath + "new");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestProperty("x-api-key", apiKey);
            String response = doPost(con, map);

			return gson.fromJson(response, GameInfoResponse.class);

        } catch (Exception ex) {
            System.out.println("Fatal error: could not create a new game");
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
            System.exit(1);
            return null;
        }
    }

    public static GameStateResponse startGame(String apiKey, String gameId) {

        try {
            URL url = new URL(BasePath + "start");
            if (gameId != null) {
                url = new URL(BasePath + "start?GameId=" + gameId);
            }
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setDoInput(true);
            con.setRequestProperty("x-api-key", apiKey);
            String response = readApiResponse(con);

            return gson.fromJson(response, GameStateResponse.class);

        } catch (Exception ex) {
            System.out.println("Fatal error: could not start the game");
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
            System.exit(1);
        }
        return null;
    }

    public static GameStateResponse startBuild(String apiKey, String gameId, String foundation) {

        try {
			URL url = new URL(BasePath + "action/startBuild");
			if (gameId != null) {
				url = new URL(BasePath + "action/startBuild?GameId=" + gameId);
			}
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestProperty("x-api-key", apiKey);
            String response = doPost(con, foundation);
			return gson.fromJson(response, GameStateResponse.class);

        } catch (Exception ex) {
            System.out.println("Fatal error: could not start a new building");
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
            System.exit(1);
            return null;
        }

    }

    public static GameStateResponse build(String apiKey, String gameId, String pos) {

        try {
			URL url = new URL(BasePath + "action/build");
			if (gameId != null) {
				url = new URL(BasePath + "action/build?GameId=" + gameId);
			}
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestProperty("x-api-key", apiKey);
            String response = doPost(con, pos);
			return gson.fromJson(response, GameStateResponse.class);

        } catch (Exception ex) {
            System.out.println("Fatal error: could not build building");
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
            System.exit(1);
            return null;
        }

    }

    public static GameStateResponse demolish(String apiKey, String gameId, String body) {

        try {
			URL url = new URL(BasePath + "action/demolish");
			if (gameId != null) {
				url = new URL(BasePath + "action/demolish?GameId=" + gameId);
			}
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestProperty("x-api-key", apiKey);
            String response = doPost(con, body);
			return gson.fromJson(response, GameStateResponse.class);

        } catch (Exception ex) {
            System.out.println("Fatal error: could not demolish building");
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
            System.exit(1);
            return null;
        }

    }

    public static GameStateResponse wait(String apiKey, String gameId) {

        try {
			URL url = new URL(BasePath + "action/wait");
			if (gameId != null) {
				url = new URL(BasePath + "action/wait?GameId=" + gameId);
			}
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestProperty("x-api-key", apiKey);
            String response = doPost(con, "");
			return gson.fromJson(response, GameStateResponse.class);

        } catch (Exception ex) {
            System.out.println("Fatal error: could not wait");
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
            System.exit(1);
            return null;
        }

    }

    public static GameStateResponse maintenance(String apiKey, String gameId, String pos) {

        try {
			URL url = new URL(BasePath + "action/maintenance");
			if (gameId != null) {
				url = new URL(BasePath + "action/maintenance?GameId=" + gameId);
			}
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestProperty("x-api-key", apiKey);
            String response = doPost(con, pos);
			return gson.fromJson(response, GameStateResponse.class);

        } catch (Exception ex) {
            System.out.println("Fatal error: could not do maintenance");
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
            System.exit(1);
            return null;
        }

    }

    public static GameStateResponse adjustEnergy(String apiKey, String gameId, String body) {

        try {
			URL url = new URL(BasePath + "action/adjustEnergy");
			if (gameId != null) {
				url = new URL(BasePath + "action/adjustEnergy?GameId=" + gameId);
			}
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestProperty("x-api-key", apiKey);
            String response = doPost(con, body);
			return gson.fromJson(response, GameStateResponse.class);

        } catch (Exception ex) {
            System.out.println("Fatal error: could not adjust energy");
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
            System.exit(1);
            return null;
        }

    }

    public static GameStateResponse buyUpgrade(String apiKey, String gameId, String body) {

        try {
			URL url = new URL(BasePath + "action/buyUpgrade");
			if (gameId != null) {
				url = new URL(BasePath + "action/buyUpgrade?GameId=" + gameId);
			}
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestProperty("x-api-key", apiKey);
            String response = doPost(con, body);
			return gson.fromJson(response, GameStateResponse.class);

        } catch (Exception ex) {
            System.out.println("Fatal error: could not buy upgrade");
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
            System.exit(1);
            return null;
        }

    }

    public static GamesResponse[] getGames(String apiKey) {
		try {
			URL url = new URL(BasePath + "games");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("x-api-key", apiKey);
			String response = readApiResponse(con);

			return gson.fromJson(response, GamesResponse[].class);

		} catch (Exception ex) {
			System.out.println("Fatal error: could not get games");
			System.out.println("Error: " + ex.getMessage());
			ex.printStackTrace();
			System.exit(1);
		}

		return null;
	}

	public static GameStateResponse getGameState(String apiKey, String gameId) {
		try {
			URL url = new URL(BasePath + "gameState");
			if (gameId != null) {
				url = new URL(BasePath + "gameState?GameId=" + gameId);
			}
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("x-api-key", apiKey);
			String response = readApiResponse(con);

			return gson.fromJson(response, GameStateResponse.class);

		} catch (Exception ex) {
			System.out.println("Fatal error: could not get game state");
			System.out.println("Error: " + ex.getMessage());
			ex.printStackTrace();
			System.exit(1);
		}

		return null;
	}

	public static void endGame(String apiKey, String gameId) {

        try {
            URL url = new URL(BasePath + "end");
            if (gameId != null) {
                url = new URL(BasePath + "end?GameId=" + gameId);
            }
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("x-api-key", apiKey);

            if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {

                con.disconnect();

            }

        } catch (Exception ex) {
            System.out.println("Fatal error: could not end the game");
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
            System.exit(1);
        }

    }

    public static GameInfoResponse getGameInfo(String apiKey, String gameId) {

        try {
            URL url = new URL(BasePath + "gameInfo");
            if (gameId != null) {
                url = new URL(BasePath + "gameInfo?GameId=" + gameId);
            }
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setDoInput(true);
            con.setRequestProperty("x-api-key", apiKey);
            String response = readApiResponse(con);

			return gson.fromJson(response, GameInfoResponse.class);

        } catch (Exception ex) {
            System.out.println("Fatal error: could not get game info");
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
            System.exit(1);
        }
        return null;
    }

	public static ScoreResponse getScore(String apiKey, String gameId) {

        try {
            URL url = new URL(BasePath + "score");
            if (gameId != null) {
                url = new URL(BasePath + "score?GameId=" + gameId);
            }
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setDoInput(true);
            con.setRequestProperty("x-api-key", apiKey);
            String response = readApiResponse(con);

			return gson.fromJson(response, ScoreResponse.class);

        } catch (Exception ex) {
            System.out.println("Fatal error: could not get score");
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
            System.exit(1);
        }
        return null;
    }

    private static String readApiResponse(HttpURLConnection con) throws IOException {

        if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String output = br.readLine();
            String response = output;
            return response;
        } else {
            throw new IOException("Http error with code " + con.getResponseCode() + " and message: ");
        }

    }


    private static String doPost(HttpURLConnection con, String body) throws IOException {
        String response = "";
        con.setRequestMethod("POST");
        con.setDoOutput(true);
        con.setDoInput(true);
        con.setRequestProperty("accept", "text/plain");
        con.setRequestProperty("Content-Type", "application/json");

        OutputStream os = con.getOutputStream();
        os.write(body.getBytes());
        os.flush();
        os.close();

        if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            response = br.readLine();
        }

        return response;
    }


}
