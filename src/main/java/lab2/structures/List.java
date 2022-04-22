package lab2.structures;

import java.util.Arrays;

public class List <T extends Comparable<T>> {
    int length = 0;
    int capacity = 10;
    private Object[] arr = new Object[capacity];

    private void extend() {
        capacity += 10;
        arr = Arrays.copyOf(arr, capacity);
    }

    public void add(T elem) {
        if (length == capacity) extend();

        arr[length] = elem;
        length++;
    }

    public void set(int index, T elem) {
        arr[index] = elem;
    }

    public T get(int index) {
        return (T) arr[index];
    }

    public int size() {
        return length;
    }

    public boolean contains(T key) {
        for (int i = 0; i < length; i++) {
            if (((T) arr[i]).compareTo(key) == 0) return true;
        }

        return false;
    }

    public void pop_back() {
        length--;
    }

    public int indexOf(T key) {
        for (int i = 0; i < length; i++) {
            if (((T) arr[i]).compareTo(key) == 0) return i;
        }

        return -1;
    }

    public void remove(int index) {
        // Shift everything after this element left by 1
        for (int i = index; i < length - 1; i++) {
            arr[i] = arr[i + 1];
        }

        length--;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();

        str.append("[");

        for (int i = 0; i < length - 1; i++) {
            str.append(arr[i] + ", ");
        }
        str.append(arr[length - 1] + "]");

        return str.toString();
    }

}