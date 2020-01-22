package cricketAnalyser;

import java.util.*;
import java.util.stream.Collectors;


public class CricketAnalyser {
    List <CricketLeagueDao>cricketMap = new ArrayList<>();
   // Map<String,Bowler> bowlerMap=new HashMap<>();

    public int loadCricketData(String csvFilePath) throws CricketAnalyserException {
        cricketMap = new CricketLeagueLoader().readCricketMostRunsData(csvFilePath);
        return cricketMap.size();
    }

    public int loadWicketsData(String csvFilePath) throws CricketAnalyserException {
        cricketMap = new CricketLeagueLoader().readCricketMostWicketsData(csvFilePath);
        return cricketMap.size();
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
/*
    public String getSortedData(SortingFields.fields sortFields) {
        Comparator<CricketLeagueDao> batsmanComparator = new SortingFields().getParameter(sortFields);
        List batsmanList =cricketMap.stream(
                sorted(batsmanComparator).
                collect(Collectors.toCollection(ArrayList::new));
        String sortedDataJson = new Gson().toJson(batsmanList);
        return sortedDataJson;
    }*/
public List<CricketLeagueDao> getSortedData(SortingFields.fields sortFields) {
    Comparator<CricketLeagueDao> comparator=new SortingFields().getParameter(sortFields);
    cricketMap=cricketMap .stream()
            .sorted(comparator.reversed())
            .collect(Collectors.toList());
    return this.cricketMap;
}
/*
    public String getSortedWicketsData(SortingFields.fields sortFields) {
        Comparator<CricketLeagueDao> bowlerComparator = new SortingFields().getParameters(sortFields);
        ArrayList bowlerList = cricketMap.values().stream().
                sorted(bowlerComparator).
                collect(Collectors.toCollection(ArrayList::new));
        String sortedDataJson = new Gson().toJson(bowlerList);
        return sortedDataJson;
    }*/
}
