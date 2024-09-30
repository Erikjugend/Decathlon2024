package decathlon;

import common.*;

public class Deca110MHurdles {

	private int score;
	private double A = 5.74352;
	private double B = 28.5;
	private double C = 1.92;
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
					runningTime = inputResult.enterResult();
				} else if (runningTime > maxBoundaryValue) {
					score = -1;
					//runningTime = inputResult.enterResult();
				} else if (runningTime > 28.5) {
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

}
