package day12;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Puzzle {
    public static void main(String[] args) throws Exception {
        final File inputFile = new File(Class.forName(Thread.currentThread().getStackTrace()[1].getClassName()).getResource("Input.json").toURI().getPath());

        final JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(new InputStreamReader(new FileInputStream(inputFile)));

        long sum = parseJson(jsonObject, false);

        System.out.println(sum);
    }

    static long parseJson(JSONObject jsonObject, boolean ignore) {
        long sum = 0;

        boolean toIgnore = ignore;

        for (Object key : jsonObject.keySet()) {
            if (key instanceof String) {
                toIgnore = toIgnore || ((String)key).equalsIgnoreCase("red");
            }

            Object obj = jsonObject.get(key);

            if (obj instanceof JSONArray) {
                JSONArray array = (JSONArray) obj;
                sum += parseJSONArray(array, toIgnore);
            } else if (obj instanceof JSONObject) {
                JSONObject newObject = (JSONObject) obj;
                sum += parseJson(newObject, toIgnore);
            } else if (obj instanceof Long) {
                sum += ((Long) obj).intValue();
            } else if (obj instanceof String) {
                toIgnore = toIgnore || ((String)obj).equalsIgnoreCase("red");
            }
        }

        return toIgnore ? 0 : sum;
    }

    static long parseJSONArray(JSONArray jsonArray, boolean ignore) {
        long sum = 0;

        if (ignore) {
            return 0;
        }

        for (int i = 0; i < jsonArray.size(); i++) {
            Object obj = jsonArray.get(i);

            if (obj instanceof JSONArray) {
                JSONArray array = (JSONArray) obj;
                sum += parseJSONArray(array, ignore);
            } else if (obj instanceof JSONObject) {
                JSONObject newObject = (JSONObject) obj;
                sum += parseJson(newObject, ignore);
            } else if (obj instanceof Long) {
                sum += ((Long) obj).intValue();
            }
        }

        return sum;
    }
}
