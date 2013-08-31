package org.coursera.algorithm.part1.week1;

public class Percolation {

    private WeightedQuickUnionUF uf; // the union class
    private WeightedQuickUnionUF uf2;
    private int[] grid; // N * N matrix to identify if is open
    private int topVirtual;
    private int bottomVirtual;
    
    /**
     * constructor for Percolation, build a N*N matrix
     * 
     * @param N
     */
    public Percolation(int N) {
        this.uf = new WeightedQuickUnionUF(N * N  + 2);
        this.uf2= new WeightedQuickUnionUF(N * N  + 1);
        this.grid = new int[N*N];
        
        this.topVirtual = N * N;
        this.bottomVirtual = N * N + 1;
        
        for(int i = 0; i < N;  i++){
            uf.union(i, topVirtual);
            uf.union((N - 1) * N + i, bottomVirtual);
            uf2.union(i, topVirtual);
        }
        
        for(int i = 0; i < N * N; i++) {
        	grid[i] = 0;
        }
    }

    /**
     * open site (row i, column j) if it is not already
     * 
     * @param i
     * @param j
     */
    public void open(int i, int j) {
    	int N = (int) Math.sqrt(grid.length);
        int index = (i - 1) * N + j - 1;

        if (i < 1 || j < 1 || i > N || j > N) {
            throw new IndexOutOfBoundsException("out of index");
        }
        
        if (grid[index] == 1)
            return;

        grid[index] = 1;
        
        
        if (j - 1 > 0 && isOpen(i, j - 1)) {
            //int root = uf.find(index - 1);
            //uf.union(index, root);
        	uf.union(index, index - 1);
        	uf2.union(index, index - 1);
        }
        if (j + 1 <= N && isOpen(i, j + 1)) {
            //int root = uf.find(index + 1);
            //uf.union(index, root);
        	uf.union(index, index + 1);
        	uf2.union(index, index + 1);
        }
        if (i - 1 > 0 && isOpen(i - 1, j)) {
            //int root = uf.find((i - 2) * N + j - 1);
            //uf.union(index, root);
        	uf.union(index, (i - 2) * N + j -1);
        	uf2.union(index, (i - 2) * N + j -1);
        }
        if (i + 1 <= N && isOpen(i + 1, j)) {
            //int root = uf.find(i * N + j - 1);
            //uf.union(index, root);
        	uf.union(index, (i) * N + j -1);
        	uf2.union(index, (i) * N + j -1);
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
    	int N = (int) Math.sqrt(grid.length);
        int index = (i - 1) * N + j - 1;
        
        if (i < 1 || j < 1 || i > N || j > N){
            throw new IndexOutOfBoundsException("out of index");
        }
        return grid[index] == 1;
    }

    /**
     * return if grid i*j is Full, have connected with top
     * 
     * @param i
     * @param j
     * @return
     */
    public boolean isFull(int i, int j) {
    	int N = (int) Math.sqrt(grid.length);
        int index = (i - 1) * N + j - 1;
        
    	if (i < 1 || j < 1 || i > N || j > N ){
            throw new IndexOutOfBoundsException("out of index");
        }

        if(grid[index] == 0) {
            return false;
        } 
        
        return uf2.connected(topVirtual, (i - 1) * N + j - 1);
    }

    /**
     * return if this matrix is percolates
     * 
     * @return
     */
    public boolean percolates() {
    	int N = (int) Math.sqrt(grid.length);
        
        boolean topFlag = false;
        boolean bottomFlag = false;
        for(int i = 1 ;  i <= N; i ++){
            if(isOpen(1, i)) topFlag = true;
            if(isOpen(N, i)) bottomFlag = true;
        } 
        return (topFlag == true && bottomFlag == true) ?  uf.connected(topVirtual, bottomVirtual) : false;
        /*if(!topFlag) return false;
        
        for(int i = 0 ; i < N; i++){
            if(isOpen(N, i + 1) && uf.connected(topVirtual, (N - 1) * N + i)) return true;
        }
        
        return false;*/
    }

    public static void main(String[] args) {
        Percolation p = new Percolation(4);
        
        //p.open(1, 1);
        //p.open(2, 1);
        //p.open(2, 2);
        
        //p.open(2, 3);
       // p.open(3, 3);
        
        //p.open(3, 4);
       // p.open(4, 4);
       // p.open(4, 1);
       // p.open(4, 2);
        p.open(4, 1);
        p.open(3, 1);
        p.open(2, 1);
        p.open(1, 1);
        
        System.out.println(p.percolates());

        p = new Percolation(2);
        p.open(1, 1);
        p.open(2, 2);
        System.out.println(p.percolates());
    }
}
