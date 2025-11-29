package aoc.luckor.A24;

import aoc.Losning;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class lucka_1 extends Losning {
    protected final Pattern listRx = Pattern.compile("^(\\d+)\\s+(\\d+)$", Pattern.MULTILINE);

    public String svar() {
        Matcher m = listRx.matcher(input);

        int[] zip = Arrays.copyOf(m.results().mapToInt(
                            r -> Integer.parseInt(r.group(1))
                        ).sorted().toArray(), inputLines.length * 2);

        System.arraycopy(m.reset().results().mapToInt(
                            r -> Integer.parseInt(r.group(2))
                        ).sorted().toArray(), 0, zip, len, len);

        return String.valueOf(IntStream.range(0, len).map(
                n -> Math.abs(zip[n] - zip[n + len])).sum());
    }
}
