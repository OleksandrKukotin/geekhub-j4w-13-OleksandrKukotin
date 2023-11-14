package org.geekhub.hw4;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;


public class CollectionExpander implements Expander {

    private static void dropElementByIndex(List<?> list, int index) {
        list.remove(index);
    }

    private static void dropElementsByObject(List<?> list, Object targetObject) {
        while (list.contains(targetObject)) {
            list.remove(targetObject);
        }
    }

    @Override
    public double getMinValue(Collection<? extends Number> collection) {
        if (collection.isEmpty()) {
            return Double.MAX_VALUE;
        }
        TreeSet<Double> sortedCollection = new TreeSet<>();
        for (Number number : collection) {
            sortedCollection.add(number.doubleValue());
        }
        return sortedCollection.first();
    }

    @Override
    public double getMaxValue(Collection<? extends Number> collection) {
        if (collection.isEmpty()) {
            return Double.MIN_VALUE;
        }
        TreeSet<Double> sortedCollection = new TreeSet<>();
        for (Number number : collection) {
            sortedCollection.add(number.doubleValue());
        }
        return sortedCollection.last();
    }

    @Override
    public double getSum(Collection<? extends Number> collection) {
        if (collection.isEmpty()) {
            return 0.0;
        }
        double sum = 0;
        for (Number number : collection) {
            sum += number.doubleValue();
        }
        return sum;
    }

    @Override
    public String join(Collection<?> collection, char delimiter) {
        if (collection.isEmpty()) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        List<Object> elements = new ArrayList<>(collection);
        Iterator<Object> iterator = elements.iterator();
        while (iterator.hasNext()) {
            result.append(iterator.next().toString());
            if (iterator.hasNext()) {
                result.append(delimiter);
            }
        }
        return result.toString();
    }

    @Override
    public List<Double> reversed(List<? extends Number> collection) {
        List<Double> collectionToReverse = new ArrayList<>();
        for (Number number : collection) {
            collectionToReverse.add(number.doubleValue());
        }
        Collections.reverse(collectionToReverse);
        return collectionToReverse;
    }

    @Override
    public List<List<Object>> chunked(Collection<?> collection, int amount) {
        List<List<Object>> resultingList = new ArrayList<>();
        final Object[] collectionArray = collection.toArray();
        for (int chunk = 0; chunk < amount; chunk++) {
            ArrayList<Object> items = new ArrayList<>();
            int chunkSize = collection.size() % amount;
            for (int j = 0; j < collection.size() / amount; j++) {
                items.add(collectionArray[j * amount + chunk]);
            }
            if (chunk < chunkSize) {
                items.add(collectionArray[collection.size() - chunkSize + chunk]);
            }
            resultingList.add(new LinkedList<>(items));
        }
        return resultingList;
    }

    @Override
    public List<?> dropElements(List<?> list, Object criteria) {
        if (criteria instanceof Integer index) {
            dropElementByIndex(list, index);
        } else {
            dropElementsByObject(list, criteria);
        }
        return list;
    }

    @Override
    public <T> List<T> getClassList(T t) {
        List<T> resultingList = new ArrayList<>();
        resultingList.add(t);
        return resultingList;
    }

    @Override
    public <T> List<T> removeDuplicatesAndNull(List<T> collection) {
        Set<T> duplicatesCleaner = new LinkedHashSet<>(collection);
        duplicatesCleaner.remove(null);
        return new ArrayList<>(duplicatesCleaner);
    }

    @Override
    public <T> Map<T, Collection<T>> grouping(Collection<T> collection) {
        Map<T, Collection<T>> resultingMap = new HashMap<>();
        for (T element : collection) {
            resultingMap.computeIfAbsent(element, k -> new ArrayList<>()).add(element);
        }
        return resultingMap;
    }

    @Override
    public <T, U> Map<T, U> merge(Map<T, U> map1, Map<T, U> map2) {
        map1.putAll(map2);
        return map1;
    }

    @Override
    public <T, U> Map<T, U> applyForNull(Map<T, U> map, U defaultValue) {
        for (Map.Entry<T, U> entry : map.entrySet()) {
            U value = entry.getValue();
            if (value == null) {
                entry.setValue(defaultValue);
            }
        }
        return map;
    }

    public <T> Collection<T> collectingList(Map<T, T> map1, Map<T, T> map2) {
        List<T> resultingList = new ArrayList<>();
        resultingList.addAll(processMapKeys(map1.keySet(), map2.values()));
        resultingList.addAll(processMapKeys(map2.keySet(), map1.values()));
        return new HashSet<>(resultingList);
    }

    private <T> List<T> processMapKeys(Set<T> mapKeys, Collection<T> othersMapValues) {
        List<T> elements = new ArrayList<>();
        for (T key : mapKeys) {
            if (othersMapValues.contains(key)) {
                elements.add(key);
            }
        }
        return elements;
    }
}
