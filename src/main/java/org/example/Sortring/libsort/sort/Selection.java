package org.example.Sortring.libsort.sort;

import edu.princeton.cs.algs4.StdDraw;

public class Selection implements Sorting {
    public static void sort(Comparable[] a) {
        for(int i = 0; i < a.length; i++) {
            int min = i;

            for(int j = i; j < a.length; j++) {
                if(Sorting.less(a[j], a[min])) min = j;
            }
            Sorting.exch(a, i, min);
        }
    }

    public static class SelectionDrawing {
        public static double x0, y0;
        public static double k, dx;

        public static void setTracingArraysSettings(Comparable<?>[] a) {
            StdDraw.enableDoubleBuffering();
//            StdDraw.setScale(-2.0, +2.0);
            StdDraw.setCanvasSize(960, 540);
            StdDraw.setTitle("Selection sort");
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
            SelectionDrawing.setTracingArraysSettings(a);

            for(int i = 0; i < a.length; i++) {
                int min = i;

                for(int j = i; j < a.length; j++) {
                    if(Sorting.less(a[j], a[min])) min = j;

                }
                drawExch(a, i, min);
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
            StdDraw.pause(150);
            StdDraw.clear();
        }

        public static void drawExch(Comparable[] a, int i, int j) {
            drawArray(a, i, j);

            Comparable t = a[i]; a[i] = a[j]; a[j] = t;
        }

        public static void drawRedLine(Comparable[] a, int i) {
            StdDraw.setPenColor(StdDraw.RED);

            StdDraw.line(x0+(dx*i), y0, x0+(dx*i), y0+((double) a[i]));

            StdDraw.setPenColor(StdDraw.BLACK);
        }
    }
}
