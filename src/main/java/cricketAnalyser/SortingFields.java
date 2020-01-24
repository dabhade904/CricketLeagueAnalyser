package cricketAnalyser;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class SortingFields {
    public static Map<fields, Comparator> sortByFields = new HashMap<>();

    public enum fields {
        AVERAGE,
        STRIKERATE,
        BOUNDARIES,
        STRIKE_WITH_BOUNDARY,
        RUNS,
        RUNS_WITH_AVERAGE,
        BOWLER_AVERAGE,
        BOWLER_STRIKE_RATE,
        BOWLER_ECONOMY,
        STRIKRATE_WITH_FIVE_AND_FOUR_WICKETS,
        AVERAGE_WITH_STRIKE_RATE,
        MAXIMUM_WICKET_WITH_AVERAGE,
        BATTING_AND_BOWLING_AVG,
        ALL_ROUNDER,
    }

    public static Comparator<CricketLeagueDao> getParameter(fields parameter) {

        Comparator<CricketLeagueDao> batsmanComparator = Comparator.comparing(batsmanRun -> batsmanRun.average, Comparator.reverseOrder());
        sortByFields.put(fields.AVERAGE, batsmanComparator);

        Comparator<CricketLeagueDao> strikeComparator = Comparator.comparing(batsmanStrike -> batsmanStrike.strikeRate, Comparator.reverseOrder());
        sortByFields.put(fields.STRIKERATE, strikeComparator);

        Comparator<CricketLeagueDao> boundariesComparator = Comparator.comparing(batsman -> batsman.fours * 4 + batsman.sixs * 6, Comparator.reverseOrder());
        sortByFields.put(fields.BOUNDARIES, boundariesComparator);

        Comparator<CricketLeagueDao> strikeRateWithBoundaryComparator = Comparator.comparing(batsman -> batsman.fours * 4 + batsman.sixs * 6, Comparator.reverseOrder());
        sortByFields.put(fields.STRIKE_WITH_BOUNDARY, strikeRateWithBoundaryComparator);

        Comparator<CricketLeagueDao> runsComparator = Comparator.comparing(batsman -> batsman.average, Comparator.reverseOrder());
        sortByFields.put(fields.RUNS, runsComparator);

        Comparator<CricketLeagueDao> avgComparator = Comparator.comparing(batsman -> batsman.runs, Comparator.reverseOrder());
        sortByFields.put(fields.RUNS_WITH_AVERAGE, avgComparator);

        Comparator<CricketLeagueDao> bowlerAverage = Comparator.comparing(bowler -> bowler.ball_average,Comparator.reverseOrder());
        sortByFields.put(fields.BOWLER_AVERAGE, bowlerAverage);

        Comparator<CricketLeagueDao> bowlerStrikeRate = Comparator.comparing(bowler -> bowler.strikeRate);
        sortByFields.put(fields.BOWLER_STRIKE_RATE, bowlerStrikeRate);

        Comparator<CricketLeagueDao> bowlerEconomy = Comparator.comparing(bowler -> bowler.economy, Comparator.reverseOrder());
        sortByFields.put(fields.BOWLER_ECONOMY, bowlerEconomy);

        Comparator<CricketLeagueDao> fiveWickets4WicketsComparator = Comparator.comparing(bowler -> bowler.fiveWickets + bowler.fourWickets, Comparator.reverseOrder());
        sortByFields.put(fields.STRIKRATE_WITH_FIVE_AND_FOUR_WICKETS, fiveWickets4WicketsComparator.thenComparing(bowlerStrikeRate));
        sortByFields.put(fields.AVERAGE_WITH_STRIKE_RATE, bowlerAverage.thenComparing(bowlerStrikeRate));

        Comparator<CricketLeagueDao>bowlerWicketes=Comparator.comparing(bowler-> bowler.wickets,Comparator.reverseOrder());

        Comparator<CricketLeagueDao>bowlingAverage=Comparator.comparing(bowler->bowler.ball_average);
        sortByFields.put(fields.MAXIMUM_WICKET_WITH_AVERAGE,bowlerWicketes.thenComparing(bowlingAverage));

        sortByFields.put(fields.BATTING_AND_BOWLING_AVG, batsmanComparator.thenComparing(bowlingAverage));

        Comparator<CricketLeagueDao>runWithWickets = Comparator.comparing(bowler -> bowler.runs*bowler.wickets,Comparator.reverseOrder());
        sortByFields.put(fields.ALL_ROUNDER, runWithWickets);

        Comparator<CricketLeagueDao> comparator = sortByFields.get(parameter);
        return comparator;
      }
}