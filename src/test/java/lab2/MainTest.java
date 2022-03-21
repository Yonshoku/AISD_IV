package lab2;

import lab1.structures.Map;
import static org.junit.Assert.*;
import org.junit.Test;

import java.util.Optional;

public class MainTest {
    String s1 = "Hello world!";
    String s2 = "abcdefghijklmnopq";
    String s3 = "Погода в Санкт-Петербурге";

    String encoded1 = "100 000 111 111 110 1011 011 110 010 111 001 1010";
    String encoded2 = "1110 1100 1101 0010 0011 0000 0001 0110 0111 0100 0101 1010 1011 1000 1001 11110 11111";
    String encoded3 = "1010 000 011 000 11100 001 1011 1000 1011 10010 001 11001 11110 010 10011 1010 11111 010 11111 1101 11101 11000 1101 011 11111";

    @Test
    public void getFrequenciesTest() {
        assertEquals(Optional.ofNullable(Main.getFrequencies(s1).find('o')), Optional.ofNullable(2));
        assertEquals(Optional.ofNullable(Main.getFrequencies(s1).find('e')), Optional.ofNullable(1));

        assertEquals(Optional.ofNullable(Main.getFrequencies(s2).find('h')), Optional.ofNullable(1));
        assertEquals(Optional.ofNullable(Main.getFrequencies(s2).find('o')), Optional.ofNullable(1));

        assertEquals(Optional.ofNullable(Main.getFrequencies(s3).find('о')), Optional.ofNullable(2));
        assertEquals(Optional.ofNullable(Main.getFrequencies(s3).find(' ')), Optional.ofNullable(2));
    }

    @Test
    public void encodeTest() {
        assertEquals(Main.getHoffmanTree(Main.getFrequencies(s1)).encode(s1), encoded1);
        assertEquals(Main.getHoffmanTree(Main.getFrequencies(s2)).encode(s2), encoded2);
        assertEquals(Main.getHoffmanTree(Main.getFrequencies(s3)).encode(s3), encoded3);
    }

    @Test
    public void decodeTest() {
        assertEquals(Main.getHoffmanTree(Main.getFrequencies(s1)).decode(encoded1), s1);
        assertEquals(Main.getHoffmanTree(Main.getFrequencies(s2)).decode(encoded2), s2);
        assertEquals(Main.getHoffmanTree(Main.getFrequencies(s3)).decode(encoded3), s3);
    }
}
