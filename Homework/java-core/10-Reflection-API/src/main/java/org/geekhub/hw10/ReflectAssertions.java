package org.geekhub.hw10;

import java.lang.reflect.Field;
import java.util.Objects;

public class ReflectAssertions {

    private ReflectAssertions() {
    }

    public static void assertEquals(Object expected, Object actual) throws AssertionError {
        if (Objects.equals(expected, actual)) {
            System.out.printf("%nExpected:%s, Actual:%s", expected, actual);
        } else {
            throw new AssertionError("\n- Test failed: Expected: " + expected + ", Actual: " + actual);
        }
    }

    public static void assertReflectionEquals(Object expected, Object actual) {
        if (expected == null && actual == null) {
            System.out.printf("%nBoth objects are null");
            return;
        }

        if (expected == null || actual == null) {
            throw new AssertionError("- Test failed: One of the objects is null");
        }

        Class<?> expectedClass = expected.getClass();
        Class<?> actualClass = actual.getClass();

        if (!expectedClass.equals(actualClass)) {
            throw new AssertionError("- Test failed: Objects are of different classes");
        }

        Field[] fields = expectedClass.getDeclaredFields();

        for (Field field : fields) {
            try {
                Object expectedValue = field.get(expected);
                Object actualValue = field.get(actual);

                if (!Objects.equals(expectedValue, actualValue)) {
                    throw new AssertionError("- Test failed: Field '" + field.getName() +
                        "' mismatch - Expected: " + expectedValue + ", Actual: " + actualValue);
                }
            } catch (IllegalAccessException e) {
                throw new AssertionError("- Test failed: Error accessing field " + field.getName());
            }
        }

        System.out.printf("%nReflectionEquals%n");
    }

    public static void assertTrue(boolean condition) {
        if (condition) {
            System.out.printf("%n+ Test passed:%nExpected:%b, Actual:%b", true, true);
        } else {
            throw new AssertionError("- Assertion failed: expected true but false retrieved");
        }
    }

    public static void assertFalse(boolean condition) {
        if (!condition) {
            System.out.printf("%n+ Test passed:%nExpected:%b, Actual:%b", true, false);
        } else {
            throw new AssertionError("- Assertion failed: expected false but true retrieved");
        }
    }
}
