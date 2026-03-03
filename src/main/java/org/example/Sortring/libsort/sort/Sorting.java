package org.example.Sortring.libsort.sort;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;

// Это шаблон для всех классов сортировок
// Complete 16.12.2025 3:34:05
public interface Sorting {
    static void sort(Comparable[] a) {}

    static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i]; a[i] = a[j]; a[j] = t;
    }

    static void show(Comparable[] a) {
        for(int i = 0; i < a.length; i++) {
            System.out.println(a[i] + " ");
        }
    }

    static boolean isSorted(Comparable[] a) {
        for(int i = 1; i < a.length; i++) {
            if(less(a[i], a[i-1])) return false;
        }
        return true;
    }

    static void main(String[] args) {
        String[] a = In.readStrings();
        sort(a);
        assert isSorted(a);
        show(a);
    }
}
