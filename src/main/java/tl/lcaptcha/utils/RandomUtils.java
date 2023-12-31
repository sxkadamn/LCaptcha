package tl.lcaptcha.utils;

import java.util.concurrent.ThreadLocalRandom;

public class RandomUtils {

    private final ThreadLocalRandom RANDOM;

    public RandomUtils(ThreadLocalRandom RANDOM) {
        this.RANDOM = RANDOM;
    }

    public int getRandomInt(int lower, int upper) {
        return RANDOM.nextInt(upper - lower + 1) + lower;
    }
}
