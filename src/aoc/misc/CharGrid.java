package aoc.misc;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static aoc.Util.splitta;

public class CharGrid {
    private final char[][] _grid;

    public final int hojd;
    public final int bredd;
    public final int tot;

    public CharGrid(String txt) {
        _grid = Arrays.stream(splitta(txt, "\n"))
                .map(String::toCharArray).toArray(char[][]::new);

        hojd = _grid.length;
        bredd = _grid[0].length;
        tot = hojd * bredd;
    }

    public Character getCharAt(Point p) {
        if (!inGrid(p)) return null;
        return _grid[p.y][p.x];
    }

    public Character getCharAt(int x, int y) {
        return _grid[y][x];
    }

    public Character getCharAt(int p) {
        return _grid[p/bredd][p % bredd];
    }

    public char[] getGridLine(int i) {
        return _grid[i];
    }

    public int getIx(Point p) {
        return p.y * bredd + p.x;
    }

    public int[] multiIx(char c) {
        return IntStream.range(0, bredd * hojd)
                        .filter(j -> _grid[j / bredd][j % bredd] == c)
                        .toArray();
    }

    public boolean inGrid(Point p) {
        return p.x >= 0 && p.x < bredd && p.y >= 0 && p.y < hojd;
    }

    public void setCharAt(Point p, char c) {
        _grid[p.y][p.x] = c;
    }

    public Point findFirst(char c) {
        int ix = flatStream().mapToObj(ch -> Character.toString((char) ch))
                .collect(Collectors.joining()).indexOf(c);

        if (ix == -1) return null;
        return new Point(ix % bredd, ix / hojd);
    }

    public IntStream flatStream() {
        return Arrays.stream(_grid).flatMapToInt(chars -> IntStream.range(0, chars.length)
                .map(i -> chars[i]));
    }

    public int countChar(char c) {
        return (int) flatStream().filter(ch -> c == ch).count();
    }

    public String toString() {
        return Arrays.stream(_grid).map(ca -> new String(ca) + "\n").collect(Collectors.joining());
    }
}
