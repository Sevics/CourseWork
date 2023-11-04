package main.java.Application;

import main.java.Graphic.ApplicationGraphicCanvas;

public class AppRunStages{
    protected static int Stage;

    public enum Stages{
        MENU(0),
        SETTINGS(1),
        PLAY(2);

        private final int value;
        Stages(int value){
            this.value = value;
        }
        public int getValue(){
            return this.value;
        }
    }

    public static int getStage(){
        return Stage;
    }
    protected static void setStage(Stages NewStage){

        switch (NewStage){

            case MENU -> MainMenuStage.getInstance();
            case SETTINGS -> SettingsStage.getInstance();
            case PLAY -> ApplicationGraphicCanvas.getInstance();
        }
        Stage = NewStage.getValue();
    }
}
