package day14;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Puzzle {
    static class Deer {
        int speed;
        int flyTime;
        int restTime;

        public Deer(int speed, int flyTime, int restTime) {
            this.speed = speed;
            this.flyTime = flyTime;
            this.restTime = restTime;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Deer deer = (Deer) o;
            return speed == deer.speed &&
                    flyTime == deer.flyTime &&
                    restTime == deer.restTime;
        }

        @Override
        public int hashCode() {
            return Objects.hash(speed, flyTime, restTime);
        }

        @Override
        public String toString() {
            return "Deer{" +
                    "speed=" + speed +
                    ", flyTime=" + flyTime +
                    ", restTime=" + restTime +
                    '}';
        }
    }

    static Map<String, Deer> deerMap = new LinkedHashMap<>();

    public static void main(String[] args) throws Exception {
        final File inputFile = new File(Class.forName(Thread.currentThread().getStackTrace()[1].getClassName()).getResource("Input.txt").toURI().getPath());
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile)));

        int totalSeconds = 2503;
        AtomicInteger maxDistance = new AtomicInteger();
        Map<String, Integer> leadMap = new LinkedHashMap<>();

        reader.lines().forEach(line -> {
            String[] words = line.split(" ");
            String deer = words[0];
            int speed = Integer.parseInt(words[3]);
            int flyTime = Integer.parseInt(words[6]);
            int restTime = Integer.parseInt(words[13]);
            deerMap.put(deer, new Deer(speed, flyTime, restTime));

            int numberOfTimesFlown = totalSeconds / (flyTime + restTime);
            int remainder = totalSeconds % (flyTime + restTime);
            int distanceTravelled = numberOfTimesFlown * speed * flyTime + (((remainder > flyTime) ? flyTime : remainder) * speed);

            if (maxDistance.get() < distanceTravelled) {
                maxDistance.set(distanceTravelled);
            }
        });

        System.out.println(maxDistance);
        int[][] deerToSecondsArray = new int[deerMap.size()][totalSeconds];

        int deerIndex = 0;
        for (String deerName : deerMap.keySet()) {
            int currentDistance = 0;
            Deer deer = deerMap.get(deerName);

            for (int i = 0; i < totalSeconds; i++) {
                if (i % (deer.flyTime + deer.restTime) < deer.flyTime) {
                    currentDistance = currentDistance + deer.speed;
                    deerToSecondsArray[deerIndex][i] = currentDistance;
                } else {
                    deerToSecondsArray[deerIndex][i] = currentDistance;
                }
            }

            deerIndex++;
        }

        List<String> deerList = new LinkedList<>(deerMap.keySet());

        for (int j = 1; j < totalSeconds; j++) {
            int higherDistance = 0;

            for (int i = 0; i < deerToSecondsArray.length; i++) {
                int distance = deerToSecondsArray[i][j];

                if (higherDistance == 0 || higherDistance <= distance) {
                    higherDistance = distance;
                }
            }

            for (int i = 0; i < deerToSecondsArray.length; i++) {
                String deer = deerList.get(i);

                if (deerToSecondsArray[i][j] == higherDistance) {
                    leadMap.compute(deer, (k, v) -> v == null ? 1 : ++v);
                }
            }
        }

        System.out.println(leadMap);
    }
}
