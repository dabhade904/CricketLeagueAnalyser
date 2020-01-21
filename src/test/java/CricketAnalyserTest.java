import com.google.gson.Gson;
import cricketAnalyser.Batsman;
import cricketAnalyser.CricketAnalyser;
import cricketAnalyser.CricketAnalyserException;
import cricketAnalyser.SortingFields;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CricketAnalyserTest {

    private static final String IPL2019_RUNS_CSV_FILE_PATH = "/home/admin1/IdeaProjects/CensusAnalyser/CricketLeague/src/test/resources/IPL2019FactSheetMostRuns.csv";
    private static final String WRONG_CSV_FILE_PATH = "/home/admin1/IdeaProjects/CensusAnalyser/CricketLeague/src/test/resources/WrongFileData.csv";
    private static final String WRONG_CSV_FILE_TYPE = "/home/admin1/IdeaProjects/CensusAnalyser/CricketLeague/src/test/resources/WrongFileType.txt";
    private static final String WRONG_CSV_FILE_PATH_DELIMITER = "/home/admin1/IdeaProjects/CensusAnalyser/CricketLeague/src/test/resources/wrongFileDelimiter.csv";
    private static final String WRONG_CSV_FILE_PATH_HEADER = "/home/admin1/IdeaProjects/CensusAnalyser/CricketLeague/src/test/resources/wrongFileHeader.csv";

    private static final String IPL2019_WICKETS_CSV_FILE_PATH = "/home/admin1/IdeaProjects/CensusAnalyser/CricketLeague/src/test/resources/IPL2019FactSheeMostWickets.csv";

    @Test
    public void givenLeagueDataCSVFIle_shouldReturnExactCount() {
        CricketAnalyser cricketAnalyser = new CricketAnalyser();
        int loadData =cricketAnalyser.getNumberOfRecord(IPL2019_RUNS_CSV_FILE_PATH);
        Assert.assertEquals(100, loadData);
    }

    @Test
    public void givenLeagueDataCSVFile_whenSortedOnAverage_shouldReturnData() {
        CricketAnalyser cricketAnalyse = new CricketAnalyser();
        try {
            cricketAnalyse.loadCricketData(IPL2019_RUNS_CSV_FILE_PATH);
            String sortedData=cricketAnalyse.getSortedData(SortingFields.fields.AVERAGE);
            Batsman[]batsmen=new Gson().fromJson(sortedData,Batsman[].class);
            Assert.assertEquals("MS Dhoni",batsmen[0].player);
        } catch (CricketAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenLeagueDataCSVFile_whenSortedOnAverage_shouldReturnHighestBattingAverage() {
        CricketAnalyser cricketAnalyse = new CricketAnalyser();
        try {
            cricketAnalyse.loadCricketData(IPL2019_RUNS_CSV_FILE_PATH);
            String sortedData=cricketAnalyse.getSortedData(SortingFields.fields.AVERAGE);
            System.out.println(sortedData);
            Batsman[]batsmen=new Gson().fromJson(sortedData,Batsman[].class);
            Assert.assertEquals(83.2,batsmen[0].avarage,0);

        } catch (CricketAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenLeagueDataCSVFile_whenSortedOnAverage_shouldReturnLowestBattingAverage() {
        CricketAnalyser cricketAnalyse = new CricketAnalyser();
        try {
            cricketAnalyse.loadCricketData(IPL2019_RUNS_CSV_FILE_PATH);
            String sortedData=cricketAnalyse.getSortedData(SortingFields.fields.AVERAGE);
            Batsman[]batsmen=new Gson().fromJson(sortedData,Batsman[].class);
            Assert.assertEquals(0.0,batsmen[99].avarage,0);
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
            censusAnalyser.loadCricketData(WRONG_CSV_FILE_PATH);
        } catch (CricketAnalyserException e) {
            Assert.assertEquals(CricketAnalyserException.ExceptionType.CRICKET_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndiaCSVFile_whenCorrectButTypeIncorrect_ShouldReturnCustomException() {
        {
            CricketAnalyser censusAnalyser = new CricketAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CricketAnalyserException.class);
            try {
                censusAnalyser.loadCricketData(WRONG_CSV_FILE_TYPE);
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
            cricketAnalyser.loadCricketData(WRONG_CSV_FILE_PATH_DELIMITER);
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
            cricketAnalyser.loadCricketData(WRONG_CSV_FILE_PATH_HEADER);
        } catch (CricketAnalyserException e) {
            Assert.assertEquals(CricketAnalyserException.ExceptionType.DELIMITER_OR_HEADER_PROBLEM, e.type);
        }
    }

    @Test
    public void givenLeagueDataCSVFile_whenSortedOnStrikeRate_shouldReturnHighestStrikeRate() {
        CricketAnalyser cricketAnalyse = new CricketAnalyser();
        try {
            cricketAnalyse.loadCricketData(IPL2019_RUNS_CSV_FILE_PATH);
            String sortedData=cricketAnalyse.getSortedData(SortingFields.fields.STRIKERATE);
            Batsman[]batsmen=new Gson().fromJson(sortedData,Batsman[].class);
            Assert.assertEquals(333.33,batsmen[0].strikeRate,0);
        } catch (CricketAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenLeagueDataCSVFile_whenSortedOnStrikeRate_shouldReturnLowestStrikeRate() {
        CricketAnalyser cricketAnalyse = new CricketAnalyser();
        try {
            cricketAnalyse.loadCricketData(IPL2019_RUNS_CSV_FILE_PATH);
            String sortedData=cricketAnalyse.getSortedData(SortingFields.fields.STRIKERATE);
            Batsman[]batsmen=new Gson().fromJson(sortedData,Batsman[].class);
            Assert.assertEquals(63.15,batsmen[99].strikeRate,0);
        } catch (CricketAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenLeagueDataCSVFile_whenSortedOnStrikeRate_shouldReturnMostSixAndFour() {
        CricketAnalyser cricketAnalyse = new CricketAnalyser();
        try {
            cricketAnalyse.loadCricketData(IPL2019_RUNS_CSV_FILE_PATH);
            String sortedData=cricketAnalyse.getSortedData(SortingFields.fields.BOUNDARIES);
            Batsman[]batsmen=new Gson().fromJson(sortedData,Batsman[].class);
              System.out.println(sortedData);
            Assert.assertEquals("Andre Russell",batsmen[0].player);
        } catch (CricketAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenLeagueDataCSVFile_whenSortedOnStrikeRate_shouldReturnMostSixAnd() {
        CricketAnalyser cricketAnalyse = new CricketAnalyser();
        try {
            cricketAnalyse.loadCricketData(IPL2019_RUNS_CSV_FILE_PATH);
            String sortedData=cricketAnalyse.getSortedData(SortingFields.fields.BOUNDARIES);
            Batsman[]batsmen=new Gson().fromJson(sortedData,Batsman[].class);
            Assert.assertEquals("Shakib Al Hasan",batsmen[99].player);
        } catch (CricketAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenLeagueDataCSVFile_whenSortedOnStrikeRate_shouldReturnMostSixAndFourWithHighestStrikeRate() {
        CricketAnalyser cricketAnalyse = new CricketAnalyser();
        try {
            cricketAnalyse.loadCricketData(IPL2019_RUNS_CSV_FILE_PATH);
            String sortedData=cricketAnalyse.getSortedData(SortingFields.fields.STRIKE_WITH_BOUNDARY);
            Batsman[]batsmen=new Gson().fromJson(sortedData,Batsman[].class);
            Assert.assertEquals("Andre Russell",batsmen[0].player);
        } catch (CricketAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenLeagueDataCSVFile_whenSortedOnStrikeRate_shouldReturnMostSixAndFourWithLowestStrikeRate() {
        try {
            CricketAnalyser cricketAnalyse = new CricketAnalyser();
            cricketAnalyse.loadCricketData(IPL2019_RUNS_CSV_FILE_PATH);
            String sortedData=cricketAnalyse.getSortedData(SortingFields.fields.STRIKE_WITH_BOUNDARY);
            Batsman[]batsmen=new Gson().fromJson(sortedData,Batsman[].class);
            Assert.assertEquals("Shakib Al Hasan", batsmen[99].player);
        } catch (CricketAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenLeagueDataCSVFile_whenSortedOnStrikeRate_shouldReturnHighestAverage() {
        try {
            CricketAnalyser cricketAnalyse = new CricketAnalyser();
            cricketAnalyse.loadCricketData(IPL2019_RUNS_CSV_FILE_PATH);
            String sortedData=cricketAnalyse.getSortedData(SortingFields.fields.RUNS);
            Batsman[]batsmen=new Gson().fromJson(sortedData,Batsman[].class);
            Assert.assertEquals("MS Dhoni",batsmen[0].player);
        } catch (CricketAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenLeagueDataCSVFile_whenSortedOnStrikeRate_shouldReturnLowestAverage() {
        try {
            CricketAnalyser cricketAnalyse = new CricketAnalyser();
            cricketAnalyse.loadCricketData(IPL2019_RUNS_CSV_FILE_PATH);
            String sortedData=cricketAnalyse.getSortedData(SortingFields.fields.RUNS);
            Batsman[]batsmen=new Gson().fromJson(sortedData,Batsman[].class);
            Assert.assertEquals("Harpreet Brar",batsmen[99].player);
        } catch (CricketAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenLeagueDataCSVFile_whenSortedOnRuns_shouldReturnHighestAverage() {
        try {
            CricketAnalyser cricketAnalyse = new CricketAnalyser();
            cricketAnalyse.loadCricketData(IPL2019_RUNS_CSV_FILE_PATH);
            String sortedData=cricketAnalyse.getSortedData(SortingFields.fields.RUNSWITHAVERAGE);
            Batsman[]batsmen=new Gson().fromJson(sortedData,Batsman[].class);
            Assert.assertEquals("David Warner ",batsmen[0].player);
        } catch (CricketAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenLeagueDataCSVFile_whenSortedOnRuns_shouldReturnLowestAverage() {
        try {
            CricketAnalyser cricketAnalyse = new CricketAnalyser();
            cricketAnalyse.loadCricketData(IPL2019_RUNS_CSV_FILE_PATH);
            String sortedData=cricketAnalyse.getSortedData(SortingFields.fields.RUNSWITHAVERAGE);
            Batsman[]batsmen=new Gson().fromJson(sortedData,Batsman[].class);
            Assert.assertEquals("Shakib Al Hasan",batsmen[99].player);
        } catch (CricketAnalyserException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void whenGivenBowlerCSV_ifDataLoad_shouldReturnExactCount() {
        CricketAnalyser cricketAnalyser = new CricketAnalyser();
        int loadData = 0;
        try {
            loadData = cricketAnalyser.readCricketMostWicketsData(IPL2019_WICKETS_CSV_FILE_PATH);
            Assert.assertEquals(99, loadData);

        } catch (CricketAnalyserException e) {
            e.printStackTrace();
        }
    }
}

