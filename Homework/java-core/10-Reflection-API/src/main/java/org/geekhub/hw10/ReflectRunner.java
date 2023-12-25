package org.geekhub.hw10;

public class ReflectRunner {

    private static final ReflectTestBox testBox = new ReflectTestBox();

    public static void main(String[] args) {
        Class<?> clazz = SampleTest.class;
        testBox.run(clazz);
    }
}
