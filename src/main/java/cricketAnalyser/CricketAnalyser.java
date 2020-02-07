package cricketAnalyser;

import java.util.*;
import java.util.stream.Collectors;

public class CricketAnalyser {

    Map<String, CricketLeagueDao> cricketMap = new HashMap<>();
   // private CricketAdapterFactory cricketAdapterFactory;
    public CricketMock cricketMock=new CricketMock();
    public Cricket cricket;

    public CricketAnalyser(CricketMock cricketMock) {
        this.cricketMock = cricketMock;
    }

    public CricketAnalyser() {
    }

    public CricketAnalyser(Cricket batsman) {
    }

    public enum Cricket {
        BATSMAN, BOWLER
    }

    public CricketAnalyser(CricketMock cricketMock, Cricket cricket) {
        this.cricketMock =cricketMock;
        this.cricket=cricket;
    }


    public int loadCricketData(Cricket cricket, String... csvFilePath) throws CricketAnalyserException {
        cricketMap = cricketMock.loadCricketData(cricket, csvFilePath);
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