package cricketAnalyser;

public class CricketLeagueDao {

    public int pos;
    public String player;
    public int matches;
    public int inns;
    public int notOut;
    public int runs;
    public int highScore;
    public double average;
    public int ballFaced;
    public double strikeRate;
    public int centuries;
    public int fifties;
    public int fours;
    public int sixs;
    public double over;
    public int wickets;
    public int bestBowlingIndex;
    public double economy;
    public int fourWickets;
    public int fiveWickets;

    public CricketLeagueDao(Batsman iplBatting) {
        pos = iplBatting.pos;
        player = iplBatting.player;
        matches = iplBatting.matches;
        inns = iplBatting.inns;
        notOut = iplBatting.notOut;
        runs = iplBatting.runs;
        highScore = iplBatting.highScore;
        average = iplBatting.avg;
        ballFaced = iplBatting.ballFaced;
        strikeRate = iplBatting.strikeRate;
        centuries = iplBatting.centuries;
        fifties = iplBatting.halfCenturies;
        fours = iplBatting.fours;
        sixs = iplBatting.sixes;
    }

    public CricketLeagueDao(Bowler iplBowling) {
        pos = iplBowling.pos;
        player = iplBowling.player;
        matches = iplBowling.matches;
        inns = iplBowling.innings;
        over = iplBowling.over;
        runs = iplBowling.runs;
        wickets = iplBowling.wickets;
        bestBowlingIndex = iplBowling.bestBowlingIndex;
        average = iplBowling.average;
        economy = iplBowling.economy;
        strikeRate = iplBowling.strikeRate;
        fourWickets = iplBowling.fourWickets;
        fiveWickets = iplBowling.fiveWickets;
    }

    public CricketLeagueDao(CricketLeagueDao cricketCSV) {

    }

    @Override
    public String toString() {
        return "CricketLeagueDao{" +
                "pos=" + pos +
                ", player='" + player + '\'' +
                ", matches=" + matches +
                ", inns=" + inns +
                ", notOut=" + notOut +
                ", runs=" + runs +
                ", highScore=" + highScore +
                ", average=" + average +
                ", ballFaced=" + ballFaced +
                ", strikeRate=" + strikeRate +
                ", centuries=" + centuries +
                ", fifties=" + fifties +
                ", fours=" + fours +
                ", sixs=" + sixs +
                ", over=" + over +
                ", wickets=" + wickets +
                ", bestBowlingIndex=" + bestBowlingIndex +
                ", economy=" + economy +
                ", fourWickets=" + fourWickets +
                ", fiveWickets=" + fiveWickets +
                '}';
    }

    public CricketLeagueDao() {
    }

        public Object getBatsmanDTO(CricketAnalyser.Cricket file) {
        if (file.equals(CricketAnalyser.Cricket.BATSMAN))
            return new Batsman(pos, player, matches, inns, notOut, runs, highScore, average, ballFaced, strikeRate, centuries, fifties, fours, sixs);
        return new Bowler(pos, player, matches, inns, over, runs, wickets, bestBowlingIndex, average, economy, strikeRate, fourWickets, fiveWickets);
    }
}