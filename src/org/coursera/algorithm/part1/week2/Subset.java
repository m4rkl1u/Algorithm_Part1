package org.coursera.algorithm.part1.week2;
import org.coursera.algorithm.part1.StdIn;

import java.util.Iterator;

public class Subset {

	public static void main(String[] args) {
		int k = Integer.parseInt(args[0]);

		RandomizedQueue<String> q = new RandomizedQueue<String>();

		while (!StdIn.isEmpty()) {
			String item = StdIn.readString();
			q.enqueue(item);
		}

		Iterator<String> iter = q.iterator();
		int i = 0;
		while (iter.hasNext()) {
			System.out.println(iter.next());
			i++;
			if (i >= k)
				break;
		}
	}
}
