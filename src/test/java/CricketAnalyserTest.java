import cricketAnalyser.Batsman;
import cricketAnalyser.CricketAnalyser;
import cricketAnalyser.CricketAnalyserException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

public class CricketAnalyserTest {

    private static final String IPL2019_RUNS_CSV_FILE_PATH = "/home/admin1/IdeaProjects/CensusAnalyser/CricketLeague/src/test/resources/IPL2019FactSheetMostRuns.csv";
    private static final String WRONG_CSV_FILE_PATH = "/home/admin1/IdeaProjects/CensusAnalyser/CricketLeague/src/test/resources/WrongFileData.csv";
    private static final String WRONG_CSV_FILE_TYPE = "/home/admin1/IdeaProjects/CensusAnalyser/CricketLeague/src/test/resources/WrongFileType.txt";
    private static final String WRONG_CSV_FILE_PATH_DELIMITER = "/home/admin1/IdeaProjects/CensusAnalyser/CricketLeague/src/test/resources/wrongFileDelimiter.csv";
    private static final String WRONG_CSV_FILE_PATH_HEADER = "/home/admin1/IdeaProjects/CensusAnalyser/CricketLeague/src/test/resources/wrongFileHeader.csv";

    @Test
    public void givenLeagueDataCSVFIle_shouldReturnExactCount() {
        CricketAnalyser cricketAnalyser = new CricketAnalyser();
        try {
            int loadData = loadData = cricketAnalyser.readCricketMostRunsData(IPL2019_RUNS_CSV_FILE_PATH);
            Assert.assertEquals(100, loadData);

        } catch (CricketAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenLeagueDataCSVFile_whenSortedOnAverage_shouldReturnData() {
        CricketAnalyser cricketAnalyse = new CricketAnalyser();
        try {
            cricketAnalyse.readCricketMostRunsData(IPL2019_RUNS_CSV_FILE_PATH);
            List<Batsman> sortedData = cricketAnalyse.getSoringBattingAverage();
            Assert.assertTrue(sortedData.get(0).toString().contains("MS Dhoni"));

        } catch (CricketAnalyserException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void givenLeagueDataCSVFile_whenSortedOnAverage_shouldReturnHighestBattingAverage() {
        CricketAnalyser cricketAnalyse = new CricketAnalyser();
        try {
            cricketAnalyse.readCricketMostRunsData(IPL2019_RUNS_CSV_FILE_PATH);
            List<Batsman> sortedData = cricketAnalyse.getSoringBattingAverage();
            Assert.assertEquals(83.2, sortedData.get(0).avarage, 0);
        } catch (CricketAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenLeagueDataCSVFile_whenSortedOnAverage_shouldReturnLowestBattingAverage() {
        CricketAnalyser cricketAnalyse = new CricketAnalyser();
        try {
            cricketAnalyse.readCricketMostRunsData(IPL2019_RUNS_CSV_FILE_PATH);
            List<Batsman> sortedData = cricketAnalyse.getSoringBattingAverage();
            Assert.assertEquals(0.0, sortedData.get(99).avarage, 0);
        } catch (CricketAnalyserException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void givenLeagueData_withWrongFile_shouldThrowException() {
        try {
            CricketAnalyser censusAnalyser = new CricketAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CricketAnalyserException.class);
            censusAnalyser.readCricketMostRunsData(WRONG_CSV_FILE_PATH);
        } catch (CricketAnalyserException e) {
            Assert.assertEquals(CricketAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndiaCSVFile_whenCorrectButTypeIncorrect_ShouldReturnCustomException() {
        {
            CricketAnalyser censusAnalyser = new CricketAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CricketAnalyserException.class);
            try {
                censusAnalyser.readCricketMostRunsData(WRONG_CSV_FILE_TYPE);
            } catch (CricketAnalyserException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void whenGivenCSVFileCorrect_butDelimiterIncorrect_shouldReturnCustomException() {
        CricketAnalyser cricketAnalyser = new CricketAnalyser();
        ExpectedException expectedException = ExpectedException.none();
        expectedException.expect(CricketAnalyserException.class);
        try {
            cricketAnalyser.readCricketMostRunsData(WRONG_CSV_FILE_PATH_DELIMITER);
        } catch (CricketAnalyserException e) {
            Assert.assertEquals(CricketAnalyserException.ExceptionType.FILE_NOT_FOUND, e.type);
        }
    }

    @Test
    public void whenGivenCSVFileCorrect_butCSVHeaderIncorrect_shouldReturnCustomException() {
        CricketAnalyser cricketAnalyser = new CricketAnalyser();
        ExpectedException expectedException = ExpectedException.none();
        expectedException.expect(CricketAnalyserException.class);
        try {
            cricketAnalyser.readCricketMostRunsData(WRONG_CSV_FILE_PATH_HEADER);
        } catch (CricketAnalyserException e) {
            Assert.assertEquals(CricketAnalyserException.ExceptionType.FILE_NOT_FOUND, e.type);
        }
    }

    @Test
    public void givenLeagueDataCSVFile_whenSortedOnStrikeRate_shouldReturnHigestStrikeRate() {
        CricketAnalyser cricketAnalyse = new CricketAnalyser();
        try {
            cricketAnalyse.readCricketMostRunsData(IPL2019_RUNS_CSV_FILE_PATH);
            List<Batsman> sortedData = cricketAnalyse.getSoringStrikeRate();
            Assert.assertEquals(333.33, sortedData.get(0).strikeRate, 0);
        } catch (CricketAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenLeagueDataCSVFile_whenSortedOnStrikeRate_shouldReturnLowestStrikeRate() {
        CricketAnalyser cricketAnalyse = new CricketAnalyser();
        try {
            cricketAnalyse.readCricketMostRunsData(IPL2019_RUNS_CSV_FILE_PATH);
            List<Batsman> sortedData = cricketAnalyse.getSoringStrikeRate();
            Assert.assertEquals(63.15, sortedData.get(99).strikeRate, 0);
        } catch (CricketAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenLeagueDataCSVFile_whenSortedOnStrikeRate_shouldReturnMostSixAndFour() {
        CricketAnalyser cricketAnalyse = new CricketAnalyser();
        try {
            cricketAnalyse.readCricketMostRunsData(IPL2019_RUNS_CSV_FILE_PATH);
            List<Batsman> sortedData = cricketAnalyse.getMostSixAndFour();
            System.out.println(sortedData);
            Assert.assertEquals("Andre Russell", sortedData.get(0).player);
        } catch (CricketAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenLeagueDataCSVFile_whenSortedOnStrikeRate_shouldReturnMostSixAnd() {
        CricketAnalyser cricketAnalyse = new CricketAnalyser();
        try {
            cricketAnalyse.readCricketMostRunsData(IPL2019_RUNS_CSV_FILE_PATH);
            List<Batsman> sortedData = cricketAnalyse.getMostSixAndFour();
            Assert.assertEquals("Tim Southee", sortedData.get(99).player);
        } catch (CricketAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenLeagueDataCSVFile_whenSortedOnStrikeRate_shouldReturnMostSixAndFourWithHighestStrikeRate() {
        CricketAnalyser cricketAnalyse = new CricketAnalyser();
        try {
            cricketAnalyse.readCricketMostRunsData(IPL2019_RUNS_CSV_FILE_PATH);
            List<Batsman> sortedData = cricketAnalyse.getBestStrikeRateSixAndFour();
            Assert.assertEquals("Andre Russell", sortedData.get(0).player);
        } catch (CricketAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenLeagueDataCSVFile_whenSortedOnStrikeRate_shouldReturnMostSixAndFourWithLowestStrikeRate() {
        try {
            CricketAnalyser cricketAnalyse = new CricketAnalyser();
            cricketAnalyse.readCricketMostRunsData(IPL2019_RUNS_CSV_FILE_PATH);
            List<Batsman> sortedData = cricketAnalyse.getBestStrikeRateSixAndFour();
            Assert.assertEquals("Shakib Al Hasan", sortedData.get(99).player);
        } catch (CricketAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenLeagueDataCSVFile_whenSortedOnStrikeRate_shouldReturnHighestAverage() {
        try {
            CricketAnalyser cricketAnalyse = new CricketAnalyser();
            cricketAnalyse.readCricketMostRunsData(IPL2019_RUNS_CSV_FILE_PATH);
            List<Batsman> sortedData = cricketAnalyse.getAverageWithBestStrikeRate();
            Assert.assertEquals("MS Dhoni", sortedData.get(0).player);
        } catch (CricketAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenLeagueDataCSVFile_whenSortedOnStrikeRate_shouldReturnLowestAverage() {
        try {
            CricketAnalyser cricketAnalyse = new CricketAnalyser();
            cricketAnalyse.readCricketMostRunsData(IPL2019_RUNS_CSV_FILE_PATH);
            List<Batsman> sortedData = cricketAnalyse.getAverageWithBestStrikeRate();
            Assert.assertEquals("Tim Southee", sortedData.get(99).player);
        } catch (CricketAnalyserException e) {
            e.printStackTrace();
        }
    }
}

