package com.xiekun.algs4.test;

import com.xiekun.algs4.uf.QuickFindUF;
import com.xiekun.algs4.util.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.io.FileNotFoundException;


public class QuickFindUFTest {
    public static void main(String[] args) throws FileNotFoundException {
        StdIn stdIn = new StdIn("E:\\algs4-data\\mediumUF.txt");
        int n = stdIn.readInt();
        QuickFindUF uf = new QuickFindUF(n);
        while (!stdIn.isEmpty()) {
            int p = stdIn.readInt();
            int q = stdIn.readInt();
            if (uf.find(p) == uf.find(q)) continue;
            uf.union(p, q);
            StdOut.println(p + " " + q);
        }
        System.out.println("连通分量的个数为：" + uf.count());
    }
}
