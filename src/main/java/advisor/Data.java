package advisor;

import java.util.HashMap;
import java.util.Map;

public class Data {

    public static Map<String, String> categories = new HashMap<>();

    private static String secretCode = "";
    private static String token = "";

    public static String getSecretCode() {
        return secretCode;
    }

    public static void setSecretCode(String secretCode) {
        Data.secretCode = secretCode;
    }

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        Data.token = token;
    }


}

