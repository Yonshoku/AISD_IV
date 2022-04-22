package lab4.structures;

import lab2.structures.List;

public class Map <K extends Comparable<K>, V extends Comparable<V>> {
    private final List<K> keys = new List<K>();
    private final List<V> values = new List<V>();

    public void put(K key, V value) {
        if (keys.indexOf(key) >= 0) return;

        keys.add(key);
        values.add(value);
    }

    public V get(K key) {
        for (int i = 0; i < keys.size(); i++) {
            if (key.compareTo(keys.get(i)) == 0) return values.get(i);
        }

        return null;
    }

    public void remove(K key) {
        int index = keys.indexOf(key);

        if (index >= 0) {
            keys.remove(index);
            values.remove(index);
        }
    }

    public void set(K key, V value) {
        int index = keys.indexOf(key);

        if (index >= 0) {
            keys.remove(index);
            values.remove(index);
        }

        put(key, value);
    }

    List<K> keyList() {
        return keys;
    }

    List<V> valueList() {
        return values;
    }

    public int size() {
        return keys.size();
    }

    public K getKeyByIndex(int index) { return keys.get(index); }
    public V getValueByIndex(int index) { return values.get(index); }
}
