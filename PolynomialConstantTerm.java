import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PolynomialConstantTerm {

    public static void main(String[] args) {
        String filePath = "input.json"; 

        try {
            String jsonInput = new String(Files.readAllBytes(Paths.get(filePath)));
            JSONObject jsonObject = new JSONObject(jsonInput);
            JSONArray testCases = jsonObject.getJSONArray("testCases");

            for (int caseIndex = 0; caseIndex < testCases.length(); caseIndex++) {
                JSONObject testCase = testCases.getJSONObject(caseIndex);
                JSONObject keys = testCase.getJSONObject("keys");
                int n = keys.getInt("n");
                int k = keys.getInt("k");

                int a_n = 2;  
                int numberOfRoots = k - 1;
                int[] roots = new int[numberOfRoots];

                for (int i = 1; i <= numberOfRoots; i++) {
                    JSONObject rootObj = testCase.getJSONObject(String.valueOf(i));
                    String base = rootObj.getString("base");
                    String value = rootObj.getString("value");
                    roots[i - 1] = Integer.parseInt(value, Integer.parseInt(base));
                }

                int productOfRoots = 1;
                for (int root : roots) {
                    productOfRoots *= root;
                }

                int constantTerm = a_n * (int)Math.pow(-1, k) * productOfRoots;
                System.out.println("The constant term for test case " + (caseIndex + 1) + " is: " + constantTerm);
            }

        } catch (IOException e) {
            System.err.println("Error reading the JSON file: " + e.getMessage());
        }
    }
}
