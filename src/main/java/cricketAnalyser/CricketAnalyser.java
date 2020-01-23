package cricketAnalyser;

import java.util.*;
import java.util.stream.Collectors;

public class CricketAnalyser {
    List<CricketLeagueDao> cricketMap = new ArrayList<>();

    public Cricket cricket;

    public CricketAnalyser() {

    }

    public enum Cricket {
        BATSMAN, BOWLER
    }

    public CricketAnalyser(Cricket cricket) {
        this.cricket = cricket;
    }

    public int loadCricketData(Cricket cricket, String... csvFilePath) throws CricketAnalyserException {
        cricketMap = new CricketAdapterFactory().getCricketData(cricket, csvFilePath);
        return cricketMap.size();
    }

    public List getSortedData(SortingFields.fields sortedFields) {
        Comparator<CricketLeagueDao> comparator = new SortingFields().getParameter(sortedFields);
        cricketMap = cricketMap.stream()
                .sorted(comparator)
                .collect(Collectors.toList());
        return cricketMap;
    }
}