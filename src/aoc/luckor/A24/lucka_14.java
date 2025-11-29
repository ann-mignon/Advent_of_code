package aoc.luckor.A24;

import aoc.Losning;
import aoc.misc.Point;

import java.util.Arrays;
import java.util.regex.Pattern;

public class lucka_14 extends Losning {

    Pattern robotRx = Pattern.compile("p=(-?\\d+),(-?\\d+) v=(-?\\d+),(-?\\d+)", Pattern.MULTILINE);
    protected Robot14[] robotar;

    int boundW;
    int boundH;

    int midW;
    int midH;

    public void setBoundH(int boundH) {
        this.boundH = boundH;
        this.midH = boundH / 2;
    }

    public void setBoundW(int boundW) {
        this.boundW = boundW;
        this.midW = boundW / 2;
    }

    public void setInput(String input) {
        super.setInput(input);
        setBoundW(101);
        setBoundH(103);

        robotar = robotRx.matcher(input).results()
                .map(rm ->
                        new Robot14(new Point(Integer.parseInt(rm.group(1)),
                                Integer.parseInt(rm.group(2))),
                                new Point(Integer.parseInt(rm.group(3)),
                                        Integer.parseInt(rm.group(4)))))
                .toArray(Robot14[]::new);
    }

    protected class Robot14 {
        Point pos;
        Point velo;

        public Robot14(Point pos, Point velo) {
            this.pos = pos;
            this.velo = velo;
        }

        public void step() {
            this.pos.x = (this.pos.x + this.velo.x + boundW) % boundW;
            this.pos.y = (this.pos.y + this.velo.y + boundH) % boundH;
        }

        public int getQ() {
            if (pos.x < midW  && pos.y < midH) return 0;
            if (pos.x > midW && pos.y < midH)  return 1;
            if (pos.x < midW && pos.y > midH)  return 2;
            if (pos.x > midW && pos.y > midH)  return 3;
            return -1;
        }
    }

    public String svar() {
        int sek = 0;

        while (sek++ < 100) {
            for(Robot14 r: robotar) {
                r.step();
            }
        }

        long q1 = Arrays.stream(robotar).filter(r -> r.getQ() == 0).count(),
             q2 = Arrays.stream(robotar).filter(r -> r.getQ() == 1).count(),
             q3 = Arrays.stream(robotar).filter(r -> r.getQ() == 2).count(),
             q4 = Arrays.stream(robotar).filter(r -> r.getQ() == 3).count();

        return String.valueOf(q1 * q2 * q3 * q4);
    }
}
