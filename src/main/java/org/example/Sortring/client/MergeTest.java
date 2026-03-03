package org.example.Sortring.client;

import edu.princeton.cs.algs4.StdRandom;
import org.example.Sortring.libsort.sort.Merge;

public class MergeTest {
    public static void main(String[] args) {
        int N = 100;
        Double[] a = new Double[N];

        for(int i = 0; i < N; i++) {
            a[i] = StdRandom.uniformDouble();
        }

        Merge.MergeDrawing.sortShow(a);

        for(Double d : a) {
            System.out.println(d);
        }

    }
}
