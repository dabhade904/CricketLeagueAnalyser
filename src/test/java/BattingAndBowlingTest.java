import cricketAnalyser.Batsman;
import cricketAnalyser.CricketAnalyser;
import cricketAnalyser.CricketAnalyserException;
import cricketAnalyser.SortingFields;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class BattingAndBowlingTest {
    private static final String IPL2019_RUNS_CSV_FILE_PATH = "/home/admin1/Downloads/CricketLeagueAnalyser/src/test/resources/IPL2019FactSheetMostRuns.csv";
    private static final String IPL2019_WICKETS_CSV_FILE_PATH = "/home/admin1/Downloads/CricketLeagueAnalyser/src/test/resources/IPL2019FactSheeMostWickets.csv";

    @Test
    public void givenLeagueBowlerAndBatsmanCSVFile_WhenSortedOnAverage_ShouldReturnSortedResult() {
        CricketAnalyser cricketAnalyser = new CricketAnalyser();
        try {
            cricketAnalyser.loadCricketData(CricketAnalyser.Cricket.BATSMAN, IPL2019_RUNS_CSV_FILE_PATH,IPL2019_WICKETS_CSV_FILE_PATH);
            List<Batsman> sortedData = cricketAnalyser.getSortedData(CricketAnalyser.Cricket.BATSMAN, SortingFields.fields.BATTING_AND_BOWLING_AVG);
            Assert.assertEquals("Krishnappa Gowtham", sortedData.get(0).player);
            Assert.assertEquals("Harpreet Brar", sortedData.get(98).player);
        } catch (CricketAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenLeagueBowlerAndBatsmanCSVFile_whenSorted_ShouldReturnAllRounder() {
        CricketAnalyser cricketAnalyser = new CricketAnalyser();
        try {
            cricketAnalyser.loadCricketData(CricketAnalyser.Cricket.BATSMAN, IPL2019_RUNS_CSV_FILE_PATH,IPL2019_WICKETS_CSV_FILE_PATH);
            List<Batsman> sortedData = cricketAnalyser.getSortedData(CricketAnalyser.Cricket.BATSMAN, SortingFields.fields.ALL_ROUNDER);
            Assert.assertEquals("Hardik Pandya", sortedData.get(0).player);
        } catch (CricketAnalyserException e) {
            e.printStackTrace();
        }
    }
}
