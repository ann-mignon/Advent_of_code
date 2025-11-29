package aoc.luckor.A24;

import aoc.misc.Point;

import java.util.*;

public class lucka_10b extends lucka_10 {

    List<Integer> cur = new ArrayList<>();
    List<int[]> tmpuq = new ArrayList<>();
    Point[] gendir = new Point[] {
            new Point(0, -1),
            new Point(1, 0),
            new Point(0, 1),
            new Point(-1, 0) };

    boolean checkE(Point p, Point m) {
        if (!cg.inGrid(p)) return false;
        char target = cg.getCharAt(p), cposc = cg.getCharAt(m);
        if (!(cposc - target == -1)) return false;
        return !cur.contains(cg.getIx(p));
    }

    int stigPoang(int p) {

        Deque<ArrayList<Integer>> pot = new ArrayDeque<>();
        tmpuq.clear();

        Point mpoint = new Point(-1, -1), npoint = new Point(-1, -1);

        pot.push(new ArrayList<>(List.of(p)));

        while (!pot.isEmpty()) {
            int k, lpos;
            ArrayList<Integer> nupot;
            cur = pot.pop();
            lpos = cur.getLast();

            if (cg.getCharAt(lpos) == '9') {
                tmpuq.add(cur.stream().mapToInt(n -> n).toArray());
                continue;
            }

            mpoint.x = lpos % wlen; mpoint.y = lpos / wlen;

            k = 0;
            while (k < 4) {
                npoint.x = mpoint.x + gendir[k].x;
                npoint.y = mpoint.y + gendir[k].y;
                if (checkE(npoint, mpoint)) {
                    nupot = new ArrayList<>(cur);
                    nupot.add(cg.getIx(npoint));
                    pot.push(nupot);
                }
                ++k;
            }
        }
        return tmpuq.size();
    }

    public String svar() {

        int[] mojliga = cg.multiIx('0');
        int[] poang = new int[mojliga.length];
        int j = 0;

        for (; j < mojliga.length; ++j) {
            poang[j] = stigPoang(mojliga[j]);
        }

        return String.valueOf(Arrays.stream(poang).sum());
    }
}
