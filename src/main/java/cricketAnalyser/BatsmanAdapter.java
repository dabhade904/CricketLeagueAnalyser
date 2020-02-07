package cricketAnalyser;

import cessusanalyser.CSVBuilderException;
import cessusanalyser.CSVBuilderFactory;
import cessusanalyser.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.StreamSupport;

public class BatsmanAdapter extends CricketAdapter {

    Map<String,CricketLeagueDao>cricketLeagueMap=new HashMap<>();

    @Override
    public <E> Map<String, CricketLeagueDao> loadCricketData(String... csvFilePath) throws CricketAnalyserException {
        Map<String, CricketLeagueDao> cricketLeagueMap = super.loadCricketData(Batsman.class, csvFilePath[0]);
        if (csvFilePath.length==2)   {
            this.combineData(cricketLeagueMap,csvFilePath[1]);
        }
        return cricketLeagueMap;
    }

    private int combineData(Map<String, CricketLeagueDao> cricketLeagueMap, String csvFilePath) throws CricketAnalyserException {
        Reader reader = null;
        try {
            reader= Files.newBufferedReader(Paths.get(csvFilePath));
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<Bowler> csvFileIterator = csvBuilder.getCSVFileIterator(reader, Bowler.class);
            Iterable<Bowler> csvIterable = () -> csvFileIterator;
            StreamSupport.stream(csvIterable.spliterator(), false)
                    .filter(bowler -> cricketLeagueMap.get(bowler.player) != null)
                    .forEach(bowler -> {cricketLeagueMap.get(bowler.player).average = bowler.ball_average;
                        cricketLeagueMap.get(bowler.player).wickets = bowler.wickets;
                    });
            return cricketLeagueMap.size();
        } catch (IOException e) {
            throw new CricketAnalyserException(e.getMessage(),
                    CricketAnalyserException.ExceptionType.CRICKET_FILE_PROBLEM);
        } catch (CSVBuilderException e) {
            throw new CricketAnalyserException(e.getMessage(), e.type.name());
        }
    }



}