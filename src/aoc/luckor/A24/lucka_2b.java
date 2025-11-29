package aoc.luckor.A24;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public class lucka_2b extends lucka_2 {

    public static List<Integer> snip(List<Integer> r, int ix) {
       List<Integer> tr = new ArrayList<>(r);
       tr.remove(ix);
       return tr;
    }

    public String svar() {
        int acc = 0;
        sammanstall();

        for (List<Integer> r : rapporter) {
            acc += IntStream.range(0, r.size())
                    .mapToObj(ix -> !Objects.equals(checker(snip(r, ix)), null))
                    .toList().stream().anyMatch(s -> s) ? 1 : 0;
        }

        return String.valueOf(acc);
    }
}
