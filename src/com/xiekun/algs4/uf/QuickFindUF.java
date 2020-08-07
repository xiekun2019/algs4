package com.xiekun.algs4.uf;

public class QuickFindUF {
    // 连通分量的id
    private int[] id;
    // 连通分量的数量
    private int count;

    /**
     * 构造函数，初始化id数组和count
     * @param n 多少个连通分量
     */
    public QuickFindUF(int n){
        count = n;
        id = new int[n];
        for(int i=0; i<n; i++){
            id[i] = i;
        }
    }

    /**
     * @return 返回连通分量的数量
     */
    public int count() {
        return count;
    }

    /**
     * 返回连通分量p的id
     * @param p 第几个连通分量
     * @return
     */
    public int find(int p){
        validate(p);
        return id[p];
    }

    // 用来判断p是否合法，即在0到n（不含n）之间
    private void validate(int p) {
        int n = id.length;
        if (p < 0 || p >= n) {
            throw new IllegalArgumentException("index " + p + " is not between 0 and " + (n-1));
        }
    }

    /**
     * 判断两个触点是否在同一个连通分量中
     * @param p 触点p
     * @param q 触点q
     * @return 两个触点同一个连通分量中返回true，否则返回false
     */
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    /**
     * 将两个触点所在的连通分量合并
     * @param p 触点p
     * @param q 触点q
     */
    public void union(int p, int q){
        int idP = find(p);
        int idQ = find(q);

        if(idP == idQ) return;
        // 将所有id == idP的触点的id设为idQ，即合并两个联通分量
        for (int i=0; i<id.length; i++)
            if(id[i] == idP) id[i] = idQ;
        count--;
    }
}
