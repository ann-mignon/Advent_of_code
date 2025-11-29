package aoc.luckor.A24;

import aoc.Losning;
import aoc.misc.Point;
import aoc.misc.PointL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class lucka_13 extends Losning {

    protected final Pattern knapprx = Pattern.compile("Button [A|B]: X\\+(\\d+), Y\\+(\\d+)");
    protected final Pattern prylrx = Pattern.compile("Prize: X=(\\d+), Y=(\\d+)");
    KloMaskin[] maskiner;
    List<long[]>[] kombination;

    static long det2d(long ax, long ay, long bx, long by) {
        return ax * by - ay * bx;
    }

    record KloMaskin (Point kA, Point kB, PointL pryl_loc) {}

    public long runCheck() {
        int cbt = 0;
        long atrk, btrk;

        for (KloMaskin m : maskiner) {

            atrk = det2d(m.pryl_loc.x, m.pryl_loc.y, m.kB.x, m.kB.y) / det2d(m.kA.x, m.kA.y, m.kB.x, m.kB.y);
            btrk = det2d(m.kA.x, m.kA.y, m.pryl_loc.x, m.pryl_loc.y) / det2d(m.kA.x, m.kA.y, m.kB.x, m.kB.y);

            if (atrk >= 0 && btrk >= 0 && m.kA.x * atrk + m.kB.x * btrk == m.pryl_loc.x &&
                                          m.kA.y * atrk + m.kB.y * btrk == m.pryl_loc.y) {
                kombination[cbt].add(new long[]{ atrk, btrk });
            }

            cbt++;
        }

        return Arrays.stream(kombination).filter(k -> !k.isEmpty())
                .map(k -> k.stream().mapToLong(mk -> mk[0] * 3 + mk[1]).min().getAsLong())
                .mapToLong(Long::longValue).sum();
    }

    protected void prepMaskiner(long offset) {
        int i = 0;
        MatchResult kmA, kmB, pm;
        String[] tmpRad;
        String[] rmSet = input.split("\n\n");
        maskiner = new KloMaskin[rmSet.length];
        kombination = new ArrayList[rmSet.length];

        for (String rm : rmSet) {
            tmpRad = rm.split("\n");
            kmA = knapprx.matcher(tmpRad[0]).results().findFirst().orElseThrow();
            kmB = knapprx.matcher(tmpRad[1]).results().findFirst().orElseThrow();
            pm  = prylrx.matcher(tmpRad[2]).results().findFirst().orElseThrow();
            maskiner[i] = new KloMaskin(
                    new Point(Integer.parseInt(kmA.group(1)), Integer.parseInt(kmA.group(2))),
                    new Point(Integer.parseInt(kmB.group(1)), Integer.parseInt(kmB.group(2))),
                    new PointL(Long.parseLong(pm.group(1)) + offset, Long.parseLong(pm.group(2)) + offset));
            kombination[i] = new ArrayList<>();
            ++i;
        }
    }

    public String svar() {
        prepMaskiner(0L);
        return String.valueOf(runCheck());
    }
}
