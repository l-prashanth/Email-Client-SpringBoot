package com.prashanth.email.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.prashanth.email.constants.Constants.*;

public class CommonUtil {
    public static boolean isNullOrEmpty(Object... objects) {
        for (Object o : objects) {
            if (o instanceof Number number) {
                double numericValue = number.doubleValue();
                if (numericValue == 0) {
                    return true;
                }
            }
            if (o == null || o.equals("")) {
                return true;
            }
        }
        return false;
    }

    public static boolean isNotNullOrEmpty(Object... objects) {
        for (Object o : objects) {
            if (o instanceof Number number) {
                double numericValue = number.doubleValue();
                if (numericValue == 0) {
                    return false;
                }
            }
            if (o == null || o.equals("")) {
                return false;
            }
        }
        return true;
    }

    public static String messageFiller(String toEmail, String body) {
        return GREET + extractNameFromEmail(toEmail) + COMMA + TWO_LINE_BREAK +
                body + TWO_LINE_BREAK + GRATITUDE + COMMA + ONE_LINE_BREAK + NAME + COMMA + ONE_LINE_BREAK + PHONE;

    }
    public static String messageFillerWithInputName(String name, String body) {
        return GREET + capitalizeFirstCharacter(name) + COMMA + TWO_LINE_BREAK +
                body + TWO_LINE_BREAK + GRATITUDE + COMMA + ONE_LINE_BREAK + NAME + COMMA + ONE_LINE_BREAK + PHONE;

    }
    public static String messageFillerWithoutToName(String body) {
        return GREET +TWO_LINE_BREAK +
                body + TWO_LINE_BREAK + GRATITUDE + COMMA + ONE_LINE_BREAK + NAME + COMMA + ONE_LINE_BREAK + PHONE;

    }
    public static String capitalizeFirstCharacter(String input) {
        if (isNullOrEmpty(input)) {
            return input;
        }

        // Convert the first character to uppercase and the rest to lowercase
        return Character.toUpperCase(input.charAt(0)) + input.substring(1).toLowerCase();
    }

    public static String extractNameFromEmail(String email) {
        // Define the pattern for the first occurrence of any special character
        String specialCharacters = "[@!,#$%^&*()0-9]";
        Pattern pattern = Pattern.compile(specialCharacters);
        Matcher matcher = pattern.matcher(email);

        // Find the index of the first occurrence of any special character
        int firstSpecialCharIndex = matcher.find() ? matcher.start() : -1;

        // Extract the substring before the first special character
        String name;
        // No special character found, use the entire email or extract the substring before the first special character
        name = (firstSpecialCharIndex == -1) ? email : email.substring(0, firstSpecialCharIndex);

// Convert the first letter to uppercase and the rest to lowercase (title case)
        name = (!name.isEmpty()) ? name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase() : name;


        // Print or use the extracted name
        System.out.println("Name: " + name);

        // You can return the name or use it as needed in your application
        return name;
    }
}