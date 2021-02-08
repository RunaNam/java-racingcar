package javaracingcar.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RacingGameTest {
    @Test
    void generateCars_자동차리스트생성() {
        List<String> cars = new ArrayList<>();
        cars.add("CarA");
        cars.add("CarB");
        cars.add("CarC");
        List<Car> generatedCars = RacingGame.generateCars(Arrays.asList("CarA", "CarB", "CarC"));
        for (int i = 0; i < cars.size(); i++) {
            assertEquals(generatedCars.get(i)
                                      .getName(), cars.get(i));
        }
    }

    @Test
    void init_중복된이름() {
        List<String> carNames = Arrays.asList("a", "b", "c", "a");
        assertThatThrownBy(() -> RacingGame.init(carNames, 5))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("중복된 이름");
    }

    @Test
    void init_정상적인생성() {
        List<String> carNames = Arrays.asList("a", "b", "c", "d");
        int trial = 5;
        RacingGame racingGame = RacingGame.init(carNames, trial);
        assertEquals(racingGame.getCarNames(), carNames);
        assertEquals(racingGame.getTrial(), trial);
    }
}