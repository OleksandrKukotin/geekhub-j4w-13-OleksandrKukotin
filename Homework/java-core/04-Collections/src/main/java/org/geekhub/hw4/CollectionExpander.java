package org.geekhub.hw4;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;


public class CollectionExpander implements Expander {
    @Override
    public double getMinValue(Collection<? extends Number> collection) {
        if (collection.isEmpty()) {
            return Double.MAX_VALUE;
        }
        Set<Double> sortedCollection = new TreeSet<>();
        for (Number number : collection) {
            sortedCollection.add(number.doubleValue());
        }
        return sortedCollection.iterator().next();
    }

    @Override
    public double getMaxValue(Collection<? extends Number> collection) {
        if (collection.isEmpty()) {
            return Double.MAX_VALUE;
        }
        Set<Double> sortedCollection = new TreeSet<>();
        for (Number number : collection) {
            sortedCollection.add(number.doubleValue());
        }
        double max = Double.MIN_VALUE;
        for (Double value : sortedCollection) {
            max = value;
        }
        return max;
    }

    @Override
    public double getSum(Collection<? extends Number> collection) {
        return 0.0;
    }

    @Override
    public String join(Collection<?> collection, char delimiter) {
        return null;
    }

    @Override
    public List<Double> reversed(List<? extends Number> collection) {
        return null;
    }

    @Override
    public List<List<Object>> chunked(Collection<?> collection, int amount) {
        return null;
    }

    @Override
    public List<?> dropElements(List<?> list, Object criteria) {
        return null;
    }

    @Override
    public <T> List<T> getClassList(T t) {
        return null;
    }

    @Override
    public <T> List<T> removeDuplicatesAndNull(List<T> collection) {
        return null;
    }

    @Override
    public <T> Map<T, Collection<T>> grouping(Collection<T> collection) {
        return null;
    }

    @Override
    public <T, U> Map<T, U> merge(Map<T, U> map1, Map<T, U> map2) {
        return null;
    }

    @Override
    public <T, U> Map<T, U> applyForNull(Map<T, U> map, U defaultValue) {
        return null;
    }

    @Override
    public <T> Collection<T> collectingList(Map<T, T> map1, Map<T, T> map2) {
        return null;
    }
}
