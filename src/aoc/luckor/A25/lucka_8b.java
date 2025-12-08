package aoc.luckor.A25;

public class lucka_8b extends lucka_8 {

    public String svar() {
        sortera();

        int i = 0;
        do { plugga(bparX[i++]); } while ( kretsar.getFirst().size() < len );

        return String.valueOf(jboxar[boxA[bparX[i - 1]]].x() * jboxar[boxB[bparX[i - 1]]].x());
    }
}
