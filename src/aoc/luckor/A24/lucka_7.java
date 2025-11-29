package aoc.luckor.A24;

import aoc.Losning;
import aoc.misc.EqCheck;
import aoc.misc.EqCheck.eqOp;

import java.util.Arrays;
import java.util.function.LongBinaryOperator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

import static aoc.Util.toLongArray;

public class lucka_7 extends Losning {
    public enum basicOps implements eqOp {
        PROD,
        SUM;

        public LongBinaryOperator getOperator() {
            return switch (this) {
                case PROD -> Math::multiplyExact;
                case SUM -> Long::sum;
            };
        }
    }

    protected eqOp[][][] precombine(eqOp[] ops) {
        return IntStream.range(0, getMax())
                        .mapToObj(n -> EqCheck.opMap(n, ops)).toArray(eqOp[][][]::new);
    }

    protected int getMax() {
        return (int) Arrays.stream(inputLines).map(s -> s.chars().filter(c -> c == ' ')
                        .count()).mapToLong(c -> c).max().getAsLong();
    }

    protected long check(eqOp[] ops) {
        Matcher m = eqrx.matcher(input);
        eqOp[][][] combo = precombine(ops);

        return m.results().mapToLong(r ->
                new EqCheck(Long.parseLong(r.group(1)),
                        toLongArray(r.group(2).split( " ")),
                        ops).check(combo)).sum();
    }

    final Pattern eqrx = Pattern.compile("^(\\d+): ((?:\\d+ )+\\d+)$", Pattern.MULTILINE);

    public String svar() {
        return String.valueOf(check(new basicOps[]{ basicOps.PROD, basicOps.SUM }));
    }
}
