package aoc.luckor.A25;

import aoc.Losning;
import aoc.misc.Point;

import java.util.*;

import static java.lang.Integer.parseInt;

public class lucka_9 extends Losning {

    long[] tprod;
    Point[] tiles;

    boolean somecond(Point p1, Point p2) { return true; }

    void bortast () {
        int i, j;
        boolean hog;
        tprod = new long[len];

        for (i = 0; i < len; ++i) {
            Point p1 = tiles[i];
            int d, mi = -1, md = 0;
            for (j = 0; j < len; ++j) {
                if (j == i) continue;
                Point p2 = tiles[j];
                d = (Math.abs(p1.x - p2.x) + 1) * (Math.abs(p1.y - p2.y) + 1);// Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
                hog = d > md & somecond(p1, p2);
                mi = hog ? j : mi;
                md = hog ? d : md;
            }
            tprod[i] = mi == -1 ? 0 : md;
        }
    }

    void las() {
        tiles = Arrays.stream(inputLines).map(l -> {
            String[] t = l.split(",");
            return new Point(parseInt(t[0]), parseInt(t[1]));})
        .toArray(Point[]::new);
    }

    public String svar() {
        las();
        bortast();
        return String.valueOf(Arrays.stream(tprod).max().getAsLong());
    }
}
