package pingPong;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Tennis extends Applet implements Runnable, KeyListener {
	final int WIDTH = 700, HEIGHT = 500;
	Thread thread;

	HumanPaddle p1;
	AIPaddle p2;
	Ball b1;
	boolean gameStarted = false;

	Graphics gfx;
	Image img;

	public void init() {
		this.resize(WIDTH, HEIGHT);

		this.addKeyListener(this);
		p1 = new HumanPaddle(1); // 1 = left, 2 would = right
		b1 = new Ball();
		p2 = new AIPaddle(2, b1); // 2 = right

		img = createImage(WIDTH, HEIGHT);
		gfx = img.getGraphics();

		thread = new Thread(this);
		thread.start(); // call the start method
	}

	public void paint(Graphics g) {
		gfx.setColor(Color.black);
		gfx.fillRect(0, 0, WIDTH, HEIGHT);

		if (b1.getX() < -10 || b1.getX() > 710) {
			gfx.setColor(Color.red);
			gfx.drawString("Game Over", 350, 250);
		} else {
			p1.draw(gfx); // pass graphics g as a parameter
			b1.draw(gfx);
			p2.draw(gfx);
		}

		if (!gameStarted) {
			gfx.setColor(Color.white);
			gfx.drawString("Tennis", 340, 100);
			gfx.drawString("Press Enter to begin..", 310, 130);

		}
		g.drawImage(img, 0, 0, this);
	}

	public void update(Graphics g) {
		paint(g);
	}

	// infinite loop that runs the game paints the world, then pauses for 10
	// milliseconds so our eye can see the ball
	@Override
	public void run() {
		for (;;) {
			if (gameStarted) {
				p1.move();
				b1.move();
				p2.move();

				b1.checkPaddleCollision(p1, p2);
			}

			repaint();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	// KeyListener
	public void keyPressed(KeyEvent e) {
		// if I press the up key, do whatever I specify
		if (e.getKeyCode() == KeyEvent.VK_UP) { // VK = Virtual Keyboard
			p1.setUpAccel(true);
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			p1.setDownAccel(true);
		} else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			gameStarted = true;
		}

	}

	// KeyListener
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) { // VK = Virtual Keyboard
			p1.setUpAccel(false);
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			p1.setDownAccel(false);
		}

	}

	// KeyListener
	public void keyTyped(KeyEvent e) {
		// if I press the up key, do whatever I specify
		if (e.getKeyCode() == KeyEvent.VK_UP) { // VK = Virtual Keyboard

		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {

		}

	}

}