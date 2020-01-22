package cricketAnalyser;

import cessusanalyser.CSVBuilderException;
import cessusanalyser.CSVBuilderFactory;
import cessusanalyser.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class CricketLeagueLoader {
    List<CricketLeagueDao> cricketList = new ArrayList();

    public <E> List readCricketMostRunsData(String csvFilePath) throws CricketAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder icsvBuilder = CSVBuilderFactory.createCSVBuilder();
            List<E> csvFileList = icsvBuilder.getCSVFileList(reader, Batsman.class);
            StreamSupport.stream(csvFileList.spliterator(), false)
                    .map(Batsman.class::cast)
                    .forEach(cricketLeague -> cricketList.add(new CricketLeagueDao(cricketLeague)));
            return this.cricketList;
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

    public <E> List readCricketMostWicketsData(String csvFilePath) throws CricketAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder icsvBuilder = CSVBuilderFactory.createCSVBuilder();
            List <E>csvFileList = icsvBuilder.getCSVFileList(reader,Bowler.class);
             StreamSupport.stream(csvFileList.spliterator(), false)
                    .map(Bowler.class::cast)
                    .forEach(cricketLeague -> cricketList.add(new CricketLeagueDao(cricketLeague)));
            return this.cricketList;
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
