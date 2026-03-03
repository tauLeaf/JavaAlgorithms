package org.example.Sortring.client;

import edu.princeton.cs.algs4.StdRandom;
import org.example.Sortring.libsort.sort.quick.QuickSignalKeys;

public class QuickTest {
    public static void main(String[] args) {
        int N = 100;
        Double[] a = new Double[N];

        for(int i = 0; i < N; i++) {
            a[i] = StdRandom.uniformDouble();
        }

        QuickSignalKeys.QuickDrawing.sortShow(a);
    }
}
