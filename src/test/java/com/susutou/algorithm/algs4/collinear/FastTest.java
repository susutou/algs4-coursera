package com.susutou.algorithm.algs4.collinear;

import org.junit.Test;

import static org.junit.Assert.*;

public class FastTest {
    @Test
    public void testSegmentsWithMoreThanFivePoints() throws Exception {
        Fast.main(new String[] {"data/collinear/input9.txt"});
    }
}