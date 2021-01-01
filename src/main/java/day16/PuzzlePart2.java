package day16;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PuzzlePart2 {
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
        Map<String, Integer> cluesMap = Stream.of(cluesArray).collect(Collectors.toMap(
                data -> (String) data.split(": ")[0].trim(), data -> Integer.parseInt(data.split(": ")[1])));

        List<String> possibleSues = new LinkedList<>();

        while ((line = reader.readLine()) != null) {
            String[] sueDetails = line.split(":", 2);

            String sueName = sueDetails[0];
            String details = sueDetails[1];

            String[] sueCluesArray = details.split(",");

            boolean validSue = true;

            for (String clue : sueCluesArray) {
                if (clue.trim().startsWith("cats")) {
                    int numberOfCats = cluesMap.get("cats");

                    if (numberOfCats >= Integer.parseInt(clue.split(": ")[1])) {
                        validSue = false;
                        break;
                    }
                } else if (clue.trim().startsWith("trees")) {
                    int numberOfTrees = cluesMap.get("trees");

                    if (numberOfTrees >= Integer.parseInt(clue.split(": ")[1])) {
                        validSue = false;
                        break;
                    }
                } else if (clue.trim().startsWith("pomeranians")) {
                    int numberOfPomeranians = cluesMap.get("pomeranians");

                    if (numberOfPomeranians <= Integer.parseInt(clue.split(": ")[1])) {
                        validSue = false;
                        break;
                    }
                } else if (clue.trim().startsWith("goldfish")) {
                    int numberOfGoldfish = cluesMap.get("goldfish");

                    if (numberOfGoldfish <= Integer.parseInt(clue.split(": ")[1])) {
                        validSue = false;
                        break;
                    }
                } else if (!cluesList.contains(clue.trim())) {
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
