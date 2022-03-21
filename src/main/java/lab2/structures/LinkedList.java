package lab2.structures;

public class LinkedList<T extends Comparable<T>> {
    Node first;
    Node last;

    boolean isEmpty() {
        return first == null;
    }

    public int get_size() {
        Node cur = first;
        int size = 0;

        while (cur != null) {
            size++;
            cur = cur.getNext();
        }

        return size;
    }

    public T at(int index) {
        Node cur = first;

        if (first == null) throw new IndexOutOfBoundsException("List empty");

        for (int i = 0; i < index; i++) {
            if (cur == null) throw new IndexOutOfBoundsException("Node with index " + index + " doesn't exist");

            cur = cur.getNext();
        }

        return (T) cur.getValue();
    }

    Node node_at(int index) {
        Node cur = first;

        for (int i = 0; i < index; i++) {
            if (cur == null) return null;

            cur = cur.getNext();
        }

        return cur;
    }

    void push_back(T value) {
        Node newNode = new Node(value);

        if (isEmpty()) {
            first = newNode;
            last = first;
        } else {
            newNode.setPrev(last);
            last.setNext(newNode);
            last = newNode;
        }
    }

    void push_front(T value) {
        Node newNode = new Node(value);

        if (isEmpty()) {
            first = newNode;
            last = first;
        } else {
            newNode.setNext(first);
            first.setPrev(newNode);
            first = newNode;
        }
    }

    public void pop_back() {
        if (isEmpty() || get_size() == 1) {
            first = null;
            last = null;
        } else {
            last = last.getPrev();
            last.setNext(null);
        }
    }

    public void pop_front() {
        if (isEmpty() || get_size() == 1) {
            first = null;
            last = null;
        } else {
            first = first.getNext();
            first.setPrev(null);
        }
    }

    void insert(int index, T value) {
        Node insertBeforeTo = node_at(index);
        Node toInsert = new Node(value);

        if (insertBeforeTo == null) {
            throw new IndexOutOfBoundsException("Node with index " + index + " doesn't exist");
        } else if (index == 0) {
            toInsert.setNext(this.first);
            first = toInsert;
        } else {
            toInsert.setPrev(insertBeforeTo.getPrev());
            toInsert.getPrev().setNext(toInsert);
            toInsert.setNext(insertBeforeTo);
            insertBeforeTo.setPrev(toInsert);
        }

    }

    // It assumes that list sorted
    public void insertByPriority(T value) {
        Node newNode = new Node(value);
        Node curNode = first;

        if (first == null) {
            push_front(value);
            return;
        }

        while (((T)newNode.getValue()).compareTo((T)curNode.getValue()) > 0) {
            if (curNode.getNext() != null) {
                curNode = curNode.getNext();
            } else {
                push_back(value);
                return;
            }
        }

        if (curNode == first) {
            push_front(value);
        } else {
            newNode.setPrev(curNode.getPrev());
            newNode.setNext(curNode);
            newNode.getPrev().setNext(newNode);
            curNode.setPrev(newNode);
        }

    }

    void remove(int index) {
        Node toRemove = node_at(index);

        if (toRemove == null) { // Node at this index doesn't exist
            return;
        } else if (index == 0) {
            pop_front();
        } else if (index == get_size() - 1) {
            pop_back();
        } else {
            toRemove.getPrev().setNext(toRemove.getNext());
            toRemove.getNext().setPrev(toRemove.getPrev());
        }
    }

    void clear() {
        first = null;
        last = null;
    }

    void set(int index, T value) {
        Node toSet = node_at(index);

        if (toSet != null) { // Index out of range
            toSet.setValue(value);
        }
    }

    void swap(int index1, int index2) {
        Node node1 = node_at(index1);
        Node node2 = node_at(index2);

        if (node1 != null && node2 != null) {
            T temp = (T) node1.getValue();
            node1.setValue(node2.getValue());
            node2.setValue(temp);
        }
    }

    public Node getFirstNode() {
        return first;
    }

    public Node getLastNode() {
        return last;
    }

    T getLast() {
        if (last != null) return (T) last.getValue();
        return null;
    }

    T getFirst() {
        if (first != null) return (T) first.getValue();
        return null;
    }

}

class Node<T> {
    T value;
    Node next = null;
    Node prev = null;

    public Node(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Node getPrev() {
        return prev;
    }

    public void setPrev(Node prev) {
        this.prev = prev;
    }
}