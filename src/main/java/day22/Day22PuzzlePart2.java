package day22;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class Day22PuzzlePart2 {
    public static void main(String[] args) throws Exception {
        final File inputFile = new File(Class.forName(Thread.currentThread().getStackTrace()[1].getClassName()).getResource("Input.txt").toURI().getPath());
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile)));
        String line;

        String patternMatcher1 = "[a-z]*(\\w)[a-z]\\1[a-z]*";
        String patternMatcher2 = "[a-z]*(\\w\\w)[a-z]*\\1[a-z]*";
        /*String stringToMatch = "ieodomkazucvgmuy";
        boolean matches = Pattern.matches(patternMatcher1, stringToMatch);
        boolean matches2 = Pattern.matches(patternMatcher2, stringToMatch);
        System.out.println(matches);
        System.out.println(matches2);*/
        int niceStrings = 0;

        while ((line = reader.readLine()) != null) {
            if (!Pattern.matches(patternMatcher1, line) || !Pattern.matches(patternMatcher2, line)) {
                continue;
            }

            niceStrings++;
        }

        System.out.println(niceStrings);
    }
}
