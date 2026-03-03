package org.example.Search;

// The base Symbol Table
public interface ST<Key, Value> {
    void put(Key key, Value val);

    Value get(Key key);

    default void delete(Key key) {
        put(key, null);
    }

    default boolean contains(Key key) {
        return get(key) != null;
    }

    default boolean isEmpty() {
        return size() == 0;
    }

    int size();

    Iterable<Key> keys();
}
