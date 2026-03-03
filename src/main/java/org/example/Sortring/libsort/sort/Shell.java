package org.example.Sortring.libsort.sort;

import edu.princeton.cs.algs4.StdDraw;

public class Shell implements Sorting {
    public static void sort(Comparable[] a) {
        int N = a.length;
        int h = 1;
        while(h < N/3) h = 3*h + 1;
        while(h >= 1) {
            for(int i = h; i < N; i++) {
                // Вставка a[i] между a[i-h], a[i-2*h], a[i-3*h]...
                for(int j = i; j >= h && Sorting.less(a[j], a[j-h]); j -= h) {
                    Sorting.exch(a, j, j-h);
                }
            }
            h = h/3;
        }
    }

    public static class ShellDrawing {
        public static double x0, y0;
        public static double k, dx;

        public static void setTracingArraysSettings(Comparable<?>[] a) {
            StdDraw.enableDoubleBuffering();
//            StdDraw.setScale(-2.0, +2.0);
            StdDraw.setCanvasSize(960, 540);
            StdDraw.setTitle("Shell sort");
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
            Shell.ShellDrawing.setTracingArraysSettings(a);

            int N = a.length;
            int h = 1;
            while(h < N/3) h = 3*h + 1;
            while(h >= 1) {
                for(int i = h; i < N; i++) {
                    // Вставка a[i] между a[i-h], a[i-2*h], a[i-3*h]...
                    for(int j = i; j >= h && Sorting.less(a[j], a[j-h]); j -= h) {
                        drawExch(a, j, j-h);
                    }
                }
                h = h/3;
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
