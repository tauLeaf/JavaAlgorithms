package org.example.Sortring.libsort.util;

import edu.princeton.cs.algs4.StdDraw;

import java.util.*;

public class DrawGraph {
    public static List<Integer> xPoints;
    public static List<Double> yPoints;
    public static double maxKey;
    public static double maxValue;

    public static void drawGraph(Map<Double, Integer> map) {
        StdDraw.setPenRadius(0.005);
        StdDraw.setScale(0, 2);

        maxKey = Collections.max(map.keySet());
        maxValue = Collections.max(map.values());

        StdDraw.setYscale(-maxKey*1.5, maxKey*1.5);
        StdDraw.setXscale(-maxValue*1.5, maxValue*1.5);
        StdDraw.line(0.0, -maxKey*1.2, 0.0, maxKey*1.2);
        StdDraw.line(-maxValue*1.2, 0.0, maxValue*1.2, 0.0);

        StdDraw.line(maxValue*1.165, maxKey*0.08, maxValue*1.2, 0.0);
        StdDraw.line(maxValue*1.165, -maxKey*0.08, maxValue*1.2, 0.0);
        StdDraw.text(maxValue*1.2, -maxKey*0.15, "Size");

        StdDraw.line(-maxValue*0.055, maxKey*1.13, 0.0, maxKey*1.2);
        StdDraw.line(maxValue*0.055, maxKey*1.13, 0.0, maxKey*1.2);
        StdDraw.text(-maxValue*0.13, maxKey*1.165, "Time");

        // max of all points
        drawPoint(maxValue, maxKey);

        // half of points
        drawPoint(maxValue/2, maxKey/2);

        // 1/4 of points
        drawPoint(maxValue/4, maxKey/4);
    }

    public static void drawPoint(double x, double y) {
        StdDraw.line(x, -maxKey*0.04, x, maxKey*0.04);
        StdDraw.text(x, -maxKey*0.15, Double.toString(x));

        StdDraw.line(-maxValue*0.04, y, maxValue*0.04, y);
        StdDraw.text(-maxValue*0.15, y, Double.toString(y));
    }

    public static void initXPoints() {
        xPoints = new ArrayList<>();
        for(int i = 0; i <= 20_000; i++) {
            xPoints.add(i, i+1);
        }
    }

    public static void main(String[] args) {
        //Insertion.InsertionDrawingSettings.setDrawingSettings();
        initXPoints();

        int T = 16;
        int N = 500;
        Map<Double, Integer> map = SortCompare.timeSortingArrays("Insertion", N, T);

        map.putAll(SortExperimental.allPoints);
        Set<Double> set = map.keySet();

        drawGraph(map);

        StdDraw.setPenRadius(0.01);
        for(Double key : set) {
            StdDraw.point(map.get(key), key);
        }

        StdDraw.show();
    }
}
