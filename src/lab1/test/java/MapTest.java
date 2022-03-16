package lab1.test.java;

import lab1.main.java.structures.Map;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class MapTest {
    Map<Integer, String> numbers = new Map<>();

    ArrayList<Integer> keysInsert = new ArrayList<>(Arrays.asList(2, 1, 4, 3, 5));
    ArrayList<String> valuesInsert = new ArrayList<>(Arrays.asList("two", "one", "four", "three", "five"));

    ArrayList<Integer> keysRemove1 = new ArrayList<>(Arrays.asList(2, 1, 3, 5));
    ArrayList<String> valuesRemove1 = new ArrayList<>(Arrays.asList("two", "one", "three", "five"));
    ArrayList<Integer> keysRemove2 = new ArrayList<>(Arrays.asList(1, 3, 5));
    ArrayList<String> valuesRemove2 = new ArrayList<>(Arrays.asList("one", "three", "five"));


    @Before
    public void setup() {
        numbers.insert(1, "one");
        numbers.insert(2, "two");
        numbers.insert(3, "three");
        numbers.insert(4, "four");
        numbers.insert(5, "five");
    }

    @Test
    public void insertTest() {
        Assert.assertTrue(numbers.getKeys().equals(keysInsert));
        Assert.assertTrue(numbers.getValues().equals(valuesInsert));
    }

    @Test
    public void removeTest() {
        numbers.remove(4);
        Assert.assertTrue(numbers.getKeys().equals(keysRemove1));
        Assert.assertTrue(numbers.getValues().equals(valuesRemove1));

        numbers.remove(2);
        Assert.assertTrue(numbers.getKeys().equals((keysRemove2)));
        Assert.assertTrue(numbers.getValues().equals(valuesRemove2));
    }

    @Test
    public void clearTest() {
        numbers.clear();

        Assert.assertTrue(numbers.getKeys().isEmpty());
        Assert.assertTrue(numbers.getValues().isEmpty());
    }
}
