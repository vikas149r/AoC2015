package day7;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class Day7Puzzle {
    static Map<String, String> wireToInstruction = new HashMap<>();
    static Map<String, String> outputInstructions = new HashMap<>();

    public static void main(String[] args) throws Exception {
        final File inputFile = new File(Class.forName(Thread.currentThread().getStackTrace()[1].getClassName()).getResource("Input.txt").toURI().getPath());
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile)));

        String line;
        int count = 0;

        while ((line = reader.readLine()) != null) {
            String[] instructions = line.split(" -> ");
            wireToInstruction.put(instructions[1], instructions[0]);
            count++;
        }

        System.out.println(wireToInstruction.values().size());
        System.out.println(count);
        System.out.println(parseInstructions("a"));
        /*String number = "53";
        String binaryString = Integer.toBinaryString(Integer.parseInt(number));

        while (binaryString.length() < 16) {
            binaryString = "0" + binaryString;
        }

        System.out.println(binaryString);
        String returnString = "";

        for (char binChar : binaryString.toCharArray()) {
            returnString = returnString + ((binChar == '0') ? '1' : '0');
        }

        System.out.println(returnString);

        int shiftQty = Integer.parseInt("4");

        String newBinary = binaryString.substring(shiftQty, binaryString.length());

        while (newBinary.length() < 16) {
            newBinary = newBinary + "0";
        }

        System.out.println(newBinary);

        String binaryWire2 = Integer.toBinaryString(Integer.parseInt("56"));

        while (binaryWire2.length() < 16) {
            binaryWire2 = "0" + binaryWire2;
        }

        System.out.println(binaryWire2);
        returnString = "";
        for (int i = 0; i < binaryString.length(); i++) {
            char wire1Char = binaryString.charAt(i);
            char wire2Char = binaryWire2.charAt(i);

            returnString = returnString + ((byte)wire1Char | (byte)wire2Char);
        }

        System.out.println(returnString);

        System.out.println(Integer.toBinaryString(53 | 56));*/
    }

    public static String parseInstructions(String wire) {
        if (Pattern.matches("[\\d]*", wire)) {
            return wire;
        }

        if (outputInstructions.containsKey(wire)) {
            return outputInstructions.get(wire);
        }

        String instruction = wireToInstruction.get(wire);

        if (Pattern.matches("[\\d]*", instruction)) {
            outputInstructions.put(wire, instruction);
            return instruction;
        }

        if (instruction.contains("AND")) {
            String[] wires = instruction.split(" AND ");
            String wire1 = parseInstructions(wires[0]);
            String wire2 = parseInstructions(wires[1]);

            int wire1Num = (Integer.parseInt(wire1));
            int wire2Num = (Integer.parseInt(wire2));

            int returnWire = wire1Num & wire2Num;

            String returnString = Integer.toBinaryString(returnWire);

            while (returnString.length() < 16) {
                returnString = "0" + returnString;
            }

            String output = Integer.parseInt(returnString, 2) + "";
            outputInstructions.put(wire, output);
            return output;
        } else if (instruction.contains("OR")) {
            String[] wires = instruction.split(" OR ");
            String wire1 = parseInstructions(wires[0]);
            String wire2 = parseInstructions(wires[1]);

            int wire1Num = (Integer.parseInt(wire1));
            int wire2Num = (Integer.parseInt(wire2));

            int returnWire = wire1Num | wire2Num;

            String returnString = Integer.toBinaryString(returnWire);

            while (returnString.length() < 16) {
                returnString = "0" + returnString;
            }

            String output = Integer.parseInt(returnString, 2) + "";
            outputInstructions.put(wire, output);
            return output;
        } else if (instruction.contains("LSHIFT")) {
            String[] wires = instruction.split(" LSHIFT ");
            String wire1 = parseInstructions(wires[0]);
            String wire2 = wires[1];

            String binaryWire1 = Integer.toBinaryString(Integer.parseInt(wire1));

            while (binaryWire1.length() < 16) {
                binaryWire1 = "0" + binaryWire1;
            }

            int shiftQty = Integer.parseInt(wire2);

            String newBinary = binaryWire1.substring(shiftQty, binaryWire1.length());

            while (newBinary.length() < 16) {
                newBinary = newBinary + "0";
            }

            String output = Integer.parseInt(newBinary, 2) + "";
            outputInstructions.put(wire, output);
            return output;
        } else if (instruction.contains("RSHIFT")) {
            String[] wires = instruction.split(" RSHIFT ");
            String wire1 = parseInstructions(wires[0]);
            String wire2 = wires[1];

            String binaryWire1 = Integer.toBinaryString(Integer.parseInt(wire1));

            while (binaryWire1.length() < 16) {
                binaryWire1 = "0" + binaryWire1;
            }

            int shiftQty = Integer.parseInt(wire2);

            String newBinary = binaryWire1.substring(0, binaryWire1.length() - shiftQty);

            while (newBinary.length() < 16) {
                newBinary = "0" + newBinary;
            }

            String output = Integer.parseInt(newBinary, 2) + "";
            outputInstructions.put(wire, output);
            return output;
        } else if (instruction.contains("NOT")) {
            String notWire = instruction.substring("NOT ".length());
            String wire1 = parseInstructions(notWire);
            String binaryString = Integer.toBinaryString(Integer.parseInt(wire1));

            while (binaryString.length() < 16) {
                binaryString = "0" + binaryString;
            }

            String returnString = "";

            for (char binChar : binaryString.toCharArray()) {
                returnString = returnString + ((binChar == '0') ? '1' : '0');
            }

            String output = Integer.parseInt(returnString, 2) + "";
            outputInstructions.put(wire, output);
            return output;
        } else {
            return parseInstructions(instruction);
        }
    }
}
