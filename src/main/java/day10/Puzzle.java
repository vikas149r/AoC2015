package day10;

public class Puzzle {
    public static void main(String[] args) throws Exception {
        final String firstInput = "1113122113";
        String input = firstInput;

        for (int i = 0; i < 40; i++) {
            String output = readInput(input);
//            System.out.println(output);
            input = output;
        }

        System.out.println(input.length());
    }

    static String readInput(String input) {
        StringBuilder output = new StringBuilder();

        for (int i = 0, j = i; i < input.length(); i = j) {
            char character = input.charAt(i);

            while (true) {
                j++;

                if (j >= input.length()) {
                    break;
                }

                char nextChar = input.charAt(j);

                if (character != nextChar) {
                    break;
                }
            }

            int difference = j - i;

            String count = null;

            switch (difference) {
                case 1:
                    count = "1";
                    break;
                case 2:
                    count = "2";
                    break;
                case 3:
                    count = "3";
                    break;
                case 4:
                    count = "4";
                    break;
                case 5:
                    count = "5";
                    break;
                case 6:
                    count = "6";
                    break;
                case 7:
                    count = "7";
                    break;
                case 8:
                    count = "8";
                    break;
                case 9:
                    count = "9";
                    break;

            }

            output.append(count + character);
        }

        return output.toString();
    }
}
