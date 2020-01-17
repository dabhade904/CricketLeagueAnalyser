import cricketAnalyser.Batsman;
import cricketAnalyser.CricketAnalyser;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class CricketAnalyserTest {

    private static final String IPL2019_RUNS_CSV_FILE_PATH = "/home/admin1/IdeaProjects/CensusAnalyser/CricketLeague/src/test/resources/IPL2019FactSheetMostRuns.csv";

    @Test
    public void givenLeagueDataCSVFIle_shouldReturnExactCount() {
        CricketAnalyser cricketAnalyser = new CricketAnalyser();
        int loadData = cricketAnalyser.readCricketMostRunsData(IPL2019_RUNS_CSV_FILE_PATH);
        Assert.assertEquals(100, loadData);
    }

    @Test
    public void givenLeagueDataCSVFile_whenSortedOnAverage_shouldReturnsortedData() {
        CricketAnalyser cricketAnalyse = new CricketAnalyser();
        cricketAnalyse.readCricketMostRunsData(IPL2019_RUNS_CSV_FILE_PATH);
        List<Batsman> sortedData = cricketAnalyse.getSoringBattingAverage();
        Assert.assertTrue(sortedData.get(0).toString().contains("MS Dhoni"));
    }

    @Test
    public void givenLeagueDataCSVFile_whenSortedOnAverage_shouldReturnHighestBattingAverage() {
        CricketAnalyser cricketAnalyse = new CricketAnalyser();
        cricketAnalyse.readCricketMostRunsData(IPL2019_RUNS_CSV_FILE_PATH);
        List<Batsman> sortedData = cricketAnalyse.getSoringBattingAverage();
        Assert.assertEquals(83.2, sortedData.get(0).avarage, 0);
    }

    @Test
    public void givenLeagueDataCSVFile_whenSortedOnAverage_shouldReturnLowestBattingAverage() {
        CricketAnalyser cricketAnalyse = new CricketAnalyser();
        cricketAnalyse.readCricketMostRunsData(IPL2019_RUNS_CSV_FILE_PATH);
        List<Batsman> sortedData = cricketAnalyse.getSoringBattingAverage();
        Assert.assertEquals(0.0, sortedData.get(99).avarage, 0);
    }
}
