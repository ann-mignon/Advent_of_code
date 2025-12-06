package aoc.luckor.A25;

import aoc.Losning;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class lucka_5 extends Losning {
    record Range(long from, long to) {}

    Range[] intervall;

    static Range[] parseRanges(String[] rs) {
        List<Range> toRet = new ArrayList<>();

        for (String _rs : rs) {
            String[] rft = _rs.split("-");
            toRet.add(new Range(Long.parseLong(rft[0]), Long.parseLong(rft[1])));
        }

        return toRet.stream().sorted(Comparator.comparingLong(Range::from)).toArray(Range[]::new);
    }

    boolean isFrasch(long ingo) {
        for (Range r : intervall) {
            if (ingo >= r.from && ingo <= r.to) {
                return true;
            }
        }
        return false;
    }

    public String svar() {
        String[] inpart = input.split("\n\n");
        intervall = parseRanges(inpart[0].split("\n"));
        long[] ing = Arrays.stream(inpart[1].split("\n")).mapToLong(Long::parseLong).toArray();
        int antFrasch = 0;

        for (long _i : ing) {
            antFrasch += isFrasch(_i) ? 1 : 0;
        }

        return String.valueOf(antFrasch);
    }
}
