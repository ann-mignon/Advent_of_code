package aoc.luckor.A25;

import aoc.Losning;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class lucka_1 extends Losning {
    protected final Pattern movRx = Pattern.compile("([L|R])(\\d+)");

    Las las;

    protected void input() {
        for (String s : inputLines) {
            if (s.matches(movRx.pattern())) {
                Matcher m = movRx.matcher(s);
                m.matches();
                las.spin(Integer.parseInt(m.group(2)) * (s.toCharArray()[0] == 'L' ? -1 : 1));
            }
        }
    }

    public String svar() {
        las = new Las();
        this.input();
        return String.valueOf(las.hist[0]);
    }

    static class Las {
        int pos = 50;
        int[] hist = new int[100];

        Las() {
            hist[50] = 1;
        }

        public void spin(int mov) {
            this.pos += mov + 1000;
            this.pos %= 100;
            hist[this.pos] += 1;
        }
    }
}
