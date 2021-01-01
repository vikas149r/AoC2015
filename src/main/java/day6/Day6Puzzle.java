package day6;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Day6Puzzle {
    public static void main(String[] args) throws Exception {
        final File inputFile = new File(Class.forName(Thread.currentThread().getStackTrace()[1].getClassName()).getResource("Input.txt").toURI().getPath());
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile)));

        final String toggle = "toggle ";
        final String off = "turn off ";
        final String on = "turn on ";
        String line;

        int[][] lights = new int[1000][1000];

        for (int i = 0; i < lights.length; i++) {
            for (int j = 0; j < lights[i].length; j++) {
                lights[i][j] = 0;
            }
        }

        while ((line = reader.readLine()) != null) {
            String action = null;

            if (line.startsWith(toggle)) {
                action = toggle;
            } else if (line.startsWith(off)) {
                action = off;
            } else if (line.startsWith(on)) {
                action = on;
            }

            String coordinatePair1 = line.substring(action.length(), line.indexOf(" through "));
            String coordinatePair2 = line.substring(line.indexOf(" through ") + " through ".length());

            String coordinate1[] = coordinatePair1.split(",");
            String coordinate2[] = coordinatePair2.split(",");

            int x1 = Integer.parseInt(coordinate1[0]);
            int y1 = Integer.parseInt(coordinate1[1]);
            int x2 = Integer.parseInt(coordinate2[0]);
            int y2 = Integer.parseInt(coordinate2[1]);

            for (int i = x1; i <= x2; i++) {
                for (int j = y1; j <= y2; j++) {
                    switch (action) {
                        case toggle:
                            int value = lights[i][j];
                            lights[i][j] = (value + 2);
                            break;
                        case on:
                            lights[i][j]++;
                            break;
                        case off:
                            if (lights[i][j] > 0) {
                                lights[i][j]--;
                            }

                            break;

                    }
                }
            }
        }

        int lightsBrightness = 0;

        for (int i = 0; i < lights.length; i++) {
            for (int j = 0; j < lights[i].length; j++) {
                lightsBrightness += lights[i][j];
            }
        }

        System.out.println(lightsBrightness);
    }
}
