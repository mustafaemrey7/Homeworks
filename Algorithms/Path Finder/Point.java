import java.util.ArrayList;

public class Point implements Comparable<Point>{
    public int x;
    public int y;
    public double distanceFromSource;
    public ArrayList<Point> path;
    public Point parent;
    public int cost;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
        this.parent=null;
        path=new ArrayList<>();
        cost=0;
    }
    public Point get() {
		return this;
	}
    public int getX() {
    	return this.x;
    }
    public int getY() {
    	return this.y;
    }
    public double getCost() {
    	return this.distanceFromSource;
    }
    public void setDist(double d) {
    	this.distanceFromSource=d;
    }
    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
	@Override
	public int compareTo(Point o) {
		// TODO Auto-generated method stub
		return Double.compare(this.distanceFromSource, o.distanceFromSource);
	}
	

    // You can add additional variables and methods if necessary.
}
