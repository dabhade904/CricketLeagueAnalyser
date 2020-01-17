import cricketAnalyser.CricketAnalyser;
import org.junit.Assert;
import org.junit.Test;

public class CricketAnalyserTest {

    private static final String IPL2019_RUNS_CSV_FILE_PATH="/home/admin1/IdeaProjects/CensusAnalyser/CricketLeague/src/test/resources/IPL2019FactSheetMostRuns.csv";

    @Test
    public void givenLeagueDataCSVFIle_shouldReturnExactCount(){
        CricketAnalyser cricketAnalyser=new CricketAnalyser();
        int records=cricketAnalyser.readCricketMostRunsData(IPL2019_RUNS_CSV_FILE_PATH);
        System.out.println(records);
        Assert.assertEquals(100,records);
    }
}
