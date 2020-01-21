package cricketAnalyser;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class SortingFields {
    public static Map<fields,Comparator> sortByFields=new HashMap<>();
    public enum fields {
        AVERAGE,STRIKERATE,BOUNDARIES,STRIKE_WITH_BOUNDARY,RUNS,RUNSWITHAVERAGE,BOWLERAVERAGE,
    }
    public Comparator<Batsman> getParameter(SortingFields.fields parameter) {
        Comparator<Batsman> batsmanComparator = Comparator.comparing(batsmanRun -> batsmanRun.avarage, Comparator.reverseOrder());
        sortByFields.put(fields.AVERAGE,batsmanComparator);
        Comparator<Batsman>strikeComparator=Comparator.comparing(batsmanStrike ->batsmanStrike.strikeRate,Comparator.reverseOrder());
        sortByFields.put(fields.STRIKERATE,strikeComparator);
        Comparator<Batsman> boundariesComparator = Comparator.comparing(batsman -> batsman.fours*4+batsman.sixs*6,Comparator.reverseOrder());
        sortByFields.put(fields.BOUNDARIES,boundariesComparator);
        Comparator<Batsman>strikeRateWithBoundaryComparator=Comparator.comparing(batsman -> (batsman.fours*4+batsman.sixs*6),Comparator.reverseOrder());
        sortByFields.put(fields.STRIKE_WITH_BOUNDARY,strikeRateWithBoundaryComparator);
        Comparator<Batsman>runsComparator=Comparator.comparing(batsman -> batsman.avarage,Comparator.reverseOrder());
        sortByFields.put(fields.RUNS,runsComparator);
        Comparator<Batsman>avgComparator=Comparator.comparing(batsman -> batsman.runs,Comparator.reverseOrder());
        sortByFields.put(fields.RUNSWITHAVERAGE,avgComparator);

        Comparator<Bowler>averageComparator=Comparator.comparing(bowlerAverage ->bowlerAverage.average,Comparator.reverseOrder());
        sortByFields.put(fields.BOWLERAVERAGE,averageComparator);

        Comparator<Batsman>comparator=sortByFields.get(parameter);

        return comparator;
    }

    public Comparator<Bowler> getParameters(SortingFields.fields parameter) {
        Comparator<Bowler>averageComparator=Comparator.comparing(bowlerAverage ->bowlerAverage.average,Comparator.reverseOrder());
        sortByFields.put(fields.BOWLERAVERAGE,averageComparator);
        Comparator<Bowler>comparator=sortByFields.get(parameter);
        return comparator;
    }
}