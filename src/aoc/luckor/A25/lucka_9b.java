package aoc.luckor.A25;

import aoc.misc.Point;

import java.util.Arrays;

public class lucka_9b extends lucka_9 {

    int w, h;
    byte[][] gronGrid;

    boolean gronline(int a, int b, int c, int d, boolean v) {
        if (v) {
            for (; a != b; a += d) {
                if (gronGrid[a][c] == 0) return false;
            }
        } else {
            for (; a != b; a += d) {
                if (gronGrid[c][a] == 0) return false;
            }
        }
        return true;
    }

    @Override
    boolean somecond(Point p1, Point p2) {
        int xinc = p1.x < p2.x ? 1 : -1,
            yinc = p1.y < p2.y ? 1 : -1;
        return gronline(p1.x, p2.x, p1.y, xinc, false) &&
            gronline(p1.y, p2.y, p1.x, yinc, true) &&
            gronline(p1.x, p2.x, p2.y, xinc, false) &&
            gronline(p1.y, p2.y, p2.x, yinc, true);
    }

    void outline() {
        int i, j, c, n;
        boolean v, dir; // false för neg;

        for (j = len - 1, i = 0; i < len; j = i++) {
            v = tiles[i].x == tiles[j].x;
            c = v ? tiles[j].y : tiles[j].x;
            n = v ? tiles[i].y : tiles[i].x;
            dir = n - c > 0;

            while (c != n) {
                gronGrid[v ? c : tiles[i].y][v ? tiles[i].x : c] = 1;
                c += dir ? 1 : -1;
            }
        }
    }

    void fyll() {
        int i, j, memog;
        boolean paint, was;

        for (i = 0; i < h - 1; ++i) {
            j = 0;
            paint = gronGrid[i][j++] == 1;
            for (; j < w - 2; ++j) {
                memog = gronGrid[i][j];
                gronGrid[i][j] = paint ? 1 : gronGrid[i][j];
                if (memog == 0 && gronGrid[i][j + 1] == 1) {
                    if (gronGrid[i][j + 2] == 0) {
                        paint = !paint;
                    } else {
                        paint = true;
                    }
                }
                if (memog == 1 && gronGrid[i][j + 1] == 1 && gronGrid[i][j + 2] == 0) {
                    paint = gronGrid[i - 1][j + 2] == 1;
                }
            }
        }
    }

    public String svar() {
        las();
        w = Arrays.stream(tiles).mapToInt(t -> t.x).max().getAsInt() + 2;
        h = Arrays.stream(tiles).mapToInt(t -> t.y).max().getAsInt() + 2;
        gronGrid = new byte[h][w];

        outline();
        fyll();
        bortast();

        return String.valueOf(Arrays.stream(tprod).max().getAsLong());
    }
}
