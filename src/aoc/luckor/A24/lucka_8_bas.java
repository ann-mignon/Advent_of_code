package aoc.luckor.A24;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.stream.IntStream;

import aoc.Losning;
import aoc.misc.CharGrid;
import aoc.misc.Mastgrupp;

import static aoc.Util.alphanum;

public abstract class lucka_8_bas<T extends Mastgrupp> extends Losning {

    Class<T>    mastRef;

    public int[]            frekvensKarta;
    protected   int[]       teckenIndex;
    protected   T[]         grupper;
    private CharGrid        kartObjekt;

    public void setInput(String input) {
        super.setInput(input);

        kartObjekt = new CharGrid(input);
        frekvensKarta = new int[len * wlen];
        teckenIndex = IntStream.range(0, alphanum.length)
                .filter(i -> linearInput.indexOf(alphanum[i]) != -1).toArray();
    }

    private T mastFabrik(int tIndex) {
        try {
            return  mastRef.getDeclaredConstructor(int[].class, lucka_8_bas.class)
                    .newInstance(kartObjekt.multiIx(alphanum[tIndex]), this);
        } catch (ReflectiveOperationException refx) {
            // välkommen till min optimistiska mastfabrik :-)
            return null;
        }
    }

    protected long summeraResonans() {
        grupper = (T[]) Array.newInstance(mastRef, teckenIndex.length);
        IntStream.range(0, teckenIndex.length).forEach(i -> grupper[i] = mastFabrik(teckenIndex[i]));

        for (T g : grupper) {
            for (int fmix : g.resonans()) {
                if(fmix < frekvensKarta.length) ++frekvensKarta[fmix];
            }
        }

        return Arrays.stream(frekvensKarta).filter(p -> p > 0).count();
    }
}
