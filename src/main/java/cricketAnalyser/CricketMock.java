package cricketAnalyser;

import java.util.*;

public class CricketMock {

    public Map<String, CricketLeagueDao> loadCricketData(CricketAnalyser.Cricket cricket, String... csvFile) throws CricketAnalyserException {
        return CricketAdapterFactory.getCricketData(cricket, csvFile);
    }
}
