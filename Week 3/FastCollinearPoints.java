package week3;

public class FastCollinearPoints {
	
	private final Point[] points;
	private int numberOfSegments=0;
	
	FastCollinearPoints(Point[] points){
		if(points==null){
			throw new IllegalArgumentException();
		}
		int length=points.length;
		for(int i=0;i<length=i++){
			if(points[i]==null){
				throw new IllegalArgumentException();
			}
		}
		this.points=points;
	}
	
	public void numberOfSegments(){
		return numberOfSegments;
	}
	
	public LineSegment[] segments(){
		int length=points.length;
		for(int i=0;i<length;i++){
			Arrays.sort(points,points[i].slopeOrder());
			
		}
	}
	
	public static void main(String[] args) {

	}

}
