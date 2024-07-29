package com.example.tdd;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StringCalculatorTest {
    @Test
    public void testAddEmptyString() {
        StringCalculator calculator = new StringCalculator();
        assertEquals(0, calculator.add(""), "Empty string should return 0");
    }

    @Test
    public void testAddSingleNumber() {
        StringCalculator calculator = new StringCalculator();
        assertEquals(1, calculator.add("1"), "Single number '1' should return 1");
    }

    @Test
    public void testAddTwoNumbers() {
        StringCalculator calculator = new StringCalculator();
        assertEquals(6, calculator.add("1,5"), "String '1,5' should return 6");
    }

    @Test
    public void testAddNumbersWithNewLines() {
        StringCalculator calculator = new StringCalculator();
        assertEquals(6, calculator.add("1\n2,3"), "String '1\n2,3' should return 6");
    }

    @Test
    public void testAddWithCustomDelimiter() {
        StringCalculator calculator = new StringCalculator();
        assertEquals(3, calculator.add("//;\n1;2"), "String '//;\n1;2' with delimiter ';' should return 3");
    }

    @Test
    public void testAddWithDifferentCustomDelimiter() {
        StringCalculator calculator = new StringCalculator();
        assertEquals(6, calculator.add("//|\n1|2|3"), "String '//|\n1|2|3' with delimiter '|' should return 6");
    }

    @Test
    public void testAddWithNegativeNumbers() {
        StringCalculator calculator = new StringCalculator();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            calculator.add("1,-2,3,-4");
        });
        assertEquals("Negative numbers not allowed: -2, -4", exception.getMessage());
    }

    @Test
    public void testAddWithNegativeNumbersAndCustomDelimiter() {
        StringCalculator calculator = new StringCalculator();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            calculator.add("//;\n1;-2;3;-4");
        });
        assertEquals("Negative numbers not allowed: -2, -4", exception.getMessage());
    }
}
