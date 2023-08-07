public abstract class Jewel {
	protected String name;
	protected String type;
	protected int score;
	public Jewel(String name,String type,int score) {
		this.name = name;
		this.type = type;
		this.score = score;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getScore() {
		return score;
	}
	public String toString() {
		return getName();
	}
	
}
