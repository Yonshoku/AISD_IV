package lab1;

import lab1.structures.Map;

public class Main {
    public static void main(String[] args) {
        Map<Integer, String> numbers = new Map<>();

        numbers.insert(1, "one");
        numbers.insert(2, "two");
        numbers.insert(3, "three");
        numbers.insert(4, "four");
        numbers.insert(5, "five");
        numbers.insert(5, "five");
        numbers.insert(5, "five");

        System.out.println("After insert: ");
        numbers.print();

        numbers.remove(4);
        numbers.remove(2);

        System.out.println("After removing 2 and 4:");
        System.out.println("Keys: " + numbers.getKeys());
        System.out.println("Values: " + numbers.getValues());

        numbers.clear();

        System.out.println("After clear:");
        numbers.print();
    }
}
