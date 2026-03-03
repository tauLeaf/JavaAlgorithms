package org.example.anotherAlg;

import edu.princeton.cs.algs4.In;
import java.util.Arrays;
import static java.lang.Math.abs;

// Найти далёкие и ближайшие пары чисел
// Complete 14.12 19:00
public class FindNearestPair {
    public static void main(String[] args) {
        In obj = new In("libs/algs4-data/1Kints.txt");
        int[] array = new int[1000];

        for(int i = 0; obj.hasNextLine(); i++) {
            array[i] = obj.readInt();
            System.out.println(array[i]);
        }

        findNearestPair(array);
        System.out.println();
        findFarthestPair(array);
    }

    public static void findNearestPair(int[] A) {
        Arrays.sort(A);
        int N = A.length;

        int minDistance = Integer.MAX_VALUE;
        int distance;
        int i0 = 0, i1 = 0;
        for(int i = 0; i < N-1; i++) {
            distance = abs(A[i] - A[i+1]);

            if(distance < minDistance) {
                minDistance = distance;
                i0 = i;
                i1 = i+1;
            }
        }

        System.out.println("MinDistance = " + minDistance);
        System.out.println("i0 = " + A[i0] + "; i1 = " + A[i1]);
    }

    public static void findFarthestPair(int[] A) {
        int N = A.length;

        int maxValue = Integer.MIN_VALUE;
        int minValue = Integer.MAX_VALUE;
        int iMax = 0, iMin = 0;
        for(int i = 0; i < N; i++) {
            if(A[i] > maxValue) {
                maxValue = A[i];
                iMax = i;
            }
            if(A[i] < minValue) {
                minValue = A[i];
                iMin = i;
            }
        }

        System.out.println("MaxDistance = " + abs(maxValue - minValue));
        System.out.println("iMax = " + A[iMax] + "; iMin = " + A[iMin]);
    }
}
