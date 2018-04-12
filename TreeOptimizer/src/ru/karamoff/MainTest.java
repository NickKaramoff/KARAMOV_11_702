package ru.karamoff;

import org.junit.*;

public class MainTest {

    private int[][] nodes;

    @Before
    public void setUp() {
        nodes = new int[4][];
        nodes[0] = new int[]{8, 0, 0, 0, 0, 0, 0, 0, 0};        // 2 при k=4
        nodes[1] = new int[]{3, 2, 0, 4, 0, 0, 0, 0, 0, 0};     // 3 при k=2
        nodes[2] = new int[]{2, 0, 0, 0, 0, 4, 0};              // 2 при k=2
        nodes[3] = new int[]{2, 0, 0};                          // 0 при k=3
    }

    @Test
    public void main() {
        Assert.assertEquals(2, Optimizer.optimize(nodes[0], 4));
        Assert.assertEquals(3, Optimizer.optimize(nodes[1], 2));
        Assert.assertEquals(2, Optimizer.optimize(nodes[2], 2));
        Assert.assertEquals(0, Optimizer.optimize(nodes[3], 3));
    }
}
