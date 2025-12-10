package aoc.luckor.A25;

import aoc.Losning;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class lucka_10 extends Losning {

    Maskin[] maskiner;

    static class Maskin {

        int mal;
        int[] knappar;

        Maskin(String mrad) {
            String[] mraw = mrad.split(" ");
            char[] indi = mraw[0].toCharArray();
            knappar = new int[mraw.length - 2];

            int i = 1;
            while (i < indi.length - 1) {
                mal |= indi[i] == '#' ? 1 << (i - 1) : 0;
                ++i;
            }

            i = 1;
            while (i < mraw.length - 1) {
                knappar[i-1] = Arrays.stream(
                    mraw[i++].replace("(", "")
                             .replace(")", "")
                             .split(",")
                    ).mapToInt(s -> 1 << Integer.parseInt(s))
                     .reduce(0, (a, b) -> a | b);
            }
        }

        int minibut() {
            List<Integer> toMal = new ArrayList<>();
            int alt = (1 << knappar.length) - 1, i = 0, c, j;

            while (i < alt) {
                j = 0; c = 0;

                while(j < knappar.length) {
                    if ((i & (1 << j)) != 0) {
                        c ^= knappar[j];
                    }
                    ++j;
                }

                if (c == mal) {
                    toMal.add(i);
                }
                ++i;
            }

            if (toMal.isEmpty()) {
                throw new RuntimeException("Lösning saknas.");
            }

            return toMal.stream().mapToInt(Integer::bitCount).min().getAsInt();
        }
    }

    public String svar() {
        return String.valueOf(
            Arrays.stream(inputLines)
                .map(Maskin::new)
                .mapToInt(Maskin::minibut).sum());
    }
}
