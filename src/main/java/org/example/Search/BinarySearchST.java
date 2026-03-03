package org.example.Search;

import java.util.Iterator;

public class BinarySearchST<Key extends Comparable<Key>, Value> implements Iterable<Key>, ST<Key, Value> {
    private Key[] keys;
    private Value[] values;
    private int N;
    private int i;

    public BinarySearchST(int capacity) {
        keys = (Key[]) new Comparable[capacity];
        values = (Value[]) new Object[capacity];
    }

    public BinarySearchST(Key[] keys, Value[] values) {

    }

    public void put(Key key, Value val) {
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) {
            values[i] = val;
            return;
        }
        for (int j = N; j > i; j--) {
            keys[j] = keys[j - 1];
            values[j] = values[j - 1];
        }
        keys[i] = key;
        values[i] = val;
        N++;
    }

    public Value get(Key key) {
        if (isEmpty()) return null;
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) return values[i];
        else return null;
    }

    public int rank(Key key, int lo, int hi) {
        if (hi < lo) return lo;

        int mid = lo + (hi - lo) / 2;
        int cmp = key.compareTo(keys[mid]);

        if (cmp < 0)
            return rank(key, lo, mid - 1);
        else if (cmp > 0)
            return rank(key, mid + 1, hi);
        else return mid;
    }

    public int rank(Key key) {
        int lo = 0, hi = N - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(keys[mid]);

            if (cmp < 0) hi = mid - 1;
            else if (cmp > 0) lo = mid + 1;
            else return mid;
        }
        return lo;
    }

    public void delete(Key key) {
        int i = rank(key);
        for (int j = i; j < N; j++) {
            keys[j] = keys[j + 1];
            values[j] = values[j + 1];
        }
    }

    public Key min() {
        return keys[0];
    }

    public Key max() {
        return keys[N - 1];
    }

    public Key select(int k) {
        return keys[k];
    }

    public Key ceiling(Key key) {
        int k = rank(key);
        return keys[k];
    }

    public Key floor(Key key) {
        int k = rank(key);
        return keys[k];
    }

    public boolean contains(Key key) {
        for (int j = 0; j < N; j++) {
            if (keys[j].equals(key)) {
                return true;
            }
        }
        return false;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public Iterable<Key> keys() {
        return null;
    }

    public Iterator<Key> iterator() {
        return new BinarySearchST.RangeArrayST();
    }

    private class RangeArrayST implements Iterator<Key> {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            if (currentIndex < size()) return true;
            else return false;
        }

        @Override
        public Key next() {
            return (Key) keys[currentIndex];
        }
    }
}
