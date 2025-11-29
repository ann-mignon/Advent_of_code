package aoc.luckor.A24;

import aoc.misc.Mastgrupp;

public class lucka_8 extends lucka_8_bas<Mastgrupp> {

    public String svar() {
        mastRef = Mastgrupp.class;
        return String.valueOf(summeraResonans());
    }
}
