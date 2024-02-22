package org.example.app.util;

import java.util.Random;

public class RandomGenerator {
    private static Random random = new Random();

    public static int getRandomInt(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }

    public static double getRandomDouble(double min, double max) {
        return min + (max - min) * random.nextDouble();
    }

    public static boolean getRandomBoolean() {
        return random.nextBoolean();
    }

    public static char getRandomChar() {
        return (char) (random.nextInt(26) + 'a');
    }


}