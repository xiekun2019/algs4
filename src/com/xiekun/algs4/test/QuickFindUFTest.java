package com.xiekun.algs4.test;

import com.xiekun.algs4.uf.QuickFindUF;
import com.xiekun.algs4.util.StdIn;
import edu.princeton.cs.algs4.StdOut;

import javax.sound.midi.SoundbankResource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class QuickFindUFTest {
    public static void main(String[] args) throws FileNotFoundException {
        StdIn stdIn = new StdIn("C:\\Users\\xiekun\\Desktop\\tinyUF.txt");
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
