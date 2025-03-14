package org.max;

import org.junit.jupiter.api.BeforeAll;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AccuweatherAbstractTest {

    protected static Properties prop = new Properties();
    private static String apiKey;
    private static String baseUrl;

    @BeforeAll
    static void initTest() throws IOException {
        try (InputStream configFile = new FileInputStream("src/test/resources/accuweather.properties")) {
            prop.load(configFile);
            apiKey = prop.getProperty("apikey");
            baseUrl = prop.getProperty("base_url");
        }
    }

    public static String getApiKey() {
        return apiKey;
    }

    public static String getBaseUrl() {
        return baseUrl;
    }
}
