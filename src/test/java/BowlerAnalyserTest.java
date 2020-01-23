import cricketAnalyser.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

public class BowlerAnalyserTest {
    private static final String WRONG_CSV_FILE_PATH = "/home/admin1/IdeaProjects/CensusAnalyser/CricketLeague/src/test/resources/WrongFileData.csv";
    private static final String WRONG_CSV_FILE_TYPE = "/home/admin1/IdeaProjects/CensusAnalyser/CricketLeague/src/test/resources/WrongFileType.txt";
    private static final String WRONG_CSV_FILE_PATH_DELIMITER = "/home/admin1/IdeaProjects/CensusAnalyser/CricketLeague/src/test/resources/wrongFileDelimiter.csv";
    private static final String WRONG_CSV_FILE_PATH_HEADER = "/home/admin1/IdeaProjects/CensusAnalyser/CricketLeague/src/test/resources/wrongFileHeader.csv";

    private static final String IPL2019_WICKETS_CSV_FILE_PATH = "/home/admin1/IdeaProjects/CensusAnalyser/CricketLeague/src/test/resources/IPL2019FactSheeMostWickets.csv";

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
            List<CricketLeagueDao> sortedData=cricketAnalyser.getSortedData(CricketAnalyser.Cricket.BOWLER,SortingFields.fields.BOWLERAVERAGE);
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
            List<CricketLeagueDao> sortedData=cricketAnalyser.getSortedData(CricketAnalyser.Cricket.BOWLER,SortingFields.fields.BOWLERSTRIKERATE);
            Assert.assertEquals(0.0,sortedData.get(0).strikeRate,0);
            Assert.assertEquals(120.0,sortedData.get(98).strikeRate,0);
        } catch (CricketAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public  void whenGivenBowlerCSV_whenSortOnEconomy_shouldReturnBestEconomy(){
        CricketAnalyser cricketAnalyser=new CricketAnalyser();
        try {
            cricketAnalyser.loadCricketData(CricketAnalyser.Cricket.BOWLER,IPL2019_WICKETS_CSV_FILE_PATH);
            List<CricketLeagueDao> sortedData=cricketAnalyser.getSortedData(CricketAnalyser.Cricket.BOWLER,SortingFields.fields.BOWLER_ECONOMY);
            Assert.assertEquals(13.5,sortedData.get(0).economy,0);
            Assert.assertEquals(4.8,sortedData.get(98).economy,0);
        } catch (CricketAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenGivenBowlerCSV_sortingStrikeRateOnFiveAndFourWicktes_shouldReturnStrikingRate(){
        CricketAnalyser cricketAnalyser=new CricketAnalyser();
        try {
            cricketAnalyser.loadCricketData(CricketAnalyser.Cricket.BOWLER,IPL2019_WICKETS_CSV_FILE_PATH);
            List<CricketLeagueDao>leagueDaoList=cricketAnalyser.getSortedData(CricketAnalyser.Cricket.BOWLER,SortingFields.fields.STRIKRATE_WITH_FIVE_AND_FOUR_WICKETS);
            System.out.println(leagueDaoList.get(98).player);
            Assert.assertEquals("Kagiso Rabada",leagueDaoList.get(0).player);
            Assert.assertEquals("Krishnappa Gowtham",leagueDaoList.get(98).player);
        } catch (CricketAnalyserException e) {
            e.printStackTrace();
        }
    }
}
