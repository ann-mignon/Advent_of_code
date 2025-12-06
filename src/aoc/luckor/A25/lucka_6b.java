package aoc.luckor.A25;

import aoc.misc.CharGrid;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static aoc.Util.splitta_notrim;
import static java.lang.Long.parseLong;
import static java.util.regex.Pattern.compile;

public class lucka_6b extends lucka_6 {

    CharGrid cg;
    Pattern padRx = compile("[+|*]( *)");
    int[] radtjonk;
    ArrayList<Long>[] boperander;

    public int ckOff (int kolno) {
        int i = 0, ret = 0;
        while (i < kolno) {
            ret += radtjonk[i++] + 1;
        }
        return ret;
    }
    
    public String svar() {
        bered();

        this.inputLines = splitta_notrim(this.input, "\n");
        this.len = inputLines.length;
        this.wlen = inputLines.length > 0 ? inputLines[0].length() : 0;
        
        radtjonk = new int[kolumner];
        boperander = new ArrayList[kolumner];

        cg = new CharGrid(input.replaceAll(" ",  ".").substring(0, (len - 1) * wlen + (len - 1)));
        cg.rot90();
        
        int r, i = 0;
        String opRaw = inputLines[inputLines.length - 1], bopStr;
        Matcher opM = padRx.matcher(opRaw);
        
        while(opM.find()) {
            radtjonk[i++] = opM.group(1).length();   
        }
        
        ++radtjonk[kolumner - 1];
        
        r = 0;
        
        while (r < kolumner) {
            i = 0;
            boperander[r] = new ArrayList<>();
            while(i < radtjonk[r]) {
                bopStr = new String(cg.getGridLine(ckOff(r) + i)).replaceAll("\\.", "");
                if (bopStr.length() > 0) {
                    boperander[r].add(parseLong(bopStr));
                }
                ++i;
            }
            ++r;
        }

        i = 0;
        while (i < kolumner) {
            resKol[i] = boperander[i].stream().mapToLong(Long::longValue)
                                     .reduce(idmap.get(operationer[i]), operationer[i].getOperator());
            ++i;
        }
        
        return String.valueOf(Arrays.stream(resKol).sum());
    }
}
