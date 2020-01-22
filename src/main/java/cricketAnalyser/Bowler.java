package cricketAnalyser;

import com.opencsv.bean.CsvBindByName;

public class Bowler {

    @CsvBindByName(column = "POS", required = true)
    public int pos;

    @CsvBindByName(column = "PLAYER", required = true)
    public String player;

    @CsvBindByName(column = "Mat", required = true)
    public int matches;

    @CsvBindByName(column = "Inns", required = true)
    public int innings;

    @CsvBindByName(column = "Ov", required = true)
    public double overs;

    @CsvBindByName(column = "Runs", required = true)
    public int runs;

    @CsvBindByName(column = "Wkts", required = true)
    public int wikets;

    @CsvBindByName(column = "BBI", required = true)
    public int bestBowlingIndex;

    @CsvBindByName(column = "Avg", required = true)
    public double average;

    @CsvBindByName(column = "Econ", required = true)
    public double economi;

    @CsvBindByName(column = "SR", required = true)
    public double strikeRate;

    @CsvBindByName(column = "4w", required = true)
    public int foursWickets;

    @CsvBindByName(column = "5w", required = true)
    public int fiveWickets;

    public Bowler(int pos, String player, int matches, int innings, double overs, int runs, int wikets, int bestBowlingIndex, double average, double economi, double strikeRate, int foursWickets, int fiveWickets) {
        this.pos = pos;
        this.player = player;
        this.matches = matches;
        this.innings = innings;
        this.overs = overs;
        this.runs = runs;
        this.wikets = wikets;
        this.bestBowlingIndex = bestBowlingIndex;
        this.average = average;
        this.economi = economi;
        this.strikeRate = strikeRate;
        this.foursWickets = foursWickets;
        this.fiveWickets = fiveWickets;
    }

    public Bowler() {
    }

    @Override
    public String toString() {
        return "Bowler{" +
                "pos=" + pos +
                ", player='" + player + '\'' +
                ", matches=" + matches +
                ", innings=" + innings +
                ", overs=" + overs +
                ", runs=" + runs +
                ", wikets=" + wikets +
                ", bestBowlingIndex=" + bestBowlingIndex +
                ", average=" + average +
                ", economi=" + economi +
                ", strikeRate=" + strikeRate +
                ", foursWickets=" + foursWickets +
                ", fiveWickets=" + fiveWickets +
                '}';
    }
}
