package lab2.structures;

public class LinkedList<T extends Comparable<T>> {
    Node<T> first;
    Node<T> last;

    boolean isEmpty() {
        return first == null;
    }

    public int get_size() {
        Node<T> cur = first;
        int size = 0;

        while (cur != null) {
            size++;
            cur = cur.getNext();
        }

        return size;
    }

    public T at(int index) {
        Node<T> cur = first;

        if (first == null) throw new IndexOutOfBoundsException("List empty");

        for (int i = 0; i < index; i++) {
            if (cur == null) throw new IndexOutOfBoundsException("Node with index " + index + " doesn't exist");

            cur = cur.getNext();
        }

        return cur.getValue();
    }

    public Node<T> node_at(int index) {
        Node<T> cur = first;

        for (int i = 0; i < index; i++) {
            if (cur == null) return null;

            cur = cur.getNext();
        }

        return cur;
    }

    public void push_back(T value) {
        Node<T> newNode = new Node<>(value);

        if (isEmpty()) {
            first = newNode;
            last = first;
        } else {
            newNode.setPrev(last);
            last.setNext(newNode);
            last = newNode;
        }
    }

    public void push_front(T value) {
        Node<T> newNode = new Node<>(value);

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

    public void insert(int index, T value) {
        Node<T> insertBeforeTo = node_at(index);
        Node<T> toInsert = new Node<>(value);

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
        Node<T> newNode = new Node<>(value);
        Node<T> curNode = first;

        if (first == null) {
            push_front(value);
            return;
        }

        while (newNode.getValue().compareTo(curNode.getValue()) > 0) {
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

    public void remove(int index) {
        Node<T> toRemove = node_at(index);

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

    public void clear() {
        first = null;
        last = null;
    }

    public void set(int index, T value) {
        Node<T> toSet = node_at(index);

        if (toSet != null) { // Index out of range
            toSet.setValue(value);
        }
    }

    void swap(int index1, int index2) {
        Node<T> node1 = node_at(index1);
        Node<T> node2 = node_at(index2);

        if (node1 != null && node2 != null) {
            T temp = node1.getValue();
            node1.setValue(node2.getValue());
            node2.setValue(temp);
        }
    }

    public Node<T> getFirstNode() {
        return first;
    }

    public Node<T> getLastNode() {
        return last;
    }

    public T getLast() {
        if (last != null) return last.getValue();
        return null;
    }

    public T getFirst() {
        if (first != null) return first.getValue();
        return null;
    }

    public boolean contains(T value) {
        Node<T> node = first;

        if (first != null && first.getValue().compareTo(value) == 0)
            return true;

        while(node.getNext() != null) {
            node = node.getNext();
            if (node.getValue().compareTo(value) == 0) return true;
        }

        return false;
    }
}

class Node<T> {
    T value;
    Node<T> next = null;
    Node<T> prev = null;

    public Node(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Node<T> getPrev() {
        return prev;
    }

    public void setPrev(Node<T> prev) {
        this.prev = prev;
    }
}