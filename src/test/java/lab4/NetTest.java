package lab4;

import lab3.TextReader;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class NetTest {
    TextReader reader = new TextReader();
    Net net1 = new Net();
    Net net2 = new Net();

    int maxFlow1 = 5;
    int maxFlow2 = 10;

    @Before
    public void setup() {
        net1.create(Main.parse(reader.read("/net.txt")));
        net2.create(Main.parse(reader.read("/net2.txt")));
    }

    @Test
    public void maxFlowTest() {
        Assert.assertEquals(maxFlow1, net1.maxFlow());
        Assert.assertEquals(maxFlow2, net2.maxFlow());
    }
}
