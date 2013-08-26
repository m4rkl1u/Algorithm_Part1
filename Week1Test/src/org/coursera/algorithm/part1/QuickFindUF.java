package org.coursera.algorithm.part1;

public class QuickFindUF {

	public int[] id;
	
	public QuickFindUF(int n){
		id = new int[n];
		for(int i = 0 ; i < n; i++){
			id[i] = i;
		}
	}
	
	public void union(int p, int q){
		int pid = id[p];
		int qid = id[q];
		for(int i = 0 ; i < id.length; i++){
			if(id[i] == pid) id[i] = qid;
		}
	}
	
	public boolean connected(int p, int q){
		return id[p] == id[q];
	}
	
	public static void main(String[] args) {
		QuickFindUF uf = new QuickFindUF(10);
		
		uf.union(6, 9);
		uf.union(6, 0);
		uf.union(2, 7);
		uf.union(6, 5);
		uf.union(4, 2);
		uf.union(4, 1);
		
		System.out.println(uf.id);
	}

}
