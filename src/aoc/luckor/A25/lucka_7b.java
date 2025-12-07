package aoc.luckor.A25;

import aoc.Losning;
import aoc.misc.CharGrid;
import aoc.misc.Point;

import java.util.Arrays;
import java.util.stream.IntStream;

public class lucka_7b extends Losning {

    CharGrid mf;
    Point spos;

    long[][] memoGrid;

    long getLines(int x, int y) {
        if (y > memoGrid.length - 1) return 1;

        if (memoGrid[y][x] == -1) {
            if (mf.getCharAt(x, y * 2) == '.' || (spos.x == x && y == 0)) {
                memoGrid[y][x] = getLines(x, y + 1);
            }
            if (mf.getCharAt(x, y * 2) == '^') {
                memoGrid[y][x] = getLines(x - 1, y) + getLines(x + 1, y);
            }
        }

        return memoGrid[y][x];
    }

    public String svar() {
        mf = new CharGrid(input);
        spos = mf.findFirst('S');

        memoGrid = new long[mf.hojd/2][mf.bredd];

        IntStream.range(0, mf.hojd/2).forEach(r -> {
            Arrays.fill(memoGrid[r], -1L);
        });

        return String.valueOf(getLines(spos.x, 0));
    }
}
