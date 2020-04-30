package ru.mai;

import java.util.Comparator;
import java.util.Map;

public class MapComparator implements Comparator<Map.Entry<String, Integer>> {
    @Override
    public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
        if (o1.getValue() - o2.getValue() != 0) {
            return -(o1.getValue() - o2.getValue());
        } else {
            return o1.getKey().compareTo(o2.getKey());
        }
    }
}
