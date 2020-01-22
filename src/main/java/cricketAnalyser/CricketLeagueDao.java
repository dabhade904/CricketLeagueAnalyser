package cricketAnalyser;

public class CricketLeagueDao {
    public String player;
    public double average;
    public double strikeRate;
    public int fours;
    public int sixes;
    public int ballFaced;
    public int runs;
    public int wickets;
    public double economy;
    public int fiveWickets;
    public int fourWickets;
    public double bowlingAverage;

    public CricketLeagueDao(Batsman batsmanRuns) {
        this.player=batsmanRuns.player;
        this.average= batsmanRuns.avarage;
        this.strikeRate=batsmanRuns.strikeRate;
        this.sixes=batsmanRuns.sixs;
        this.fours=batsmanRuns.fours;
        this.ballFaced=batsmanRuns.ballsFaced;
        this.runs=batsmanRuns.runs;
    }

    public CricketLeagueDao(Bowler bowler) {
        this.player= bowler.player;
        this.wickets=bowler.wikets;
        this.bowlingAverage=bowler.average;
        this.economy = bowler.economi;
        this.fiveWickets = bowler.fiveWickets;
        this.fourWickets = bowler.foursWickets;
        this.strikeRate = bowler.strikeRate;
    }

    public CricketLeagueDao() { }

    public CricketLeagueDao(int position, String player, int mat, int innings, int no, int runs, double average, int ballFaced, double strikeRate, int century, int halfcentury, int fours, int sixes) {
        this.player = player;
        this.runs = runs;
        this.average = average;
        this.ballFaced = ballFaced;
        this.strikeRate =strikeRate;
        this.fours=fours;
        this.sixes=sixes;
    }

    @Override
    public String toString() {
        return "CricketLeagueDao{" +
                "player='" + player + '\'' +
                ", average=" + average +
                ", strikeRate=" + strikeRate +
                ", fours=" + fours +
                ", sixes=" + sixes +
                ", ballFaced=" + ballFaced +
                ", runs=" + runs +
                ", wickets=" + wickets +
                ", economy=" + economy +
                ", fiveWickets=" + fiveWickets +
                ", fourWickets=" + fourWickets +
                ", bowlingAverage=" + bowlingAverage +
                '}';
    }
}
