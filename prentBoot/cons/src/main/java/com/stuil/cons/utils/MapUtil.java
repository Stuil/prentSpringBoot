package com.stuil.cons.utils;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

class MapKeyComparator implements Comparator<String> {
    @Override
    public int compare(String str1, String str2) {
        return str1.compareTo(str2);
    }
}