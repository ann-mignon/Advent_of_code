package aoc.luckor.A24;

import aoc.Losning;
import aoc.misc.CharGrid;
import aoc.misc.Point;

import java.util.Map;

public class lucka_6 extends Losning {

    CharGrid grid;
    Vakt vakt;

    static final Map<Character, Point> dirChar =
        Map.of('^', new Point(0, -1),
               '>', new Point(1, 0),
               'v', new Point(0, 1),
               '<', new Point(-1, 0));

    static Point r90 (Point p) {
        if (p.x == 0 && p.y == -1) {
            return new Point(1, 0);
        }
        if (p.x == 1 && p.y == 0) {
            return new Point(0, 1);
        }
        if (p.x == 0 && p.y == 1) {
            return new Point(-1, 0);
        }
        if (p.x == -1 && p.y == 0) {
            return new Point(0, -1);
        }
        throw new RuntimeException("ej enhetsvektor");
    }

    static class Vakt {
        public Point pos;
        public Point dir;

        public Vakt(Point p, Point d) {
            pos = p;
            dir = d;
        }

        public void step() {
            pos.x += dir.x;
            pos.y += dir.y;
        }
    }

    public void setup() {
        grid = new CharGrid(input);

        for (char c : dirChar.keySet()) {
            Point vPos = grid.findFirst(c);
            if (vPos != null) {
                vakt = new Vakt(vPos, dirChar.get(c));
                break;
            }
        }
    }

    Character ahead() {
        Character a = grid.getCharAt(vakt.pos.add(vakt.dir));
        return a == null ? '?' : a;
    }

    public void run() {
        while (grid.inGrid(vakt.pos)) {
            while (ahead().equals('#')) vakt.dir = r90(vakt.dir);

            grid.setCharAt(vakt.pos, 'X');
            vakt.step();
        }
    }

    public String svar() {
        setup();
        run();

        return String.valueOf(grid.countChar('X'));
    }
}
