package cricketAnalyser;

import cessusanalyser.CSVBuilderException;
import cessusanalyser.CSVBuilderFactory;
import cessusanalyser.ICSVBuilder;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CricketAnalyser {
    List<Batsman> batsmanList = new ArrayList<>();

    public int readCricketMostRunsData(String csvFilePath) throws CricketAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder icsvBuilder = CSVBuilderFactory.createCSVBuilder();
            batsmanList = icsvBuilder.getCSVFileList(reader, Batsman.class);
            return batsmanList.size();


        } catch (IOException e) {
            throw new CricketAnalyserException(e.getMessage(),
                    CricketAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            throw new CricketAnalyserException(e.getMessage(),
                    CricketAnalyserException.ExceptionType.FILE_NOT_FOUND);
        }
        return 0;
    }

    public List getSoringBattingAverage() {

        batsmanList = batsmanList.stream()
                .sorted((data1, data2) -> data1.avarage - data2.avarage > 0 ? -1 : 1)
                .collect(Collectors.toList());
        return batsmanList;
    }

    public List getSoringStrikeRate() {

        batsmanList = batsmanList.stream()
                .sorted((data1, data2) -> data1.strikeRate - data2.strikeRate > 0 ? -1 : 1)
                .collect(Collectors.toList());
        return batsmanList;
    }

    public List getMostSixAndFor() {
        batsmanList = batsmanList.stream()
                .sorted((data1, data2) -> (data2.sixs * 6 + data2.sixs * 4) - (data1.sixs * 6 + data1.sixs * 4))
                .collect(Collectors.toList());
        return batsmanList;
    }

}