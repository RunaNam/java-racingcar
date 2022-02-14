package racingCar.view;

import static racingCar.constants.RacingCarConstants.*;

import java.util.List;

import racingCar.domain.Car;
import racingCar.domain.RacingCars;

public class OutputView {
	public static void printError(String message) {
		System.out.println(message);
	}

	public static void startPrintResultMessage() {
		System.out.println(EXECUTION_RESULT_MESSAGE);
	}

	public static void printCars(RacingCars cars) {
		System.out.println(cars);
	}

	public static void printWinners(List<Car> winners) {
		for (int idx = 0; idx < winners.size() - 1; idx++) {
			winners.get(idx).printName();
			System.out.print(", ");
		}
		winners.get(winners.size() - 1).printName();
		System.out.println(FINAL_WINNER_MESSAGE);
	}

}
