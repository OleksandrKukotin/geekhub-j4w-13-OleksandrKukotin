package org.geekhub.hw10;

import org.geekhub.hw10.annotation.AfterMethod;
import org.geekhub.hw10.annotation.BeforeMethod;
import org.geekhub.hw10.annotation.Test;

import static org.geekhub.hw10.ReflectAssertions.assertEquals;
import static org.geekhub.hw10.ReflectAssertions.assertThrows;

public class SampleTest {

    private Sample sample;

    @BeforeMethod
    public void setUp() {
        sample = new Sample();
        System.out.printf("-- An instance of the class is created successfully: %n%s --", sample);
    }

    @Test
    public void testAddition() {
        assertEquals(5, sample.add(2, 3));
    }

    @Test
    public void testAddition_whenAdditionResultIsTooLargeForInteger_shouldThrowException() {
        assertThrows(() -> sample.add(2147483647, 1));
    }

    @Test
    public void testSubtraction() {
        assertEquals(2, sample.subtract(5, 3));
    }

    @Test
    public void testMultiplication() {
        assertEquals(15, sample.multiply(3, 5));
    }

    @Test
    public void testMultiplication_whenExpectedIsWrong_shouldThrowException() {
        assertEquals(10, sample.multiply(2, 3));
    }

    @Test
    public void testDivision() {
        assertEquals(2, sample.divide(6, 3));
    }

    @Test
    public void testDivision_whenDivisorIsZero_shouldThrowException() {
        assertThrows(() -> sample.divide(1, 0));
    }

    @AfterMethod
    public void testsEnd() {
        sample = null;
        System.out.println("-- End of the test file --");
    }
}
