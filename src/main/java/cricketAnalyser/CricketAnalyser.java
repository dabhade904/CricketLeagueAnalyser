package cricketAnalyser;

import com.google.gson.Gson;
import java.util.*;
import java.util.stream.Collectors;

public class CricketAnalyser {
    Map<String, Batsman> cricketMap = new HashMap<>();
    Map<String,Bowler> bowlerMap=new HashMap<>();

    public int loadCricketData(String csvFilePath) throws CricketAnalyserException {
        cricketMap = new CricketLeagueLoader().readCricketMostRunsData(csvFilePath);
        return cricketMap.size();
    }

    public int loadWicketsData(String csvFilePath) throws CricketAnalyserException {
        bowlerMap = new CricketLeagueLoader().readCricketMostWicketsData(csvFilePath);
        return bowlerMap.size();
    }

    public int getNumberOfRecord(String csvFilePath) {
        int count = 0;
        try {
            int batsmanMap1 = loadCricketData(csvFilePath);
            return batsmanMap1;
        } catch (CricketAnalyserException e) {
        }
        return count;
    }

    public String getSortedData(SortingFields.fields sortFields) {
        Comparator<Batsman> batsmanComparator = new SortingFields().getParameter(sortFields);
        ArrayList batsmanList = cricketMap.values().stream().
                sorted(batsmanComparator).
                collect(Collectors.toCollection(ArrayList::new));
        String sortedDataJson = new Gson().toJson(batsmanList);
        return sortedDataJson;
    }

    public String getSortedWicketsData(SortingFields.fields sortFields) {
        Comparator<Bowler> bowlerComparator = new SortingFields().getParameters(sortFields);
        ArrayList bowlerList = bowlerMap.values().stream().
                sorted(bowlerComparator).
                collect(Collectors.toCollection(ArrayList::new));
        String sortedDataJson = new Gson().toJson(bowlerList);
        return sortedDataJson;
    }
}
