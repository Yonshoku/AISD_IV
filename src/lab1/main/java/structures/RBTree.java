package lab1.main.java.structures;

import java.util.ArrayList;

public class RBTree <T extends Comparable<T>> {
    Node<T> root = null;

    // Public methods
    public void print() {
        print(root);
    }

    private void print(Node node) {
        if (node != null) {
            System.out.println(node.key + " (" + node.color + ")");
            // if (node.parent != null) System.out.println("PARENT of " + node.key + " = " + node.parent.key);

            if (node.left != null) System.out.print(node.key + " left ");
            print(node.left);
            if (node.right != null) System.out.print(node.key + " right ");
            print(node.right);
        }

    }

    private void leftRotate(Node x) {
        Node<T> y = x.right;

        // Make parent of x point at y
        if (root == x) {
            root = y;
            y.parent = null;
        } else {
            if (x.parent.left == x) x.parent.left = y;
            else x.parent.right = y;

            y.parent = x.parent;
        }

        // Rotate
        x.right = y.left;
        if (x.right != null) x.right.parent = x;

        y.left = x;
        if (y.left != null) y.left.parent = y;
    }

    private void rightRotate(Node x) {
        Node<T> y = x.left;

        // Make parent of x point at y
        if (root == x) {
            root = y;
            y.parent = null;
        } else {
            if (x.parent.left == x) x.parent.left = y;
            else x.parent.right = y;

            y.parent = x.parent;
        }

        // Rotate
        x.left = y.right;
        if (x.left != null) x.left.parent = x;

        y.right = x;
        y.right.parent = y;
    }

    public void insert(T key) {
        insert(new Node(key));
    }

    private void insert(Node<T> newNode) {
        Node<T> curNode = root;

        // Root doesn't exist
        if (root == null) {
            newNode.color = Color.BLACK;
            root = newNode;

        // Plain insert
        } else {
            newNode.color = Color.RED;

            while (true) { // Go down until nil found
                if (newNode.key.compareTo(curNode.key) < 0) { // newNode smaller than curNode
                    if (curNode.left == null) {
                        curNode.left = newNode;
                        newNode.parent = curNode;
                        break;
                    }

                    curNode = curNode.left;

                } else { // newNode equal or bigger than curNode
                    if (curNode.right == null) {
                        curNode.right = newNode;
                        newNode.parent = curNode;
                        break;
                    }

                    curNode = curNode.right;
                }
            }
        }

        insertFixUp(newNode);
    }

    private void insertFixUp(Node node) {
        Node<T> p = node.parent;
        Node<T> g = getGrandParent(node);
        Node<T> u = getUncle(node);

        if (root == node) node.color = Color.BLACK;

        if (p != null && p.color == Color.RED) {
            // Case 1. Uncle U is RED and G is not root
            if (u != null && u.color == Color.RED) {
                g.color = Color.RED;
                p.color = Color.BLACK;
                u.color = Color.BLACK;

                insertFixUp(g);

            } else if (p != null && g != null) {

                // Case 2. U is BLACK and P with G in different sides => go to case 3 after
                if (g.left == p && p.right == node) leftRotate(g);
                else if (g.right == p && p.left == node) rightRotate(g);

                // Case 3. U is BLACK and P with G on the same side
                if (p.left == node && g.left == p) rightRotate(g);
                else if (p.right == node && g.right == p) leftRotate(g);

                p.color = Color.BLACK;
                g.color = Color.RED;
            }
        }
    }

    public T get(T key) {
        Node<T> node = search(root, key);

        if (node != null) return node.key;
        else return null;
    }

    private Node<T> search(Node<T> curNode, T key) {
        if (curNode == null) return null;

        if (key.compareTo(curNode.key) == 0) return curNode;
        else if (key.compareTo(curNode.key) > 0) return search(curNode.right, key);
        else return search(curNode.left, key);
    }

    public void remove (T key) {
        remove(search(root, key));
    }

    private void remove (Node<T> node) {
        // Case with RED node with one child is impossible

        // Node with 2 children
        if (node.left != null && node.right != null) {
            Node<T> swapNode = node.left;
            T tempKey;

            // Go to the max node from the left of removing node
            while (swapNode.right != null) swapNode = swapNode.right;

            // Swap swapNode and node keys
            tempKey = node.key;
            node.key = swapNode.key;
            swapNode.key = tempKey;

            remove (swapNode);
        }
        // node with 0 children
        else if (node.left == null && node.right == null) {
            if (node.color == Color.BLACK) {
                Node<T> p = node.parent;
                String side;
                if (node.parent != null && node.parent.left == node) side = "right";
                else side = "left";

                destroy(node);
                removeFixUp(p, side);
            } else {
                destroy(node);
            }
        }
        // BLACK node with 1 child
        else if (node.color == Color.BLACK) {
            if (node.left != null) {
                node.key = node.left.key;
                destroy(node.left);
            } else {
                node.key = node.right.key;
                destroy(node.right);
            }
        }

    }

    private void destroy(Node<T> node) {
        if (node.parent != null) {
            if (node.parent.left == node) node.parent.left = null;
            else node.parent.right = null;
        }
    }

