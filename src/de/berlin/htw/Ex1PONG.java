package de.berlin.htw;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class Ex1PONG {
  private static int width = 800;
  private static int height = 640;
  private long fps, lastFPS;
  private long lastTime;
  private static int paddleHeight = 100;
  private static int paddleThickness = 10;
  private int player1Pos = height / 2;
  private int player2Pos = height / 2;
  private int ballX = width / 2;
  private int ballY = height / 2;
  private int ballSize = 20;
  int playerSpeed = 1;
  int ballXspeed = 5;
  int ballYspeed = 5;

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

    lastFPS = getTime();
  }

  public boolean update() {
    boolean end = Display.isCloseRequested();
    Display.sync(60);
    Display.update();
    /* long time = getTime();
    float timeDiff = time - lastTime; // delta between frames
    lastTime = time; */
    updateFPS();
    if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) end = true;
    // if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) isRunning = true;
    if (Keyboard.isKeyDown(Keyboard.KEY_UP)) {
      if (player2Pos + paddleHeight < height)
        player2Pos += playerSpeed * timeDiff;
    }
    if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
      if (player1Pos + paddleHeight < height)
        player1Pos += playerSpeed * timeDiff;
    }
    if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
      if (player2Pos > 0)
        player2Pos -= playerSpeed * timeDiff;
    }
    if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
      if (player1Pos > 0)
        player1Pos -= playerSpeed * timeDiff;
    }
    return !end;
  }

  public void draw() {
    // clear screen
    GL11.glClearColor(0.1f, 0.1f, 0.1f, 1);
    GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

    // set color to white
    GL11.glColor3f(1.0f, 1.0f, 1.0f);

    // Player 1
    drawRect(0, player1Pos, paddleThickness, paddleHeight);

    // Player 2
    drawRect(width - paddleThickness, player2Pos, paddleThickness, paddleHeight);

    // Ball
    drawRect(ballX, ballY, ballSize, ballSize);
    if (ballX + ballSize >= width || ballX <= 0) {
      ballXspeed = -ballXspeed;
    }
    if (ballY + ballSize >= height || ballY <= 0) {
      ballYspeed = -ballYspeed;
    }

    ballX += ballXspeed;
    ballY += ballYspeed;
  }

  public void finish() {
    Display.destroy();
    System.out.println("GAME OVER");
  }

  private void drawRect(int x, int y, int width, int height) {
    GL11.glBegin(GL11.GL_QUADS);
    GL11.glVertex2f(x, y);
    GL11.glVertex2f(x + width, y);
    GL11.glVertex2f(x + width, y + height);
    GL11.glVertex2f(x, y + height);
    GL11.glEnd();
  }

  private long getTime() {
    return (Sys.getTime() * 1000) / Sys.getTimerResolution();
  }

  private void updateFPS() {
    if (getTime() - lastFPS > 1000) {
      Display.setTitle("FPS: " + fps);
      fps = 0; // reset FPS counter
      lastFPS += 1000;
    }
    fps++;
  }

}
