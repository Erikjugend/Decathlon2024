package decathlon;

import common.*;

public class Deca400M {

	private int score;
	private double A = 1.53775;
	private double B = 82;
	private double C = 1.81;
	private double minBoundaryValue = 20;
	private double maxBoundaryValue = 100;
	boolean active = true;

	CalcTrackAndField calc = new CalcTrackAndField();
	InputResult inputResult = new InputResult();

	// Calculate the score based on time. All running events.
	public int calculateResult(double runningTime) {

		while (active) {

			try {
				if (runningTime > minBoundaryValue) {
					score = -1;
					//runningTime = inputResult.enterResult();
				} else if (runningTime > maxBoundaryValue) {
					System.out.println("Value too high");
					score = -2;
					//runningTime = inputResult.enterResult();
				} else {
					score = calc.calculateTrack(A, B, C, runningTime);
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
