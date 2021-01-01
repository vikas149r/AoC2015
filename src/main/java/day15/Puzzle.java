package day15;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Puzzle {
    static class Ingredient {
        String name;
        int capacity;
        int durability;
        int flavor;
        int texture;
        int calories;

        public Ingredient(String name, int capacity, int durability, int flavor, int texture, int calories) {
            this.name = name;
            this.capacity = capacity;
            this.durability = durability;
            this.flavor = flavor;
            this.texture = texture;
            this.calories = calories;
        }

        @Override
        public String toString() {
            return "Ingredient{" +
                    "name='" + name + '\'' +
                    ", capacity=" + capacity +
                    ", durability=" + durability +
                    ", flavor=" + flavor +
                    ", texture=" + texture +
                    ", calories=" + calories +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Ingredient that = (Ingredient) o;
            return capacity == that.capacity &&
                    durability == that.durability &&
                    flavor == that.flavor &&
                    texture == that.texture &&
                    calories == that.calories &&
                    name.equals(that.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, capacity, durability, flavor, texture, calories);
        }

    }
    public static void main(String[] args) throws Exception {
        final File inputFile = new File(Class.forName(Thread.currentThread().getStackTrace()[1].getClassName()).getResource("Input.txt").toURI().getPath());
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile)));

        List<Ingredient> ingredients = new LinkedList<>();
        String line;

        while ((line = reader.readLine()) != null) {
            String[] ingredientDetails = line.split(":");
            String ingredientName = ingredientDetails[0];

            String[] details = ingredientDetails[1].split(",");

            String[] capacityDetails = details[0].trim().split(" ");
            int capacity = Integer.parseInt(capacityDetails[1]);
            String[] durabilityDetails = details[1].trim().split(" ");
            int durability = Integer.parseInt(durabilityDetails[1]);
            String[] flavorDetails = details[2].trim().split(" ");
            int flavor = Integer.parseInt(flavorDetails[1]);
            String[] textureDetails = details[3].trim().split(" ");
            int texture = Integer.parseInt(textureDetails[1]);
            String[] caloriesDetails = details[4].trim().split(" ");
            int calories = Integer.parseInt(caloriesDetails[1]);

            Ingredient ingredient = new Ingredient(ingredientName, capacity, durability, flavor, texture, calories);
            ingredients.add(ingredient);
        }

        int max = 0;
        int bestA = 0, bestB = 0, bestC = 0, bestD = 0;

        for (int a = 0; a < 100; a++) {
            for (int b = 0; b < 100 - a; b++) {
                for (int c = 0; c < 100 - a - b; c++) {
                    int d = 100 - a - b - c;

                    Ingredient ingredient1 = ingredients.get(0);
                    Ingredient ingredient2 = ingredients.get(1);
                    Ingredient ingredient3 = ingredients.get(2);
                    Ingredient ingredient4 = ingredients.get(3);

                    int totalCapacity = a * ingredient1.capacity + b * ingredient2.capacity + c * ingredient3.capacity + d * ingredient4.capacity;
                    int totalDurability = a * ingredient1.durability + b * ingredient2.durability + c * ingredient3.durability + d * ingredient4.durability;
                    int totalFlavor = a * ingredient1.flavor + b * ingredient2.flavor + c * ingredient3.flavor + d * ingredient4.flavor;
                    int totalTexture = a * ingredient1.texture + b * ingredient2.texture + c * ingredient3.texture + d * ingredient4.texture;

                    if (totalCapacity < 0 || totalDurability < 0 || totalFlavor < 0 || totalTexture < 0) {
                        continue;
                    }

                    int totalCalories = a * ingredient1.calories + b * ingredient2.calories + c * ingredient3.calories + d * ingredient4.calories;

                    if (totalCalories != 500) {
                        continue;
                    }

                    int total = totalCapacity * totalDurability * totalFlavor * totalTexture;

                    if (max == 0 || max < total) {
                        max = total;
                        bestA = a;
                        bestB = b;
                        bestC = c;
                        bestD = d;
                    }
                }
            }
        }

        System.out.println("Max : " + max + " Details : " + bestA + ", " + bestB + ", " + bestC + ", " + bestD);
    }
}
