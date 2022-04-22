package lab4;

import lab3.TextReader;

public class Main {

    public static void main(String[] args) {
        TextReader reader = new TextReader();
        String str = reader.read("/net2.txt");

        Net net = new Net();
        net.create(parse(str));
        System.out.println("Max flow is: " + net.maxFlow());
    }

    public static String[][] parse(String s) {
        String[] str = s.split("");
        String[][] input = new String[str.length / 3][3];

        for (int i = 0; i < str.length; i++) {
            if (i % 3 == 0) input[i / 3][0] = str[i];
            if (i % 3 == 1) input[i / 3][1] = str[i];
            if (i % 3 == 2) input[i / 3][2] = str[i];
        }

        return input;
    }

}
