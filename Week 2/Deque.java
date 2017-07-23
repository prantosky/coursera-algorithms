import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

	private Node first = null;
	private Node last = null;
	private int size;

	private class Node {
		Item item;
		Node previous;
		Node next;
	}

	private class DequeIterator implements Iterator<Item> {

		Node current = first;

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public Item next() {
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
		Node oldNode = first;
		first = new Node();
		first.item = item;
		first.next = oldNode;
		oldNode.previous = first;
		size++;
	}

	public void addLast(Item item) {
		if (item == null) {
			throw new IllegalArgumentException();
		}
		Node node = new Node();
		node.item = item;
		node.previous = last;
		last.next = node;
		last = node;
		size++;
	}

	public Item removeFirst() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		Item item = first.item;
		first = first.next;
		size--;
		first.previous = null;
		return item;
	}

	public Item removeLast() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		Item item = last.item;
		last = last.previous;
		last.next = null;
		size--;
		return item;

	}

	public Iterator<Item> iterator() {
		return new DequeIterator();
	}

}
