package aoc.misc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static aoc.Util.splitta_notrim;

public class CharGrid {
    private char[][] _grid;
    public static char NO_CHAR = '©';

    public int hojd;
    public int bredd;
    public int tot;

    private void recalc() {
        hojd = _grid.length;
        bredd = _grid[0].length;
        tot = hojd * bredd;
    }
    
    public CharGrid(String txt) {
        _grid = Arrays.stream(splitta_notrim(txt, "\n"))
                .map(String::toCharArray).toArray(char[][]::new);

        recalc();
    }
    
    public CharGrid(char[][] grid) {
        _grid = grid;
        recalc();
    }

    public Character getCharAt(Point p) {
        if (!inGrid(p)) return null;
        return _grid[p.y][p.x];
    }

    public Character getCharAt(int x, int y) {
        return _grid[y][x];
    }

    public Character getCharAt(int p) {
        try {
            return _grid[p / bredd][p % bredd];
        } catch (ArrayIndexOutOfBoundsException x) {
            return NO_CHAR;
        }
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
        return new Point(ix % bredd, ix / bredd);
    }
    
    public Point[] findAll(char c) {
        List<Point> all = new ArrayList<>();
        
        for (int i = 0; i < hojd; ++i) {
            for (int j = 0; j < bredd; ++j) {
                if(_grid[i][j] == c) {
                    all.add(new Point(j, i));
                }
            }
        }
        
        return all.toArray(Point[]::new);
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
    
    public void rot90() {
        int nw = this.hojd, nh = this.bredd;
        
        char[][] roterad = new char[nh][nw];
                
        for (int y = 0; y < nh; ++y) {
            for (int x = 0; x < nw; ++x) {
                roterad[y][x] = _grid[x][y];
            }
        }
        
        this._grid = roterad;
        recalc();
    }
}
