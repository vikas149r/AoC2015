package day8;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class Day8Puzzle {
    public static void main(String[] args) throws Exception {
        final File inputFile = new File(Class.forName(Thread.currentThread().getStackTrace()[1].getClassName()).getResource("Input.txt").toURI().getPath());

        FileInputStream fis = new FileInputStream(inputFile);
        BufferedReader reader = new BufferedReader(new InputStreamReader(fis, "ISO-8859-1"));

        List<Integer> rafLength = new LinkedList<>();
        List<Integer> bufferedReaderLength = new LinkedList<>();

        /*String test = "sjdivfriyaaqa\\xd2v\"k\"mpcu\"yyu\"en";
        System.out.println(test);*/
        String line;

        while ((line = reader.readLine()) != null) {
            int lineCount = 0;
            char[] characters = line.substring(1, line.length() - 1).toCharArray();

            for (int i = 0; i < characters.length; i++) {
                char character = characters[i];

                if (character == '\\') {
                    i++;
                    character = characters[i];

                    switch (character) {
                        case 'x':
                            i = i + 2;
                            break;
                    }
                }

                lineCount++;
            }

            bufferedReaderLength.add(lineCount);
//            bufferedReaderLength.add(line.length());
        }

        System.out.println(bufferedReaderLength);

        RandomAccessFile raf = new RandomAccessFile(inputFile, "r");

        int count = 0;
        int character = raf.read();

        while (character != -1) {
            if (character == '\r') {
                character = raf.read();
                continue;
            }

            if (character == '\n') {
                rafLength.add(count);
                count = 0;
                character = raf.read();
                continue;
            }

            count++;
            character = raf.read();
        }

        rafLength.add(count);

        System.out.println(rafLength);

        int bufferTotal = 0;
        int rafTotal = 0;

        for (int i = 0; i < rafLength.size(); i++) {
            bufferTotal += bufferedReaderLength.get(i);
            rafTotal += rafLength.get(i);
        }

        System.out.println(rafTotal - bufferTotal);
    }
}
