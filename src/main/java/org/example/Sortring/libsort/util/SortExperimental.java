package org.example.Sortring.libsort.util;

import java.util.HashMap;
import java.util.Map;

public class SortExperimental implements Runnable {
    public Thread obj;
    public static Map<Double, Integer> allPoints = new HashMap<>();
    public int N, T;

    public SortExperimental(String name, int N, int T) {
        obj = new Thread(this, name);
        this.N = N;
        this.T = T;
    }
    // Abstract fabric method
    public static SortExperimental createAndStart(String name, int N, int T) {
        SortExperimental sort = new SortExperimental(name, N, T);

        sort.obj.start();
        return sort;
    }

    public void run() {
        Map<Double, Integer> map = SortCompare.timeSortingArrays("Insertion", N, T);
        allPoints.putAll(map);
    }
}
