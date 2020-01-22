package cricketAnalyser;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class SortingFields {
    public static Map<fields,Comparator> sortByFields=new HashMap<>();

    public enum fields {
        AVERAGE,STRIKERATE,BOUNDARIES,STRIKE_WITH_BOUNDARY,RUNS,RUNSWITHAVERAGE,BOWLERAVERAGE,BOWLERSTRIKERATE,
    }
    public Comparator<CricketLeagueDao> getParameter(fields parameter) {
        Comparator<CricketLeagueDao> batsmanComparator = Comparator.comparing(batsmanRun -> batsmanRun.average,Comparator.reverseOrder());
        sortByFields.put(fields.AVERAGE,batsmanComparator);
        Comparator<CricketLeagueDao>strikeComparator=Comparator.comparing(batsmanStrike ->batsmanStrike.strikeRate,Comparator.reverseOrder());
        sortByFields.put(fields.STRIKERATE,strikeComparator);
        Comparator<CricketLeagueDao> boundariesComparator = Comparator.comparing(batsman -> batsman.fours*4+batsman.sixes*6,Comparator.reverseOrder());
        sortByFields.put(fields.BOUNDARIES,boundariesComparator);
        Comparator<CricketLeagueDao>strikeRateWithBoundaryComparator=Comparator.comparing(batsman -> batsman.fours*4+batsman.sixes*6,Comparator.reverseOrder());
        sortByFields.put(fields.STRIKE_WITH_BOUNDARY,strikeRateWithBoundaryComparator);
        Comparator<CricketLeagueDao>runsComparator=Comparator.comparing(batsman -> batsman.average,Comparator.reverseOrder());
        sortByFields.put(fields.RUNS,runsComparator);
        Comparator<CricketLeagueDao>avgComparator=Comparator.comparing(batsman -> batsman.runs,Comparator.reverseOrder());
        sortByFields.put(fields.RUNSWITHAVERAGE,avgComparator);
        Comparator<CricketLeagueDao>bowlerAverage=Comparator.comparing(bowler-> bowler.average);
        sortByFields.put(fields.BOWLERAVERAGE,bowlerAverage);
        Comparator<CricketLeagueDao>bowlerStrikeRate=Comparator.comparing(bowler ->bowler.strikeRate,Comparator.reverseOrder());
        sortByFields.put(fields.BOWLERSTRIKERATE,bowlerStrikeRate);
        Comparator<CricketLeagueDao>comparator=sortByFields.get(parameter);
        return comparator;
    }
}