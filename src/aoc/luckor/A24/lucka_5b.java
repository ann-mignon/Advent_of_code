package aoc.luckor.A24;

import java.util.Arrays;
import java.util.List;

public class lucka_5b extends lucka_5 {

    protected List[] oordning;

    @Override
    public String svar() {

        sammanstall();

        oordning = Arrays.stream(pglist).filter(pl -> !checker(pl)).toArray(List[]::new);
        int[][] nyordning = new int[oordning.length][0];

        int i = 0;
        for (List<Integer> oo : oordning) {
            nyordning[i++] = oo.stream()
                    .sorted((a, b) -> ord.get(a).arEfter(b) ? 1 : -1)
                    .mapToInt(j -> j).toArray();
        }

        int res = Arrays.stream(nyordning).mapToInt(n -> n[n.length / 2]).sum();

        return String.valueOf(res);
    }
}
