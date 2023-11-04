package org.geekhub.hw3.orkostat.model;

public class SimpleCollection implements Collection {
    private Object[] elements;

    private static final int DEFAULT_CAPACITY = 6;
    private int size;

    public SimpleCollection() {
        elements = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    public SimpleCollection(int capacity) {
        elements = new Object[capacity];
        size = 0;
    }

    public SimpleCollection(Object... initialElements) {
        elements = new Object[initialElements.length + 10];
        size = 0;
        for (Object element : initialElements) {
            add(element);
        }
    }

    @Override
    public void add(Object element) {
        if (element != null) {
            elements[size++] = element;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Object[] getElements() {
        Object[] copy = new Object[size];
        System.arraycopy(elements, 0, copy, 0, size);
        return copy;
    }
}
