package org.example.Search;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class BST_NoRecursion<Key extends Comparable<Key>, Value> implements ST<Key, Value> {
    private Node root;                  // корень ДБП

    private class Node {
        private Key key;                // ключ
        private Value val;              // связанное значение
        private Node left, right;       // ссылки на поддеревья
        private int N;                  // Количество узлов в поддереве с этим корнем

        public Node(Key key, Value val, int N) {
            this.key = key;
            this.val = val;
            this.N = N;
        }
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) return 0;
        else return x.N;
    }

    public int height() {
        return height(root);
    }

    private int height(Node x) {
        if (x == null) return -1;
        return Math.max(height(x.left), height(x.right)) + 1;
    }

    public Value get(Key key) {
        // Возвращает значение, связанное с ключом в поддереве с корнем x
        // возвращает null, если ключ в поддереве с корнем x отсутствует
        Node x = root;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp == 0) return x.val;
            else if (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
        }
        return null;
    }

    // С помощью ChatGPT, но хотя бы что-то сам сделал, но есть ли в этом смысл?..
    public void put(Key key, Value val) {
        // Если ключ key присутствует в поддереве с корнем x,
        // его значение изменяется на val.
        // Иначе в поддерево добавляется новый узел с ключом key и значением val.
        Node t = root, x = root;
        if (root == null) {
            root = new Node(key, val, 1);
            return;
        }
        Stack<Node> path = new Stack<>();

        while (x != null) {
            int cmp = key.compareTo(x.key);
            path.push(x);

            if (cmp < 0) {
                t = x;
                x = x.left;
            } else if (cmp > 0) {
                t = x;
                x = x.right;
            } else {
                x.val = val;
                return;
            }
        }

        int cmp = key.compareTo(t.key);
        if (cmp < 0) {
            t.left = new Node(key, val, 1);
        } else if (cmp > 0) {
            t.right = new Node(key, val, 1);
        }

        while (!path.isEmpty()) {
            t = path.pop();
            t.N = size(t.left) + size(t.right) + 1;
        }
    }

    public Key min() {
        Node x = root;
        while (x.left != null) x = x.left;
        return x.key;
    }

    public Key max() {
        Node x = root;
        while (x.right != null) x = x.right;
        return x.key;
    }

    public Key floor(Key key) {
        Node x = floor(root, key);
        if (x == null) return null;
        return x.key;
    }

    private Node floor(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);

        if (cmp == 0) return x;
        if (cmp < 0) return floor(x.left, key);
        Node t = floor(x.right, key);
        if (t != null) return t;
        else return x;
    }

    public Key select(int k) {
        return select(root, k).key;
    }

    private Node select(Node x, int k) {
        // Возвращает узел, содержащий ключ ранга k
        if (x == null) return null;
        int t = size(x.left);
        if (t > k) return select(x.left, k);
        else if (t < k) return select(x.right, k - t - 1);
        else return x;
    }

    public int rank(Key key) {
        return rank(key, root);
    }

    private int rank(Key key, Node x) {
        // Возвращает количество ключей, меньших x.key, в поддереве с корнем x
        if (x == null) return 0;
        int cmp = key.compareTo(x.key);

        if (cmp < 0) return rank(key, x.left);
        else if (cmp > 0) return 1 + size(x.left) + rank(key, x.right);
        else return size(x.left);
    }

    public void deleteMin() {
        root = deleteMin(root);
    }

    private Node deleteMin(Node x) {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void delete(Key key) {
        root = delete(root, key);
    }

    private Node delete(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);

        if (cmp < 0) x.left = delete(x.left, key);
        else if (cmp > 0) x.right = delete(x.right, key);
        else {
            if (x.right == null) return x.left;
            if (x.left == null) return x.right;
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    private void print(Node x) {
        if (x == null) return;
        print(x.left);
        StdOut.println(x.key);
        print(x.right);
    }

    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> queue = new Queue<Key>();
        keys(root, queue, lo, hi);
        return queue;
    }

    private void keys(Node x, Queue<Key> queue, Key lo, Key hi) {
        if (x == null) return;
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        if (cmplo < 0) keys(x.left, queue, lo, hi);
        if (cmplo <= 0 && cmphi >= 0) queue.enqueue(x.key);
        if (cmphi > 0) keys(x.right, queue, lo, hi);
    }
}

// Блять неправильно ёбаный рот столько времени зря сука....
//    public int height() {
//        return height(root);
//    }
//
//    private int height(Node x) {
//        int height = 0;
//        if (x == null) return 0;
//
//        if (x.left != null) {
//            height += height(x.left) + 1;
//        }
//        if (x.right != null) {
//            if (x.left != null) height -= 1;
//            height += height(x.right) + 1;
//        }
//        return height;
//    }



