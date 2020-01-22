import cricketAnalyser.*;
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

    private static final String IPL2019_WICKETS_CSV_FILE_PATH = "/home/admin1/IdeaProjects/CensusAnalyser/CricketLeague/src/test/resources/IPL2019FactSheeMostWickets.csv";

    @Test
    public void givenLeagueDataCSVFIle_shouldReturnExactCount() throws CricketAnalyserException {
        CricketAnalyser cricketAnalyser = new CricketAnalyser();
        int loadData = cricketAnalyser.loadCricketData(CricketAnalyser.Cricket.BATSMAN,IPL2019_RUNS_CSV_FILE_PATH);
        Assert.assertEquals(100, loadData);
    }

    @Test
    public void givenLeagueDataCSVFile_whenSortedOnAverage_shouldReturnData() {
        CricketAnalyser cricketAnalyse = new CricketAnalyser();
        try {
            cricketAnalyse.loadCricketData(CricketAnalyser.Cricket.BATSMAN,IPL2019_RUNS_CSV_FILE_PATH);
            List<CricketLeagueDao> sortedData = cricketAnalyse.getSortedData(SortingFields.fields.AVERAGE);
            Assert.assertEquals("MS Dhoni", sortedData.get(0).player);
        } catch (CricketAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenLeagueDataCSVFile_whenSortedOnAverage_shouldReturnHighestBattingAverage() {
        CricketAnalyser cricketAnalyse = new CricketAnalyser();
        try {
            cricketAnalyse.loadCricketData(CricketAnalyser.Cricket.BATSMAN,IPL2019_RUNS_CSV_FILE_PATH);
            List<CricketLeagueDao> sortedData = cricketAnalyse.getSortedData(SortingFields.fields.AVERAGE);
            Assert.assertEquals(83.2, sortedData.get(0).average, 0);
        } catch (CricketAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenLeagueDataCSVFile_whenSortedOnAverage_shouldReturnLowestBattingAverage() {
        CricketAnalyser cricketAnalyse = new CricketAnalyser();
        try {
            cricketAnalyse.loadCricketData(CricketAnalyser.Cricket.BATSMAN,IPL2019_RUNS_CSV_FILE_PATH);
            List<CricketLeagueDao> sortedData = cricketAnalyse.getSortedData(SortingFields.fields.AVERAGE);
            Assert.assertEquals(0.0, sortedData.get(99).average, 0);
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
            censusAnalyser.loadCricketData(CricketAnalyser.Cricket.BATSMAN,WRONG_CSV_FILE_PATH);
        } catch (CricketAnalyserException e) {
            Assert.assertEquals(CricketAnalyserException.ExceptionType.DELIMITER_OR_HEADER_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndiaCSVFile_whenCorrectButTypeIncorrect_ShouldReturnCustomException() {
        {
            CricketAnalyser censusAnalyser = new CricketAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CricketAnalyserException.class);
            try {
                censusAnalyser.loadCricketData(CricketAnalyser.Cricket.BATSMAN,WRONG_CSV_FILE_TYPE);
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
            cricketAnalyser.loadCricketData(CricketAnalyser.Cricket.BATSMAN,WRONG_CSV_FILE_PATH_DELIMITER);
        } catch (CricketAnalyserException e) {
            Assert.assertEquals(CricketAnalyserException.ExceptionType.DELIMITER_OR_HEADER_PROBLEM, e.type);
        }
    }

    @Test
    public void whenGivenCSVFileCorrect_butCSVHeaderIncorrect_shouldReturnCustomException() {
        CricketAnalyser cricketAnalyser = new CricketAnalyser();
        ExpectedException expectedException = ExpectedException.none();
        expectedException.expect(CricketAnalyserException.class);
        try {
            cricketAnalyser.loadCricketData(CricketAnalyser.Cricket.BATSMAN,WRONG_CSV_FILE_PATH_HEADER);
        } catch (CricketAnalyserException e) {
            Assert.assertEquals(CricketAnalyserException.ExceptionType.DELIMITER_OR_HEADER_PROBLEM, e.type);
        }
    }

    @Test
    public void givenLeagueDataCSVFile_whenSortedOnStrikeRate_shouldReturnHighestStrikeRate() {
        CricketAnalyser cricketAnalyse = new CricketAnalyser();
        try {
            cricketAnalyse.loadCricketData(CricketAnalyser.Cricket.BATSMAN,IPL2019_RUNS_CSV_FILE_PATH);
            List<CricketLeagueDao> sortedData = cricketAnalyse.getSortedData(SortingFields.fields.STRIKERATE);
            Assert.assertEquals(333.33, sortedData.get(0).strikeRate, 0);
        } catch (CricketAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenLeagueDataCSVFile_whenSortedOnStrikeRate_shouldReturnLowestStrikeRate() {
        CricketAnalyser cricketAnalyse = new CricketAnalyser();
        try {
            cricketAnalyse.loadCricketData(CricketAnalyser.Cricket.BATSMAN,IPL2019_RUNS_CSV_FILE_PATH);
            List<CricketLeagueDao> sortedData = cricketAnalyse.getSortedData(SortingFields.fields.STRIKERATE);
            Assert.assertEquals(63.15, sortedData.get(99).strikeRate, 0);
        } catch (CricketAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenLeagueDataCSVFile_whenSortedOnStrikeRate_shouldReturnMostSixAndFour() {
        CricketAnalyser cricketAnalyse = new CricketAnalyser();
        try {
            cricketAnalyse.loadCricketData(CricketAnalyser.Cricket.BATSMAN,IPL2019_RUNS_CSV_FILE_PATH);
            List<CricketLeagueDao> sortedData = cricketAnalyse.getSortedData(SortingFields.fields.BOUNDARIES);
            Assert.assertEquals("Andre Russell", sortedData.get(0).player);
        } catch (CricketAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenLeagueDataCSVFile_whenSortedOnStrikeRate_shouldReturnMostSixAnd() {
        CricketAnalyser cricketAnalyse = new CricketAnalyser();
        try {
            cricketAnalyse.loadCricketData(CricketAnalyser.Cricket.BATSMAN,IPL2019_RUNS_CSV_FILE_PATH);
            List<CricketLeagueDao> srtedData = cricketAnalyse.getSortedData(SortingFields.fields.BOUNDARIES);
            Assert.assertEquals("Tim Southee", srtedData.get(99).player);
        } catch (CricketAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenLeagueDataCSVFile_whenSortedOnStrikeRate_shouldReturnMostSixAndFourWithHighestStrikeRate() {
        CricketAnalyser cricketAnalyse = new CricketAnalyser();
        try {
            cricketAnalyse.loadCricketData(CricketAnalyser.Cricket.BATSMAN,IPL2019_RUNS_CSV_FILE_PATH);
            List<CricketLeagueDao> sortedData = cricketAnalyse.getSortedData(SortingFields.fields.STRIKE_WITH_BOUNDARY);
            Assert.assertEquals("Andre Russell", sortedData.get(0).player);
        } catch (CricketAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenLeagueDataCSVFile_whenSortedOnStrikeRate_shouldReturnMostSixAndFourWithLowestStrikeRate() {
        try {
            CricketAnalyser cricketAnalyse = new CricketAnalyser();
            cricketAnalyse.loadCricketData(CricketAnalyser.Cricket.BATSMAN,IPL2019_RUNS_CSV_FILE_PATH);
            List<CricketLeagueDao> sortedData = cricketAnalyse.getSortedData(SortingFields.fields.STRIKE_WITH_BOUNDARY);
            Assert.assertEquals("Tim Southee", sortedData.get(99).player);
        } catch (CricketAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenLeagueDataCSVFile_whenSortedOnStrikeRate_shouldReturnHighestAverage() {
        try {
            CricketAnalyser cricketAnalyse = new CricketAnalyser();
            cricketAnalyse.loadCricketData(CricketAnalyser.Cricket.BATSMAN,IPL2019_RUNS_CSV_FILE_PATH);
            List<CricketLeagueDao> sortedData = cricketAnalyse.getSortedData(SortingFields.fields.RUNS);
            Assert.assertEquals("MS Dhoni", sortedData.get(0).player);
        } catch (CricketAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenLeagueDataCSVFile_whenSortedOnStrikeRate_shouldReturnLowestAverage() {
        try {
            CricketAnalyser cricketAnalyse = new CricketAnalyser();
            cricketAnalyse.loadCricketData(CricketAnalyser.Cricket.BATSMAN,IPL2019_RUNS_CSV_FILE_PATH);
            List<CricketLeagueDao> sortedData = cricketAnalyse.getSortedData(SortingFields.fields.RUNS);
            Assert.assertEquals("Tim Southee", sortedData.get(99).player);
        } catch (CricketAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenLeagueDataCSVFile_whenSortedOnRuns_shouldReturnHighestAverage() {
        try {
            CricketAnalyser cricketAnalyse = new CricketAnalyser();
            cricketAnalyse.loadCricketData(CricketAnalyser.Cricket.BATSMAN,IPL2019_RUNS_CSV_FILE_PATH);
            List<CricketLeagueDao> sortedData = cricketAnalyse.getSortedData(SortingFields.fields.RUNSWITHAVERAGE);
            Assert.assertEquals("David Warner ", sortedData.get(0).player);
        } catch (CricketAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenLeagueDataCSVFile_whenSortedOnRuns_shouldReturnLowestAverage() {
        try {
            CricketAnalyser cricketAnalyse = new CricketAnalyser();
            cricketAnalyse.loadCricketData(CricketAnalyser.Cricket.BATSMAN,IPL2019_RUNS_CSV_FILE_PATH);
            List<CricketLeagueDao> sortedData = cricketAnalyse.getSortedData(SortingFields.fields.RUNSWITHAVERAGE);
            Assert.assertEquals("Tim Southee", sortedData.get(99).player);
        } catch (CricketAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenGivenBowlerCSV_ifDataLoad_shouldReturnExactCount() throws CricketAnalyserException {
        CricketAnalyser cricketAnalyser = new CricketAnalyser();
        int loadData = cricketAnalyser.loadCricketData(CricketAnalyser.Cricket.BOWLER,IPL2019_WICKETS_CSV_FILE_PATH);
        Assert.assertEquals(99, loadData);
    }

    @Test
    public void whenGivenBowlerCSV_whenSortOnAverage_shouldReturnTopAverage(){
        CricketAnalyser cricketAnalyser=new CricketAnalyser();
        try {
            cricketAnalyser.loadCricketData(CricketAnalyser.Cricket.BOWLER,IPL2019_WICKETS_CSV_FILE_PATH);
            List<CricketLeagueDao> sortedData=cricketAnalyser.getSortedData(SortingFields.fields.BOWLERAVERAGE);
            Assert.assertEquals(0.0,sortedData.get(0).average,0);
            Assert.assertEquals(0.0,sortedData.get(98).average,0);
        } catch (CricketAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public  void whenGivenBowlerCSV_whenSortOnStrikeRate_shouldReturnTopStrikeRate(){
        CricketAnalyser cricketAnalyser=new CricketAnalyser();
        try {
            cricketAnalyser.loadCricketData(CricketAnalyser.Cricket.BOWLER,IPL2019_WICKETS_CSV_FILE_PATH);
            List<CricketLeagueDao> sortedData=cricketAnalyser.getSortedData(SortingFields.fields.BOWLERSTRIKERATE);
            Assert.assertEquals(120.0,sortedData.get(0).strikeRate,0);
            Assert.assertEquals(0.0,sortedData.get(98).strikeRate,0);
        } catch (CricketAnalyserException e) {
            e.printStackTrace();
        }
    }
}