    private void removeFixUp(Node<T> node, String side) {
        if (node == null) return;

        Node<T> l = node.left;
        Node<T> r = node.right;
        Node<T> ll = (node.left != null) ? node.left.left : null;
        Node<T> lr = (node.left != null) ? node.left.right : null;
        Node<T> rl = (node.right != null) ? node.right.left : null;
        Node<T> rr = (node.right != null) ? node.right.right : null;

        if (node.color == Color.RED) { // RED
            if (side.equals("left") && l != null) {// LEFT side
                if (l.color == Color.BLACK
                    && (ll == null || ll.color == Color.BLACK)
                    && (lr == null || lr.color == Color.BLACK)) {

                    node.color = Color.BLACK;
                    l.color = Color.RED;
                } else if (l.color == Color.BLACK
                    && ll != null && ll.color == Color.RED) {

                    node.color = Color.BLACK;
                    l.color = Color.RED;
                    ll.color = Color.BLACK;

                    rightRotate(node);
                }
            } else if (side.equals("right") && r != null) {// RIGHT side
                if (r.color == Color.BLACK
                    && (rr == null || rr.color == Color.BLACK)
                    && (rl == null || rl.color == Color.BLACK)) {

                    node.color = Color.BLACK;
                    r.color = Color.RED;
                } else if (r.color == Color.BLACK
                    && rr != null && rr.color == Color.RED) {

                    node.color = Color.BLACK;
                    r.color = Color.RED;
                    rr.color = Color.BLACK;

                    leftRotate(node);
                }
            }

        } else { // BLACK
             if (side.equals("left")) { // LEFT side
                if (l != null && l.color == Color.RED
                    && ll != null && ll.color == Color.BLACK
                    && lr != null && lr.color == Color.BLACK
                    && lr.left != null && lr.left.color == Color.BLACK
                    && lr.right != null && lr.right.color == Color.BLACK) {

                    lr.color = Color.RED;
                    l.color = Color.BLACK;

                    rightRotate(node);
                } else if (l != null && l.color == Color.RED
                    && ll != null && ll.color == Color.BLACK
                    && lr != null && lr.color == Color.BLACK
                    && lr.left != null && lr.left.color == Color.RED) {

                    lr.left.color = Color.BLACK;
                    leftRotate(l);
                    rightRotate(node);
                } else if (l != null && l.color == Color.BLACK
                    && lr != null && lr.color == Color.RED) {

                    lr.color = Color.BLACK;
                    leftRotate(l);
                    rightRotate(node);
                } else {
                    if(l != null) l.color = Color.RED;

                    if (node.parent != null && node.parent.left == node)
                        removeFixUp(node.parent, "right");
                    else
                        removeFixUp(node.parent, "left");
                }
            } else if (side.equals("right")){ // RIGHT side
                if (r != null && r.color == Color.RED
                    && rr != null && rr.color == Color.BLACK
                    && rl != null && rl.color == Color.BLACK
                    && rl.left != null && rl.left.color == Color.BLACK
                    && rl.right != null && rl.right.color == Color.BLACK) {

                    rl.color = Color.RED;
                    r.color = Color.BLACK;

                    leftRotate(node);
                } else if (r != null && r.color == Color.RED
                    && rr != null && rr.color == Color.BLACK
                    && rl != null && rl.color == Color.BLACK
                    && rl.left != null && rl.left.color == Color.RED) {

                    rl.left.color = Color.BLACK;
                    rightRotate(r);
                    leftRotate(node);
                } else if (r != null && r.color == Color.BLACK
                    && rl != null && rl.color == Color.RED) {

                    rl.color = Color.BLACK;
                    rightRotate(r);
                    leftRotate(node);
                } else {
                    if (r != null) r.color = Color.RED;

                    if (node.parent != null && node.parent.left == node)
                        removeFixUp(node.parent, "right");
                    else
                        removeFixUp(node.parent, "left");
                }
            }
        }

        root.color = Color.BLACK;
    }

    private Node<T> getUncle(Node<T> node) {
        if (node.parent != null) { // Parent exist
            if (node.parent.parent != null) { // Grandparent exist
                if (node.parent.parent.left == node.parent) return node.parent.parent.right; // Uncle is right if parent is left
                else return node.parent.parent.left;
            }
        }

        return null;
    }

    private Node<T> getGrandParent(Node<T> node) {
        if (node.parent != null) return node.parent.parent;
        return null;
    }

    /*
    private void countBlackHeight(Node<T> curNode) {
        if (curNode == root) {
            curNode.blackHeight = 1;
        } else if (curNode.color == Color.BLACK) {
            curNode.blackHeight = curNode.parent.blackHeight + 1;
        } else curNode.blackHeight = curNode.parent.blackHeight;

        if (curNode.left != null) countBlackHeight(curNode.left);
        if (curNode.right != null) countBlackHeight(curNode.right);
    }

    private boolean isHeightValid(Node<T> curNode) {
        return true;
    }
    */

    public ArrayList<T> getKeysList() {
        ArrayList<T> list = new ArrayList<>();

        return getKeysList(list, root);
    }

    private ArrayList<T> getKeysList(ArrayList<T> list, Node<T> curNode) {
        if (curNode != null) {
            list.add(curNode.key);

            getKeysList(list, curNode.left);
            getKeysList(list, curNode.right);
        }

        return list;
    }

    public void clear() {
        root = null;
    }

}


class Node<T>{
    public Node() {};
    public Node(T key) {this.key = key;}

    Color color;
    T key;
    Node<T> left = null;
    Node<T> right = null;
    Node<T> parent;
    int blackHeight;
}

enum Color {
    RED,
    BLACK,
}