package com.xiekun.algs4.percolation;

import edu.princeton.cs.algs4.StdRandom;

import java.util.ArrayList;
import java.util.List;

public class PercolationStats {
    double mean;
    double stddev;
    double confidenceLo;
    double confidenceHi;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials){
        double[] openSiteThreshold = new double[trials];
        for (int i = 0; i< trials; i++) {
            Percolation percolation = new Percolation(n);
            while (!percolation.percolates()) {
                int randomX = StdRandom.uniform(1, n + 1);
                int randomY = StdRandom.uniform(1, n + 1);
                percolation.open(randomX, randomY);
                // printSite(percolation.site);
            }
            openSiteThreshold[i] = percolation.numberOfOpenSites()*1.0/(n*n);
        }
        mean = countMean(openSiteThreshold);
        stddev = countStd(openSiteThreshold, mean);
        confidenceLo = mean-(1.96*stddev)/Math.sqrt(trials);
        confidenceHi = mean+(1.96*stddev)/Math.sqrt(trials);
    }

    @Deprecated
    private void printSite(int[][] site){
        for (int i=1; i< site[0].length; i++){
            for(int j=1; j< site[0].length; j++){
                System.out.print(site[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    private double countMean(double[] nums){
        double sum = 0.0;
        for (double num : nums) {
            sum += num;
        }
        return sum/(nums.length);
    }

    private double countStd(double[] nums, double mean){
        double std = 0;
        for (double num : nums) {
            std += Math.pow(num-mean, 2)/(nums.length-1);
        }
        return std;
    }

    // sample mean of percolation threshold
    public double mean(){
        return mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev(){
        return stddev;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo(){
        return confidenceLo;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi(){
        return confidenceHi;
    }

    // test client (see below)
    public static void main(String[] args){
        PercolationStats stats = new PercolationStats(200, 5000);
        System.out.println("mean                    = " + stats.mean());
        System.out.println("stddev                  = " + stats.stddev());
        System.out.println("95% confidence interval = [" + stats.confidenceLo() + ", " +stats.confidenceHi() + "]");
    }
}
