package main.java.Application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;


public class WindowApplication extends AppRunStages{
    private static WindowApplication Instance = null;
    private static JFrame Window;

    private static ComponentAdapter resizeActionHandle;

    private static final int MIN_WIDTH = 600;
    private static final int MIN_HEIGHT = 480;
    private static int getScreenWidth(){
        return Toolkit.getDefaultToolkit().getScreenSize().width;
    }

    private static int getFixedScreenWidth(){
        return (int) (getScreenWidth() / 1.5);
    }

    private static int getScreenHeight(){
        return Toolkit.getDefaultToolkit().getScreenSize().height;
    }

    private static int getFixedScreenHeight(){
        return (int) (getScreenHeight() / 1.35);
    }
    private static void setSizeAfterRunApp(JFrame Object){
        Object.setSize(getFixedScreenWidth(), getFixedScreenHeight());
    }
    private static int getStartBorderByXAxis(JFrame Object){
        return (getScreenWidth() - Object.getWidth()) / 2 ;
    }

    private static int getStartBorderByYAxis(JFrame Object){
        return (getScreenHeight() - Object.getHeight()) / 2;
    }

    private static void centralizeWindow(JFrame Object){
        Object.setLocation(getStartBorderByXAxis(Object), getStartBorderByYAxis(Object));
    }

    private static JFrame InitializeWindow(){
        JFrame Frame = new JFrame();

        Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Frame.setMinimumSize(new Dimension(MIN_WIDTH, MIN_HEIGHT));
        setSizeAfterRunApp(Frame);

        centralizeWindow(Frame);

        Frame.setVisible(true);

        Frame.addComponentListener(resizeActionHandle = new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                if(e.getComponent().getWidth() <= MIN_WIDTH || e.getComponent().getHeight() <= MIN_HEIGHT){
                    setSizeAfterRunApp((JFrame) e.getComponent());

                    JOptionPane.showMessageDialog(
                            e.getComponent(),
                            "Зміну розміру вікна менше мінімального розміру не допускається (600x480).",
                            "Попередження про зміну розміру застосунку до неприпустимого",
                            JOptionPane.WARNING_MESSAGE
                    );

                    setSizeAfterRunApp((JFrame) e.getComponent());
                    centralizeWindow((JFrame) e.getComponent());
                    return;
                }
                System.out.println("Resizing event.");
                System.out.print("Width - " + e.getComponent().getWidth());
                System.out.println("\t Height - " + e.getComponent().getHeight());

                if(AppRunStages.getStage() == Stages.MENU.getValue()){
                    MainMenuStage.getInstance().handleResizeWindowEvent();
                }
            }
        });
        return Frame;
    }
    private WindowApplication(){
        System.out.println("Application Window is created.");
    }
    public static synchronized WindowApplication getInstance(){
        if(Instance == null){
           Instance = new WindowApplication();
            Window = InitializeWindow();

            setStage(Stages.MENU);
        }
        return Instance;
    }

    public static synchronized void destroyInstance(){
        if(WindowApplication.getInstance() != null){
            Instance = null;
            System.out.println("Application were destroyed");
        }
    }

    public int getScreenAppWidth(){
        return getInstance().getFrame().getWidth();
    }

    public int getScreenAppHeight(){
        return getInstance().getFrame().getHeight();
    }
    public JFrame getFrame(){
        return Window;
    }

}