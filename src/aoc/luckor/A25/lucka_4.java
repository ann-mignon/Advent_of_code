package aoc.luckor.A25;

import aoc.Losning;
import aoc.misc.CharGrid;
import aoc.misc.Point;

import java.util.Arrays;

public class lucka_4 extends Losning {

    CharGrid cg;

    Point[] neighbors(int x, int y) {
        return new Point[]{
            new Point(x + 1, y - 1),
            new Point(x + 1, y),
            new Point(x + 1, y + 1),
            new Point(x, y + 1),
            new Point(x - 1, y + 1),
            new Point(x - 1, y),
            new Point(x - 1, y - 1),
            new Point(x, y - 1)
        };
    }

    int locWeight(int x, int y) {
        Point[] nb = neighbors(x, y);
        return Arrays.stream(nb).mapToInt(p -> {
            Character c = cg.getCharAt(p);
            return c != null && c == '@' ? 1 : 0;
        }).sum();
    }

    public String svar() {

         cg = new CharGrid(input);

        int x, y = 0, avail = 0;
        while (y < cg.hojd) {
            x = 0;
            while (x < cg.bredd) {
                avail += cg.getCharAt(x, y) == '@' && locWeight(x, y) < 4 ? 1 : 0;
                ++x;
            }
            ++y;
        }

        return String.valueOf(avail);
    }
}
