package org.brs.library.helper;

import java.util.Arrays;
import java.util.Objects;
import java.util.ResourceBundle;

public class ValidationHelper {

    public static boolean nonNull(Object... objects) {
        return Arrays.stream(objects).allMatch(Objects::nonNull);
    }

    public static boolean isNull(Object... objects) {
        return Arrays.stream(objects).allMatch(Objects::isNull);
    }

    public static boolean isStringNullOrEmpty(String... strings) {
        return Arrays.stream(strings).anyMatch(s -> s == null || s.equals(""));
    }

    public static boolean validateLoginForm(String email, String password) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("regex");
        return email.matches(resourceBundle.getString("email.regexp"))
                && password.matches(resourceBundle.getString("password.regexp"));
    }

    public static boolean validateRegistrationForm(String email, String password, String username, String phoneNumber) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("regex");
        return email.matches(resourceBundle.getString("email.regexp"))
                && password.matches(resourceBundle.getString("password.regexp"))
                && username.matches(resourceBundle.getString("username.regexp"))
                && phoneNumber.matches(resourceBundle.getString("phone.number.regexp"))
                ;
    }
}
