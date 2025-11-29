package aoc.luckor.A24;

import java.util.Arrays;

public class lucka_9b extends lucka_9 {

    int gapAt(int mpos) {
        int gap = 0;
        while (mpos < minne.length && minne[mpos++] == -1) gap++;
        return gap;
    }

    protected void defragmentera() {
        int mtptr = filer.length, lpos, dec, mem;

        while (--mtptr > 0) {
            lpos = 0; dec = 0;
            mem = Arrays.stream(inData, 0, mtptr * 2).sum();
            while(++lpos < mem) {
                if (gapAt(lpos) >= filer[mtptr]) {
                    while (dec < filer[mtptr]) {
                        minne[lpos + dec] = mtptr;
                        minne[mem + dec] = -1;
                        ++dec;
                    }
                    break;
                }
            }
        }
    }
}
