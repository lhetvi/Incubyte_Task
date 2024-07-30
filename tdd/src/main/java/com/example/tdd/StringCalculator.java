package com.example.tdd;

import java.util.ArrayList;
import java.util.List;

public class StringCalculator {
    public int add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }

        List<String> negativeNumbers = new ArrayList<>();

        if (numbers.startsWith("//")) {
            int delimiterIndex = numbers.indexOf('\n');
            String delimiter = numbers.substring(2, delimiterIndex);
            String numberString = numbers.substring(delimiterIndex + 1);

            // custom delimiter
            String[] numArray = numberString.split(java.util.regex.Pattern.quote(delimiter));
            int sum = 0;
            for (String num : numArray) {
                if (!num.isEmpty()) {
                    int number = Integer.parseInt(num);
                    if (number < 0) {
                        negativeNumbers.add(num);
                    } else if (number <= 1000) {
                        sum += number;
                    }
                }
            }

            // handle negative numbers
            if (!negativeNumbers.isEmpty()) {
                throw new IllegalArgumentException(
                        "Negative numbers not allowed: " + String.join(", ", negativeNumbers));
            }

            return sum;

        } else {

            String[] numArray = numbers.split("[,\n]");
            int sum = 0;
            for (String num : numArray) {
                if (!num.isEmpty()) {
                    int number = Integer.parseInt(num);
                    if (number < 0) {
                        negativeNumbers.add(num);
                    } else if (number <= 1000) {
                        sum += number;
                    }
                }
            }

            if (!negativeNumbers.isEmpty()) {
                throw new IllegalArgumentException(
                        "Negative numbers not allowed: " + String.join(", ", negativeNumbers));
            }

            return sum;
        }
    }
}
