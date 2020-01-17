package cricketAnalyser;

import cessusanalyser.CSVBuilderException;
import cessusanalyser.CSVBuilderFactory;
import cessusanalyser.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class CricketAnalyser {

    public int  readCricketMostRunsData(String csvFilePath) {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder icsvBuilder = CSVBuilderFactory.createCSVBuilder();
            List<Batsman> list = icsvBuilder.getCSVFileList(reader, Batsman.class);
            System.out.println("list of batsman" + list);
            return list.size();
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
}