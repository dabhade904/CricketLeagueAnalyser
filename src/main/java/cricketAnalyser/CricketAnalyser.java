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
import java.util.Collections;
import java.util.Comparator;
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

    public List getSoringBattingAverage() throws CricketAnalyserException {
        if ((batsmanList == null) || (batsmanList.size() == 0)) {
            throw new CricketAnalyserException("No Cricket data", CricketAnalyserException.ExceptionType.NO_CRICKET_DATA);
        }
        batsmanList = batsmanList.stream()
                .sorted((data1, data2) -> data1.avarage - data2.avarage > 0 ? -1 : 1)
                .collect(Collectors.toList());
        return batsmanList;
    }

    public List getSoringStrikeRate() throws CricketAnalyserException {
        if ((batsmanList == null) || (batsmanList.size() == 0)) {
            throw new CricketAnalyserException("No Cricket data", CricketAnalyserException.ExceptionType.NO_CRICKET_DATA);
        }
        batsmanList = batsmanList.stream()
                .sorted((data1, data2) -> data1.strikeRate - data2.strikeRate > 0 ? -1 : 1)
                .collect(Collectors.toList());
        return batsmanList;
    }

    public List getMostSixAndFour() throws CricketAnalyserException {
        if ((batsmanList == null) || (batsmanList.size() == 0)) {
            throw new CricketAnalyserException("No Cricket data", CricketAnalyserException.ExceptionType.NO_CRICKET_DATA);
        }
        batsmanList = batsmanList.stream()
                .sorted((data1, data2) -> (data2.sixs * 6 + data2.fours * 4) - (data1.sixs * 6 + data1.fours * 4))
                .collect(Collectors.toList());
        return batsmanList;
    }


    public List getBestStrikeRateSixAndFour() throws CricketAnalyserException {
        if ((batsmanList == null) || (batsmanList.size() == 0)) {
            throw new CricketAnalyserException("No Cricket data", CricketAnalyserException.ExceptionType.NO_CRICKET_DATA);
        }
        Comparator<Batsman> comparator = ((data1, data2) -> (data2.sixs * 6 + data2.fours * 4) - (data1.sixs * 6 + data1.fours * 4));
        comparator = comparator.thenComparing((data1, data2) -> data1.strikeRate - data2.strikeRate > 0 ? -1 : 1);
        batsmanList = batsmanList.stream()
                .sorted(comparator)
                .collect(Collectors.toList());
        System.out.println(batsmanList);
        return batsmanList;
    }

    public List getAverageWithBestStrikeRate() throws CricketAnalyserException {
        if ((batsmanList == null) || (batsmanList.size() == 0)) {
            throw new CricketAnalyserException("No Cricket data", CricketAnalyserException.ExceptionType.NO_CRICKET_DATA);
        }
        Comparator<Batsman> comparator = ((data1, data2) -> (int) ((data1.avarage) - (data2.avarage)));
        comparator = comparator.thenComparing((data1, data2) -> data1.strikeRate - data2.strikeRate < 0 ? -1 : 1);
        Collections.sort(batsmanList, comparator);
        Collections.reverse(batsmanList);
        System.out.println(batsmanList);
        return batsmanList;
    }
}