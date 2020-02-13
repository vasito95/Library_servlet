package org.brs.library.utility;

import java.util.Arrays;
import java.util.Objects;

public class ValidationHelper {

    public static boolean nonNull(Object... objects) {
        return Arrays.stream(objects).allMatch(Objects::nonNull);
    }

    public static boolean isNull(Object... objects) {
        return Arrays.stream(objects).noneMatch(Objects::nonNull);
    }

    public static boolean isStringValid(String s) {
        return s != null && !s.equals("") && !s.equals("null");
    }
    public static boolean isStringsNullOrEmpty(String... strings) {
        return Arrays.stream(strings).anyMatch(s -> s == null || s.equals(""));
    }
}
