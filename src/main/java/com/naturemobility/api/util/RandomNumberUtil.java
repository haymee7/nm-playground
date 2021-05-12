package com.naturemobility.api.util;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RandomNumberUtil {

    private static Random random = new Random();

    /**
     * 0.0 ~ 1.0 사이의 랜덤 double 리턴
     *
     * @return
     */
    public static double generateRandomDouble() {
        return random.nextDouble();
    }

    /**
     * min ~ max 사이의 랜덤 double 리턴
     * @param min
     * @param max
     * @return
     */
    public static double getRandomDoubleBetweenRange(double min, double max) {
        double x = (Math.random() * ((max - min) + 1)) + min;
        return x;
    }

    /**
     * min ~ max 사이의 랜덤 int 리턴
     * @param min
     * @param max
     * @return
     */
    public static int generateRandomIntBetweenRange(int min, int max) {

        return random.ints(min, (max + 1)).findAny().getAsInt();
    }

    /**
     * upperRange 이하의 랜덤 int 리턴
     * @param upperRange
     * @return
     */
    public static int generateRandomInt(int upperRange) {
        return random.nextInt(upperRange);
    }
}
