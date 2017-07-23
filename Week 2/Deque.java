import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class Deque<Item> implements Iterable<Item> {

	private Node first = null;
	private Node last = null;
	private int size;

	private class Node {
		private Item item;
		private Node previous;
		private Node next;
	}

	private class DequeIterator implements Iterator<Item> {

		private Node current = first;

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public Item next() {
			if (current == null) {
				throw new NoSuchElementException();
			}
			Item item = current.item;
			current = current.next;
			return item;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

	}

	public Deque() {
		size = 0;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public int size() {
		return size;
	}

	public void addFirst(Item item) {
		if (item == null) {
			throw new IllegalArgumentException();
		}
		Node node = new Node();
		node.item = item;
		if (size == 0) {
			last = node;
			first = node;
		} else {
			node.next = first;
			first.previous = node;
			first = node;
		}
		size++;
	}

	public void addLast(Item item) {
		if (item == null) {
			throw new IllegalArgumentException();
		}
		Node node = new Node();
		node.item = item;
		if (size == 0) {
			last = node;
			first = node;
		} else {
			last.next = node;
			node.previous = last;
			last = node;
		}
		size++;
	}

	public Item removeFirst() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		Item item = first.item;
		Node trash = first;
		first = first.next;
		trash.next = null;
		size--;
		if (size == 0) {
			first = null;
			last = null;
		} else {
			first.previous = null;
		}
		return item;
	}

	public Item removeLast() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		Item item = last.item;
		Node trash = last;
		last = last.previous;
		trash.previous = null;
		size--;
		if (size == 0) {
			first = null;
			last = null;
		} else {
			last.next = null;
		}
		return item;

	}

	public Iterator<Item> iterator() {
		return new DequeIterator();
	}

}
