package day9;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Puzzle {
    static Map<String, Map<String, Integer>> townToDistanceMapping = new HashMap<>();

    public static void main(String[] args) throws Exception {
        final File inputFile = new File(Class.forName(Thread.currentThread().getStackTrace()[1].getClassName()).getResource("Input.txt").toURI().getPath());
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile)));

        String line;

        while ((line = reader.readLine()) != null) {
            String[] townsDistances = line.split(" = ");
            String[] towns = townsDistances[0].split(" to ");
            int distance = Integer.parseInt(townsDistances[1]);

            Map<String, Integer> distanceMapping = townToDistanceMapping.get(towns[0]);

            if (distanceMapping == null) {
                distanceMapping = new HashMap<>();
                townToDistanceMapping.put(towns[0], distanceMapping);
            }

            distanceMapping.put(towns[1], distance);

            Map<String, Integer> reverseDistanceMapping = townToDistanceMapping.get(towns[1]);

            if (reverseDistanceMapping == null) {
                reverseDistanceMapping = new HashMap<>();
                townToDistanceMapping.put(towns[1], reverseDistanceMapping);
            }

            reverseDistanceMapping.put(towns[0], distance);
        }

        System.out.println(townToDistanceMapping);

        int minDistance = 0;
        List<String> pathTaken = new LinkedList<>();

        for (String town : townToDistanceMapping.keySet()) {
            List<String> townPath = new LinkedList<>();
            townPath.add(town);
            int distance = getDistance(town, townPath, 0);

            if (minDistance == 0 || distance < minDistance) {
                minDistance = distance;
                pathTaken.clear();
                pathTaken.addAll(townPath);
            }
        }

        System.out.println(minDistance);
        System.out.println(pathTaken);

        int maxDistance = 0;

        for (String town : townToDistanceMapping.keySet()) {
            List<String> townPath = new LinkedList<>();
            townPath.add(town);
            int distance = getMaxDistance(town, townPath, 0);

            if (maxDistance == 0 || distance > maxDistance) {
                maxDistance = distance;
                pathTaken.clear();
                pathTaken.addAll(townPath);
            }
        }

        System.out.println(maxDistance);
        System.out.println(pathTaken);
    }

    static int getDistance(String town, List<String> townNames, int distanceToTown) {
        if (townNames.size() == townToDistanceMapping.keySet().size()) {
            return distanceToTown;
        }

        int minDistance = 0;
        String minDistancePath = null;
        Map<String, Integer> distanceMapping = townToDistanceMapping.get(town);

        for (String townName : distanceMapping.keySet()) {
            if (townNames.contains(townName)) {
                continue;
            }

            List<String> newTownNames = new LinkedList<>(townNames);
            newTownNames.add(townName);
            int distance = getDistance(townName, newTownNames, distanceMapping.get(townName));

            if (minDistance == 0 || distance < minDistance) {
                minDistance = distance;
                minDistancePath = townName;
            }
        }

        townNames.add(minDistancePath);
        return distanceToTown + minDistance;
    }

    static int getMaxDistance(String town, List<String> townNames, int distanceToTown) {
        if (townNames.size() == townToDistanceMapping.keySet().size()) {
            return distanceToTown;
        }

        int maxDistance = 0;
        String maxDistancePath = null;
        Map<String, Integer> distanceMapping = townToDistanceMapping.get(town);

        for (String townName : distanceMapping.keySet()) {
            if (townNames.contains(townName)) {
                continue;
            }

            List<String> newTownNames = new LinkedList<>(townNames);
            newTownNames.add(townName);
            int distance = getMaxDistance(townName, newTownNames, distanceMapping.get(townName));

            if (maxDistance == 0 || distance > maxDistance) {
                maxDistance = distance;
                maxDistancePath = townName;
            }
        }

        townNames.add(maxDistancePath);
        return distanceToTown + maxDistance;
    }
}
