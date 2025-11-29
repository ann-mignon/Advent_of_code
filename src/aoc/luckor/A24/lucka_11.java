package aoc.luckor.A24;

import aoc.Losning;
import static aoc.Util.*;

import java.util.HashMap;

public class lucka_11 extends Losning {

    HashMap<Long, Long> stenmap = new HashMap<>();
    HashMap<Long, Long> tmpmap = new HashMap<>();

    public void setInput(String input) {
        super.setInput(input);
        int[] fsten = toIntArray(inputLines[0].split(" "));

        for (long fs: fsten) {
            stenmap.merge(fs, 1L, Long::sum);
        }
    }

    void blinka () {
        int decnum, splitn;
        long n1, n2;
        String nstr;
        tmpmap.clear();

        for (long k : stenmap.keySet()) {
            if (stenmap.get(k) == 0L) continue;

            if (k == 0) {
                tmpmap.put(1L, stenmap.get(0L));
            } else if ((decnum = decimals(k)) % 2 == 0) {
                nstr = String.valueOf(k);
                splitn = decnum / 2;
                n1 = Long.parseLong(nstr.substring(0, splitn));
                n2 = Long.parseLong(nstr.substring(splitn));

                tmpmap.merge(n1, stenmap.get(k), Long::sum);
                tmpmap.merge(n2, stenmap.get(k), Long::sum);
            } else {
                tmpmap.merge(k * 2024, stenmap.get(k), Long::sum);
            }
            stenmap.put(k, 0L);
        }

        for (long t : tmpmap.keySet()) {
            stenmap.merge(t, tmpmap.get(t), Long::sum);
        }
    }

    public String svar() {
        int i = 0;
        while(i++ < 25) {
            blinka();
        }

        return String.valueOf(stenmap.values().stream().mapToLong(Long::valueOf).sum());
    }
}
