package org.example.safescamapp.utils;

import java.io.*;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

public class Setting {
    private static final String RESOURCE_PATH = "config/settings.properties";
    private static final Properties prop = new Properties();


    static {
        createSettingsFileIfNotExists();
        loadExistProperty();
    }

    private static Properties loadExistProperty() {
        try {
            FileInputStream propFile = new FileInputStream(RESOURCE_PATH);
            prop.load(propFile);
            return prop;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void createSettingsFileIfNotExists() {
        if (!new File(RESOURCE_PATH).exists()) {
            File settingsFile = new File(RESOURCE_PATH);
            settingsFile.getParentFile().mkdirs();
            try {
                settingsFile.createNewFile();
            } catch (IOException e) {
                System.out.println("Error creating settings file");
            }
        }
    }

    public static void outputSetting(String profileName, SettingKey key, String value) {
        prop.setProperty(key.getKey(profileName), value);
        saveProperty();
    }

    public static String inputSetting(String profileName, SettingKey key) {

        return prop.getProperty(key.getKey(profileName));
    }

    public static Set <String> getAppProfiles() {
        Set <String> set = new HashSet<>();
        for (Object key : prop.keySet()) {
            if(key.toString().endsWith(".profileName")) {
                set.add(key.toString().split("\\.")[0]);
            }
        }
        return set;
    }

    public static void deleteProfile(String profileName) {
        for (Object key : prop.keySet()) {
            if(key.toString().startsWith(profileName)) {
                prop.remove(key);
            }
        }
        saveProperty();
    }



    public static void saveProperty() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(RESOURCE_PATH);
            prop.store(fileOutputStream, null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}
