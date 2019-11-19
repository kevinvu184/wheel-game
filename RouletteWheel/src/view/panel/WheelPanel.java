package view.panel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.net.URL;

@SuppressWarnings("serial")
public class WheelPanel extends JPanel {
	private final int BALL_DIAMETER = 15;
	private final int NUM_OF_SLOT = 38;

	private BufferedImage wheelImage;
	private int position = 0;

	public WheelPanel() {
		try {
			URL url = WheelPanel.class.getResource("/img/wheel.png");
			wheelImage = ImageIO.read(url);
		} catch (IOException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, ex.getMessage(), "Error Image", JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}
	}

	public void repaintWheel(int position) {
		this.position = position;
		this.repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		// Wheel draw
		int wheelSide = wheelImage.getHeight();

		int panelWidth = getWidth();
		int panelHeight = getHeight();

		int xTopCoordinator = 0;
		int yTopCoordinator = 0;

		if (panelHeight < panelWidth) {
			xTopCoordinator = panelWidth;
			panelWidth = panelHeight;
			xTopCoordinator = (xTopCoordinator - panelWidth) / 2;
		} else {
			yTopCoordinator = panelHeight;
			panelHeight = panelWidth;
			yTopCoordinator = (yTopCoordinator - panelHeight) / 2;
		}

		int xBottomCoordinator = panelWidth + xTopCoordinator;
		int yBottomCoordinator = panelHeight + yTopCoordinator;

		g.drawImage(wheelImage.getScaledInstance(wheelSide, wheelSide, Image.SCALE_SMOOTH), xTopCoordinator,
				yTopCoordinator, xBottomCoordinator, yBottomCoordinator, 0, 0, wheelSide, wheelSide, this);

		// Ball draw
		int wheelDiameter = (getWidth() > getHeight()) ? getHeight() : getWidth();
		int xCenter = (getWidth() - wheelDiameter) / 2;
		int yCenter = (getHeight() - wheelDiameter) / 2;

		int ballDiameter = BALL_DIAMETER;
		Point circumference = getCircumference(position, (wheelDiameter / 2f) - (ballDiameter / 2), panelHeight,
				panelWidth);

		g.setColor(Color.yellow);
		g.fillOval(xCenter + circumference.x - (int) (ballDiameter / 2),
				yCenter + circumference.y - (int) (ballDiameter / 2), ballDiameter, ballDiameter);
	}

	private Point getCircumference(int position, float radius, int height, int width) {
		float degree = ((float) position * (360.0f / NUM_OF_SLOT));
		double positionInRadius = Math.toRadians(degree - 90);
		int xCoordinator = Math.round((float) (Math.round(width / 2) + Math.cos(positionInRadius) * radius));
		int yCoordinator = Math.round((float) (Math.round(height / 2) + Math.sin(positionInRadius) * radius));
		return new Point(xCoordinator, yCoordinator);
	}
}
