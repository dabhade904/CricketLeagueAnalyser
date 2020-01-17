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
import java.util.stream.Collectors;

public class CricketAnalyser {
    List<Batsman> batsmanList = new ArrayList<>();

    public int readCricketMostRunsData(String csvFilePath) {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder icsvBuilder = CSVBuilderFactory.createCSVBuilder();
            batsmanList = icsvBuilder.getCSVFileList(reader, Batsman.class);
            return batsmanList.size();
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List getSoringBattingAverage() {

        batsmanList = batsmanList.stream()
                .sorted((data1, data2) -> data1.avarage - data2.avarage > 0 ? -1 : 1)
                .collect(Collectors.toList());
        return batsmanList;
    }
}