package com.amazon.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    // 1. Create Properties object
    private static Properties p = new Properties();

    static {
        try {
            // 2. Load a file into a FileInputStream
            FileInputStream file = new FileInputStream("config.properties");

            // 3. Load properties object with a file
            p.load(file);
            // 4. Close a file
            file.close();
        } catch (IOException e) {
            System.out.println("File not found in Configuration properties");
        }
    }

    public static String getProperty(String property) {
        return p.getProperty(property);
    }
}
