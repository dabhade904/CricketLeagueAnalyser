import cricketAnalyser.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;

public class CricketLeagueMockTest {

    private static final String IPL2019_WICKETS_CSV_FILE_PATH = "/home/admin1/Downloads/CricketLeagueAnalyser/src/test/resources/IPL2019FactSheeMostWickets.csv";
    private static final String IPL2019_RUNS_CSV_FILE_PATH = "/home/admin1/Downloads/CricketLeagueAnalyser-master/src/test/resources/IPL2019FactSheetMostRuns.csv";

    @Mock
    CricketMock cricketMock;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    Map<String, CricketLeagueDao> cricketMap = new HashMap<>();

    @Test
    public void givenBatsmanDataCSVFIle_shouldReturnExactCount() {
        CricketAnalyser cricketAnalyser = new CricketAnalyser(cricketMock);
        cricketMap.put("virat kohli", new CricketLeagueDao());
        try {
            when(cricketMock.loadCricketData(CricketAnalyser.Cricket.BATSMAN, IPL2019_RUNS_CSV_FILE_PATH)).thenReturn(cricketMap);
            int numOfRecords = cricketAnalyser.loadCricketData(CricketAnalyser.Cricket.BATSMAN, IPL2019_RUNS_CSV_FILE_PATH);
            Assert.assertEquals(1, numOfRecords);
        } catch (CricketAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenBowlerDataCSVFIle_shouldReturnExactCount() {
        CricketAnalyser cricketAnalyser = new CricketAnalyser(cricketMock);
        try {
            cricketMap.put("jasprit bhumrah", new CricketLeagueDao());
            when(cricketMock.loadCricketData(CricketAnalyser.Cricket.BOWLER, IPL2019_WICKETS_CSV_FILE_PATH)).thenReturn(cricketMap);
            int numOfRecords = cricketAnalyser.loadCricketData(CricketAnalyser.Cricket.BOWLER, IPL2019_WICKETS_CSV_FILE_PATH);
            Assert.assertEquals(1, numOfRecords);
        } catch (CricketAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenSampleBattingData_ShouldReturnData() {
        CricketAnalyser cricketAnalyser = new CricketAnalyser(cricketMock, CricketAnalyser.Cricket.BATSMAN);
        try {
            cricketMap.put("Virat kohli", new CricketLeagueDao());
            cricketMap.put("MS Dhoni",new CricketLeagueDao());
            when(cricketMock.loadCricketData(CricketAnalyser.Cricket.BATSMAN,IPL2019_RUNS_CSV_FILE_PATH,IPL2019_WICKETS_CSV_FILE_PATH)).thenReturn(cricketMap);
            int numOfRecords = cricketAnalyser.loadCricketData(CricketAnalyser.Cricket.BATSMAN,IPL2019_RUNS_CSV_FILE_PATH,IPL2019_WICKETS_CSV_FILE_PATH);
            Assert.assertEquals(2, numOfRecords);
        } catch (CricketAnalyserException e) {
            e.printStackTrace();
        }
    }


}
