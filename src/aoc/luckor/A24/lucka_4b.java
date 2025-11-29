package aoc.luckor.A24;

public class lucka_4b extends lucka_4 {

    public String svar() {
        str = new char[]{'M', 'A', 'S'};
        rot = new int[][] { {1, 1}, {-1, 1}, {-1, -1}, {1, -1}};

        getPoints(1);

        return String.valueOf(xpos.stream().mapToInt(p -> score(p.x, p.y)).sum());
    }

    private int score(int x, int y) {
        int inc = 0, mnx, mny, snx, sny;

        for (int[] r : rot) {
            mnx = x + r[0] * -1; mny = y + r[1] * -1;
            snx = x + r[0]; sny = y + r[1];
            if (mnx < 0 || mnx >= bredd || mny < 0 || mny >= hojd) break;
            if (snx < 0 || snx >= bredd || sny < 0 || sny >= hojd) break;
            if (inputLines[mny].charAt(mnx) == str[0] && inputLines[sny].charAt(snx) == str[2]) ++inc;
        }

        return inc == 2 ? 1 : 0;
    }
}
