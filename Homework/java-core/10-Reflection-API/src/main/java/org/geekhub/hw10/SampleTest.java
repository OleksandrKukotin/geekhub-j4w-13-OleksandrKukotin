package org.geekhub.hw10;

import org.geekhub.hw10.annotation.BeforeMethod;
import org.geekhub.hw10.annotation.Test;

import static org.geekhub.hw10.ReflectAssertions.assertEquals;

public class SampleTest {

    @BeforeMethod
    public void setUp() {
        Sample sample = new Sample();
    }

    @Test
    public void testAddition() {
        Sample sample = new Sample();
        assertEquals(4, sample.add(2, 3));
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
}
