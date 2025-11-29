package aoc.misc;

import java.util.Arrays;

import aoc.luckor.A24.lucka_8_bas;
import static aoc.Util.bino;

public class Mastgrupp {

    protected int[] grupp;
    lucka_8_bas lucka;

    public Mastgrupp(int[] grupp, lucka_8_bas lucka) {
        this.grupp = grupp;
        this.lucka = lucka;
    }

    protected boolean vertikal(int p) {
        return p < lucka.frekvensKarta.length && p >= 0;
    }

    protected boolean horisontal(int a, int b) {
        int mp = b % lucka.wlen, np = a % lucka.wlen;
        return mp <= (np * 2) && mp > (np * 2 - lucka.wlen);
    }

    public int[] resonans() {
        int[] noder = new int[bino(grupp.length, 2) * 2];
        int a = 0, m = 0, n, dist, anti1, anti2;

        while (m < grupp.length) {
            n = m;

            while(++n < grupp.length) {
                dist = grupp[n] - grupp[m];

                if (vertikal(anti1 = grupp[n] + dist) &&
                    horisontal(grupp[n], grupp[m])) {
                    noder[a++] = anti1;
                }
                if (vertikal(anti2 = grupp[m] - dist) &&
                    horisontal(grupp[m], grupp[n])) {
                    noder[a++] = anti2;
                }
            }
            ++m;
        }

        return Arrays.copyOfRange(noder, 0, a);
    }
}
