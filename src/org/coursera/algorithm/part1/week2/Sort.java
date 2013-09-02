package org.coursera.algorithm.part1.week2;

public class Sort {
	int[] array;
	
	public Sort(int[] list){
		array = list;
	}
	
	public static void SelectSort(int[] array, int times){
		for(int i = 0 ; i < times; i ++) {
			int min = i;
			for(int j = i + 1; j < array.length; j++){
				if(array[min] > array[j]) {
					min = j;
				}
			}
			if(min != i) {
				int tmp = array[i];
				array[i] = array[min];
				array[min] = tmp;
			}
		}
	}
	
	public static void ShellSort(int[] array, int times){
		int N = array.length;
		
		int h = 1;
		while(h < N / 3) h = 3*h + 1;
		
		int iter = 0;
		
		while(iter < times && h >= 1){
			for(int i = h; i < N; i ++){
				for(int j = i; j >= h && array[j] < array[j - h]; j -= h ){
					int tmp = array[j];
					array[j] = array[j - h];
					array[j - h] = tmp;
				}
			}
			h = h /3;
		}
	}
	
	public static void main(String[] args){
		int[] array = {42, 24, 98, 88, 90, 46, 41, 58, 85, 66};
		Sort.ShellSort(array, 4);
		
		int[] array2 = {58, 59, 72, 46, 93, 31, 99, 32, 12, 50};
		Sort.ShellSort(array2, 4);
		System.out.println(array);
	}
}
