package org.coursera.algorithm.part1.week2;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

public class RandomizedQueue<Item> implements Iterable<Item> {
	private Item[] q; // queue elements
	private int N = 0; // number of elements on queue

	private Random random;

	/**
	 * construct an empty deque
	 */
	@SuppressWarnings("unchecked")
	public RandomizedQueue() {
		q = (Item[]) new Object[2];
		N = 0;
		random = new Random();
	}

	/**
	 * is the deque empty?
	 * 
	 * @return true or false
	 */
	public boolean isEmpty() {
		return N == 0;
	}

	/**
	 * return the number of items on the deque
	 * 
	 * @return
	 */
	public int size() {
		return N;
	}

	/**
	 * add the item
	 * 
	 * @param item
	 */
	public void enqueue(Item item) {
		if (item == null)
			throw new NullPointerException("null input");
		if (N == q.length)
			resize(2 * q.length); // double size of array if necessary
		q[N] = item; // add item
		N++;
	}

	/**
	 * delete and return a random item
	 * 
	 * @return
	 */
	public Item dequeue() {
		if (isEmpty())
			throw new NoSuchElementException("Queue null");

		int index = random.nextInt(N);
		Item item = q[index];
		q[index] = null; // to avoid loitering

		q[index] = q[N - 1];
		q[N - 1] = null;

		N--;
		if (N > 0 && N == q.length / 4)
			resize(q.length / 2);
		return item;
	}

	/**
	 * return (but do not delete) a random item
	 * 
	 * @return
	 */
	public Item sample() {
		if (N == 0)
			throw new NoSuchElementException("Queue null");
		int index = random.nextInt(N);
		return q[index];
	}

	@SuppressWarnings("unchecked")
	private void resize(int max) {
		assert max >= N;
		Item[] temp = (Item[]) new Object[max];
		for (int i = 0; i < N; i++) {
			temp[i] = q[i];
		}
		q = temp;
	}

	/**
	 * return an iterator over items in order from front to end
	 */
	public Iterator<Item> iterator() {
		return new ArrayIterator();
	}

	private class ArrayIterator implements Iterator<Item> {
		private int i = 0;

		private Item[] list;

		@SuppressWarnings("unchecked")
		public ArrayIterator() {
			list = (Item[]) new Object[N];
			for (int i = 0; i < N; i++) {
				int j = random.nextInt(N);
				Item tmp = q[i];
				q[i] = q[j];
				q[j] = tmp;
			}

			for (int i = 0; i < N; i++) {
				list[i] = q[i];
			}
		}

		@Override
		public boolean hasNext() {
			return i < N;
		}

		@Override
		public Item next() {
			if (!hasNext())
				throw new NoSuchElementException();
			Item item = list[i];
			i++;
			return item;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

	public static void main(String[] args) {
		RandomizedQueue<String> q = new RandomizedQueue<String>();
		String[] list = { "A", "B", "C", "D", "E", "F", "G", "H", "I" };
		for (int i = 0; i < list.length; i++) {
			q.enqueue(list[i]);
		}

		for (String s : q) {
			System.out.println(s);
		}
	}
}