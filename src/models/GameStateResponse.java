package models;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class GameStateResponse {
    public int turn;
    public List<BuiltResidenceBuilding> residenceBuildings;
    public List<BuiltUtilityBuilding> utilityBuildings;
    public double funds;
    public double totalCo2 ;
    public double totalHappiness ;
    public double currentTemp;
    public int housingQueue;
    public double queueHappiness ;
    public List<String> errors ;
    public List<String> messages ;
}


