package cricketAnalyser;

import cessusanalyser.CSVBuilderException;
import cessusanalyser.CSVBuilderFactory;
import cessusanalyser.ICSVBuilder;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class CricketAnalyser {
    Map<String, Batsman> batsmanMap = new HashMap<>();
    List bowlerList=new ArrayList();
    public Map<String, Batsman> loadCricketData(String csvFilePath) throws CricketAnalyserException {
        batsmanMap = new CricketLeagueLoader().readCricketMostRunsData(csvFilePath);
        return batsmanMap;
    }

    public int readCricketMostWicketsData(String csvFilePath) throws CricketAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder icsvBuilder = CSVBuilderFactory.createCSVBuilder();
            bowlerList = icsvBuilder.getCSVFileList(reader, Bowler.class);
            System.out.println(bowlerList);
            return bowlerList.size();

        } catch (CSVBuilderException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            throw new CricketAnalyserException(e.getMessage(),
                    CricketAnalyserException.ExceptionType.FILE_NOT_FOUND);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }


    public int getNumberOfRecord(String csvFilePath) {
        int count = 0;
        try {
            Map<String, Batsman> batsmanMap1 = loadCricketData(csvFilePath);
            return count=batsmanMap1.size();
        } catch (CricketAnalyserException e) {
        }
        return count;
    }

    public String getSortedData(SortingFields.fields sortFields) {
        Comparator<Batsman> batsmanComparator = new SortingFields().getParameter(sortFields);
        ArrayList batsmanList = batsmanMap.values().stream().
                sorted(batsmanComparator).
                collect(Collectors.toCollection(ArrayList::new));
        String sortedDataJson = new Gson().toJson(batsmanList);
        return sortedDataJson;
    }
}
