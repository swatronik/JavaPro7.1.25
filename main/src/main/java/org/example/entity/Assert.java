package org.example.entity;

import org.example.exceptions.TestAssertionError;

public class Assert {

    public static void error() {
        error("");
    }

    public static void error(String msg) {
        throw new TestAssertionError(msg);
    }

    public static void isTrue(boolean cond) {
        if (!cond) {
            error("Ожидалось true а пришло false");
        }
    }

    public static void equals(Object excepted, Object actual) {
        if (!excepted.equals(actual)) {
            error(String.format("Ожидалось (%s) а пришло (%s)", excepted, actual));
        }
    }

    public static void isNull(Object o) {
        if (o != null) {
            error("Ожидалось значение NULL");
        }
    }

    public static void isNonNull(Object o) {
        if (o == null) {
            error("Ожидалось значение не NULL");
        }
    }
}
