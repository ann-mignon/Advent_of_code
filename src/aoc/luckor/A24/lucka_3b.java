package aoc.luckor.A24;

import java.util.regex.Matcher;

public class lucka_3b extends lucka_3 {
    final String dorx = "do\\(\\)";
    final String norx = "don't\\(\\)";

    public String svar() {
        int acc = 0;
        String[] doRun = input.split(dorx);

        for (String dr : doRun) {
            Matcher m = mulrx.matcher(dr.split(norx)[0]);
            acc += m.results().mapToInt(r ->
                Integer.parseInt(r.group(1)) *
                Integer.parseInt(r.group(2))).sum();
        }

        return String.valueOf(acc);
    }
}
