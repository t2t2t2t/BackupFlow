package backuplow.fillter;

import java.io.File;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileFilterUtil {
    //через enum сделать разные фильтры

    public static List<File> filterByExtension(File dir, String extension) {
        List<File> result = new ArrayList<File>();
        if (!dir.isDirectory()) {
            return result;
        }

        for (File file : dir.listFiles()) {
            if (file.isFile() && file.exists() && file.getName().endsWith(extension)) {
                result.add(file);
            }
        }
        return result;
    }

    public static Set<String> fileExtraction(String path) {
        if (path == null || path.trim().isEmpty()) {
            System.err.println("Path is null or empty");
            return Collections.emptySet();
        }

        File file = new File(path);

        if (!file.exists() || !file.isDirectory()) {
            return Collections.emptySet();
        }

        Set<String> list = new LinkedHashSet<>();
        Pattern pattern = Pattern.compile("(\\.\\w+)$");
        for (File f : file.listFiles()) {
            Matcher matcher = pattern.matcher(f.getName());
            if (matcher.find()) {
                String ext = matcher.group(1);
                list.add(ext);
            }
        }
        return list;
    }

    public static List<File> fileFilterLastMod(String path, Integer time) {
        if (path == null || path.trim().isEmpty()) {
            return Collections.emptyList();
        }
        File folder = new File(path);
        if (!folder.exists() || !folder.isDirectory()) {
            return Collections.emptyList();
        }
        Long timeRightNow = System.currentTimeMillis();
        Long timeMod = timeRightNow - TimeUnit.MINUTES.toMillis(time);

        List<File> listFiles = new ArrayList<>();
        for (File file : folder.listFiles()) {
            if (file.lastModified() >= timeMod) {
                listFiles.add(file);
            }
        }
        return listFiles;
    }

}
