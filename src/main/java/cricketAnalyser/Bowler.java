package cricketAnalyser;

import com.opencsv.bean.CsvBindByName;

public class Bowler {

    @CsvBindByName(column = "POS", required = true)
    public String pos;

    @CsvBindByName(column = "PLAYER", required = true)
    public String player;

    @CsvBindByName(column = "Mat", required = true)
    public String matches;

    @CsvBindByName(column = "Inns", required = true)
    public String innings;

    @CsvBindByName(column = "Ov", required = true)
    public String overs;

    @CsvBindByName(column = "Runs", required = true)
    public String runs;

    @CsvBindByName(column = "Wkts", required = true)
    public String wikets;

    @CsvBindByName(column = "BBI", required = true)
    public String bestBowlingIndex;

    @CsvBindByName(column = "Avg", required = true)
    public String average;

    @CsvBindByName(column = "Econ", required = true)
    public String economi;

    @CsvBindByName(column = "SR", required = true)
    public String strikeRate;

    @CsvBindByName(column = "4w", required = true)
    public String foursWickets;

    @CsvBindByName(column = "5w", required = true)
    public String fiveWickets;

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
