package cricketAnalyser;

import com.opencsv.bean.CsvBindByName;

public class Batsman {

    @CsvBindByName(column = "POS", required = true)
    public int pos;

    @CsvBindByName(column = "PLAYER", required = true)
    public String player;

    @CsvBindByName(column = "Mat", required = true)
    public int matches;

    @CsvBindByName(column = "Inns", required = true)
    public int innings;

    @CsvBindByName(column = "NO", required = true)
    public int notOut;

    @CsvBindByName(column = "Runs", required = true)
    public int runs;

    @CsvBindByName(column = "HS", required = true)
    public String highScore;

    @CsvBindByName(column = "Avg", required = true)
    public double avarage;

    @CsvBindByName(column = "BF", required = true)
    public int ballsFaced;

    @CsvBindByName(column = "SR", required = true)
    public double strikeRate;

    @CsvBindByName(column = "100", required = true)
    public int hundreds;

    @CsvBindByName(column = "50", required = true)
    public int fifty;

    @CsvBindByName(column = "4s", required = true)
    public int fours;

    @CsvBindByName(column = "6s", required = true)
    public int sixs;

    public Batsman(int pos, String player, int matches, int innings, int notOut, int runs, String highScore, double avarage, int ballsFaced, double strikeRate, int hundreds, int fifty, int fours, int sixs) {
        this.pos = pos;
        this.player = player;
        this.matches = matches;
        this.innings = innings;
        this.notOut = notOut;
        this.runs = runs;
        this.highScore = highScore;
        this.avarage = avarage;
        this.ballsFaced = ballsFaced;
        this.strikeRate = strikeRate;
        this.hundreds = hundreds;
        this.fifty = fifty;
        this.fours = fours;
        this.sixs = sixs;
    }

    public Batsman() {
    }

    @Override
    public String toString() {
        return "Batsman{" +
                "pos=" + pos +
                ", player='" + player + '\'' +
                ", matches=" + matches +
                ", innings=" + innings +
                ", noPlay=" + notOut +
                ", runs=" + runs +
                ", highScore='" + highScore + '\'' +
                ", avarage='" + avarage + '\'' +
                ", ballsFaced=" + ballsFaced +
                ", strikeRate=" + strikeRate +
                ", hundreds=" + hundreds +
                ", fifty=" + fifty +
                ", fours=" + fours +
                ", sixs=" + sixs +
                '}';
    }
}
