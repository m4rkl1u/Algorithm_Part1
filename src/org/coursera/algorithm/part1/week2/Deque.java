package org.coursera.algorithm.part1.week2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

	private Node first = null;
	private Node last = null;
	private int num = 0;

	private class Node {
		private Item item;
		private Node prev;
		private Node next;
	}

	/**
	 * construct an empty deque
	 */
	public Deque() {
		first = null;
		last = null;
		num = 0;
	}

	/**
	 * is the deque empty?
	 * 
	 * @return true or false
	 */
	public boolean isEmpty() {
		return num == 0;
	}

	/**
	 * return the number of items on the deque
	 * 
	 * @return
	 */
	public int size() {
		return num;
	}

	/**
	 * insert the item at the front
	 * 
	 * @param item
	 */
	public void addFirst(Item item) {
		if (item == null)
			throw new NullPointerException();
		Node tmp = new Node();
		tmp.item = item;
		tmp.next = first;
		tmp.prev = null;
		if (first != null) {
			first.prev = tmp;
		}
		first = tmp;
		if (last == null) {
			last = first;
		}
		num++;
	}

	/**
	 * insert the item at the end
	 * 
	 * @param item
	 */
	public void addLast(Item item) {
		if (item == null)
			throw new NullPointerException();
		Node tmp = new Node();
		tmp.item = item;
		tmp.prev = last;
		tmp.next = null;
		if (last != null) {
			last.next = tmp;
		}
		last = tmp;
		if (first == null) {
			first = last;
		}
		num++;
	}

	/**
	 * delete and return the item at the front
	 * 
	 * @return
	 */
	public Item removeFirst() {
		if (num == 0)
			throw new NoSuchElementException();
		Item item = first.item;
		if (num == 1) {
			first.item = null;
			first.next = null;
			first.prev = null;
			first = null;
			last = null;
		} else {
			first.item = null;
			first = first.next;
			first.prev = null;
		}
		num--;
		return item;
	}

	/**
	 * delete and return the item at the end
	 * 
	 * @return
	 */
	public Item removeLast() {
		if (num == 0)
			throw new NoSuchElementException();
		Item item = last.item;
		if (num == 1) {
			last.item = null;
			last.prev = null;
			last.next = null;
			first = null;
			last = null;
		} else {
			last.item = null;
			last = last.prev;
			last.next = null;
		}
		num--;
		return item;
	}

	/**
	 * return an iterator over items in order from front to end
	 */
	public Iterator<Item> iterator() {
		return new DequeIterator();
	}

	private class DequeIterator implements Iterator<Item> {

		private Node current = first;

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public Item next() {
			if (!hasNext())
				throw new NoSuchElementException();
			Item item = current.item;
			current = current.next;
			return item;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

	}

	public static void main(String[] args) {
		Deque<String> q = new Deque<String>();
		q.addFirst("aaa");
		q.addFirst("vvv");
		q.addLast("zzzz");
		q.removeFirst();
		q.removeLast();
		q.addLast("ddddd");
		q.removeFirst();
		q.removeLast();

		for (String s : q) {
			System.out.println(s);
		}
	}
}