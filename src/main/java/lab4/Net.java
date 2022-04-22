package lab4;

import lab2.structures.LinkedList;
import lab2.structures.List;
import lab4.structures.Map;

public class Net {
    Node start = null;
    String startName = "S";
    String endName = "T";

    void create(String[][] input) {
        List<Node> nodes = new List<>();

        // Parent -> child
        Node parent;
        Node child;
        int c;

        for (int i = 0; i < input.length; i++) {
            parent = getNodeFromList(nodes, input[i][0]);
            child = getNodeFromList(nodes, input[i][1]);

            // If parent and child doesn't exist than create them
            if (parent == null) parent = new Node(input[i][0]);
            if (child == null) child = new Node(input[i][1]);

            parent.children.add(child);
            parent.capacities.put(child, Integer.parseInt(input[i][2]));

            // Find out start of the net
            if (parent.name.equals(startName)) this.start = parent;

            // Add parent and child to the list if they weren't there before
            if (getNodeFromList(nodes, parent.name) == null) nodes.add(parent);
            if (getNodeFromList(nodes, child.name) == null) nodes.add(child);
        }
    }

    Node getNodeFromList(List<Node> list, String name) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).name.equals(name)) return list.get(i);
        }

        return null;
    }

    List<Node> dfsPath(String name) {
        List<Node> list = new List<>();

        if (dfsPath(name, list, start)) {
            return list;
        } else {
            return null;
        }
    }

    boolean dfsPath(String name, List<Node> list, Node node) {
        if (node == null) return false; // Node doesn't exist
        if (list.contains(node)) return false; // Reached same node again

        list.add(node);
        if (node.name.equals(name)) return true; // Target reached

        for (int i = 0; i < node.children.size(); i++) {
            if (dfsPath(name, list, node.children.get(i))) return true;
        }

        list.pop_back();
        return false;
    }

    public int maxFlow() {
        int max_flow = 0;
        int flow = Integer.MAX_VALUE;
        List<Node> path;

        path = dfsPath(endName);
        while(path != null) {
            // Get min capacity
            for (int i = 0; i < path.size() - 1; i++) {
                flow = Math.min(flow, path.get(i).capacities.get(path.get(i + 1)));
            }

            // Add flow of min capacity
            // Add back flow of remains
            int capacity;
            int back_capacity;
            Node child;
            Node parent;

            for (int i = 0; i < path.size() - 1; i++) {
                parent = path.get(i);
                child = path.get(i + 1);
                capacity = parent.capacities.get(child) - flow;
                back_capacity = 0;

                // Delete edge
                if (capacity == 0) {
                    parent.children.remove(parent.children.indexOf(child));
                    parent.capacities.remove(child);

                } else {
                    // Set new capacity
                    parent.capacities.set(child, capacity);

                    // Create back flow of remains
                    child.children.add(parent);
                    if (child.capacities.get(parent) != null) {
                        back_capacity += child.capacities.get(parent);
                    }
                    back_capacity += flow;
                    child.capacities.set(parent, back_capacity);
                }

            }

            max_flow += flow == Integer.MAX_VALUE ? 0 : flow;
            flow = Integer.MAX_VALUE;

            path = dfsPath(endName);
        }

        return max_flow;
    }
}

class Node implements Comparable<Node> {
    String name;

    List<Node> children = new List<>();
    Map<Node, Integer> capacities = new Map<>();

    public Node(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Node node) {
        if (this == node) return 0;
        else return -1;
    }
}