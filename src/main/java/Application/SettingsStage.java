package main.java.Application;

import javax.swing.*;

public class SettingsStage {
    private static SettingsStage Instance = null;

    public static void generateSettingsMenu(JFrame Window){

    }
    public static synchronized SettingsStage getInstance(){
        if(Instance == null){
            Instance = new SettingsStage();

            generateSettingsMenu(WindowApplication.getInstance().getFrame());
        }
        return Instance;
    }
}
