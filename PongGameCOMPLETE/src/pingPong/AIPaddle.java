package pingPong;

import java.awt.Color;
import java.awt.Graphics;

public class AIPaddle implements Paddle {

	// x and y represent position of the actual paddle in the applet

	final double GRAVITY = 0.94;
	// player represents player 1 or player 2, so we can decide whether they're on
	// the left or right
	double y, yVel; // yVelocity
	boolean upAccel, downAccel;
	int player, x;

	Ball b1; // ADDED - Going to track the y value of the ball

	public AIPaddle(int player, Ball b) {
		upAccel = false;
		downAccel = false;
		y = 210; // centre point of ball
		yVel = 0; // start ball not moving

		b1 = b; // ADDED

		// if player 1, assign to left side of screen, else assign to right
		if (player == 1)
			x = 20;
		else
			x = 660;

	}

	public void draw(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(x, (int) y, 20, 80); // 20 x 80 gives us a nice vertical paddle (ball?)

	}

	public void move() {
		y = b1.getY() - 40; // centers the ball y value on the ball

		if (y < 0) {
			y = 0;
		} else if (y > 420) {
			y = 420;
		}

	}

	public void setUpAccel(boolean input) {
		upAccel = input;

	}

	public void setDownAccel(boolean input) {
		downAccel = input;

	}

	public int getY() {
		return (int) y; // return interger version of y
	}

}
