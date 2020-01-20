package cricketAnalyser;

import com.google.gson.Gson;

import java.util.*;
import java.util.stream.Collectors;

public class CricketAnalyser {
    Map<String, Batsman> batsmanMap = new HashMap<>();

    public Map<String, Batsman> loadCricketData(String csvFilePath) throws CricketAnalyserException {
        batsmanMap = new CricketLeagueLoader().readCricketMostRunsData(csvFilePath);
        return batsmanMap;
    }

    public int getNumberOfRecord(String csvFilePath) {
        int count = 0;
        try {
            Map<String, Batsman> batsmanMap1 = loadCricketData(csvFilePath);
            return count=batsmanMap1.size();
        } catch (CricketAnalyserException e) {
        }
        return count;
    }

    public String getSortedData(SortingFields.fields sortFields) {
        Comparator<Batsman> batsmanComparator = new SortingFields().getParameter(sortFields);
        ArrayList batsmanList = batsmanMap.values().stream().
                sorted(batsmanComparator).
                collect(Collectors.toCollection(ArrayList::new));
        String sortedDataJson = new Gson().toJson(batsmanList);
        return sortedDataJson;
    }
}
