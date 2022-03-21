package lab2;

public class Tree {
    Node root;

    public Tree(Node root) {
        this.root = root;

        setCodes(root);
    }

    public String encode (String s) {
        String code = "";

        for (int i = 0; i < s.length(); i++) {
            code += encodeChar(s.charAt(i));
            if (i != s.length() - 1) code += " ";
        }

        return code;
    }

    String encodeChar(Character c) {
        Node target = search(root, c);
        if (target != null) return target.code;
        return null;
    }

    public String decode(String code) {
        String charCodes[] = code.split(" ");
        String s = "";

        for (int i = 0; i < charCodes.length; i++) {
            s += decodeChar(root, charCodes[i]);
        }

        return s;
    }

    Character decodeChar(Node node, String code) {
        if (node == null) return null;

        for (int i = 0; i < code.length(); i++) {
            if (node.left != null && code.charAt(i) == '0') node = node.left;
            else if (node.right != null && code.charAt(i) == '1') node = node.right;
            else return null;
        }

        return node.c;
    }

    void print(Node node) {
        if (node != null) System.out.print("{" + node.c + ", " + node.frequency + "}");
        System.out.println();

        if (node.left != null) {
            System.out.print("{" + node.c + ", " + node.frequency + "}" + "LEFT -> ");
            print(node.left);
        }

        if (node.right != null) {
            System.out.print("{" + node.c + ", " + node.frequency + "}" + "RIGHT ->");
            print(node.right);
        }
    }

    void setCodes(Node node) {
        if (node == null) return;

        if (node.left != null) {
            node.left.code = node.code + "0";
            setCodes(node.left);
        }

        if (node.right != null) {
            node.right.code = node.code + "1";
            setCodes(node.right);
        }
    }

    Node search(Node node, Character c) {
        if (node == null) return null;

        if (Character.compare(c, node.c) == 0) return node;

        Node left = search(node.left, c);
        Node right = search(node.right, c);

        if (left != null) return left;
        return right;
    }

    public void printTable() {
        printTable(root);
    }

    void printTable(Node node) {
        if (node != null) {
            if (node.c != '♠') System.out.println(node.c + " | " + node.frequency + " | " + node.code);
            printTable(node.left);
            printTable(node.right);
        }
    }

}

class Node implements Comparable<Node> {
    public Node() {}

    public Node(char c, int frequency) {
        this.c = c;
        this.frequency = frequency;
    }

    @Override
    public int compareTo(Node that) {
        return (frequency.compareTo(that.frequency));
    }

    char c = '♠';
    Integer frequency;
    Node left;
    Node right;
    Node parent;

    String code = "";

}