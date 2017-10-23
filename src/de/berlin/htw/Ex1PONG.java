package de.berlin.htw;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class Ex1PONG {

  private static int height = 600;
  private static int width = 1200;

  public Ex1PONG() {
    try {
      Display.setDisplayMode(new DisplayMode(width, height));
      Display.create();

      while (!Display.isCloseRequested()) {
        Display.update();
      }

      Display.destroy();
    } catch (LWJGLException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    new Ex1PONG();
  }

}
