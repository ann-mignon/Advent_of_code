package aoc.luckor.A24;

import aoc.misc.CharGrid;
import aoc.misc.Point;

import java.util.Arrays;

public class lucka_6b extends lucka_6 {

    Point curPos;
    int[] duplo;

    public int dirIx(Point dirv) {
        if (dirv.x ==  1 && dirv.y ==  0)  return 0;
        if (dirv.x ==  0 && dirv.y ==  1)  return 1;
        if (dirv.x == -1 && dirv.y ==  0)  return 2;
        if (dirv.x ==  0 && dirv.y == -1)  return 3;
        throw new RuntimeException("unreachable");
    }

    public boolean run2() {
        while (grid.inGrid(vakt.pos)) {

            while (ahead().equals('#')) vakt.dir = r90(vakt.dir);
            vakt.step();

            if (++duplo[grid.tot * dirIx(vakt.dir) + grid.getIx(vakt.pos)] > 1)
                return true;
        }

        return false;
    }

    public String svar() {
        setup();
        Point spos = vakt.pos.add(new Point(0, 0));
        Point vdir = vakt.dir.add(new Point(0, 0));

        run();
        CharGrid pathGrid = new CharGrid(grid.toString());

        duplo = new int[grid.tot * 4];

        int obsCount = 0;

        for (int i : pathGrid.multiIx('X')) {

            curPos = new Point(i % grid.bredd, i / grid.hojd);
            Arrays.fill(duplo, 0);

            if (!curPos.equals(spos) && !grid.getCharAt(curPos).equals('#')) {
                grid = new CharGrid(input);
                vakt.pos = spos.add(new Point(0, 0));
                vakt.dir = vdir.add(new Point(0, 0));
                grid.setCharAt(curPos, '#');
                obsCount += run2() ? 1 : 0;
            }

            ++i;
        }

        return String.valueOf(obsCount);
    }
}
