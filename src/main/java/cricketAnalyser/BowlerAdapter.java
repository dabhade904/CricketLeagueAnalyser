package cricketAnalyser;

import java.util.List;

public class BowlerAdapter extends CricketAdapter{

    @Override
    public <E> List <CricketLeagueDao> loadCricketData(String... csvFilePath) throws CricketAnalyserException {
        List <CricketLeagueDao>cricketLeagueDaos=super.loadCricketData(Bowler.class,csvFilePath[0]);
        return cricketLeagueDaos;
    }
}
