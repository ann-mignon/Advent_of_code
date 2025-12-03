package aoc.luckor.A25;

import aoc.Losning;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class lucka_3 extends Losning {

    public String svar() {
        List<Integer> vals = new ArrayList<>();

        for (String bank : inputLines) {
            int[] bcvals = bank.chars().toArray();
            char f = (char) Arrays.stream(Arrays.copyOfRange(bcvals, 0, bcvals.length - 1)).max().getAsInt();
            char l = (char) Arrays.stream(Arrays.copyOfRange(bcvals, bank.indexOf(f) + 1, bcvals.length)).max().getAsInt();

            vals.add(Integer.parseInt(new String(new char[]{f, l})));
        }

        return String.valueOf(vals.stream().reduce(0, Integer::sum));
    }
}
