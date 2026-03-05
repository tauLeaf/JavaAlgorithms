package org.example.Search.baseST;

import org.example.Search.ST;

import java.util.Iterator;

public class ArrayST<Key, Value> implements Iterable<Key>, ST<Key, Value> {
    int size;
    public Key[] keys;
    public Value[] values;

    public ArrayST(int N) {
//        keys = new Key[N*2];
        keys = (Key[]) new Comparable[N * 2];
        values = (Value[]) new Object[N * 2];
    }

    public ArrayST(Key[] keys, Value[] values) {
        this.keys = keys;
        this.values = values;
        this.size = keys.length;
    }

    public void put(Key key, Value val) {
//        if(keys())    <-- тут доделать проверку, но возможно потом...
        for (int i = 0; i < size; i++) {
            if (keys[i].equals(key)) {
                values[i] = val;
                return;
            }
        }

        keys[++size] = key;
        values[size] = val;
    }

    public Value get(Key key) {
        for (int j = 0; j < size; j++) {
//            Objects.equals(keys[j], key);
            if (keys[j].equals(key)) {
                rightShift(j);
                return values[0];
            }
        }
        return null;
    }

    // Wow! С первого раза правильно написал
    public void rightShift(int j) {
        Key keyTmp = keys[j];
        Value valTmp = values[j];

        for (int i = j; i > 0; i--) {
            keys[i] = keys[i - 1];
            values[i] = values[i - 1];
        }
        keys[0] = keyTmp;
        values[0] = valTmp;
    }

    public void delete(Key key) {
        int i = -1;
        for (int j = 0; j < size; j++) {
            if (keys[j].equals(key)) {
                i = j;
                break;
            }
        }
        if (i == -1) return;
        for (int j = i; j < size; j++) {
            keys[j] = keys[j + 1];
            values[j] = values[j + 1];
        }
    }

    public boolean contains(Key key) {
        for (int j = 0; j < size; j++) {
            if (keys[j].equals(key)) {
                return true;
            }
        }
        return false;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int size() {
        return this.size;
    }

    public Iterable<Key> keys() {
        return null;
    }

    public Iterator<Key> iterator() {
        return new ArrayST.RangeArrayST();
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
