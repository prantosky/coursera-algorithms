import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
	private RandomizedQueue<String> queue = null;

	public Permutation() {
		queue = new RandomizedQueue<>();
	}

	public static void main(String[] args) {
		int num = Integer.parseInt(args[0]);
		Permutation permutation = new Permutation();
		while (StdIn.isEmpty()) {
			permutation.queue.enqueue(StdIn.readString());
		}
		for (int i = 0; i < num; i++) {
			StdOut.println(permutation.queue.dequeue());
		}

	}
}
