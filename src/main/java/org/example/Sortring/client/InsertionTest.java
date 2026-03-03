package org.example.Sortring.client;

import edu.princeton.cs.algs4.StdRandom;
import org.example.Sortring.libsort.sort.Insertion;

public class InsertionTest {
    public static void main(String[] args) {
        int N = 100;
        Double[] a = new Double[N];

        for(int i = 0; i < N; i++) {
            a[i] = StdRandom.uniformDouble();
        }

//        for(int i = 0; i < N; i++) {
//            a[i] = (double) i;
//        }
//        Sorting.exch(a, 0, 49);

        Insertion.InsertionDrawing.sortShow(a);
    }
}
