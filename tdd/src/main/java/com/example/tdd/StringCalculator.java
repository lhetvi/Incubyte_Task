package com.example.tdd;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {
    public int add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }

        List<String> negativeNumbers = new ArrayList<>();
        String delimiter = ",|\n";

        if (numbers.startsWith("//")) {
            int delimiterIndex = numbers.indexOf('\n');
            String delimiterPart = numbers.substring(2, delimiterIndex);

            // allows multiple delimiters
            if (delimiterPart.startsWith("[")) {
                Matcher matcher = Pattern.compile("\\[(.*?)\\]").matcher(delimiterPart);
                List<String> delimiters = new ArrayList<>();
                while (matcher.find()) {
                    delimiters.add(Pattern.quote(matcher.group(1)));
                }
                delimiter = String.join("|", delimiters);

            } else {

                delimiter = Pattern.quote(delimiterPart); // Single custom delimiter
            }

            numbers = numbers.substring(delimiterIndex + 1);
        }

        String[] numArray = numbers.split(delimiter);
        int sum = 0;

        for (String num : numArray) {
            num = num.trim();
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
