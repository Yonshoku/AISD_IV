package lab3;

import java.io.InputStream;
import java.util.Scanner;

public class TextReader {

    public String read(String path) {

        StringBuilder text = new StringBuilder();

        InputStream is = getClass().getResourceAsStream(path);
        assert is != null;

        Scanner sc = new Scanner(is);

        while (sc.hasNext()) {
            text.append(sc.next());
        }

        return text.toString();
    }

}
