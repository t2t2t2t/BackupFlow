package org.example.safescamapp;


import org.example.safescamapp.utils.Setting;
import org.example.safescamapp.utils.SettingKey;

import java.io.IOException;

public class exampleCopy{
    public static void main(String[] args) throws IOException {
       // testFile();
        //testFolder();
//        testFileFilter();
        testProp();

    }

    public static void testFile() throws IOException  {
//        String source = Setting.inputSetting(SettingKey.SOURCE.getKey());
//        String destination = Setting.inputSetting(SettingKey.DESTINATION.getKey());
//
//        Copy.copyFolder(source, destination);
    }

    public static void testFolder() throws IOException {
        String source = "C:\\Users\\kiree\\Desktop\\out";
        String destination = "C:\\Users\\kiree\\Desktop\\in";


        Copy.copyFolder(source, destination);
    }
    public static void testFileFilter() throws IOException {
        String source = "C:\\Users\\kiree\\Desktop\\out";
        String destination = "C:\\Users\\kiree\\Desktop\\in";


    }
    public static void testProp() throws IOException {

        //System.out.println(Setting.inputSetting(SettingKey.DESTINATION));
        //System.out.println(Setting.inputSetting(SettingKey.SOURCE));

 /*       String source = "C:\\Users\\kiree\\Desktop\\out";
        String destination = "C:\\Users\\kiree\\Desktop\\in";

        Copy.copyFilesFilter(source, destination,".txt");*/
    }

}
