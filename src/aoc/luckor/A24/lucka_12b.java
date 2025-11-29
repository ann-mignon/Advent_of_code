package aoc.luckor.A24;

import java.util.function.BiPredicate;

public class lucka_12b extends lucka_12 {

    BiPredicate<Character, Integer> diffpos = (c, p) -> cg.getCharAt(p) != c;

    @Override
    protected int addAtIx(int p) {
        int count = 0,
            upos = p - wlen,
            npos = p + wlen,
            hpos = p % wlen == wlen - 1 ? -1 : p + 1,
            vpos = p % wlen == 0 ? -1 : p - 1,
            nvpos = p % wlen == 0 ? -1 : p - wlen - 1,
            nepos = p % wlen == wlen - 1 ? -1 : p - wlen + 1,
            svpos = p % wlen == 0 ? -1 : p + wlen - 1,
            sepos = p % wlen == wlen - 1 ? -1 : p + wlen + 1;

        char c = cg.getCharAt(p);

        if (diffpos.test(c, upos) && diffpos.test(c, vpos))  ++count;
        if (diffpos.test(c, upos) && diffpos.test(c, hpos))  ++count;
        if (diffpos.test(c, npos) && diffpos.test(c, vpos))  ++count;
        if (diffpos.test(c, npos) && diffpos.test(c, hpos))  ++count;
        if (diffpos.test(c, nvpos) && (!diffpos.test(c, upos) && !diffpos.test(c, vpos)))     ++count;
        if (diffpos.test(c, nepos) && (!diffpos.test(c, upos) && !diffpos.test(c, hpos)))     ++count;
        if (diffpos.test(c, svpos) && (!diffpos.test(c, npos) && !diffpos.test(c, vpos)))     ++count;
        if (diffpos.test(c, sepos) && (!diffpos.test(c, npos) && !diffpos.test(c, hpos)))     ++count;

        return Math.min(count, 4);
    }
}
