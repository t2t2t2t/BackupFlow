package org.example.safescamapp;

import org.apache.commons.io.FileUtils;
import org.example.safescamapp.fillter.FileFilterUtil;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Copy {

    public static void copyFolder(String src, String dest) throws IOException {
        copyFolder(src, dest, CopyMode.COPY_CONTENT_ONLY);
    }

    public enum CopyMode {
        COPY_FOLDER_ITSELF,
        COPY_CONTENT_ONLY
    }


    public static void copyFolder(String src, String dest, CopyMode copyMode) throws IOException {

        File sourceDirectory = new File(src);
        File destinationDirectory = new File(dest);
        if (!sourceDirectory.exists() || !sourceDirectory.isDirectory()) {
            System.out.println(sourceDirectory.getAbsolutePath() + " does not exist or is not a directory");
            return;
        }

        if (!destinationDirectory.exists()) {
            destinationDirectory.mkdirs();
            System.out.println(destinationDirectory.getAbsolutePath() + " has been created");
        }

        switch (copyMode) {
            case COPY_FOLDER_ITSELF:
                FileUtils.copyDirectoryToDirectory(sourceDirectory, destinationDirectory);
                break;
            case COPY_CONTENT_ONLY:
                FileUtils.copyDirectory(sourceDirectory, destinationDirectory);
                break;
        }
    }

    public static void copyFile(String src, String dest) {

        File sourceFile = new File(src);

        if (!sourceFile.isFile()) {
            System.out.println("This is not a file");
            return;
        }
        if (!sourceFile.exists()) {
            System.out.println("File does not exist");
            return;
        }

        File destinationDirectory = new File(dest, sourceFile.getName());

        try {
            FileUtils.copyFile(sourceFile, destinationDirectory);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void copyFileLasMod(List<File>nameFilter, String src, String dest) throws IOException {

        File sourceFile = new File(src);

        if (!sourceFile.isDirectory()) {
            System.out.println("This is not a file");
            return;
        }
        if (!sourceFile.exists()) {
            System.out.println("File does not exist");
            return;
        }


        for (File filterFile : nameFilter) {
            File destinationFile = new File(dest, filterFile.getName());
            FileUtils.copyFile(filterFile, destinationFile);
        }
    }


    public static void copyFilesFilterExt(String filter,String src, String dest) throws IOException {
        File sourceFile = new File(src);

        if (!sourceFile.isDirectory()) {
            System.out.println("This is not a file");
            return;
        }
        if (!sourceFile.exists()) {
            System.out.println("File does not exist");
            return;
        }


        List<File> filterFiles = FileFilterUtil.filterByExtension(sourceFile, filter);

        for (File filterFile : filterFiles) {
            File destinationFile = new File(dest, filterFile.getName());
            FileUtils.copyFile(filterFile, destinationFile);
        }

    }
}

