package aoc.luckor.A25;

import aoc.Losning;
import aoc.luckor.A24.lucka_7;
import aoc.misc.EqCheck;

import java.util.Arrays;
import java.util.Map;

public class lucka_6 extends Losning {

    int kolumner;
    String[] oplines;
    long[] resKol;

    long[][] operander;
    EqCheck.eqOp[] operationer;
    Map<Character, lucka_7.basicOps>
            opmap = Map.of('*', lucka_7.basicOps.PROD,
                           '+', lucka_7.basicOps.SUM);
    Map<lucka_7.basicOps, Long>
            idmap = Map.of(lucka_7.basicOps.PROD, 1L,
                           lucka_7.basicOps.SUM, 0L);

    public void bered() {
        kolumner = inputLines[0].split("\s+").length;
        oplines = Arrays.stream(Arrays.copyOfRange(inputLines, 0, inputLines.length - 1)).toArray(String[]::new);
        resKol = new long[kolumner];

        operationer = Arrays.stream(inputLines[inputLines.length - 1].split("\s+"))
                .map(s -> opmap.get(s.toCharArray()[0])).toArray(lucka_7.basicOps[]::new);
    }

    public String svar() {
        int i = 0, j;
        bered();

        long[][] opgrid = Arrays.stream(oplines).map(s -> Arrays.stream(s.split("\s+"))
                                .mapToLong(Long::parseLong).toArray()).toArray(long[][]::new);
        operander = new long[kolumner][opgrid.length];

        while (i < kolumner) {
            operander[i] = new long[opgrid.length];

            j = 0;
            while(j < opgrid.length) {
                operander[i][j] = opgrid[j][i];
                ++j;
            }
            ++i;
        }

        i = 0;
        while (i < kolumner) {
            resKol[i] = Arrays.stream(operander[i]).reduce(idmap.get(operationer[i]), operationer[i].getOperator());
            ++i;
        }

        return String.valueOf(Arrays.stream(resKol).sum());
    }
}
