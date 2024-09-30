package decathlon;

import common.*;

public class Deca100M {

	private int score;
	private double A = 25.4347;
	private double B = 18;
	private double C = 1.81;

	private double minBoundaryValue = 5;
	private double maxBoundaryValue = 20;

	boolean active = true;
	CalcTrackAndField calc = new CalcTrackAndField();
	InputResult inputResult = new InputResult();

	// Calculate the score based on time. All running events.
	public int calculateResult(double runningTime) {

		while (active) {

			try {
				// Acceptable values.
				if (runningTime > minBoundaryValue) {
					score = -1;
					System.out.println("Value too low");
					//runningTime = inputResult.enterResult(); //remove later
				} else if (runningTime > maxBoundaryValue) {
					System.out.println("Value too high");
					//runningTime = inputResult.enterResult();
					score = -2;
				} else {

					score = calc.calculateTrack(A, B, C, runningTime);
				}
				active = false;
			} catch (Exception e) {

				System.out.println("Please enter numbers");
			}
		}
		System.out.println("The result is " + score);

		return score;

	}
	public double getMinBoundaryValue() {
		return minBoundaryValue;
	}
	public double getMaxBoundaryValue() {
		return maxBoundaryValue;
	}

}
