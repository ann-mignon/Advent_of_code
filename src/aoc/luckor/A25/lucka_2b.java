package aoc.luckor.A25;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static aoc.Util.*;

public class lucka_2b extends lucka_2 {

    public void checkRep(long num) {
        String str = String.valueOf(num);
        Set<Long> nnum = new HashSet<>();
        int len = str.length();
        if (len > 1) {
            String[] splits;
            int leni = 0;
            while (++leni <= len / 2) {
                if (len % leni == 0) {
                    splits = spool(str, len, leni);
                    if (Arrays.stream(splits).distinct().count() == 1) {
                        nnum.add(num);
                    }
                }
            }
        }
        this.invids.addAll(nnum);
    }

    static String[] spool(String text, int len, int size) {
        char[] data = text.toCharArray();
        String[] result = new String[len / size];
        int linha = 0, i = 0;

        while(i < len) {
            result[linha++] = new String(data, i, size);
            i += size;
        }

        return result;
    }

    public String svar() {
        String[] rngs;
        long[] rng;

        for (String r : splitta(input, ",")) {
            rngs = r.split("-");
            rng = Arrays.stream(rngs).mapToLong(Long::parseLong).toArray();

            long o1 = rng[0], o2 = rng[1];

            while (o1 <= o2) {
                checkRep(o1++);
            }
        }

        return String.valueOf(invids.stream().reduce(0L, Long::sum));
    }
}
