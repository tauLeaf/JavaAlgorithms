package org.example.Sortring.libsort.sort;

import edu.princeton.cs.algs4.StdDraw;

// Этот класс описывает сортировку вставками
// Complete 16.12.2025 18:04; 18.12.2025
public class Insertion implements Sorting {
    public static void sort(Comparable<?>[] a) {
        for(int i = 1; i < a.length; i++) {
            for(int j = i; j > 0 && Sorting.less(a[j], a[j-1]); j--) {
                Sorting.exch(a, j, j-1);
            }
        }
    }

    // Don't work
    public static void sortEnhanced(Comparable<?>[] a) {
        //InsertionDrawingSettings.setDrawingSettings();

        double x0 = 0.1, y0 = 0.05;
        double k = 0.0;

        int tmp = 0, j = 0;
        for(int i = 1; i < a.length; i++) {
            for(j = i; j > 0 && Sorting.less(a[j], a[j-1]); j--) {
                if(Sorting.less(a[j], a[tmp])) {
                    Sorting.exch(a, j, tmp);
                }
                else {
                    Sorting.exch(a, j, j-1);
                }

                //Sorting.drawArray(a, x0, y0, k);
            }
            tmp = j;
        }
    }

    public static void sortMarker(Comparable<?>[] a) {
        //InsertionDrawingSettings.setDrawingSettings();

        double x0 = 0.1, y0 = 0.05;
        double k = 0.0;
        int minIndex = 0;
        for(int i = 0; i < a.length; i++) {
            if(Sorting.less(a[i], a[minIndex])) {
                minIndex = i;
            }

        }
//        Sorting.drawArray(a, x0, y0, k);
//        Sorting.exch(a, 0, minIndex);
//        Sorting.drawArray(a, x0, y0, k);

        for(int i = 1; i < a.length; i++) {
            for(int j = i; Sorting.less(a[j], a[j-1]); j--) {
                Sorting.exch(a, j, j-1);

                //Sorting.drawArray(a, x0, y0, k);
            }
        }
    }

    public static class InsertionDrawing {
        public static double x0, y0;
        public static double k, dx;

        public static void setTracingArraysSettings(Comparable<?>[] a) {
            StdDraw.enableDoubleBuffering();
//            StdDraw.setScale(-2.0, +2.0);
            StdDraw.setCanvasSize(960, 540);
            StdDraw.setTitle("Insertion sort");
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
            Insertion.InsertionDrawing.setTracingArraysSettings(a);

            for(int i = 1; i < a.length; i++) {
                for(int j = i; j > 0 && Sorting.less(a[j], a[j-1]); j--) {
                    drawExch(a, j, j-1);
                }
            }
        }

        public static void drawArray(Comparable<?>[] a, int i, int j) {
            for(int n = 0; n < a.length; n++) {
                if(n == i || n == j) {
                    drawRedLine(a, n);
                }
                else {
                    StdDraw.line(x0+k, y0, x0+k, y0+((double) a[n]));
                }
                k += dx;
            }
            k = 0.0;
            StdDraw.show();
            StdDraw.pause(100);
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
}
