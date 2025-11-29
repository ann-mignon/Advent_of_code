package aoc.luckor.A24;

import aoc.Losning;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class lucka_3 extends Losning {
    protected final Pattern mulrx = Pattern.compile("mul\\((\\d+),(\\d+)\\)");

    public String svar() {
        Matcher m = mulrx.matcher(input);

        return String.valueOf(
                m.results().map(r -> Integer.parseInt(r.group(1)) *
                                     Integer.parseInt(r.group(2)))
                            .reduce(0, Integer::sum)
        );
    }
}
