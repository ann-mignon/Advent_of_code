package aoc.luckor.A24;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.stream.IntStream;

public class lucka_1b extends lucka_1 {

    public String svar() {
        Matcher m = listRx.matcher(input);

        int[] lzip = m.results().mapToInt(r -> Integer.parseInt(r.group(1))).toArray(),
              rzip = m.reset().results().mapToInt(r -> Integer.parseInt(r.group(2))).toArray();

        return String.valueOf(
            IntStream.range(0, len).mapToLong(
                    ix -> Arrays.stream(rzip)
                            .filter(r -> r == lzip[ix]).count() * lzip[ix])
                            .sum());
    }
}
