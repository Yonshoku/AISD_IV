package lab3;

import lab2.structures.List;

import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        // Get cities list
        TextReader reader = new TextReader();
        String[] data = reader.read("/cities.txt").split(";");
        int[][] result = new BellmanFloyd(data).run();

        // Print the result:
        System.out.println("Adjacency matrix: ");
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }

        for (int i = 0; i < data.length; i++) {
            System.out.print("\"" + data[i] + "\", ");
        }

    }
}
