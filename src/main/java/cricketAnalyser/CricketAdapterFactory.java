package cricketAnalyser;

import java.util.*;

public class CricketAdapterFactory {
    public <E> Map<String, CricketLeagueDao> getCricketData(CricketAnalyser.Cricket cricket, String... csvFilePath) throws CricketAnalyserException {
        if (cricket.equals(CricketAnalyser.Cricket.BATSMAN)) {
            return new BatsmanAdapter().loadCricketData(csvFilePath);
        } else if (cricket.equals(CricketAnalyser.Cricket.BOWLER)) {
            return new BowlerAdapter().loadCricketData(csvFilePath);
        } else {
            throw new CricketAnalyserException("INCORRECT_DATA", CricketAnalyserException.ExceptionType.CRICKET_FILE_PROBLEM);
        }
    }
}


