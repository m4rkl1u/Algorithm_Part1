package org.coursera.algorithm.part1;

public class Percolation {

    private WeightedQuickUnionUF uf; // the union class
    private int N; // Dimensional of matrix
    private int[][] grid; // N * N matrix to identify if is open
   
    /**
     * constructor for Percolation, build a N*N matrix
     * 
     * @param N
     */
    public Percolation(int N) {
        this.uf = new WeightedQuickUnionUF(N * N);
        this.N = N;
        this.grid = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                grid[i][j] = 0;
            }
        }
    }

    /**
     * open site (row i, column j) if it is not already
     * 
     * @param i
     * @param j
     */
    public void open(int i, int j) {
        if (i < 1 || j < 1 || i > N || j > N) {
            throw new IndexOutOfBoundsException("out of index");
        }

        if (grid[i - 1][j - 1] == 1)
            return;

        grid[i - 1][j - 1] = 1;

        int index = (i - 1) * N + j - 1;
        if (j - 1 > 0 && isOpen(i, j - 1) && !uf.connected(index, index - 1 )) {
            uf.union(index, index - 1);
        }
        if (j + 1 <= N && isOpen(i, j + 1) && !uf.connected(index, index + 1)) {
            uf.union(index, index + 1);
        }
        if (i - 1 > 0 && isOpen(i - 1, j) && !uf.connected(index, (i - 2) *N + j -1)) {
            uf.union(index, (i - 2) * N + j - 1);
        }
        if (i + 1 <= N && isOpen(i + 1, j) && !uf.connected(index, i * N + j - 1)) {
            uf.union(index, (i) * N + j - 1);
        }
    }

    /**
     * return if grid is open.
     * 
     * @param i
     * @param j
     * @return
     */
    public boolean isOpen(int i, int j) {
        if (i < 1 || j < 1 || i > N || j > N){
            throw new IndexOutOfBoundsException("out of index");
        }
        return grid[i - 1][j - 1] == 1;
    }

    /**
     * return if grid i*j is Full, have connected with top
     * 
     * @param i
     * @param j
     * @return
     */
    public boolean isFull(int i, int j) {
        // is site (row i, column j) full?
        if (i < 1 || j < 1 || i > N || j > N ){
            throw new IndexOutOfBoundsException("out of index");
        }

        if(grid[i - 1][j - 1] == 0) {
            return false;
        } 

        for (int k = 0; k < N; k++) {
            int index = (i - 1) * N + j - 1;
            if (isOpen(1, k+1) && uf.connected(k, index))
                return true;
        }
        return false;
    }

    /**
     * return if this matrix is percolates
     * 
     * @return
     */
    public boolean percolates() {
        for (int i = 1; i <= N; i++) {
            if (isOpen(N, i) && isFull(N, i))
                return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Percolation p = new Percolation(2);
        p.open(1, 1);
        p.open(2, 2);
        p.open(1, 2);
        System.out.println(p.percolates());
    }
}
