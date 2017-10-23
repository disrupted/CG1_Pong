package de.berlin.htw;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class Ex1PONG {
  private static int width = 800;
  private static int height = 640;

  public Ex1PONG() {
  }

  public static void main(String[] args) {
    Ex1PONG game = new Ex1PONG();
    game.run();
  }

  public void run() {
    setup();
    while (update()) {
      draw();
    }
    finish();
  }

  public void setup() {
    try {
      Display.setDisplayMode(new DisplayMode(width, height));
      Display.create();
    } catch (LWJGLException e) {
      e.printStackTrace();
      System.exit(0);
    }

    // set OpenGL ortho
    GL11.glMatrixMode(GL11.GL_PROJECTION);
    GL11.glLoadIdentity();
    GL11.glOrtho(0, width, 0, height, 1, -1);
    GL11.glMatrixMode(GL11.GL_MODELVIEW);
  }

  public boolean update() {
    boolean end = Display.isCloseRequested();
    Display.update();
    return !end;
  }

  public void draw() {
    // clear screen
    GL11.glClearColor(0.3f, 0.3f, 0.3f, 1);
    GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

    // RGBA set color to blue
    GL11.glColor3f(0.5f, 0.5f, 1.0f);

    drawRect(100, 200, 400, 600);
  }

  public void finish() {
    Display.destroy();
    System.out.println("GAME OVER");
  }

  private void drawRect(int xs, int ys, int xe, int ye) {
    GL11.glBegin(GL11.GL_QUADS);
    GL11.glVertex2f(xs, ys);
    GL11.glVertex2f(xs, ye);
    GL11.glVertex2f(xe, ye);
    GL11.glVertex2f(xe, ys);
    GL11.glEnd();
  }

}
