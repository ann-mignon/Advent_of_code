package aoc.luckor.A25;

import aoc.misc.CharGrid;
import aoc.misc.Point;

import java.util.ArrayList;
import java.util.List;

public class lucka_4b extends lucka_4{

    public String svar() {
        cg = new CharGrid(input);
        int removed = 0;
        List<Point> remList = new ArrayList<>();

        do {
            remList.clear();
            int x, y = 0;
            while (y < cg.hojd) {
                x = 0;
                while (x < cg.bredd) {
                    if (cg.getCharAt(x, y) == '@' && locWeight(x, y) < 4) {
                        remList.add(new Point(x, y));
                    }
                    ++x;
                }
                ++y;
            }

            removed += remList.size();

            for (Point p : remList) {
                cg.setCharAt(p, '.');
            }
        } while (!remList.isEmpty());

        return String.valueOf(removed);
    }
}
