package aoc.misc;

public class Point {
    public int x;
    public int y;


    public boolean equals(Point p) {
        if (p == null) return false;
        return x == p.x && y == p.y;
    }

    public Point() {
        this.x = 0;
        this.y = 0;
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point add (Point p) { // factory
        return new Point(x + p.x, y + p.y);
    }

    public Point dup() {
        return add(Point.ID);
    }
    
    public int manhattan (Point p) {
        return Math.abs(this.x - p.x) + Math.abs(this.y - p.y);
    }

    @Override
    public String toString() {
        return String.format("Point x: %d y: %d", x, y);
    }

    public static final Point VUP = new Point(0, -1);
    public static final Point VDOWN = new Point(0, 1);
    public static final Point VRIGHT = new Point(1, 0);
    public static final Point VLEFT = new Point(-1, 0);
    public static final Point ID = new Point(0, 0);

}
