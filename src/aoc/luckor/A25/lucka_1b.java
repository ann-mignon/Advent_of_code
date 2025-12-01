package aoc.luckor.A25;

public class lucka_1b extends lucka_1 {

    public String svar() {
        las = new Laso();
        this.input();

        return String.valueOf(las.hist[0] + ((Laso) las).klick);
    }

    static class Laso extends Las {

        int klick = 0;

        @Override
        public void spin(int mov) {
            klick += Math.abs(mov) / 100;
            int tmp = this.pos + (mov % 100);

            if (this.pos != 0 && (tmp < 0 || tmp > 100)) ++klick;

            super.spin(mov);
        }
    }
}
