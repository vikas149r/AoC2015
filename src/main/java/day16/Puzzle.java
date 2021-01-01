package day16;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class Puzzle {
    public static void main(String[] args) throws Exception {
        final File inputFile = new File(Class.forName(Thread.currentThread().getStackTrace()[1].getClassName()).getResource("Input.txt").toURI().getPath());
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile)));

        String line;

        List<String> cluesList = new LinkedList<>();

        String clues = "children: 3, cats: 7, samoyeds: 2, pomeranians: 3, akitas: 0, vizslas: 0," +
                "goldfish: 5, trees: 3, cars: 2, perfumes: 1";

        String[] cluesArray = clues.split(",");

        for (String clue : cluesArray) {
            cluesList.add(clue.trim());
        }

        List<String> possibleSues = new LinkedList<>();

        while ((line = reader.readLine()) != null) {
            String[] sueDetails = line.split(":", 2);

            String sueName = sueDetails[0];
            String details = sueDetails[1];

            String[] sueCluesArray = details.split(",");

            boolean validSue = true;

            for (String clue : sueCluesArray) {
                if (!cluesList.contains(clue.trim())) {
                    validSue = false;
                    break;
                }
            }

            if (validSue) {
                possibleSues.add(sueName);
            }
        }

        System.out.println(possibleSues);
    }
}
