package org.brs.library.helper;

import java.util.Arrays;
import java.util.Objects;

public class ValidationHelper {

    public static boolean nonNull(Object... objects) {
        return Arrays.stream(objects).allMatch(Objects::nonNull);
    }

    public static boolean isNull(Object... objects) {
        return Arrays.stream(objects).allMatch(Objects::isNull);
    }

    public static boolean isStringsNoTNullOrEmpty(String... strings) {
        return Arrays.stream(strings).anyMatch(s -> s == null || s.equals(""));
    }
}
