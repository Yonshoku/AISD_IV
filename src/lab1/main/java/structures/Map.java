package lab1.main.java.structures;

import java.util.ArrayList;

public class Map <K extends Comparable<K>, V>{

    RBTree<Entry<K, V>> tree = new RBTree<>();

    public void insert(K key, V value) {
        // Avoid of repeating
        if (tree.get(new Entry<K, V>(key, value)) == null)
            tree.insert(new Entry<K, V>(key, value));
    }

    public void remove(K key) {
        tree.remove(new Entry<K, V>(key, null));
    }

    public V find (K key) {
        return tree.get(new Entry<K, V>(key, null)).getValue();
    }

    public void clear() {
        tree.clear();
    }

    public ArrayList<K> getKeys() {
        ArrayList<K> keys = new ArrayList<>();

        for(Entry<K, V> entry : tree.getKeysList()) {
            keys.add(entry.getKey());
        }

        return keys;
    }

    public ArrayList<V> getValues() {
        ArrayList<V> values = new ArrayList<>();

        for (Entry<K, V> entry : tree.getKeysList()) {
            values.add(entry.getValue());
        }

        return values;
    }

    public void print() {
        String str = "[";

        for (Entry<K, V> entry : tree.getKeysList()) {
            str += "{" + entry.getKey() + ", " + entry.getValue() + "}, ";
        }

        if (str.length() >= 3) str = str.substring(0, str.length() - 2);
        str += "]";
        System.out.println(str);
    }

    class Entry <K extends Comparable<K>, V> implements Comparable <Entry <K, V>> {
        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        private K key;
        private V value;

        @Override
        public int compareTo(Entry<K, V> entry) {
            return key.compareTo(entry.key);
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }
}

