package day22;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class Day22Puzzle {
    public static void main(String[] args) throws Exception {
        final File inputFile = new File(Class.forName(Thread.currentThread().getStackTrace()[1].getClassName()).getResource("Input.txt").toURI().getPath());
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile)));
        String line;

        String patternMatcher1 = "[a-z]*(ab|cd|pq|xy)[a-z]*";
        String patternMatcher2 = "[a-z]*(aa|bb|cc|dd|ee|ff|gg|hh|ii|jj|kk|ll|mm|nn|oo|pp|qq|rr|ss|tt|uu|vv|ww|xx|yy|zz)+[a-z]*";
        String patternMatcher3 = "[[a-z-[aeiou]]*[aeiou]]{3,}";
        String patternMatcher4 = "[a-z-[aeiou]]*[aeiou][a-z-[aeiou]]*[aeiou][a-z-[aeiou]]*[aeiou][a-z-[aeiou]]*";
        /*boolean matches = Pattern.matches(patternMatcher3, "jchzaelrnmimnmhp");
        boolean matches2 = Pattern.matches(patternMatcher4, "jchzaelrnmimnmhp");
        System.out.println(matches);
        System.out.println(matches2);*/
        int niceStrings = 0;

        while ((line = reader.readLine()) != null) {
            if (Pattern.matches(patternMatcher1, line) || !Pattern.matches(patternMatcher2, line) || !Pattern.matches(patternMatcher4, line)) {
                continue;
            }

            niceStrings++;
        }

        System.out.println(niceStrings);
    }
}
