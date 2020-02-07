import cricketAnalyser.*;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;


import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.when;

public class CricketLeagueMockTest {

    private static final String IPL2019_WICKETS_CSV_FILE_PATH = "/home/admin1/Downloads/CricketLeagueAnalyser-master/src/test/resources/IPL2019FactSheeMostWickets.csv";
    private static final String IPL2019_RUNS_CSV_FILE_PATH = "/home/admin1/Downloads/CricketLeagueAnalyser-master/src/test/resources/IPL2019FactSheetMostRuns.csv";

    @Mock
    CricketAdapterFactory cricketAdapterFactory;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

   @Test
    public void givenBatsmanDataCSVFIle_shouldReturnExactCount() {
       try {
           Map<String , CricketLeagueDao> cricketMap = new HashMap<>();
           cricketMap.put("dgf",new CricketLeagueDao());
          CricketAnalyser cricketAnalyser = new CricketAnalyser(cricketAdapterFactory,CricketAnalyser.Cricket.BATSMAN);
           when(cricketAdapterFactory.getCricketData(CricketAnalyser.Cricket.BATSMAN,IPL2019_RUNS_CSV_FILE_PATH)).thenReturn(cricketMap);
            int numOfRecords=cricketAnalyser.loadCricketData(CricketAnalyser.Cricket.BATSMAN,IPL2019_RUNS_CSV_FILE_PATH);
           Assert.assertEquals(1,numOfRecords);
       } catch (CricketAnalyserException e) {
           e.printStackTrace();
       }
    }

    @Test
    public void givenBowlerDataCSVFIle_shouldReturnExactCount() {
        try {
            Map<String , CricketLeagueDao> cricketMap = new HashMap<>();
            cricketMap.put("dgf",new CricketLeagueDao());
            CricketAnalyser cricketAnalyser = new CricketAnalyser(cricketAdapterFactory,CricketAnalyser.Cricket.BOWLER);
            when(cricketAdapterFactory.getCricketData(CricketAnalyser.Cricket.BOWLER,IPL2019_WICKETS_CSV_FILE_PATH)).thenReturn(cricketMap);
            int numOfRecords=cricketAnalyser.loadCricketData(CricketAnalyser.Cricket.BATSMAN,IPL2019_RUNS_CSV_FILE_PATH);
            Assert.assertEquals(1,numOfRecords);
        } catch (CricketAnalyserException e) {
            e.printStackTrace();
        }
    }

}
