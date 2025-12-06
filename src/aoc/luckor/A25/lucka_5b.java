package aoc.luckor.A25;

import java.util.Arrays;

public class lucka_5b extends lucka_5 {

    void trimVall() {
        intervall = parseRanges(input.split("\n\n")[0].split("\n"));
        Range[] cpIntervall = Arrays.stream(intervall).map(r -> new Range(r.from(), r.to())).toArray(Range[]::new);

        int i = 0, j;

        while (i < intervall.length) {
            Range fint = intervall[i];
            j = i + 1;
            while (j < cpIntervall.length) {
                Range tint = cpIntervall[j];
                if (tint.from() <= fint.to()) {
                    intervall[j] = new Range(fint.to() + 1, tint.to());
                }
                ++j;
            }
            ++i;
        }

        intervall = Arrays.stream(intervall).filter(r -> r.from() <= r.to()).toArray(Range[]::new);
    }

    public String svar() {
        trimVall();
        return String.valueOf(Arrays.stream(intervall).mapToLong(r -> r.to() - r.from() + 1).sum());
    }
}
