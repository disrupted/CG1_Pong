package de.berlin.htw;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class Ex1PONG {
  private static int width = 800;
  private static int height = 640;

  public Ex1PONG() {
    // open APP
    try {
      Display.setDisplayMode(new DisplayMode(width, height));
      Display.create();

      // INIT
      GL11.glPointSize(10);
      GL11.glClearColor(0.3f, 0.3f, 0.3f, 1);

      // LOOP
      while (!Display.isCloseRequested()) {
        Display.update();

        // CLEAR
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

        GL11.glBegin(GL11.GL_POINTS);
        for (int i = 0; i < 1000; i++) {
          float u = (float) Math.random();
          float v = (float) Math.random();
          float x = (float) Math.random();
          float y = (float) Math.random();
          GL11.glColor3f(1, 1, 1);
          GL11.glVertex2f(u * 2.f - 1.f, v * 2.f - 1.f);
        }
        GL11.glEnd();
      }

      Display.destroy();
    } catch (LWJGLException e) {
      //e.printStackTrace();
      System.exit(1);
    }
  }

  public static void main(String[] args) {
    Ex1PONG game = new Ex1PONG();
  }

}
