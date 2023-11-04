package main.java;

import main.java.Application.WindowApplication;

import java.io.File;
import java.io.InputStream;

public class Main {

    public static String ExeCompatibility;
    public static boolean jarExecution = false;
    private static void loadNatives(){
        String currentDir = Main.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        String nativeDir = new File(currentDir).getParentFile().getParentFile().getParentFile().getParentFile().toString();

        nativeDir = nativeDir + File.separator + "natives" + File.separator + "windows-amd64" + File.separator;

        System.setProperty("java.library.path", nativeDir);
        System.out.println("Path to native .DLL files for JOGl: " + nativeDir);
    }

    public static void main(String[] args){
        loadNatives();
        WindowApplication Application = WindowApplication.getInstance();
    }
}