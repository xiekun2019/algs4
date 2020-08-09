package com.xiekun.algs4.percolation;
import com.xiekun.algs4.uf.WeightedQuickUnionUF;

public class Percolation {
    // n-by-n grid
    int[][] site;
    // WeightedQuickUnionUF 对象
    WeightedQuickUnionUF weightedQuickUnionUF;
    int countOpen;
    int size;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n){
        site = new int[n+1][n+1];
        countOpen = 0;
        size = n;
        // 0 ~ n^2-1 是site中的元素
        // n^2 和 n^2+1 是额外添加的两个元素用来连接最上面一层和最下面一层的site
        // 其中最上面一层的site为xyTo1D(1,1) ~ xyTo1D(1,n)
        // 同时最下面一层的site为xyTo1D(n,1) ~ xyTo1D(n,n)
        // n^2 连接 xyTo1D(1,1) ~ xyTo1D(1,n)
        // n^2+1 连接 xyTo1D(n,1) ~ xyTo1D(n,n)
        weightedQuickUnionUF = new WeightedQuickUnionUF(n*n + 2);
        for(int i=1; i<=n; i++){
            for(int j=1; j<=n; j++){
                site[i][j] = 1;
            }
        }
        for(int i=1; i<=n; i++){
            weightedQuickUnionUF.union(n*n, xyTo1D(1, i));
            weightedQuickUnionUF.union(n*n + 1, xyTo1D(n, i));
        }
    }

    private int xyTo1D(int x, int y){
        return y + (x-1) * size - 1;
    }

    private Boolean valid(int i){
        if (i <= 0 || i > size) return false;
        return true;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col){
        //if site (row, col) is blocked
        if(site[row][col] > 0) {
            site[row][col] = 0;
            // 判断上面的site
            if(valid(row-1) && site[row-1][col] == 0)
                weightedQuickUnionUF.union(xyTo1D(row, col), xyTo1D(row-1, col));
            // 判断下面的site
            if(valid(row+1) && site[row+1][col] == 0)
                weightedQuickUnionUF.union(xyTo1D(row, col), xyTo1D(row+1, col));
            // 判断左边的site
            if(valid(col-1) && site[row][col-1] == 0)
                weightedQuickUnionUF.union(xyTo1D(row, col), xyTo1D(row, col-1));
            // 判断右边的site
            if(valid(col+1) && site[row][col+1] == 0)
                weightedQuickUnionUF.union(xyTo1D(row, col), xyTo1D(row, col+1));
            countOpen ++;
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col){
        return site[row][col] == 0;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col){
        return weightedQuickUnionUF.connected(size*size, xyTo1D(row, col));
    }

    // returns the number of open sites
    public int numberOfOpenSites(){
        return countOpen;
    }

    // does the system percolate?
    public boolean percolates(){
        return weightedQuickUnionUF.find(size*size) == weightedQuickUnionUF.find(size*size + 1);
    }

    // test client (optional)
    public static void main(String[] args){
        int n = 3;
        Percolation percolation = new Percolation(n);
        percolation.open(1, 1);
        percolation.open(1, 2);
        percolation.open(1, 3);
        percolation.open(2, 3);
        percolation.open(3, 3);
        for (int i=1; i<=n; i++){
            for (int j=1; j<=n; j++){
                System.out.print(percolation.site[i][j]);
            }
            System.out.println();
        }
//        Boolean result = percolation.weightedQuickUnionUF.connected(1, 2);
        // Boolean result = percolation.percolates();
        int result1 = percolation.weightedQuickUnionUF.find(9);
        int result2 = percolation.weightedQuickUnionUF.find(10);
        System.out.println(result1);
        System.out.println(result2);
    }
}
