package pingPong;

import java.awt.Color;
import java.awt.Graphics;

public class HumanPaddle implements Paddle {

	// x and y represent position of the actual paddle in the applet

	final double GRAVITY = 0.94;
	// player represents player 1 or player 2, so we can decide whether they're on
	// the left or right
	double y, yVel; // yVelocity
	boolean upAccel, downAccel;
	int player, x;

	public HumanPaddle(int player) {
		upAccel = false;
		downAccel = false;
		y = 210; // centre point of ball
		yVel = 0; // start ball not moving

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
		if (upAccel) {
			yVel -= 2;
		} else if (downAccel) {
			yVel += 2;
		} else if (!upAccel && !downAccel) {
			yVel *= GRAVITY;
		}

		if (yVel >= 5) {
			yVel = 5;
		} else if (yVel <= -5) {
			yVel = -5;
		}

		y += yVel; // y represents the position

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
