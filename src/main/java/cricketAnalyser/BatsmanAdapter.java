package cricketAnalyser;

import java.util.List;

public class BatsmanAdapter extends CricketAdapter {

    @Override
    public <E> List <CricketLeagueDao> loadCricketData(String... csvFilePath) throws CricketAnalyserException {
        List<CricketLeagueDao>cricketLeagueDaos=super.loadCricketData(Batsman.class,csvFilePath[0]);
        return cricketLeagueDaos;

    }
}
