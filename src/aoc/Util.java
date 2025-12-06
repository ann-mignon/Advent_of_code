package aoc;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import static java.lang.Math.multiplyExact;

public class Util {
    public static void putln(Object o) {
        System.out.println(o);
    }

    public static String[] splitta(String str, String delim) {
        List<String> ut = Arrays.stream(str.split(delim)).map(String::trim).toList();
        return ut.toArray(new String[0]);
    }

    public static String[] splitta_notrim(String str, String delim) {
        List<String> ut = Arrays.stream(str.split(delim)).toList();
        return ut.toArray(new String[0]);
    }

    public static int[] toIntArray(String[] strings) {
        return Arrays.stream(strings).mapToInt(Integer::valueOf).toArray();
    }
    
    public static String strFromCodeStream(IntStream charsI) {
        return new String(charsI.mapToObj(Character::toString)
                .collect(Collectors.joining())
                .toCharArray());
    }

    public static String strFromCodes(int[] chars) {
        return new String(toCharArray(chars));
    }

    public static char[] toCharArray(int[] charsI) {
        return strFromCodeStream(IntStream.of(charsI)).toCharArray();
    }

    public static char[] toCharArray(Character[] charBox) {
        return toCharArray(Arrays.stream(charBox).mapToInt(c -> c.charValue()).toArray());
    }

    public static int[] reverseIntArray(int[] array) {
        int[] rev = new int[array.length];
        int j = array.length;
        while (j-- > 0) {
            rev[j] = array[array.length - j - 1];
        }
        return rev;
    }

    public static int indexOfIntArray(int[] array, int value) {
        int i = 0;
        for (int v : array) {
            if(v == value) return i;
            ++i;
        }
        return -1;
    }

    public static IntStream toIntStream(String[] strings) {
        return Arrays.stream(strings).mapToInt(Integer::valueOf);
    }

    public static long[] toLongArray(String[] strings) {
        return toLongStream(strings).toArray();
    }

    public static LongStream toLongStream(String[] strings) {
        return Arrays.stream(strings).mapToLong(Long::valueOf);
    }

    public static List<Integer> toIntegerList(String[] strings) {
        return Arrays.stream(strings).map(Integer::parseInt).collect(Collectors.toList());
    }

    public static final char[] alphanum = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"
                                          .toCharArray();

    static final long[] _pow10 = {
      1, 10, 100, 1000, 10000, 100000, 1000000,
      10000000, 100000000, 1000000000, 10000000000L,
      100000000000L,  1000000000000L,  10000000000000L,
      100000000000000L, 1000000000000000L, 10000000000000000L,
      100000000000000000L, 1000000000000000000L };

    public static long pow10(int n) { return _pow10[n]; }

    public static int bino(int t, int n) throws ArithmeticException {
        long bino = 1;
        int i = 1;
        n = Math.min(n, t - n);

        while (i <= n) {
            bino = multiplyExact(bino, t + 1 - i) / i++;
        }

        return (int) bino;
    }

    /**
     * Determine number of decimals of a positive int64
     * <p>
     * Adapted from an earlier version of the simple and useful
     * <code>Long.stringSize</code> method found in OpenJDK 8 source.
     * For anyone curious about this, an evolved version of it is found
     * in the OpenJDK codebase, link below.
     * @see <a href="https://github.com/openjdk/jdk/blob/master/src/java.base/share/classes/jdk/internal/util/DecimalDigits.java#L125">DecimalDigits.java @ Github</a>
     * </p>
     * @param n long value (only positive integers)
     * @return int decimals
     *
     */
    public static int decimals(long n) {
        long t = 10; int i = 0;
        while (++i < 19) {
            if (t > n) return i;
            t *= 10;
        }
        return i;
    }
}
