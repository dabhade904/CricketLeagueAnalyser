package cricketAnalyser;

import cessusanalyser.CSVBuilderException;
import cessusanalyser.CSVBuilderFactory;
import cessusanalyser.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.StreamSupport;

public class CricketLeagueLoader {
    Map<String,Batsman> batsmanMap = new HashMap();

    public  Map<String,Batsman> readCricketMostRunsData(String csvFilePath) throws CricketAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder icsvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<Batsman>batsmanIterator=icsvBuilder.getCSVFileIterator(reader,Batsman.class);
            Iterable<Batsman>batsmanIterable=() -> batsmanIterator;
            StreamSupport.stream(batsmanIterable.spliterator(),false).map(Batsman.class::cast).forEach(batsmanRuns ->batsmanMap.put(batsmanRuns.player,batsmanRuns));
            return batsmanMap;
        } catch (IOException e) {
            throw new CricketAnalyserException(e.getMessage(),
                    CricketAnalyserException.ExceptionType.CRICKET_FILE_PROBLEM);
        } catch (CSVBuilderException e) {
            throw new CricketAnalyserException(e.getMessage(),CricketAnalyserException.ExceptionType.UNABLE_TO_PARSE);
        } catch (RuntimeException e) {
            throw new CricketAnalyserException(e.getMessage(),
                    CricketAnalyserException.ExceptionType.DELIMITER_OR_HEADER_PROBLEM);
        }
    }

}
