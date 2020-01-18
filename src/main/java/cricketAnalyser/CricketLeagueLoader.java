package cricketAnalyser;

import cessusanalyser.CSVBuilderException;
import cessusanalyser.CSVBuilderFactory;
import cessusanalyser.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CricketLeagueLoader {
    List<Batsman> batsmanList = new ArrayList<>();

    public List<Batsman> readCricketMostRunsData(String csvFilePath) throws CricketAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder icsvBuilder = CSVBuilderFactory.createCSVBuilder();
            batsmanList = icsvBuilder.getCSVFileList(reader, Batsman.class);
            return batsmanList;
        } catch (IOException e) {
            throw new CricketAnalyserException(e.getMessage(),
                    CricketAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            throw new CricketAnalyserException(e.getMessage(),
                    CricketAnalyserException.ExceptionType.FILE_NOT_FOUND);
        }
        return null;
    }

}
