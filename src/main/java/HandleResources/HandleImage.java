package main.java.HandleResources;

import java.io.File;
public class HandleImage {
    public static String getPathtoDirectory(String Path){
        String path;

        String currentDir = HandleImage.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        String nativeDir = new File(currentDir).getParentFile().getParentFile().getParentFile().getParentFile().toString();
        path = nativeDir + File.separator + "assets" + File.separator + "images" + File.separator + Path + File.separator;

        System.out.println(path);
        System.out.println(System.getProperty("user.dir"));

        return path;
    }
}
