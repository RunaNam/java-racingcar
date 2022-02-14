package racingCar.controller;

import static racingCar.constants.RacingCarConstants.*;

import java.util.ArrayList;
import java.util.List;

import racingCar.domain.Car;
import racingCar.domain.RacingCars;
import racingCar.validator.RacingCarValidator;
import racingCar.view.InputView;
import racingCar.view.OutputView;

public class RacingCarController {

	public void start() {
		RacingCars racingCars;
		String[] carNames = getSplitCarNames();
		racingCars = makeCars(carNames);
		race(getCarTimes(), racingCars);
		OutputView.printWinners(racingCars.findWinner());
	}

	private String[] getSplitCarNames() {
		String inputCarName = InputView.userStringInput(INPUT_CAR_NAME_MESSAGE);
		return separateCarNames(inputCarName);
	}

	public String[] separateCarNames(String input) {
		String[] carNames = input.split(SPLIT_COMMA);
		for (int idx = 0; idx < carNames.length; idx++) {
			carNames[idx] = carNames[idx].trim();
			checkCarName(carNames[idx]);
		}
		return carNames;
	}

	private void checkCarName(String name) {
		try {
			RacingCarValidator.isRightLength(name);
		} catch (RuntimeException e) {
			OutputView.printError(e.getMessage());
			getSplitCarNames();
		}
	}

	private int getCarTimes() {
		String inputRacingTimes = InputView.userStringInput(INPUT_COUNT_MESSAGE);
		try {
			RacingCarValidator.isRightTimes(inputRacingTimes);
			return Integer.parseInt(inputRacingTimes);
		} catch (RuntimeException e) {
			OutputView.printError(e.getMessage());
			return getCarTimes();
		}
	}

	private RacingCars makeCars(String[] carNames) {
		List<Car> cars = new ArrayList<>();
		for (String carName : carNames) {
			cars.add(new Car(carName));
		}
		return new RacingCars(cars);
	}

	public void race(int count, RacingCars racingCars) {
		OutputView.startPrintResultMessage();
		for (int i = 0; i < count; i++) {
			racingCars.moveRacingCars();
			OutputView.printCars(racingCars);
		}
	}
}
