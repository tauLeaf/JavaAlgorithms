package org.example.Search;

import java.util.Iterator;

public class SequentialSearchST<Key, Value> implements Iterable<Key>, ST<Key, Value> {
    private Node first;

    private class Node {
        Key key;
        Value val;
        Node next;

        public Node(Key key, Value val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    public Value get(Key key) {
        for (Node x = first; x != null; x = x.next)
            if (key.equals(x.key))
                return x.val;       // Успешный поиск
        return null;        // неудача
    }

    public void put(Key key, Value val) {
        for (Node x = first; x != null; x = x.next)
            if (key.equals(x.key)) {
                x.val = val;    // Найден: изменяется значение
                return;
            }
        first = new Node(key, val, first);  // Не найден: добавляется новый узел
    }

    public int size() {
        int count = 0;
        for (Node x = first; x != null; x = x.next)
            count++;
        return count;
    }

    @Override
    public Iterable<Key> keys() {
        return null;
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public Iterator<Key> iterator() {
        return new RangeSequentialSearchST();
    }

    private class RangeSequentialSearchST implements Iterator<Key> {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            if (currentIndex < size()) return true;
            else return false;
        }

        @Override
        public Key next() {
            return (Key) first.next.key;
        }
    }

    // Обрабатываются не все случаи
    public void delete(Key key) {
        for (Node x = first; x != null; x = x.next) {
            if (x.next.key.equals(key)) {
                x.next = x.next.next;
            }
        }
    }
}
