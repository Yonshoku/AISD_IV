package lab3;

import lab2.structures.List;

import java.util.Arrays;

public class BellmanFloyd {

    String[] data;
    int[][] edges;
    List<String> vertices = new List<String>();

    public BellmanFloyd(String[] data) {
        this.data = data;
    }

    public int[][] run() {
        // Parse data into graph edges and vertices
        getVertices();
        getEdges();

        // Init graph d
        int[][] d = new int[vertices.size()][vertices.size()];

        for (int i = 0; i < d.length; i++) {
            for (int j = 0; j < d[i].length; j++) {
                if (i == j) d[i][j] = 0;
                else d[i][j] = 10000;
            }
        }

        // Do algorithm for every city
        for (int i = 0; i < d.length; i++) {

            // Bellman-Floyd
            for (int j = 0; j < vertices.size() - 1; j++) {
                for (int k = 0; k < edges.length; k++) {
                    int from = d[i][edges[k][0]];
                    int to = d[i][edges[k][1]];
                    int w = edges[k][2];

                    if (from + w < to) d[i][edges[k][1]] = from + w;
                }
            }

        }

        return d;
    }

    private void getVertices() {

        for (int i = 0; i < data.length; i++) {
            // Parse cities as vertices
            if (i % 4 == 0 || i % 4 == 1) {
                if (!vertices.contains(data[i])) vertices.add(data[i]);
            }
        }

    }

    private void getEdges() {
        edges = new int[data.length / 2][3];
        int edgesNum = 0;

        for (int i = 0; i < data.length; i++) {

            // Flight forward
            if (i % 4 == 2) {

                // If flight cost is unparseable => it's N/A => edge doesn't exist
                try {
                    edges[edgesNum][2] = Integer.parseInt(data[i]);
                    edges[edgesNum][0] = vertices.indexOf(data[i - 2]);
                    edges[edgesNum][1] = vertices.indexOf(data[i - 1]);
                    edgesNum++;

                } catch (NumberFormatException e) {}

            } else if (i % 4 == 3) {

                try {
                    edges[edgesNum][2] = Integer.parseInt(data[i]);
                    edges[edgesNum][0] = vertices.indexOf(data[i - 2]);
                    edges[edgesNum][1] = vertices.indexOf(data[i - 3]);
                    edgesNum++;
                } catch (NumberFormatException e) {}

            }
        }

        // Remove empty edges from its array
        edges = Arrays.copyOfRange(edges, 0, edgesNum);
    }

}
