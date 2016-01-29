package com.susutou.algorithm.algs4.collinear;

import org.junit.Test;

public class ClientTest {
    @Test
    public void testBruteCollinearPoints() throws Exception {
        Client.main(new String[] {"data/collinear/input8.txt"});
    }
}