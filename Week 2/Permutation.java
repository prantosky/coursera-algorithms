import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
	private static RandomizedQueue<String> queue = null;

	private static void initialise() {
		queue = new RandomizedQueue<>();
	}

	public static void main(String[] args) {
		int num = Integer.parseInt(args[0]);
		initialise();
		while (StdIn.isEmpty()) {
			Permutation.queue.enqueue(StdIn.readString());
		}
		for (int i = 0; i < num; i++) {
			StdOut.println(Permutation.queue.dequeue());
		}

	}
}
