import java.util.Arrays;
import java.util.Vector;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints {

	private final Point[] points;
	private int numberOfSegments = 0;

	public FastCollinearPoints(Point[] points) {
		if (points == null) {
			throw new IllegalArgumentException();
		}
		int length = points.length;
		for (int i = 0; i < length; i++) {
			if (points[i] == null) {
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
			array = points.clone();
			Arrays.sort(array, points[i].slopeOrder());
			double currentSlope = Double.NEGATIVE_INFINITY;

			for (int j = 0; j < length; j++) {
				currentSlope = points[i].slopeTo(array[j]);

				if (currentSlope == Double.NEGATIVE_INFINITY) {
					continue;
				} else if (previousSlope != currentSlope) {
					if (numberOfPointsOnSegment > 2) {
						vector.addElement(new LineSegment(points[i], array[j - 1]));
						numberOfSegments++;
					}
					previousSlope = currentSlope;
					numberOfPointsOnSegment = 1;
				} else {
					numberOfPointsOnSegment++;
					if (j == length - 1 && numberOfPointsOnSegment > 2) {
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
