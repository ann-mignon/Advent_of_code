package aoc.luckor.A24;

import aoc.AOC;

import java.util.Arrays;

public class lucka_14b extends lucka_14 {

    String granbild;

    public void setInput(String input) {
        super.setInput(input);
        granbild = AOC.hamtaInputFil(24, 14, "gran.txt");
    }

    public boolean checkGran() {
        int y = 0;
        StringBuilder sb = new StringBuilder();
        char[][] img = new char[boundH][boundW];
        for(;y < boundH;++y) {
            Arrays.fill(img[y], '.');
        }

        for(Robot14 r: robotar) {
            img[r.pos.y][r.pos.x] = 'w';
        }

        for (char[] rad : img) {
            sb.append(new String(rad)).append("\n");
        }

        return granbild.contentEquals(sb);
     }


    public String svar() {
        int sek = 0;

        while (!checkGran()) {
            for(Robot14 r: robotar) {
                  r.step();
            }
            ++sek;
        }

        return String.valueOf(sek);
    }
}
