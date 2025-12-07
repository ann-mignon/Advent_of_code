package aoc.luckor.A25;

import aoc.Losning;
import aoc.misc.CharGrid;
import aoc.misc.Point;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class lucka_7 extends Losning {
    
    CharGrid mf;
    ArrayList<Beam> beams = new ArrayList<>();
    Set<Beam> nubeams = new HashSet<>();
    Point spos;
    
    boolean beamAt(Point w) {
        return nubeams.stream().anyMatch(b -> b.start.equals(w)) ||
                 beams.stream().anyMatch(b -> b.head.equals(w) || b.head.equals(w.add(Point.VUP)));
    }
    
    boolean splitAt(Point w) {
        return mf.getCharAt(w) == '^';
    }
    
    class Beam {
        boolean isSplit = false;
        
        boolean active() {
            return !isSplit && head.y < mf.hojd - 1;
        }
        
        Point start;
        Point head;
        
        public Beam(Point p) {
            start = p;
            head = p.add(new Point(0, 0));
        }
        
        void split() {
            isSplit = true;
            if (head.y < mf.hojd - 2) {
                if (head.x > 0) {
                    spawnAt(head.add(new Point(-1, 1)));
                }
                if (head.x < mf.bredd - 1) {
                    spawnAt(head.add(new Point(1, 1)));
                }
            }
        }

        void prop() {
            if (active()) {
                Point pro = head.add(Point.VDOWN);
                if (splitAt(pro)) {
                    split();
                } else {
                    head = pro;
                }
            }
        }
        
        void spawnAt(Point loc) {
            if (!beamAt(loc)) {
                nubeams.add(new Beam(loc));
            }
        }
    }
    
    void propAll() {
        for (Beam b : beams) {
            b.prop();
        }
        beams.addAll(nubeams);
        nubeams.clear();
    }

    public String svar() {
        mf = new CharGrid(input);
        spos = mf.findFirst('S');
        beams.add(new Beam(spos));
        
        while (beams.stream().anyMatch(Beam::active)) {
            propAll();
        }
        
        return String.valueOf(beams.stream().filter(b -> b.isSplit).count());
    }
}
