package com.example.astar.maze;

import java.util.Random;

public class RandomIntGenerator {
    private static RandomIntGenerator instance = null;

    private final Random random;

    private RandomIntGenerator(){
        random = new Random(System.currentTimeMillis());
    }

    public static RandomIntGenerator getInstance() {
        if(instance == null)
            instance = new RandomIntGenerator();

        return instance;
    }

    public int getRandomIntBetween(int a, int b) {
        return random.nextInt(a, b);
    }


}
