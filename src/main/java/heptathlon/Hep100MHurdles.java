package heptathlon;

import common.CalcTrackAndField;
import common.InputResult;

public class Hep100MHurdles {

	private int score;
	private double A = 9.23076;
	private double B = 26.7;
	private double C = 1.835;
	private double minBoundaryValue = 10;
	private double maxBoundaryValue = 30;
	boolean active = true;
	CalcTrackAndField calc = new CalcTrackAndField();
	InputResult inputResult = new InputResult();

	// Calculate the score based on time. All running events.
	public int calculateResult(double runningTime) {

		while (active) {

			try {
				// Acceptable values.
				if (runningTime < minBoundaryValue) {
					System.out.println("Value too low");
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
