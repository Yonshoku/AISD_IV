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
            if ((T) arr[i] == key) return true;
        }

        return false;
    }

    public void pop_back() {
        length--;
    }

    public int indexOf(T key) {
        for (int i = 0; i < length; i++) {
            if ((T) arr[i] == key) return i;
        }

        return -1;
    }

}