package cricketAnalyser;

import java.util.List;
import java.util.Map;

public class BatsmanAdapter extends CricketAdapter {

    @Override
    public <E> Map<String,CricketLeagueDao> loadCricketData(String... csvFilePath) throws CricketAnalyserException {
        Map<String,CricketLeagueDao>cricketLeagueDaos=super.loadCricketData(Batsman.class,csvFilePath[0]);
        return cricketLeagueDaos;

    }
}
