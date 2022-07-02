package com.webservicewithapi.webservicefilm.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Utils {

    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    private static final Pattern VALID_USERNAME_REGEX =
            Pattern.compile("^[a-zA-Z]+$", Pattern.CASE_INSENSITIVE);

    public static boolean validateEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    public static boolean validateUsername(String username) {
        Matcher matcher = VALID_USERNAME_REGEX.matcher(username);
        return matcher.find();
    }

}
