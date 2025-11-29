package aoc.misc;

import java.util.function.LongBinaryOperator;

public class EqCheck {
    public interface eqOp {
        LongBinaryOperator getOperator();
    }

    public final long toCheck;
    final long[] varden;
    final eqOp[] ops;

    public EqCheck(long toCheck, long[] varden, eqOp[] ops) {
        this.toCheck = toCheck;
        this.varden = varden;
        this.ops = ops;
    }

    public static eqOp[][] opMap(int len, eqOp[] ops) {
        int olen = (int) Math.pow(ops.length, len);
        eqOp[][] proc = new eqOp[olen][len];

        for (int k = 0; k < olen; ++k) {
            for (int l = 0; l < len; ++l) {
                proc[k][l] = ops[(k / (int) Math.pow(ops.length, l)) % ops.length];
            }
        }
        return proc;
    }

    public long check(eqOp[][][] omap) {
        eqOp[][] opx = omap[varden.length - 1];

        for (eqOp[] o: opx) {
            int oix = 0; long acc = varden[0];
            do {
                acc = o[oix].getOperator().applyAsLong(acc, varden[++oix]);
            } while (oix < o.length);
            if (acc == toCheck) return toCheck;
        }
        return 0;
    }
}
