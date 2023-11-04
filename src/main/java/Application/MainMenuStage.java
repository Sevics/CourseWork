package main.java.Application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuStage {

    private static JPanel panel;
    private static JButton StartPlayButton;
    private static JButton SettingsButton;
    private static JButton ExitButton;
    private static MainMenuStage Instance = null;

    // Border by X axis equal to 12.5% of Window width.
    // Border by Y axis equal to 25% of Window height.
    private static final double X_AXIS_BORDER = 0.125;
    private static final double Y_AXIS_BORDER = 0.25;
    // panel rows quantity equal to 3, because we have 3 buttons.
    // panel columns quantity equal to 1.
    private static final int PANEL_ROWS = 3;
    private static final int PANEL_COLUMNS = 1;

    // panel vertical gap equal multiplier constraint.
    private static final double VERTICAL_GAP_MULTIPLIER = 0.125;
    private static int BorderX;
    private static int BorderY;

    private static int VerticalGap;
    public static void handleResizeWindowEvent(){
        BorderX =  (int) (WindowApplication.getInstance().getScreenAppWidth() * X_AXIS_BORDER);
        BorderY = (int) (WindowApplication.getInstance().getScreenAppHeight() * Y_AXIS_BORDER);
        VerticalGap = (int) (BorderY * VERTICAL_GAP_MULTIPLIER);

        if(panel.getBorder() != null){
            panel.setBorder(null);
        }

        panel.setBorder(BorderFactory.createMatteBorder(getBorderY(),getBorderX(),getBorderY(),getBorderX(), (Color) null));
        panel.setLayout(new GridLayout(PANEL_ROWS,PANEL_COLUMNS,0,VerticalGap));
    }
    private MainMenuStage(){
        System.out.println("Stage was changed to Main Menu.");
    }
    public static void generateMenu(JFrame Window){

        panel = new JPanel();
        handleResizeWindowEvent();

        panel.setBackground(null);

        Window.add(panel, BorderLayout.CENTER);

        StartPlayButton = new JButton();
        StartPlayButton.setText("Розпочати гру");

        StartPlayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(WindowApplication.getInstance().getFrame().isResizable()){
                    WindowApplication.getInstance().getFrame().setResizable(false);
                }
                Runnable destruct = new Runnable() {
                    @Override
                    public void run() {
                        destructMainMenuStage();
                    }
                };

                Runnable createCanvas = new Runnable() {
                    @Override
                    public void run() {
                        WindowApplication.getInstance().setStage(AppRunStages.Stages.PLAY);
                    }
                };

                new Thread(destruct).start();
                new Thread(createCanvas).start();
            }
        });

        panel.add(StartPlayButton);

        SettingsButton = new JButton();
        SettingsButton.setText("Перейти до налаштувань");


        panel.add(SettingsButton);

        ExitButton = new JButton();
        ExitButton.setText("Вийти з гри");

        ExitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                destructMainMenuStage();
                WindowApplication.getInstance().getFrame().dispose();
                WindowApplication.destroyInstance();
                System.exit(1);
            }
        });

        panel.add(ExitButton);
        Window.setVisible(true);

    }

    public static int getBorderX(){
        return BorderX;
    }

    public static int getBorderY(){
        return BorderY;
    }
    public static synchronized MainMenuStage getInstance(){
        if(Instance == null){
            Instance = new MainMenuStage();
            generateMenu(WindowApplication.getInstance().getFrame());
        }
        return Instance;
    }
    private static synchronized void destroyInstance(){
        if(MainMenuStage.getInstance() != null){
            Instance = null;
            System.out.println("Main menu stage were destroyed");
        }
    }

    private static void destructMainMenuStage(){
        JFrame Window = WindowApplication.getInstance().getFrame();
        panel.remove(StartPlayButton);
        panel.remove(SettingsButton);
        panel.remove(ExitButton);

        Window.remove(panel);

        destroyInstance();

        Window.revalidate();
        Window.repaint();

    }
}