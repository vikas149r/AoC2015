package day2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day2Puzzle {
    public static void main(String[] args) throws Exception {
        final File inputFile = new File(Class.forName(Thread.currentThread().getStackTrace()[1].getClassName()).getResource("Input.txt").toURI().getPath());
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile)));
        String line;
        int totalPaperRequired = 0;
        int totalRibbonRequired = 0;

        while ((line = reader.readLine()) != null) {
            String[] dimensions = line.split("x");
            List<Long> dimensionsList = new ArrayList<>();

            long length = Long.parseLong(dimensions[0]);
            long width = Long.parseLong(dimensions[1]);
            long height = Long.parseLong(dimensions[2]);

            dimensionsList.add(length);
            dimensionsList.add(height);
            dimensionsList.add(width);
            Collections.sort(dimensionsList);
            totalPaperRequired += (2 * ((length * width) + (length * height) +
                    (height * width))) + (dimensionsList.get(0) * dimensionsList.get(1));

            totalRibbonRequired += ((2 * (dimensionsList.get(0) + dimensionsList.get(1))) + (length * width * height));
        }

        System.out.println(totalPaperRequired);
        System.out.println(totalRibbonRequired);
    }
}
