public class Film {
	private String name,path,duration;

	
	
	public Film(String name,String path,String duration ) {
		
		this.name=name;
		this.path=path;
		this.duration=duration;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String toString() {
		return name+" "+path+ " "+ duration;
	}
}
