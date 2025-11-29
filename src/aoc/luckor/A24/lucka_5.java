package aoc.luckor.A24;

import aoc.Losning;

import java.util.*;
import static aoc.Util.*;

public class lucka_5 extends Losning {

    public static class Pord {
        public Set<Integer> efter = new HashSet<>();

        public static Pord fromEfter(int fore) {
            Pord p = new Pord();
            p.efter.add(fore);
            return p;
        }

        public boolean arEfter(int n) {
            return efter.contains(n);
        }
    }

    public boolean checker(List<Integer> pl) {
        for (int p : pl) {
            for (int k : pl) {
                if (k == p) continue;
                if (pl.indexOf(k) > pl.indexOf(p) && ord.get(p).arEfter(k)) {
                    return false;
                }
            }
        }
        return true;
    }

    protected Map<Integer, Pord> ord = new HashMap<>();
    protected List<Integer>[] pglist;

    protected void sammanstall() {
        String[] delar = splitta(input, "\n\n");
        String[] konf = splitta(delar[0], "\n");
        pglist = Arrays.stream(splitta(delar[1], "\n"))
                .map(pl -> toIntegerList(splitta(pl, ","))).toArray(List[]::new);

        for (String l : konf) {
            int[] cond = toIntArray(splitta(l, "\\|"));

            if (!ord.containsKey(cond[0])) {
                ord.put(cond[0], new Pord());
            }

            if (ord.containsKey(cond[1])) {
                ord.get(cond[1]).efter.add(cond[0]);
            } else {
                ord.put(cond[1], Pord.fromEfter(cond[0]));
            }
        }
    }

    public String svar() {
        sammanstall();

        return String.valueOf(Arrays.stream(pglist).filter(this::checker)
                                    .mapToInt(pl -> pl.get(pl.size()/2)).sum());
    }
}
