import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Matches {
	public static int score = 0;
	
	public Matches() {}
	
	
	public static void control(int i,int k,Jewel[][] gridJewel) {

		ArrayList<String> maths	= new ArrayList<String>();
		maths.add("/");maths.add("+");maths.add("-");maths.add("\\");maths.add("|");
		String board = gridJewel[i][k].getName();
		//diagonal
		boolean isStop = false;
		if(board.equals("D")) {
			// 1 direction  
			if((k>1&&i>1)&& isStop==false) {
				
				isStop = findMatch(gridJewel, i, k, 1,"jewel");
			}
			// 9 direction
			if((i<gridJewel.length-2 && k<gridJewel[i].length-2)&& isStop==false){
				isStop = findMatch(gridJewel, i, k, 9,"jewel");
			}
			// 3 direction
			if((1<i&&k<gridJewel[i].length-2)&& isStop==false){
				isStop = findMatch(gridJewel, i, k, 3,"jewel");
			}
			//7 direction
			if((i<gridJewel.length-2&&k>1)&& isStop==false){
				isStop = findMatch(gridJewel, i, k, 7,"jewel");
			}
		}
		//HORIZONTAL
		else if(board.equals("S")) {
			//4 direction
			if(k>2) {
				isStop = findMatch(gridJewel, i, k, 4,"jewel");
			}
			//6 direction
			else if(k<gridJewel[i].length-2) {
				isStop = findMatch(gridJewel, i, k, 6,"jewel");
			}
		}
		//VERTICAL
		else if(board.equals("T")) {
				//UPWARD 2 direction
				if(i>1) {
					isStop = findMatch(gridJewel, i, k, 2,"jewel");
					}
				//DOWNWARD 8 direction
				else if((k<gridJewel[i].length-2)&&isStop==false) {
					isStop = findMatch(gridJewel, i, k, 8,"jewel");
					}
		}
		//WILDCARD
		else if(board.equals("W")) {
			//UPWARD 2 direction
			if(i>1) {
				isStop = findMatch(gridJewel, i, k, 2,"wildcard");
			}
			//DOWNWARD 8 direction
			if(i<gridJewel.length-2&&isStop==false) {
				isStop = findMatch(gridJewel, i, k, 8,"wildcard");	
				}
			//4 direction
			if((k>2)&&isStop==false) {
				isStop = findMatch(gridJewel, i, k, 4,"wildcard");	
				}
			//6 direction
			if((k<gridJewel[i].length-2)&&isStop==false) {
				isStop = findMatch(gridJewel, i, k, 6,"wildcard");	
				}
			//1 direction
			if((k>1&&i>1)&& isStop==false) {
				isStop = findMatch(gridJewel, i, k, 1,"wildcard");	
			}
			// 9 direction
			if((i<gridJewel.length-2 && k<gridJewel[i].length-2)&& isStop==false){
				isStop = findMatch(gridJewel, i, k, 9,"wildcard");
			}
			// 3 direction
			if((i>1&&k<gridJewel[i].length-2)&& isStop==false){
				isStop = findMatch(gridJewel, i, k, 3,"wildcard");	
			}
			//7 direction
			if((i<gridJewel.length-2&&k>1)&& isStop==false){
				isStop = findMatch(gridJewel, i, k, 7,"wildcard");
			}	
		}
		else if(board.equals("/")) {
			
			// 3 direction
			if(i>1&&k<gridJewel[i].length-2) {
				isStop = findMatch(gridJewel, i, k, 3,"math");
			}
			// 7 direction
			if((i<gridJewel.length-2&&k>1)&&isStop==false) {
				isStop= findMatch(gridJewel, i, k, 7,"math");
				
			}
		}
		else if(board.equals("-")) {
			
			//4 direction
			if((k>2)&&isStop==false) {
				isStop = findMatch(gridJewel, i, k, 4,"math");
				}
			//6 direction
			if((k<gridJewel[i].length-2)&&isStop==false) {
				isStop = findMatch(gridJewel, i, k, 6,"math");	
				}
		}
		else if(board.equals("+")) {
			
			//4 direction
			if((k>2)&&isStop==false) {
				isStop = findMatch(gridJewel, i, k, 4,"math");	
				}
			//6 direction
			if((k<gridJewel[i].length-2)&&isStop==false) {
				isStop = findMatch(gridJewel, i, k, 6,"math");
				}
			//2 direction
			if((i>1&&isStop==false)) {
				isStop = findMatch(gridJewel, i, k, 2,"math");	
				}
			//8 direction
			if((i<gridJewel.length-2)&&isStop==false) {
				isStop = findMatch(gridJewel, i, k, 8,"math");	
				}
			
		}
		else if(board.equals("\\")) {
			// 1 direction
			if((k>1&&i>1)&& isStop==false) {
				isStop = findMatch(gridJewel, i, k, 1,"math");
			}
			// 9 direction
			if((i<gridJewel.length-2 && k<gridJewel[i].length-2)&& isStop==false) {
				isStop = findMatch(gridJewel, i, k, 7,"math");
						
				}
		}
		else if(board.equals("|")) {
			//2 direction
			if((i>1&&isStop==false)) {
				isStop = findMatch(gridJewel, i, k, 2,"math");	
				}
			//8 direction
			if((i<gridJewel.length-2)&&isStop==false) {
				isStop = findMatch(gridJewel, i, k, 8,"math");	
				}
		}
		
	}
	public static void slideDown(Jewel[][] grid) {
		for(int r=0;r<5;r++) {for(int i=grid.length-1;1<=i;--i) {
			for(int k =0;k<grid[i].length;k++) {
				if(grid[i][k].getName().equals(" ")) {
					grid[i][k] = grid[i-1][k];
					grid[i-1][k] = new Empty();
					}
				}
			}
		}
	}
	public static int getPoint(String w1,String w2, String w3) {
		int total=0;
		ArrayList<String> array = new ArrayList<String>();
		array.add(w1);array.add(w2);array.add(w3);
		for (String st : array) {
			if(st.equals("D")) {total = total + 30;}
			else if(st.equals("S")) {total = total + 15;}
			else if(st.equals("T")) {total = total + 15;}
			else if(st.equals("W")) {total = total + 10;}
		}
		
		return total;
	}
	
	public static void showGridJewel(Jewel[][] grid,FileWriter writer) throws IOException {
		for(int i = 0;i<grid.length;i++) {
			for(int k = 0;k<grid[i].length;k++) {
				writer.write(grid[i][k]+" ");	
				
			}
			writer.write("\n");
		}
		writer.write("\n");
	}
	public static boolean findMatch(Jewel[][] grid,int i,int k,int direction,String searchType) {
		int i1 = 0;
		int i2 = 0;
		int k1 = 0;
		int k2 = 0;
		if(direction==1) {i1 = -1;i2 = -2;k1= -1; k2= -2; }
		else if(direction==2) {i1 = -1;i2 = -2;k1= 0; k2= 0;}
		else if(direction==3) {i1 = -1;i2 = -2;k1= 1; k2= 2;}
		else if(direction==4) {i1 = 0;i2 = 0;k1= -1; k2= -2;}
		else if(direction==6) {i1 = 0;i2 = 0;k1= 1; k2= 2;}
		else if(direction==7) {i1 = 1;i2 = 2;k1= -1; k2= -2;}
		else if(direction==8) {i1 = 1;i2 = 2;k1= 0; k2= 0;}
		else if(direction==9) {i1 = 1;i2 = 2;k1= 1; k2= 2;}
		String starter = grid[i][k].getName();
		String word1 = grid[i+i1][k+k1].getName();
		String word2 = grid[i+i2][k+k2].getName();
		if(searchType.equals("jewel")) {
			if(word1.equals(starter) &&	word2.equals(starter)
					|| word1.equals("W") &&	word2.equals(starter)
					|| word1.equals(starter) && word2.equals("W")
						) {
				score = score + getPoint(word1, word2,starter);
				grid[i+i1][k+k1]= new Empty();
				grid[i+i2][k+k2]= new Empty();
				grid[i][k]= new Empty();
				return true;
			}		
			else {
				return false;
				}
		}
		else if(searchType.equals("wildcard")) {
			if(word1.equals(word2)
					||word1.equals("W")
					||word2.equals("W")) {
					score = score + getPoint(word1, word2,starter);
					grid[i+i1][k+k1]= new Empty();
					grid[i+i2][k+k2]= new Empty();
					grid[i][k]= new Empty();
					return true;
			}		
			else {
				return false;
				}	
		}
		else if(searchType.equals("math")) {
			if(grid[i+i1][k+k1].getType().equals("math")&&grid[i+i2][k+k2].getType().equals("math")) {
				grid[i+i1][k+k1]= new Empty();
				grid[i+i2][k+k2]= new Empty();
				grid[i][k]= new Empty();		
				score = 60 + score;
				return true;
			}
			else {
			return false;
				}
			}
		else {
			return false;
		}
	}
}
	
	