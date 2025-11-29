package aoc.luckor.A24;

import aoc.Losning;
import aoc.misc.CharGrid;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class lucka_9 extends Losning {

    int[]  inData;
    int[]   filer;
    int[]   minne;

    private void layout() {
        int p = 0, dp = 0, dec;
        boolean semafor = true;

        while (dp < inData.length) {
            dec = inData[dp];
            while(dec-- > 0) {
                minne[p++] = semafor ? dp / 2 : -1;
            }
            semafor = !semafor; ++dp;
        }
    }

    protected void defragmentera() {
        int lptr = 0,
            rptr = minne.length - 1;

        while (lptr++ < rptr) {
            if (minne[lptr] == -1) {
                while (minne[rptr] == -1) --rptr;
                minne[lptr] = minne[rptr];
                minne[rptr--] = -1;
            }
        }
        while(minne[lptr - 1] == -1) --lptr;
    }

    private void processera() {
        boolean filSist = inData.length % 2 == 1;
        filer = IntStream.range(0, inData.length / 2 + (filSist ? 1 : 0))
                         .map(i -> inData[i * 2]).toArray();
        minne = new int[Arrays.stream(inData).sum()];

        layout();
    }

    public void setInput(String input) {
        super.setInput(input);
        char[] cline = new CharGrid(this.input).getGridLine(0);
        inData = IntStream.range(0, cline.length).map(i -> (int) cline[i] - 48).toArray();
    }

    public String svar() {
        processera();
        defragmentera();
        return String.valueOf(LongStream.range(0, minne.length)
                .filter(n -> minne[(int) n] > 0)
                .map(n -> n * minne[(int) n]).sum());
    }
}
