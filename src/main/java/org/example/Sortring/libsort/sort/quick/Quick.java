package org.example.Sortring.libsort.sort.quick;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;
import org.example.Sortring.libsort.sort.Sorting;

public class Quick implements Sorting {
    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length-1);
    }

    public static void sort(Comparable[] a, int low, int high) {
        if(high <= low) return;
        int j = partition(a, low, high);  // Разбиение
        sort(a, low, j-1);           // Сортировка левой части a[low .. j-1]
        sort(a, j+1, high);           // Сортировка правой части a[j+1 .. high]
    }

    private static int partition(Comparable[] a, int low, int high) {
        // Разбиение на a[low .. i-1], a[i], a[i+1 .. high]
        int i = low, j = high+1;    // Левый и правый индексы просмотра
        Comparable v = a[low];      // Центральный элемент
        while(true) {
            // Просмотр справа, просмотр слева, проверка на завершение и обмен
            while(Sorting.less(a[++i], v)) if(i == high) break;
            while(Sorting.less(v, a[--j])) if(j == low) break;
            if(i >= j) break;
            Sorting.exch(a, i, j);
        }
        Sorting.exch(a, low, j);    // Помещение v = a[j] на своё место
        return j;                   // так, что a[low .. j-1] <= a[j] <= a[j+1 .. high]
    }

    private static void sort3p(Comparable[] a, int low, int high) {
        // Общедоступный метод sort(), вызывающий этот метод
        if(high <= low) return;
        int lt = low, i = low+1, gt = high;
        Comparable v = a[low];
        while(i <= gt) {
            int cmp = a[i].compareTo(v);
            if (cmp < 0) Sorting.exch(a, lt++, i++);
            else if(cmp > 0) Sorting.exch(a, i, gt--);
            else i++;
        }
        // Теперь a[low .. lt-1] < v = a[lt .. gt] < a[gt+1 .. high]
        sort3p(a, low, lt-1);
        sort3p(a, gt+1, high);
    }

    public static class QuickDrawing {
        public static double x0, y0;
        public static double k, dx;

        public static void setTracingArraysSettings(Comparable<?>[] a) {
            StdDraw.enableDoubleBuffering();
//            StdDraw.setScale(-2.0, +2.0);
            StdDraw.setCanvasSize(960, 540);
            StdDraw.setTitle("Quick sort");
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

        public static void sortShow(Comparable[] a) {
            setTracingArraysSettings(a);
            StdRandom.shuffle(a);
            sortShow(a, 0, a.length-1);
        }

        public static void sortShow(Comparable[] a, int low, int high) {
            if(high <= low) return;
            int j = partitionShow(a, low, high);  // Разбиение
            sortShow(a, low, j-1);           // Сортировка левой части a[low .. j-1]
            sortShow(a, j+1, high);           // Сортировка правой части a[j+1 .. high]
        }

        private static int partitionShow(Comparable[] a, int low, int high) {
            drawArray(a, low, high);

            // Разбиение на a[low .. i-1], a[i], a[i+1 .. high]
            int i = low, j = high+1;    // Левый и правый индексы просмотра
            Comparable<Double> v = a[low];      // Центральный элемент
            while(true) {
                // Просмотр справа, просмотр слева, проверка на завершение и обмен
                while(Sorting.less(a[++i], v)) if(i == high) break;
                while(Sorting.less(v, a[--j])) if(j == low) break;
                if(i >= j) break;
                Sorting.exch(a, i, j);
                drawArray(a, i, j);
            }
            Sorting.exch(a, low, j);

            drawArray(a, low, j);
            return j;
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
