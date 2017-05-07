package com.leszko.cd.calculator;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CalculatorControllerTest {
    private CalculatorController calculator = new CalculatorController();

    @Test
    public void sumTwoElements() {
        // given
        int a = 1;
        int b = 2;

        // when
        String result = calculator.sum(a, b);

        // then
        assertEquals("3", result);
    }

}