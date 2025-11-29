package aoc.luckor.A24;

import aoc.Losning;
import aoc.misc.CharGrid;
import aoc.misc.Point;

import java.util.*;

public class lucka_10 extends Losning {

    CharGrid cg;

    private final Deque<Integer> stack = new ArrayDeque<>();
    private final List<Integer> besokt = new ArrayList<>();
    private final int[][] gendir = new int[][]{{0, -1}, {1, 0}, {0, 1}, {-1, 0}};

    boolean checkE(Point p, Point m) {
        if (!cg.inGrid(p)) return false;
        char target = cg.getCharAt(p), cposc = cg.getCharAt(m);
        if (!(cposc - target == 1)) return false;
        return !besokt.contains(cg.getIx(p));
    }

    int stigPoang(int p) {
        int cur, k;
        Point mpoint = new Point(-1, -1), npoint = new Point(-1, -1);

        stack.clear();
        besokt.clear();

        stack.push(p);

        while (!stack.isEmpty()) {
            cur = stack.pop();
            besokt.add(cur);

            mpoint.x = cur % wlen; mpoint.y = cur / wlen;

            k = 0;
            while (k < 4) {
                npoint.x = mpoint.x + gendir[k][0];
                npoint.y = mpoint.y + gendir[k][1];
                if (checkE(npoint, mpoint)) {
                    stack.push(cg.getIx(npoint));
                }
                ++k;
            }
        }

        return (int) besokt.stream().filter(x -> cg.getCharAt(x) == '0').count();
    }

    public void setInput(String input) {
        super.setInput(input);
        cg = new CharGrid(input);
    }

    public String svar() {

        int[] mojliga = cg.multiIx('9');
        int[] poang = new int[mojliga.length];

        for (int j = 0; j < mojliga.length; ++j) {
            poang[j] = stigPoang(mojliga[j]);
        }

        return String.valueOf(Arrays.stream(poang).sum());
    }
}
