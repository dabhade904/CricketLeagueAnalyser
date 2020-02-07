package cricketAnalyser;

import java.util.*;
import java.util.stream.Collectors;

public class CricketAnalyser {
    Map<String, CricketLeagueDao> cricketMap = new HashMap<>();
    private CricketAdapterFactory cricketAdapterFactory;
    public Cricket cricket;

    public CricketAnalyser() {

    }
    public enum Cricket {
        BATSMAN, BOWLER
    }

    public CricketAnalyser(CricketAdapterFactory cricketAdapterFactory, Cricket cricket) {
        this.cricketAdapterFactory = cricketAdapterFactory;
        this.cricket=cricket;
    }


    public int loadCricketData(Cricket cricket, String... csvFilePath) throws CricketAnalyserException {
        cricketMap = cricketAdapterFactory.getCricketData(cricket, csvFilePath);
        return cricketMap.size();
    }

    public List getSortedData(Cricket cricket, SortingFields.fields... sortField) {
        Comparator<CricketLeagueDao> batsmanComparator = null;
        if (sortField.length == 0)
            batsmanComparator = SortingFields.getParameter(sortField[1]);
        else {
            batsmanComparator = SortingFields.getParameter(sortField[0]);
        }
        List cricketMap1 = cricketMap.values().stream().
                sorted(batsmanComparator).
                map(batsmanDAO -> batsmanDAO.getBatsmanDTO(cricket))
                .collect(Collectors.toList());
        return cricketMap1;
    }
}