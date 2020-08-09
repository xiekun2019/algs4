package com.xiekun.algs4.test;

import com.xiekun.algs4.uf.WeightedQuickUnionUF;
import com.xiekun.algs4.util.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.io.FileNotFoundException;

public class WeightedQuickUnionUFTest {
    public static void main(String[] args) throws FileNotFoundException {
        long start = System.currentTimeMillis();
        StdIn stdIn = new StdIn("E:\\algs4-data\\largeUF.txt");
        int n = stdIn.readInt();
        WeightedQuickUnionUF uf = new WeightedQuickUnionUF(n);
        while (!stdIn.isEmpty()) {
            int p = stdIn.readInt();
            int q = stdIn.readInt();
            if (uf.find(p) == uf.find(q)) continue;
            uf.union(p, q);
            // StdOut.println(p + " " + q);
        }
        long end = System.currentTimeMillis();
        long costTime = end - start;
        System.out.println("花费时间：" + costTime*1.0/1000 + "秒");
        System.out.println("连通分量的个数为：" + uf.count());
    }
}
