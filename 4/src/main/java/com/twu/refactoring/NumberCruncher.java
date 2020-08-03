package com.twu.refactoring;

import java.util.function.Function;

public class NumberCruncher {
    private final int[] numbers;

    public NumberCruncher(int... numbers) {
        this.numbers = numbers;
    }

    public int countEven() {
        return counter(isEven,true);
    }

    public int countOdd() {
        return counter(isEven,false);
    }

    public int countPositive() {
        return counter(isPositive,true);
    }

    public int countNegative() {
        return counter(isPositive,false);
    }

    private int counter(Function<Integer, Boolean> func, boolean isTrue) {
        int count = 0;
        for (int number : numbers) {
            if (!func.apply(number)^isTrue) count++;
        }
        return count;
    }

    private Function<Integer, Boolean> isPositive = number -> number >= 0;

    private Function<Integer, Boolean> isEven = number -> number % 2 == 0;
}
