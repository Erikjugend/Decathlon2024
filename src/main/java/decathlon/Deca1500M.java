package decathlon;

import common.*;

public class Deca1500M {

	private int score;
	private double A = 0.03768;
	private double B = 480;
	private double C = 1.85;
	boolean active = true;

	CalcTrackAndField calc = new CalcTrackAndField();
	InputResult inputResult = new InputResult();

	// Calculate the score based on time. All running events.
	public int calculateResult(double runningTime) {

		while (active) {

			try {
				// Acceptable values.
				if (runningTime < 2) { //These are boundary values in minutes
					System.out.println("Value too low");
					score = -1;
					//runningTime = inputResult.enterResult();
				} else if (runningTime > 7) { //These are boundary values in minutes
					System.out.println("Value too high");
					score = -2;
					//runningTime = inputResult.enterResult();
				} else {
					runningTime = convertMinutesToSeconds(runningTime); //Converting minute value to seconds
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
	public double convertMinutesToSeconds(double runningTimeInSeconds)
	{
		runningTimeInSeconds = runningTimeInSeconds * 60;
		return runningTimeInSeconds;
	}

}
