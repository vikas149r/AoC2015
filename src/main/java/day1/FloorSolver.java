package day1;

import java.io.File;
import java.io.RandomAccessFile;

public class FloorSolver {
    public static void main(String[] args) throws Exception {
        final File inputFile = new File(Class.forName(Thread.currentThread().getStackTrace()[1].getClassName()).getResource("Input.txt").toURI().getPath());
        RandomAccessFile raf = new RandomAccessFile(inputFile, "r");

        char openBracket = '(';
        char closeBracket = ')';
        final int openBracketChar = openBracket;
        final int closeBracketChar = closeBracket;
        int character = raf.read();
        int floorNumber = 0;

        while (character != -1) {
            switch (character) {
                case '(':
                    floorNumber++;
                    break;

                case ')':
                    floorNumber--;
                    break;
            }

            if (floorNumber < 0) {
                System.out.println(raf.getFilePointer());
            }

            character = raf.read();
        }

        System.out.println(floorNumber);
    }
}
