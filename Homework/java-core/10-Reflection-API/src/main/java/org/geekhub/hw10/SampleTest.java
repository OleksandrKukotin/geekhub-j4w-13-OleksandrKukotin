package org.geekhub.hw10;

import org.geekhub.hw10.annotation.AfterMethod;
import org.geekhub.hw10.annotation.BeforeMethod;
import org.geekhub.hw10.annotation.Test;

import static org.geekhub.hw10.ReflectAssertions.assertEquals;
import static org.geekhub.hw10.ReflectAssertions.assertThrows;

public class SampleTest {

    @BeforeMethod
    public void setUp() {
        Sample sample = new Sample();
        System.out.printf("-- An instance of the class is created successfully: %n%s --", sample);
    }

    @Test
    public void testAddition() {
        Sample sample = new Sample();
        assertEquals(6, sample.add(2, 3));
    }

    @Test
    public void testSubtraction() {
        Sample sample = new Sample();
        assertEquals(2, sample.subtract(5, 3));
    }

    @Test
    public void testMultiplication() {
        Sample sample = new Sample();
        assertEquals(15, sample.multiply(3, 5));
    }

    @Test
    public void testDivision() {
        Sample sample = new Sample();
        assertEquals(2, sample.divide(6, 3));
    }

    @Test
    public void testExceptionThrowing() {
        Sample sample = new Sample();
        assertThrows(() -> sample.divide(1, 0));
    }

    @AfterMethod
    public void testsEnd() {
        System.out.println("-- End of the test file --");
    }
}
