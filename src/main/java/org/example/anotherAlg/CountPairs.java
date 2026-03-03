package org.example.anotherAlg;

import edu.princeton.cs.algs4.In;

import java.util.Arrays;

//Complete 14.12.2025
public class CountPairs {
    public static void main(String[] args) {
        In obj = new In("libs/algs4-data/1Kints.txt");
        int[] array = new int[1000];

        for(int i = 0; obj.hasNextLine(); i++) {
            array[i] = obj.readInt();
            System.out.println(array[i]);
        }

        System.out.println(countTwoPairs(array));
        System.out.println(countThreePairs(array));
    }

    public static int countTwoPairs(int[] A) {
        Arrays.sort(A);
        int N = A.length;

        int cnt = 0, sum;
        int i = 0, j = N-1;
        while(i < j) {
            sum = A[i] + A[j];

            if(sum == 0) {
                cnt++;
                i++;
                j--;
            }
            else if(sum < 0) {
                i++;
            }
            else if(sum > 0) {
                j--;
            }
        }
        return cnt;
    }

    public static int countThreePairs(int[] A) {
        Arrays.sort(A);
        int N = A.length;

        int cnt = 0, sum;
        int k = N-1;
        for(int i = 0; i < k; i++) {
            for(int j = i + 1; j < k; j++) {
                sum = A[i] + A[j] + A[k];

                if(sum == 0) {
                    cnt++;
                    j++;
                    k--;
                }
                else if(sum < 0) {
                    j++;
                }
                else if(sum > 0) {
                    k--;
                }
            }

        }
        return cnt;
    }
// Вот правильно решение
//    public static int countThreePairs(int[] A) {
//        Arrays.sort(A);
//        int N = A.length;
//        int cnt = 0;
//
//        for (int i = 0; i < N; i++) {
//            int j = i + 1;
//            int k = N - 1;
//
//            while (j < k) {
//                int sum = A[i] + A[j] + A[k];
//
//                if (sum == 0) {
//                    cnt++;
//                    j++;
//                    k--;
//                }
//                else if (sum < 0) {
//                    j++;
//                }
//                else {
//                    k--;
//                }
//            }
//        }
//        return cnt;
//    }

}