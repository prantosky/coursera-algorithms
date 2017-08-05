import java.util.Arrays;
import java.util.Vector;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints {

	private final Point[] points;
	private int numberOfSegments = 0;

	FastCollinearPoints(Point[] points) {
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
		Vector<LineSegment> vector = new Vector<>();
		Point[] array = null;
		int length = points.length;
		double previousSlope = Double.NEGATIVE_INFINITY;
		int numberOfPointsOnSegment = 1;

		for (int i = 0; i < length; i++) {
			numberOfPointsOnSegment = 1;
			array = copy(i);
			Arrays.sort(array, points[i].slopeOrder());
			double currentSlope = Double.NEGATIVE_INFINITY;

			for (int j = 0; j < array.length; j++) {
				currentSlope = points[i].slopeTo(array[j]);

				if (Double.compare(currentSlope, Double.NEGATIVE_INFINITY) == 0) {
					continue;
				} else if (Double.compare(previousSlope, currentSlope) != 0) {
					if (numberOfPointsOnSegment > 2) {
						vector.addElement(new LineSegment(points[i], array[j - 1]));
						numberOfSegments++;
					}
					previousSlope = currentSlope;
					numberOfPointsOnSegment = 1;
				} else {
					numberOfPointsOnSegment++;
					if (j == array.length - 1 && numberOfPointsOnSegment > 2) {
						vector.addElement(new LineSegment(points[i], array[j]));
						numberOfSegments++;
					}
				}
			}
		}
		LineSegment[] segments = new LineSegment[vector.size()];
		vector.toArray(segments);
		return segments;
	}

	private Point[] copy(int low) {
		if (low >= points.length) {
			return null;
		}
		int length = points.length;
		Point[] array = new Point[length - low];
		for (int i = 0; i < length - low; i++) {
			array[i] = points[i + low];
		}
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
		FastCollinearPoints collinear = new FastCollinearPoints(points);
		for (LineSegment segment : collinear.segments()) {
			StdOut.println(segment);
			segment.draw();
		}
		StdDraw.show();
	}

}
