package decathlon;

import common.*;

public class DecaLongJump {

	private int score;
	private double A = 0.14354;
	private double B = 220;
	private double C = 1.4;
	private double minBoundaryValue = 0;
	private double maxBoundaryValue = 1000;
	boolean active = true;
	CalcTrackAndField calc = new CalcTrackAndField();
	InputResult inputResult = new InputResult();

	// Calculate the score based on distance and height. Measured in centimetres.
	public int calculateResult(double distance) {

		while (active) {

			try {
				// Acceptable values.
				if (distance < minBoundaryValue) {
					System.out.println("Value too low");
					score = -1;
					//distance = inputResult.enterResult();
				} else if (distance > maxBoundaryValue) {
					System.out.println("Value too high");
					score = -2;
					//distance = inputResult.enterResult();

				} else {

					score = calc.calculateField(A, B, C, distance);
				}
				active = false;
			} catch (Exception e) {

				System.out.println("Please enter numbers");
			}
		}
		System.out.println("The result is: " + score);
		return score;
	}
	public double getMinBoundaryValue() {
		return minBoundaryValue;
	}
	public double getMaxBoundaryValue() {
		return maxBoundaryValue;
	}

}
