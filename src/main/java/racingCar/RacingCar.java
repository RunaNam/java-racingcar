package racingCar;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RacingCar {
	private ArrayList<Car> cars = new ArrayList<>();
	private int maxPosition = Integer.MIN_VALUE;

	public void start() {
		List<Car> winners;
		String[] carNames = racingCarNames();
		int count = racingCarTimes();
		makeCars(carNames);
		race(count);
		winners = findWinner(cars);
	}

	private String[] racingCarNames() {
		String inputCarName = userInput("경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).");
		return getCarNames(inputCarName);
	}

	private int racingCarTimes() {
		String inputRacingTimes = userInput("시도할 횟수는 몇회인가요?");
		return enterTimes(inputRacingTimes);
	}

	private String userInput(String message) {
		System.out.println(message);
		Scanner scanner = new Scanner(System.in);
		return scanner.nextLine();
	}

	public String[] getCarNames(String input) {
		String[] carNames = input.split(",");
		for (int idx = 0; idx < carNames.length; idx++) {
			carNames[idx] = carNames[idx].trim();
			checkCarName(carNames[idx]);
		}
		return carNames;
	}

	private void checkCarName(String name) {
		try {
			isRightLength(name);
		} catch (RuntimeException e) {
			System.out.println("[ERROR] 이름이 공백이거나 6자 이상이면 안됩니다. 다시 입력해주세요.");
			racingCarNames();
		}
	}

	public void isRightLength(String name) throws RuntimeException {
		if (name.length() == 0 || name.length() > 5) {
			throw new RuntimeException();
		}
	}

	public int enterTimes(String input) {
		try {
			isRightTimes(input);

		} catch (RuntimeException e) {
			System.out.println("[ERROR] 시도 횟수는 양수인 정수여야 합니다. 다시 입력해주세요.");
			racingCarTimes();
		}
		return Integer.parseInt(input);
	}

	public void isRightTimes(String input) throws RuntimeException {
		final String REGEX = "[0-9]+";
		if (!input.matches(REGEX)) {
			throw new RuntimeException();
		}
	}

	private void makeCars(String[] carNames) {
		for (String carName : carNames) {
			cars.add(new Car(carName));
		}
	}

	private void race(int count) {
		for (int i = 0; i < count; i++) {
			moveCars();
		}
	}

	private void moveCars() {
		for (int idx = 0; idx < cars.size(); idx++) {
			moveCar(idx);
		}
	}

	private void moveCar(int idx) {
		Car car = cars.get(idx);
		car.moveCar(makeRandom());
		System.out.println(car);
	}

	private boolean makeRandom() {
		return ((int)(Math.random() * 10) - 1) >= 4;
	}

	public List<Car> findWinner(List<Car> cars) {
		List<Car> winners = new ArrayList<>();
		for (Car car : cars) {
			winners = checkLead(car, winners);
		}
		return winners;

	}

	private List<Car> checkLead(Car car, List<Car> winners) {
		if (car.getPosition() > maxPosition) {
			winners.clear();
			winners.add(car);
			maxPosition = car.getPosition();
			return winners;
		}
		if (car.getPosition() == maxPosition) {
			winners.add(car);
		}
		return winners;
	}

}
