package org.example.safescamapp.utils;


public enum SettingKey {
    PROFILE_NAME("profileName"),
    SOURCE("source"),
    DESTINATION("destination"),
    FILEFILTEREXT("filefilterext"),
    ISFILEFILTEREXT("isfilefilterext"),
    FILEFILTERLATMOD("filefilterlastmod"),
    ISFILEFILTERLATMOD("isfilefilterlastmod"),
    TIMER("timer");


    private final String key;

    SettingKey(String key) {
        this.key = key;
    }
    public String getKey(String profileName) {
        return profileName+"."+key;
    }
    public String getKey() {
        return key;
    }
}

