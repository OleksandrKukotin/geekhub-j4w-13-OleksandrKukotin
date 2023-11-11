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
            return Double.MIN_VALUE;
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
        if (collection.isEmpty()) {
            return 0.0;
        }
        double sum = 0;
        List<Double> arrayToSum = new ArrayList<>();
        for (Number number : collection) {
            arrayToSum.add(number.doubleValue());
        }
        for (Double number : arrayToSum) {
            sum += number;
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
        for (int i = 0; i < amount; i++) {
            ArrayList<Object> items = new ArrayList<>();
            int count = collection.size() % amount;
            for (int j = 0; j < collection.size() / amount; j++) {
                items.add(collection.toArray()[j * 3 + i]);
            }
            if (i < count) {
                items.add(collection.toArray()[collection.size() - count + i]);
            }
            resultingList.add(new LinkedList<>(items));
        }
        return resultingList;
    }

    @Override
    public List<?> dropElements(List<?> list, Object criteria) {
        List<?> resultingList = new ArrayList<>(list);
        if (criteria instanceof Integer index) {
            resultingList.remove(index.intValue());
        } else {
            while (resultingList.contains(criteria)) {
                resultingList.remove(criteria);
            }
        }
        return resultingList;
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
        while (duplicatesCleaner.contains(null)) {
            duplicatesCleaner.remove(null);
        }
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
        Collection<T> resultingList = new ArrayList<>();
        ArrayList<T> values = new ArrayList<>(map1.values());
        ArrayList<T> keys = new ArrayList<>(map2.keySet());
        for (int i = 0; i < map2.size(); i++) {
            if (values.contains(keys.get(i))) {
                resultingList.add(keys.get(i));
            }
        }
        values = new ArrayList<>(map2.values());
        keys = new ArrayList<>(map1.keySet());
        for (int i = 0; i < map1.size(); i++) {
            if (values.contains(keys.get(i))) {
                resultingList.add(keys.get(i));
            }
        }
        return new HashSet<>(resultingList);
    }
}
