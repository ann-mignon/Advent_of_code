package aoc.misc;

public class PointL {
    public long x;
    public long y;

    public boolean equals(PointL p) {
        if (p == null) return false;
        return x == p.x && y == p.y;
    }

    public PointL() {
        this.x = 0;
        this.y = 0;
    }

    public PointL(long x, long y) {
        this.x = x;
        this.y = y;
    }

    public PointL add (PointL p) { // factory
        return new PointL(x + p.x, y + p.y);
    }
}
