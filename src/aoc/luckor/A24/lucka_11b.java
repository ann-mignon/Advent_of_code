package aoc.luckor.A24;

public class lucka_11b extends lucka_11 {

    public String svar() {
        int i = 0;
        while(i++ < 75) {
            blinka();
        }

        return String.valueOf(stenmap.values().stream().mapToLong(Long::valueOf).sum());
    }
}
