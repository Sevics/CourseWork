package main.java.Graphic;

import main.java.Application.WindowApplication;
import main.java.HandleResources.HandleImage;
import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;

import javax.swing.*;

public class ApplicationGraphicCanvas {
    private static long prevTime;
    private static int frameCount;
    private static ApplicationGraphicCanvas Instance = null;
    private static GLCanvas canvas = null;
    private ApplicationGraphicCanvas(){ System.out.println("Game Process started.");    }

    private static void generateCanvas(){
        JFrame ApplicationWindow = WindowApplication.getInstance().getFrame();
        GLProfile glProfile = GLProfile.get(GLProfile.GL2);
        GLCapabilities caps = new GLCapabilities(glProfile);

        String backPath = HandleImage.getPathtoDirectory("background");

        canvas = new GLCanvas(caps);

        canvas.addGLEventListener(new GLEventListener() {
            @Override
            public void init(GLAutoDrawable glAutoDrawable) {
            }

            @Override
            public void dispose(GLAutoDrawable glAutoDrawable) {

            }

            @Override
            public void display(GLAutoDrawable glAutoDrawable) {
                GL2 gl = glAutoDrawable.getGL().getGL2();

                gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);

                gl.glLoadIdentity();

                // Ваш рендеринг тут
                gl.glBegin(GL2.GL_TRIANGLES);
                gl.glColor3f(1.0f, 0.0f, 0.0f); // Червоний колір
                gl.glVertex2f(-0.5f, -0.5f);
                gl.glColor3f(0.0f, 1.0f, 0.0f); // Зелений колір
                gl.glVertex2f(0.5f, -0.5f);
                gl.glColor3f(0.0f, 0.0f, 1.0f); // Синій колір
                gl.glVertex2f(0.0f, 0.5f);
                gl.glEnd();
            }

            @Override
            public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int i2, int i3) {
            }
        });
        canvas.setVisible(true);

        ApplicationWindow.getContentPane().add(canvas);
        ApplicationWindow.revalidate();

        FPSAnimator animator = new FPSAnimator(canvas, 60); // 60 кадрів в секунду
        animator.start();

    }
    public static synchronized ApplicationGraphicCanvas getInstance() {
        if(Instance == null){
            Instance = new ApplicationGraphicCanvas();
            generateCanvas();
        }
        return Instance;
    }
}