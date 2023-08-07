public class Trail implements Comparable<Trail>{
    public Location source;
    public Location destination;
    public int danger;
    boolean isAdded;

    public Trail(Location source, Location destination, int danger) {
        this.source = source;
        this.destination = destination;
        this.danger = danger;
        this.isAdded=false;
    }

	@Override
	public int compareTo(Trail o) {
		// TODO Auto-generated method stub
		return Integer.compare(this.danger, o.danger);
	}
}
