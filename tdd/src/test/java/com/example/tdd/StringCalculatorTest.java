package com.example.tdd;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StringCalculatorTest {
    @Test
    public void emptyString() {
        StringCalculator calculator = new StringCalculator();
        assertEquals(0, calculator.add(""), "Empty string should return 0");
    }

    @Test
    public void singleNumber() {
        StringCalculator calculator = new StringCalculator();
        assertEquals(0, calculator.add("0"), "Single number '0' should return 0");
    }

    @Test
    public void twoNumbers() {
        StringCalculator calculator = new StringCalculator();
        assertEquals(6, calculator.add("1,5"), "String '1,5' should return 6");
    }

    @Test
    public void numbersWithNewLines() {
        StringCalculator calculator = new StringCalculator();
        assertEquals(6, calculator.add("1\n2,3"), "String '1\n2,3' should return 6");
    }

    @Test
    public void commaDelimiter() {
        StringCalculator calculator = new StringCalculator();
        assertEquals(3, calculator.add("//;\n1;2"), "String '//;\n1;2' with delimiter ';' should return 3");
    }

    @Test
    public void customDelimiter() {
        StringCalculator calculator = new StringCalculator();
        assertEquals(6, calculator.add("//|\n1|2|3"), "String '//|\n1|2|3' with delimiter '|' should return 6");
    }

    @Test
    public void negativeNumbers() {
        StringCalculator calculator = new StringCalculator();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            calculator.add("1,-9,2,-7");
        });
        assertEquals("Negative numbers not allowed: -9, -7", exception.getMessage());
    }

    @Test
    public void negativeNumbersAndDelimiter() {
        StringCalculator calculator = new StringCalculator();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            calculator.add("//;\n1;2;-3;-4");
        });
        assertEquals("Negative numbers not allowed: -3, -4", exception.getMessage());
    }

    @Test
    public void numbersGreaterThan1000() {
        StringCalculator calculator = new StringCalculator();
        assertEquals(2, calculator.add("2,1001"), "String '2,1001' should return 2");
    }

    @Test
    public void numbersGreaterThan1000WithDelimiter() {
        StringCalculator calculator = new StringCalculator();
        assertEquals(3, calculator.add("//;\n1;2;1001"), "String '//;\n1;2;1001' should return 3");
    }

    @Test
    public void longDelimiter() {
        StringCalculator calculator = new StringCalculator();
        assertEquals(6, calculator.add("//[***]\n1***2***3"), "String '//[***]\n1***2***3' should return 6");
    }

    @Test
    public void multipleDelimiters() {
        StringCalculator calculator = new StringCalculator();
        assertEquals(6, calculator.add("//[*][%]\n1*2%3"), "String '//[*][%]\n1*2%3' should return 6");
    }

    @Test
    public void testAddWithMultipleLongDelimiters() {
        StringCalculator calculator = new StringCalculator();
        assertEquals(10, calculator.add("//[***][%%%]\n1***2%%% 3***4"),
                "String '//[***][%%%]\n1***2%%% 3***4' should return 10");
    }

}
