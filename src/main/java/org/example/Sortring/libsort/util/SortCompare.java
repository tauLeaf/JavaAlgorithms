package org.example.Sortring.libsort.util;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;
import org.example.Sortring.libsort.sort.Insertion;
import org.example.Sortring.libsort.sort.Selection;
import org.example.Sortring.libsort.sort.Shell;
import org.example.Sortring.libsort.sort.quick.Quick;
import org.example.Sortring.libsort.sort.quick.QuickMedianOfThree;
import org.example.Sortring.libsort.sort.quick.QuickSignalKeys;

import java.util.*;

public class SortCompare {
    public static double time(String alg, Double[] a) {
        Stopwatch timer = new Stopwatch();

        if(alg.equals("Insertion")) Insertion.sort(a);
        else if(alg.equals("InsertionShow")) Insertion.InsertionDrawing.sortShow(a);
        else if(alg.equals("Selection")) Selection.sort(a);
        else if(alg.equals("SelectionShow")) Selection.SelectionDrawing.sortShow(a);
        else if(alg.equals("Shell")) Shell.sort(a);
        else if(alg.equals("ShellShow")) Shell.ShellDrawing.sortShow(a);
        //Merge
        //MergeShow
        else if(alg.equals("Quick")) Quick.sort(a);
        else if(alg.equals("QuickShow")) Quick.QuickDrawing.sortShow(a);
        else if(alg.equals("QuickSignalKeys")) QuickSignalKeys.sort(a);
        //else if(alg.equals("QuickSignalKeysShow")) QuickSignalKeys.QuickDrawing.sortShow(a);
        else if(alg.equals("QuickMedianOfThree")) QuickMedianOfThree.sort(a);
        else System.out.println("Algorithm is not found");

        return timer.elapsedTime();
    }

    public static double timeRandomInput(String alg, int N, int T) {
        double total = 0.0;
        Double[] a = new Double[N];

        for(int t = 0; t < T; t++) {
            for(int i = 0; i < N; i++) {
                a[i] = StdRandom.uniformDouble();
            }
            total += time(alg, a);
        }
        return total;
    }

    public static double timeSortingArray(String alg, int N) {
        Double[] a = new Double[N];

        for(int i = 0; i < N; i++) {
            a[i] = StdRandom.uniformDouble();
        }

        return time(alg, a);
    }

    public static Map<Double, Integer> timeSortingArrays(String alg, int N, int T) {
        Map<Double, Integer> map = new HashMap<>();

        for(int t = 0; t < T; t++) {
            map.put(timeSortingArray(alg, N), N);
            N += 500;
        }
        return map;
    }

    public static void timeSortingSameArrays(String alg1, String alg2) {
        int N = 30000;
        int T = 10;
        Double[] a1 = new Double[N];
        Double[] a2 = new Double[N];

        double t1 = 0.0;
        double t2 = 0.0;

        for(int t = 0; t < T; t++) {
            for(int i = 0; i < N; i++) {
                a1[i] = StdRandom.uniformDouble();
            }
            a2 = a1.clone();
            t1 += time(alg1, a1);
            t2 += time(alg2, a2);
        }

        System.out.println("<--Из " + T + " массивов типа Doubles по " + N + " элементов" +
                " среднее время выполнения сортировки составляет--Ю");
        System.out.println(alg1 + ": t = " + t1/T + " секунд");
        System.out.println(alg2 + ": t = " + t2/T + " секунд");
        System.out.println();
        if(t1 < t2) {
            System.out.println(alg1 + " в " + t2/t1 + " быстрее, чем " + alg2);
        }
        else {
            System.out.println(alg2 + " в " + t1/t2 + " быстрее, чем " + alg1);
        }
    }

    public static void main(String[] args) {
//        String alg1 = "Selection";
//        String alg2 = "Insertion";
//        int N = 10000;
//        int T = 100;
        String[] algs = {"Selection", "Insertion", "Shell", "Merge", "Quick", "QuickSignalKeys", "QuickMedianOfThree"};
        String alg1 = algs[0];
        String alg2 = algs[1];
        timeSortingSameArrays(alg1, alg2);
//        int N = 1000000;
//        int T = 100;
//        double t1 = timeRandomInput(alg1, N, T);
//        double t2 = timeRandomInput(alg2, N, T);
//
//        System.out.println("<--Для случайных " + T + " массивов типа Doubles по " + N + " элементов-->");
//        System.out.println(alg1 + ": t = " + t1 + " секунд");
//        System.out.println(alg2 + ": t = " + t2 + " секунд");
//        System.out.println();
//        if(t1 < t2) {
//            System.out.println(alg1 + " в " + t2/t1 + " быстрее, чем " + alg2);
//        }
//        else {
//            System.out.println(alg2 + " в " + t1/t2 + " быстрее, чем " + alg1);
//        }

        // При N = 10000; T = 1000; в среднем QuickSignalKeys в 1.1 быстрее, чем Quick
        // Quick в 1.0078055877784557 быстрее, чем Quick
    }
}
