import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Matrix {
	public static void makeMatrix(Jewel[][] grid,File gridFile) throws FileNotFoundException {
		Scanner reader = new Scanner(gridFile);
		int column = 0;
		while(reader.hasNextLine()) {
			String line=reader.nextLine();
			String[] row = line.trim().split(" ");
			Jewel jewel = null;
			for(int i = 0;i<row.length;i++) {
				String jewelName =  row[i].trim();
				if (jewelName.equals("D")){
	        		jewel = new Diamond();
	        	}else if (jewelName.equals("S")){
	        		jewel = new Square();
	        	}else if (jewelName.equals("T")){
	        		jewel = new Triangle();
	        	}else if (jewelName.equals("W")){
	        		jewel = new Wildcard();
	        	}else if (jewelName.equals("+")){
	        		jewel = new Plus();
	        	}else if (jewelName.equals("-")){
	        		jewel = new Minus();
	        	}else if (jewelName.equals("/")){
	        		jewel = new Slash();
	        	}else if (jewelName.equals("\\")){
	        		jewel = new Backslash();
	        	}else if (jewelName.equals("|")){
	        		jewel = new Pipe();
	        	}
				grid[column][i]=jewel;
			}
			column++;
		}
		reader.close();
	}
}
