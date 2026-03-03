package org.example.Sortring.libsort.sort;

import edu.princeton.cs.algs4.StdDraw;

public class Merge {
    public static Comparable<?>[] aux;

    public static void sort(Comparable<?>[] a) {
        aux = new Comparable<?>[a.length];
        sort(a, 0, a.length-1);
    }

    public static void sort(Comparable<?>[] a, int low, int high) {
        if(high <= low) return;
        int mid = (low + high) / 2;
        sort(a, low, mid);  // Сортировка левой половины
        sort(a, mid+1, high);   // Сортировка правой половины
        merge(a, low, mid, high);   // Слияние результатов
    }

    // Слияние a[low...mid] и a[mid+1...high]
    public static void merge(Comparable<?>[] a, int low, int mid, int high) {
        int i = low, j = mid + 1;
        // auxiliary - вспомогательный
        for(int k = low; k <= high; k++) {
            aux[k] = a[k];
        }
        for(int k = low; k <= high; k++) {
            if(i > mid) a[k] = aux[j++];
            else if(j > high) a[k] = aux[i++];
            else if(Sorting.less(aux[j], aux[i])) a[k] = aux[j++];
            else a[k] = aux[i++];
        }
    }

    public static class MergeDrawing {
        public static double x0, y0;
        public static double k, dx;

        public static void setTracingArraysSettings(Comparable<?>[] a) {
            StdDraw.enableDoubleBuffering();
//            StdDraw.setScale(-2.0, +2.0);
            StdDraw.setCanvasSize(960, 540);
            StdDraw.setTitle("Merge sort");
            StdDraw.setPenRadius(0.01);
            StdDraw.setPenColor(StdDraw.BLACK);

            double maxValue = 0.0;
            for(Comparable i : a) {
                if((double) i > maxValue) maxValue = (double) i;
            }

            StdDraw.setYscale(0, maxValue*2);

            x0 = 0.1;  y0 = maxValue*2*0.02;
            k = 0.0;  dx = 0.008;
        }

        public static void sortShow(Comparable<?>[] a) {
            setTracingArraysSettings(a);
            aux = new Comparable<?>[a.length];
            sortShow(a, 0, a.length-1);
        }

        public static void sortShow(Comparable<?>[] a, int low, int high) {
            if(high <= low) return;
            int mid = (low + high) / 2;
            sortShow(a, low, mid);  // Сортировка левой половины
            sortShow(a, mid+1, high);   // Сортировка правой половины
            mergeShow(a, low, mid, high);   // Слияние результатов
        }

        // Слияние a[low...mid] и a[mid+1...high]
        public static void mergeShow(Comparable<?>[] a, int low, int mid, int high) {
            int i = low, j = mid + 1;
            // auxiliary - вспомогательный
            for(int k = low; k <= high; k++) {
                aux[k] = a[k];
            }
            for(int k = low; k <= high; k++) {
                drawArray(a, low, high);
                if(i > mid) a[k] = aux[j++];
                else if(j > high) a[k] = aux[i++];
                else if(Sorting.less(aux[j], aux[i])) a[k] = aux[j++];
                else a[k] = aux[i++];
            }
        }

        public static void drawArray(Comparable<?>[] a, int i, int j) {
            for(int n = 0; n < a.length; n++) {
                StdDraw.line(x0+k, y0, x0+k, y0+((double) a[n]));
                k += dx;
            }
            k = 0.0;
            StdDraw.show();
            StdDraw.pause(200);
            StdDraw.clear();
        }

        public static void drawExch(Comparable[] a, int i, int j) {
            drawArray(a, i, j);

            Comparable t = a[i]; a[i] = a[j]; a[j] = t;

            drawArray(a, i, j);
        }

        public static void drawRedLine(Comparable[] a, int i) {
            StdDraw.setPenColor(StdDraw.RED);

            StdDraw.line(x0+(dx*i), y0, x0+(dx*i), y0+((double) a[i]));

            StdDraw.setPenColor(StdDraw.BLACK);
        }
    }

    public static void sortAscending(Comparable<?>[] a) {
        int N = a.length;
        aux = new Comparable<?>[N];
        for(int sz = 1; sz < N; sz = sz+sz) {               // sz - размер подмассива
            for(int low = 0; low < N-sz; low += sz+sz) {    // low - индекс в подмассиве
                merge(a, low, low+sz-1, Math.min(low+sz+sz-1, N-1));
            }
        }
    }
}
