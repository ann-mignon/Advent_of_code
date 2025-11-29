package aoc.luckor.A24;

import aoc.Losning;
import aoc.misc.CharGrid;
import aoc.misc.Point;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static aoc.Util.putln;

public class lucka_15 extends Losning {

    CharGrid cg;
    Lager lokal;
    LagerRobot automat;
    String prgstr;

    abstract class LagerObj extends Point {
        public LagerObj(int p) {
            this.x = p % wlen;
            this.y = p / wlen;
        }

        abstract boolean knuff(Point dir);
    }

    class LagerRobot extends Point {

        static Map<Character, Point> movs =
                Map.of('>', new Point(1, 0),
                       'v', new Point(0, 1),
                       '<', new Point(-1, 0),
                       '^', new Point(0, -1));

        int ptr = 0;
        char[] prg;

        public void step() {
            Point mov = movs.get(prg[ptr++]);
            LagerObj lo = lokal.objAt(this.add(mov));

            if (lo == null || lo.knuff(mov)) {
                this.x += mov.x;
                this.y += mov.y;
            }
        }

        public void run() {
            while(ptr < prg.length) {
                step();
            }
        }

        public LagerRobot(int p, String prg) {
            this.x = p % wlen;
            this.y = p / wlen;
            this.prg = prg.toCharArray();
        }
    }

    class Lager {
        List<Lada> lador;
        Set<Vagg> vaggar;

        public LagerObj objAt(Point p) {
            Lada lada = lador.stream().filter(l -> l.equals(p)).findFirst().orElse(null);
            if (lada != null) return lada;
            return vaggar.stream().filter(v -> v.equals(p)).findFirst().orElse(null);
        }

        public Lager(List<Lada> lador, Set<Vagg> vaggar) {
            this.lador = lador;
            this.vaggar = vaggar;
        }

        public long gpSum() {
            return this.lador.stream().mapToInt(l -> l.y * 100 + l.x).sum();
        }

        public void print() {
            int y = 0;
            StringBuilder sb = new StringBuilder();
            char[][] img = new char[len][wlen];

            for (;y < len;++y) {
                Arrays.fill(img[y], '.');
            }

            for (Vagg v : vaggar) {
                img[v.y][v.x]       = '#';
                img[v.y][v.x + 1]   = '#';
            }

            for (Lada l : lador) {
                img[l.y][l.x] = '[';
                img[l.y][l.x + 1]  = ']';
            }

            img[automat.y][automat.x] = '@';

            for (char[] rad : img) {
                sb.append(new String(rad)).append("\n");
            }

            putln(sb.toString());
        }
    }

    class Lada extends LagerObj {
        public Lada(int p) { super(p); }
        boolean knuff(Point dir) {
            LagerObj o = lokal.objAt(this.add(dir));

            if (o == null || o.knuff(dir)) {
                this.x += dir.x;
                this.y += dir.y;
                return true;
            }

            return false;
        }
    }

    class Vagg extends LagerObj {
        public Vagg(int p) { super(p); }
        boolean knuff(Point dir) { return false; }
    }

    public void setInput(String input) {
        super.setInput(input);
        String[] inprep = input.split("\n\n");
        String[] matt = inprep[0].split("\n");
        cg = new CharGrid(inprep[0]);
        prgstr = inprep[1].replaceAll("\n", "");
        wlen = matt[0].length();
        len = matt.length;
    }

    public String svar() {
        automat = new LagerRobot(linearInput.indexOf('@'), prgstr);
        lokal = new Lager(Arrays.stream(cg.multiIx('O')).mapToObj(Lada::new).toList(),
                Arrays.stream(cg.multiIx('#')).mapToObj(Vagg::new).collect(Collectors.toSet()));

        automat.run();

        return String.valueOf(lokal.gpSum());
    }
}
