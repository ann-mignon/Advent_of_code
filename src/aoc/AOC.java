package aoc;

import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static aoc.Util.putln;

public class AOC {
    private final static String aocURL = "https://adventofcode.com/20%d/day/%d/input";

    private final static Set<Integer> aocAR = Set.of(24, 25);

    static String arFmt() {
        return String.join(", ", aocAR.stream()
            .sorted()
            .map(Object::toString)
            .toList())
            .replaceAll(", (\\d+)$", " och $1");
    }

    public static void main(String[] args) {
        if (args.length > 1) {
            try {
                int iAr = Integer.parseInt(args[0]);

                if (!aocAR.contains(iAr)) {
                    throw new RuntimeException(String.format("Fel år: %s tillgängliga", arFmt()));
                }

                Matcher dpmc = Pattern.compile("(\\d{1,2})([a-z]+)?").matcher(args[1]);

                if (dpmc.find()) {
                    Losning losning = losningById(iAr, args[1]);

                    int dag = Integer.parseInt(dpmc.group(1));

                    String input = args.length == 2 ?
                            hamtaInputWeb(iAr, dag) :
                            hamtaInputFil(iAr, dag, args[2]);

                    losning.setInput(input);

                    svar(iAr, dag, args[1], losning.svar(), args.length > 2 ? args[2] : null);

                } else {
                    throw new RuntimeException("Ange lucka som 13 / 13b .. ( mönster: \\d{1,2}[a-z]+ )");
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

    static Losning losningById(int ar, String dagId) throws ReflectiveOperationException {
        Class<? extends Losning> losklass =
                Class.forName("aoc.luckor.A%1$s.lucka_%2$s".formatted(Integer.toString(ar), dagId))
                     .asSubclass(Losning.class);

        return losklass.getConstructor().newInstance();
    }

    static void svar(int ar, int luckdag, String dagStr, String svar, String filnamn) {
        if (filnamn == null) {
            putln("AoC '%s, lucka %s » %s".formatted(Integer.toString(ar), dagStr, svar));
        } else {
            putln("Svar AoC '%1$s, lucka %2$s » %3$s   (med test-input: /res/20%1$s/%4$s/%5$s)"
                    .formatted(ar, dagStr, svar, luckdag, filnamn));
        }
    }

    static String hamtaInputWeb(int ar, int dag) throws IOException, InterruptedException {
        try {
            HttpRequest hr = HttpRequest.newBuilder().uri(URI.create(aocURL.formatted(ar, dag)))
                    .setHeader("Cookie", "session=%s".formatted(getProperty("pepparkaka")))
                    .method("GET", HttpRequest.BodyPublishers.noBody()).build();

            return HttpClient.newHttpClient().send(hr, HttpResponse.BodyHandlers.ofString()).body();
        } catch ( Exception e ) {
            throw new RuntimeException("Det gick inte att hämta input automatiskt från domänen.\n" +
                    "Har du ställt in pepparkaksparametern? ( i /src/aoc24/res/aoc.properties )");
        }
    }

    public static String hamtaInputFil(int ar, int dag, String sv) {
        try (InputStream is = AOC.class.getClassLoader()
                                       .getResourceAsStream("%d/%d/%s".formatted(2000 + ar, dag, sv))) {
            if (is != null) {
                return new String(is.readAllBytes(), StandardCharsets.UTF_8);
            } else {
                throw new Exception();
            }
        } catch (Exception x) {
            throw new RuntimeException(String.format("Infilen saknas (%d/%s) %n", 2000 + ar, sv));
        }
    }

    public static String getProperty(String key) {
        try (InputStream propFile = AOC.class.getClassLoader().getResourceAsStream("aoc.properties")) {
            Properties prop = new Properties();
            prop.load(propFile);
            return prop.getProperty(key);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
