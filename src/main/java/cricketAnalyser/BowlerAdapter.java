package cricketAnalyser;

import java.util.*;

public class BowlerAdapter extends CricketAdapter{

    @Override
    public <E> Map<String, CricketLeagueDao> loadCricketData(String... csvFilePath) throws CricketAnalyserException {
        Map<String, CricketLeagueDao> cricketLeagueMap = super.loadCricketData(Bowler.class, csvFilePath[0]);
        return cricketLeagueMap;    }
}
