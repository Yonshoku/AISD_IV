package lab1;

import lab1.structures.RBTree;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertTrue;


public class RBTreeTest {
    RBTree tree1 = new RBTree<Integer>();
    RBTree tree2 = new RBTree<Integer>();

    ArrayList<Integer> insertList1 = new ArrayList<Integer> (Arrays.asList(8, 4, 2, 1, 3, 6, 5, 7, 12, 10, 9, 11, 16, 14, 13, 15, 18, 17, 19, 20));
    ArrayList<Integer> insertList2 = new ArrayList<Integer> (Arrays.asList(10, 0, -4, 100, 16));

    ArrayList<Integer> removeList11 = new ArrayList<Integer> (Arrays.asList(8, 4, 2, 1, 3, 6, 5, 7, 16, 12, 10, 9, 14, 13, 15, 18, 17, 19, 20));
    ArrayList<Integer> removeList12 = new ArrayList<Integer> (Arrays.asList(8, 4, 2, 1, 3, 6, 5, 7, 16, 12, 10, 9, 14, 13, 15, 18, 17, 20));
    ArrayList<Integer> removeList13 = new ArrayList<Integer> (Arrays.asList(7, 4, 2, 1, 3, 6, 5, 16, 12, 10, 9, 14, 13, 15, 18, 17, 20));
    ArrayList<Integer> removeList21 = new ArrayList<Integer> (Arrays.asList(0, -4, 100, 16));
    ArrayList<Integer> removeList22 = new ArrayList<Integer> (Arrays.asList(0, -4, 100));

    @Before
    public void setup() {
        tree1.clear();

        tree1.insert(1);
        tree1.insert(2);
        tree1.insert(3);
        tree1.insert(4);
        tree1.insert(5);
        tree1.insert(6);
        tree1.insert(7);
        tree1.insert(8);
        tree1.insert(9);
        tree1.insert(10);
        tree1.insert(11);
        tree1.insert(12);
        tree1.insert(13);
        tree1.insert(14);
        tree1.insert(15);
        tree1.insert(16);
        tree1.insert(17);
        tree1.insert(18);
        tree1.insert(19);
        tree1.insert(20);

        tree2.clear();

        tree2.insert(10);
        tree2.insert(100);
        tree2.insert(0);
        tree2.insert(-4);
        tree2.insert(16);
    }

    @Test
    public void insertTest() {
        assertTrue(tree1.getKeysList().equals(insertList1));
        assertTrue(tree2.getKeysList().equals(insertList2));

    }

    @Test
    public void removeTest() {
        tree1.remove(11);
        assertTrue(tree1.getKeysList().equals(removeList11));
        tree1.remove(19);
        assertTrue(tree1.getKeysList().equals(removeList12));
        tree1.remove(8);
        assertTrue(tree1.getKeysList().equals(removeList13));

        tree2.remove(10);
        assertTrue(tree2.getKeysList().equals(removeList21));
        tree2.remove(16);
        assertTrue(tree2.getKeysList().equals(removeList22));
    }

}
