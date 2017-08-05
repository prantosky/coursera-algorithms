import java.util.Arrays;
import java.util.Vector;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints {

	private final Point[] points;
	private int numberOfSegments = 0;

	public BruteCollinearPoints(Point[] points) {
		if (points == null) {
			throw new IllegalArgumentException();
		}
		int length = points.length;
		for (int i = 0; i < length; i++) {
			if (points[i] == null) {
				throw new IllegalArgumentException();
			}
		}
		Arrays.sort(points);
		for (int i = 0; i < length - 1; i++) {
			if (points[i].slopeTo(points[i + 1]) == Double.NEGATIVE_INFINITY) {
				throw new IllegalArgumentException();
			}
		}
		this.points = points.clone();
	}

	public int numberOfSegments() {
		return numberOfSegments;
	}

	public LineSegment[] segments() {
		Arrays.sort(points);
		Vector<LineSegment> lineSegments = new Vector<>();
		int length = points.length;
		for (int i = 0; i < length - 3; i++) {
			for (int j = i + 1; j < length - 2; j++) {
				for (int k = j + 1; k < length - 1; k++) {
					if (Double.compare(points[i].slopeTo(points[j]), points[i].slopeTo(points[k])) != 0) {
						continue;
					}
					for (int x = k + 1; x < length; x++) {
						if (Double.compare(points[i].slopeTo(points[x]), points[i].slopeTo(points[k])) == 0) {
							lineSegments.addElement(new LineSegment(points[i], points[x]));
							numberOfSegments++;
						}
					}
				}
			}
		}
		LineSegment[] array = new LineSegment[lineSegments.size()];
		lineSegments.toArray(array);
		return array;
	}

	public static void main(String[] args) {
		In in = new In(args[0]);
		int n = in.readInt();
		Point[] points = new Point[n];
		for (int i = 0; i < n; i++) {
			int x = in.readInt();
			int y = in.readInt();
			points[i] = new Point(x, y);
		}

		// draw the points
		StdDraw.enableDoubleBuffering();
		StdDraw.setXscale(0, 32768);
		StdDraw.setYscale(0, 32768);
		for (Point p : points) {
			p.draw();
		}
		StdDraw.show();
		// print and draw the line segments
		BruteCollinearPoints collinear = new BruteCollinearPoints(points);
		for (LineSegment segment : collinear.segments()) {
			StdOut.println(segment);
			segment.draw();
		}
		StdDraw.show();
	}
}
