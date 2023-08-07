public class Seat {
	private String filmName;
	private String hallName;
	private String owner;
	private int row;
	private int column;
	private int price;
	
	
	
	public Seat(String film,String hall,int row,int column,String owner,int price) {
		this.filmName = film;
		this.hallName = hall;
		this.owner = owner;
		this.row = row;
		this.column = column;
		this.price = price;
	}
		
	public String getFilmName() {
		return filmName;
	}
	public void setFilmName(String filmName) {
		this.filmName = filmName;
	}
	public String getHallName() {
		return hallName;
	}
	public void setHallName(String hallName) {
		this.hallName = hallName;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getColumn() {
		return column;
	}
	public void setColumn(int column) {
		this.column = column;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
}
