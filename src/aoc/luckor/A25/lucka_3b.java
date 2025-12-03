package aoc.luckor.A25;

import aoc.Losning;

import java.util.*;

import static aoc.Util.*;

public class lucka_3b extends Losning {

    private static final int BATLEN = 12;

    public String svar() {
        List<Long> vals = new ArrayList<>();

        for (String bank : inputLines) {
            char[] batta = new char[BATLEN];
            int[] bcvals = bank.chars().toArray(), window;
            int ix = 0, bix = 0, cmax;

            do {
                window = Arrays.copyOfRange(bcvals, ix, bcvals.length - (BATLEN - (bix + 1)));
                cmax = Arrays.stream(window).max().getAsInt();
                batta[bix++] = (char) cmax;
                ix += indexOfIntArray(window, cmax) + 1;
            } while (bix < BATLEN && window.length > 1);

            while (bix < BATLEN) {
                batta[bix++] = (char) bcvals[ix++];
            }

            vals.add(Long.parseLong(new String(batta)));
        }

        return String.valueOf(vals.stream().reduce(0L, Long::sum));
    }
}
