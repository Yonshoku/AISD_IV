package lab3;

import org.junit.Assert;
import org.junit.Test;

public class BellmanFloydTest {
    String[] data1 = {"Saint-Petersburg", "Moscow", "10", "10", "Saint-Petersburg", "Kaliningrad", "5", "N/A", "Kaliningrad", "Moscow", "4", "4", "Sofia", "Saint-Petersburg", "20", "25", "Sofia", "Moscow", "5", "N/A"};
    String[] data2 = {"Saint-Petersburg", "Moscow", "5", "10", "Saint-Petersburg", "Kaliningrad", "40", "N/A", "Kaliningrad", "Moscow", "100", "20", "Sofia", "Saint-Petersburg", "100", "10", "Sofia", "Moscow", "100", "N/A"};

    int[][] result1 = {
            {0, 9, 5, 25},
            {10, 0, 4, 35},
            {14, 4, 0, 39},
            {15, 5, 9, 0}
    };

    int[][] result2 = {
            {0, 5, 25, 10},
            {10, 0, 20, 20},
            {110, 100, 0, 120},
            {100, 100, 120, 0}
    };

    @Test
    public void BellmanFloydRunTest() {
        Assert.assertArrayEquals(result1, new BellmanFloyd(data1).run());
        Assert.assertArrayEquals(result2, new BellmanFloyd(data2).run());
    }
}
