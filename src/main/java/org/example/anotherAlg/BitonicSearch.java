package org.example.anotherAlg;

public class BitonicSearch {
    public static void main(String[] args) {
        int[] A = {1, 2, 3, 4, 5, 6, 7, 8, 15, 14, 13, 12, 11};
        System.out.println("Mid = " + findMidInBitonicArray(A));
    }

    public static int findMidInBitonicArray(int[] A) {
        int N = A.length;

        int low = 0;
        int high = N - 1;
        int mid = 0, item;
        int index = 0;
        while (low <= high) {
            mid = (low + high) / 2;
            item = A[mid];

            if (A[mid] < A[mid + 1] && A[mid] > A[mid - 1]) {
                low = mid;
            } else if (A[mid] > A[mid + 1] && A[mid] < A[mid - 1]) {
                high = mid;
            } else if (A[mid] > A[mid - 1] && A[mid + 1] > A[mid + 2]) {
                return mid - 1;
            } else if (A[mid - 1] > A[mid - 2] && A[mid] > A[mid + 1]) {
                return mid;
            }
        }

        return index;
    }
}
