package aoc.luckor.A24;

import aoc.Losning;

import java.util.ArrayList;
import java.util.List;

public class lucka_4 extends Losning {

    static class Point {
        public int x;
        public int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    final List<Point> xpos = new ArrayList<>();
    int hojd;
    int bredd;

    char[] str;
    int[][] rot;

    @Override
    public void setInput(String input) {
        super.setInput(input);
        hojd = inputLines.length; bredd = inputLines[0].length();
    }

    protected void getPoints(int str_ix) {
        int i, j;

        for (i = 0; i < hojd; ++i) {
            char[] cs = inputLines[i].toCharArray();
            for (j = 0; j < bredd; ++j) {
                if (cs[j] == str[str_ix]) {
                    xpos.add(new Point(j, i));
                }
            }
        }
    }

    public String svar() {
        str = new char[]{'X', 'M', 'A', 'S'};
        rot = new int[][] { {1, 0}, {1, 1},  {0, 1}, {-1, 1},
                            {-1, 0}, {-1, -1}, {0, -1}, {1, -1} };

        getPoints(0);

        return String.valueOf(xpos.stream().map(p -> score(p.x, p.y)).reduce(0, Integer::sum));
    }

    private int score(int x, int y) {
        int sp, inc, score = 0, nx, ny;

        for (int[] r : rot) {
            sp = 0; inc = 1;

            while (++sp < str.length) {
                nx = x + r[0] * sp; ny = y + r[1] * sp;
                if (nx < 0 || nx >= bredd || ny < 0 || ny >= hojd) break;
                if (inputLines[ny].charAt(nx) == str[sp]) ++inc;
            }

            if (inc == str.length) ++score;
        }

        return score;
    }
}
