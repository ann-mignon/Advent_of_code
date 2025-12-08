package aoc.luckor.A25;

import aoc.Losning;

import java.util.*;

public class lucka_8 extends Losning {

    int plug = 1000;
    int plen;

    int[] boxA, boxB;
    long[] bparD;
    Integer[] bparX;
    jbox[] jboxar;

    List<Set<Integer>> kretsar = new ArrayList<>();

    record jbox(long x, long y, long z) {
        long rtvdist(jbox vb) {
            long _x = vb.x - x, _y = vb.y - y, _z = vb.z - z;
            return (_x * _x) + ( _y * _y) + ( _z * _z );      // kvadratrot onödigt
        }

        static jbox make(String str) {
            var koord = Arrays.stream(str.split(",")).mapToInt(Integer::valueOf).toArray();
            return new jbox(koord[0], koord[1], koord[2]);
        }
    }

    Set<Integer> kByJ(int jb) {
        return kretsar.stream()
            .filter(_krets -> _krets.contains(jb))
            .findFirst().orElse(null);
    }

    int plugga(int parix) {
        int jb1 = boxA[parix], jb2 = boxB[parix];

        Set<Integer> krets, kretsA = kByJ(jb1),
                            kretsB = kByJ(jb2);

        if (kretsA != null && kretsB != null && !kretsA.equals(kretsB)) {
            kretsar.remove(kretsB);
            kretsA.addAll(kretsB);
        } else {
            krets = kretsA == null ? kretsB : kretsA;
            if (krets == null) {
                kretsar.add(new HashSet<>(Set.of(jb1, jb2)));
            } else {
                krets.addAll(Set.of(jb1, jb2));
            }
        }
        return 1;
    }

    protected void sortera() {
        int a, b, i;

        plen = (len * (len - 1)) / 2;
        jboxar = new jbox[len];
        boxA = new int[plen]; boxB = new int[plen]; bparD = new long[plen];
        bparX = new Integer[plen];

        a = i = 0;

        while (a < len) {
            b = a + 1;
            jboxar[a] = jbox.make(inputLines[a]);

            while (b < len) {
                boxA[i]  = a;
                boxB[i]  = b;
                bparX[i] = i;
                ++b; ++i;
            }
            ++a;
        }

        for (i = 0; i < plen; ++i) {
            bparD[i] = jboxar[boxA[i]].rtvdist(jboxar[boxB[i]]);
        }

        Arrays.sort(bparX, (c, d) -> Long.compare(bparD[c], bparD[d]));
    }

    public String svar() {
        sortera();
        int i = 0;
        while (plug > 0) plug -= plugga(bparX[i++]);

        kretsar = kretsar.stream().sorted(Comparator.comparingInt(Set::size)).toList().reversed();

        return String.valueOf(kretsar.get(0).size() * kretsar.get(1).size() * kretsar.get(2).size());
    }
}
