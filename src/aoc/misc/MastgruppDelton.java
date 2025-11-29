package aoc.misc;

import java.util.ArrayList;
import java.util.List;

import aoc.luckor.A24.lucka_8_bas;

public class MastgruppDelton extends Mastgrupp {

    public MastgruppDelton(int[] mast, lucka_8_bas lucka) {
        super(mast, lucka);
    }

    public int[] resonans() {
        List<Integer> noder = new ArrayList<>();
        int m = 0, n, dist, anti1, anti2, tn, tm;

        while (m < grupp.length) {
            n = m;

            while(++n < grupp.length) {
                dist = grupp[n] - grupp[m];

                noder.add(tn = grupp[n]);
                noder.add(tm = grupp[m]);

                while (vertikal(anti1 = tn + dist) && horisontal(tn, tm)) {
                    noder.add(anti1);
                    tm = tn; tn = anti1;
                }

                tn = grupp[n]; tm = grupp[m];
                while (vertikal(anti2 = tm - dist) && horisontal(tm, tn)) {
                    noder.add(anti2);
                    tn = tm; tm = anti2;
                }
            }
            ++m;
        }

        return noder.stream().mapToInt(fm -> fm).toArray();
    }
}
