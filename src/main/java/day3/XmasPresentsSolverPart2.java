package day3;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class XmasPresentsSolverPart2 {
    static class Cell {
        int row;
        int column;

        public Cell(int row, int column) {
            this.row = row;
            this.column = column;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Cell cell = (Cell) o;
            return row == cell.row &&
                    column == cell.column;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, column);
        }
    }
    public static void main(String[] args) throws Exception {
        final File inputFile = new File(Class.forName(Thread.currentThread().getStackTrace()[1].getClassName()).getResource("Input.txt").toURI().getPath());
        RandomAccessFile raf = new RandomAccessFile(inputFile, "r");

        int character = raf.read();
        Set<Cell> housesVisited = new HashSet<>();
        int row = 0;
        int column = 0;

        int roboRow = 0;
        int roboColumn = 0;

        housesVisited.add(new Cell(row, column));
        housesVisited.add(new Cell(roboRow, roboColumn));

        while (character != -1) {
            long number = raf.getFilePointer();

            switch (character) {
                case '<':
                    if (number % 2 == 0) {
                        roboRow--;
                    } else {
                        row--;
                    }

                    break;

                case '>':
                    if (number % 2 ==0) {
                        roboRow++;
                    } else {
                        row++;
                    }
                    break;

                case 'v':
                    if (number % 2 ==0) {
                        roboColumn++;
                    } else {
                        column++;
                    }
                    break;

                case '^':
                    if (number % 2 ==0) {
                        roboColumn--;
                    } else {
                        column--;
                    }

                    break;
            }

            if (number % 2 ==0) {
                housesVisited.add(new Cell(roboRow, roboColumn));
            } else {
                housesVisited.add(new Cell(row, column));
            }

            character = raf.read();
        }

        System.out.println(housesVisited.size());
    }
}