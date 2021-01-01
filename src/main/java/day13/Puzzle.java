package day13;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Puzzle {
    static Map<String, Map<String, Integer>> happinessPointsMap = new HashMap<>();

    public static void main(String[] args) throws Exception {
        final File inputFile = new File(Class.forName(Thread.currentThread().getStackTrace()[1].getClassName()).getResource("Input.txt").toURI().getPath());
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile)));

        String line;
        while ((line = reader.readLine()) != null) {
            String[] words = line.split(" ");

            String p1 = words[0];
            String p2 = words[words.length - 1].replace('.', ' ').trim();
            boolean add = (words[2].equalsIgnoreCase("gain")) ? true : false;
            int qty = (Integer.parseInt(words[3])) * (add ? +1 : -1);

            Map<String, Integer> happinessChangeMap = happinessPointsMap.get(p1);

            if (happinessChangeMap == null) {
                happinessChangeMap = new HashMap<>();
                happinessPointsMap.put(p1, happinessChangeMap);
            }

            Integer oneWayHappiness = happinessChangeMap.get(p2);
            happinessChangeMap.put(p2, oneWayHappiness != null ? oneWayHappiness.intValue() + qty : qty);

            Map<String, Integer> reverseHappinessMap = happinessPointsMap.get(p2);

            if (reverseHappinessMap == null) {
                reverseHappinessMap = new HashMap<>();
                happinessPointsMap.put(p2, reverseHappinessMap);
            }

            oneWayHappiness = reverseHappinessMap.get(p1);
            reverseHappinessMap.put(p1, oneWayHappiness != null ? oneWayHappiness.intValue() + qty : qty);
        }

        String key = happinessPointsMap.keySet().iterator().next();
        List<String> persons = new LinkedList<>();
        persons.add(key);
        int happiness = getHappinessUnits(key, persons);
        System.out.println(happiness);
    }

    static int getHappinessUnits(String person, List<String> personStream) {
        if (personStream.size() == happinessPointsMap.keySet().size()) {
            return happinessPointsMap.get(person).get(personStream.get(0));
        }

        int maxHappiness = 0;

        Map<String, Integer> happinessChangeMap = happinessPointsMap.get(person);

        for (String p1 : happinessPointsMap.keySet()) {
            if (personStream.contains(p1)) {
                continue;
            }

            List<String> newPersonStream = new LinkedList<>(personStream);
            newPersonStream.add(p1);
            int happiness = happinessChangeMap.get(p1) + getHappinessUnits(p1, newPersonStream);

            if (maxHappiness == 0 || happiness > maxHappiness) {
                maxHappiness = happiness;
            }
        }

        return maxHappiness;
    }
}
