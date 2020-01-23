package cricketAnalyser;

import cessusanalyser.CSVBuilderException;
import cessusanalyser.CSVBuilderFactory;
import cessusanalyser.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.StreamSupport;

public abstract class CricketAdapter {

    public abstract <E> Map<String,CricketLeagueDao> loadCricketData(String... csvFilePath) throws CricketAnalyserException;

    Map<String,CricketLeagueDao> cricketMap = new HashMap<>();

    public <E> Map<String,CricketLeagueDao> loadCricketData(Class<E> crickteClass, String... filePath) throws CricketAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath[0]))) {
            ICSVBuilder icsvBuilder = CSVBuilderFactory.createCSVBuilder();
            List<E> csvFileList = icsvBuilder.getCSVFileList(reader, crickteClass);
            if (crickteClass.getName().equals("cricketAnalyser.Batsman")) {
                StreamSupport.stream(csvFileList.spliterator(), false)
                        .map(Batsman.class::cast)
                        .forEach(cricketLeague -> cricketMap.put(cricketLeague.player,new CricketLeagueDao(cricketLeague)));
            }
            if (crickteClass.getName().equals("cricketAnalyser.Bowler")) {
                StreamSupport.stream(csvFileList.spliterator(), false)
                        .map(Bowler.class::cast)
                        .forEach(cricketLeague -> cricketMap.put(cricketLeague.player,new CricketLeagueDao(cricketLeague)));
            }
            return cricketMap;
        } catch (CSVBuilderException e) {
            throw new CricketAnalyserException(e.getMessage(),
                    CricketAnalyserException.ExceptionType.CRICKET_FILE_PROBLEM);
        } catch (IOException e) {
            throw new CricketAnalyserException(e.getMessage(), CricketAnalyserException.ExceptionType.UNABLE_TO_PARSE);
        } catch (RuntimeException e) {
            throw new CricketAnalyserException(e.getMessage(),
                    CricketAnalyserException.ExceptionType.DELIMITER_OR_HEADER_PROBLEM);
        }
    }
}