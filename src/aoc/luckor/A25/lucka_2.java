package aoc.luckor.A25;

import aoc.Losning;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static aoc.Util.*;

public class lucka_2 extends Losning {

    List<Long> invids = new ArrayList<>();

    public String svar() {
        String[] rngs;
        long[] rng;

        for (String r : splitta(input, ",")) {
            rngs = r.split("-");
            rng = Arrays.stream(rngs).mapToLong(Long::parseLong).toArray();

            int cln;
            long o1 = rng[0], o2 = rng[1], p1, p2, pw;

            while (o1 <= o2) {
                cln = decimals(o1);
                if (cln % 2 == 0) {
                    pw = pow10(cln / 2);
                    p1 = o1 / pw;
                    p2 = o1 % pw;
                    if (p1 == p2) {
                        invids.add(o1);
                        o1 += pw;
                        continue;
                    }
                }
                ++o1;
            }
        }

        return String.valueOf(invids.stream().reduce(0L, Long::sum));
    }
}
