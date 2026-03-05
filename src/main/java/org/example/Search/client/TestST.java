package org.example.Search.client;

import edu.princeton.cs.algs4.StdOut;
import org.example.Search.BST;

public class TestST {
    public static void main(String[] args) {
        BST<Integer, Integer> bst = new BST<Integer, Integer>();
//        for (int i = 0; i < 10; i += 2) {
//            bst.put(i, i);
//        }
        bst.put(5, 5);
        bst.put(15, 15);
        bst.put(20, 20);
        bst.put(8, 8);
        bst.put(9, 9);
        bst.put(10, 10);
        bst.put(12, 12);
        bst.put(11, 11);

        for (Integer i : bst.keys()) {
            StdOut.println(i + " " + bst.get(i));
        }
        System.out.println(bst.height());
    }
}
