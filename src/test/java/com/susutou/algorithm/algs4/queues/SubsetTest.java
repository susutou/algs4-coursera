package com.susutou.algorithm.algs4.queues;

import org.junit.Test;

import java.io.FileInputStream;

/**
 * @author susen
 */
public class SubsetTest {
    @Test
    public void uniformityTest() throws Exception {
        System.setIn(new FileInputStream("data/queues/letters.txt"));
        Subset.main(new String[] {"1"});
    }
}