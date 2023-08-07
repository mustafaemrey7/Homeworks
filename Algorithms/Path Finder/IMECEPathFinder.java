import java.util.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.sound.sampled.DataLine;


public class IMECEPathFinder{
	  public int[][] grid;
	  public int height, width;
	  public int maxFlyingHeight;
	  public double fuelCostPerUnit, climbingCostPerUnit;
	  public int min=0;
	  public int max=0;
	  
	  public IMECEPathFinder(String filename, int rows, int cols, int maxFlyingHeight, double fuelCostPerUnit, double climbingCostPerUnit){

		  grid = new int[rows][cols];
		  this.height = rows;
		  this.width = cols;
		  this.maxFlyingHeight = maxFlyingHeight;
		  this.fuelCostPerUnit = fuelCostPerUnit;
		  this.climbingCostPerUnit = climbingCostPerUnit;
		  this.min=Integer.MAX_VALUE;
		  this.max=0;
			// TODO: fill the grid variable using data from filename
		  File file = new File(filename);
		  try {
			
			 
			Scanner sc = new Scanner(file);
			for (int i = 0; i < rows; i++) {
	            for (int j = 0; j < cols; j++) {
	                grid[i][j] = sc.nextInt();
	                if (grid[i][j]>max) {
						max= grid[i][j];
					}
	                if ( grid[i][j]<min) {
						min= grid[i][j];
					}
	            }
	        }
			
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		  
	  }


	  /**
	   * Draws the grid using the given Graphics object.
	   * Colors should be grayscale values 0-255, scaled based on min/max elevation values in the grid
	   */
	  public void drawGrayscaleMap(Graphics g){

		  // TODO: draw the grid, delete the sample drawing with random color values given below
		  for (int i = 0; i < grid.length; i++)
		  {
			  for (int j = 0; j < grid[0].length; j++) {
				  int value = ((grid[i][j]-min)*256)/(max-min+1);
				  g.setColor(new Color(value, value, value));
				  g.fillRect(j, i, 1, 1);
			  }
		  }
	  }

	/**
	 * Get the most cost-efficient path from the source Point start to the destination Point end
	 * using Dijkstra's algorithm on pixels.
	 * @return the List of Points on the most cost-efficient path from start to end
	 * @throws IOException 
	 */
	public List<Point> getMostEfficientPath(Point start, Point end) throws IOException {
		FileWriter myWriter = null;
		
		
		try {
			myWriter = new FileWriter("out.txt");
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		List<Point> path = new ArrayList<>();
		Point[][] graphPoints=new Point[grid.length][grid[0].length];
		double[][] di=new double[grid.length][grid[0].length];
		for (int i = 0; i < di.length; i++) {
			for (int j = 0; j < di[i].length; j++) {
				di[i][j]=Double.MAX_VALUE;
				
			}
		}
		di[start.y][start.x]=0;
		PriorityQueue<Point> pq = new PriorityQueue<>();
		for (int i = 0; i < graphPoints.length; i++) {
			for (int j = 0; j < graphPoints[i].length; j++) {
				Point temp=new Point(i, j);
				temp.distanceFromSource=Integer.MAX_VALUE;
				temp.parent=null;
				graphPoints[i][j]=temp;
			}
		}
		graphPoints[start.y][start.x].distanceFromSource=0;
		Set<Point> settled = new HashSet<Point>();
		Point startPoint=new Point(start.y,start.x);
		startPoint.distanceFromSource=0;
		startPoint.parent=null;
		
		pq.add(startPoint);
		while(!pq.isEmpty()) {
		
			Point current=pq.poll();		
			
			if (settled.contains(current)) {
				continue;
			}
			
			settled.add(current);		
			if (current.x==end.x&&current.y==end.y) {
				
				
				break;
			}	
			for (int j = 0; j < 3; j++) {
			    for (int i = 0; i < 3; i++) {
			    
			        if (i == 0 && j == 0) {
			            continue;
			        }
			        int first=0;
			        int second=0;
			        try {
			        	if (j==0&&i==1) {second=-1;first=0;	}
			        	if (j==0&&i==2) {second=+1;first=0;	}
			        	if (j==1&&i==0) {first=-1;second=0;	}
			        	if (j==1&&i==1) {first=+1;second=0;	}
			        	if (j==1&&i==2) {second=-1;first=1;}
			        	if (j==2&&i==0) {second=-1;first=-1;}
			        	if (j==2&&i==1) {second=1;first=1;	}
			        	if (j==2&&i==2) {second=1;first=-1;	}
			        	int neighborX = current.x + first;
			            int neighborY = current.y + second; 
			            
			            if (grid[neighborX][neighborY] < maxFlyingHeight) {
			                    Point d = graphPoints[current.x][current.y];
			                    Point u = graphPoints[neighborX][neighborY];
			                    if ((di[d.x][d.y] + getC(d, u)) < di[u.x][u.y]) {
			                        di[u.x][u.y] = di[d.x][d.y] + getC(d, u);
			                        
			                        graphPoints[neighborX][neighborY].distanceFromSource = di[current.x][current.y] + getC(d, u);
			                        graphPoints[neighborX][neighborY].parent=d;
			                        graphPoints[neighborX][neighborY].path.add(d);
			                    }
			                    if (!settled.contains(u)) {		
			                        pq.add(u);
			                    }
			                }
			            
			        } catch (Exception e) {
			            // TODO: handle exception
			        }
			    }
			}	
		}
		Point it=graphPoints[end.y][end.x];
		int e=1;
		while (it.parent!=null) {
			
			Point temp1=new Point(it.y, it.x);
			temp1.distanceFromSource=di[it.x][it.y];
			myWriter.write(temp1.x+","+temp1.y+" cost: "+temp1.distanceFromSource+"\n");
			e=e+1;
			path.add(temp1);
			it=it.parent;
		}
		
		path.add(start);
		Collections.reverse(path);
		
		
		myWriter.close();
		return path;
	}
	/**
	 * Calculate the most cost-efficient path from source to destination.
	 * @return the total cost of this most cost-efficient path when traveling from source to destination
	 */
	public double getMostEfficientPathCost(List<Point> path){
		double totalCost = 0.0;
		
		// TODO: Your code goes here, use the output from the getMostEfficientPath() method
		
		totalCost=path.get(path.size()-1).distanceFromSource;
		return totalCost;
	}
	public double getC(Point x,Point y) {
		double dist=Math.sqrt(Math.pow((x.x-y.x),2)+Math.pow((x.y-y.y),2));
		double cost= ((dist*fuelCostPerUnit)+(climbingCostPerUnit*heightImpact(x,y)));
		
		return cost;
	}
	public int heightImpact(Point x,Point y) {
		if (grid[x.getX()][x.getY()]>=grid[y.getX()][y.getY()]) {
			return 0;
			
		}
		else {
			return grid[y.getX()][y.getY()]-grid[x.getX()][x.getY()];
		}
		
	}

	/**
	 * Draw the most cost-efficient path on top of the grayscale map from source to destination.
	 */
	public void drawMostEfficientPath(Graphics g, List<Point> path){
		for (Point point : path) {
			g.setColor(new Color(0, 255, 0));
			g.fillRect(point.x, point.y, 1, 1);
		}
	}

	/**
	 * Find an escape path from source towards East such that it has the lowest elevation change.
	 * Choose a forward step out of 3 possible forward locations, using greedy method described in the assignment instructions.
	 * @return the list of Points on the path
	 */
	public List<Point> getLowestElevationEscapePath(Point start){
		List<Point> pathPointsList = new ArrayList<>();
		Point[][] graphPoints=new Point[grid.length][grid[0].length];
		for (int i = 0; i < graphPoints.length; i++) {
			for (int j = 0; j < graphPoints[i].length; j++) {
				Point temp=new Point(i, j);
				temp.distanceFromSource=Integer.MAX_VALUE;
				temp.parent=null;
				graphPoints[i][j]=temp;
			}
		}	
		Point it=new Point(start.y, start.x);
		pathPointsList.add(start);
		while (true) {
			min =Integer.MAX_VALUE;
			int act=0;
			int c=0;
			if (it.y==grid[0].length-1) {
				break;
			}
			for (int i = 0; i < 3; i++) {
				int f=0;int s=0;
				if (i==0) {f=0;s=+1;}	if (i==1) {f=-1;s=+1;}	if (i==2){f=+1;s=1;}				
				try {
					int dif = Math.abs(grid[it.x][it.y]-grid[it.x+f][it.y+1]);
					if (dif<min) {
						min=dif;
						act=f;
						c=Math.abs(grid[it.x][it.y]-grid[it.x+f][it.y+1]);	
					}
				} catch (Exception e) {
					// TODO: handle exception
				}	
			}
			graphPoints[it.x+act][it.y+1].cost=c;
			Point temp=new Point(graphPoints[it.x+act][it.y+1].y, graphPoints[it.x+act][it.y+1].x);
			temp.cost=c;
			pathPointsList.add(temp);
			it=graphPoints[it.x+act][it.y+1];
		}
		return pathPointsList;
	}


	/**
	 * Calculate the escape path from source towards East such that it has the lowest elevation change.
	 * @return the total change in elevation for the entire path
	 */
	public int getLowestElevationEscapePathCost(List<Point> pathPointsList){
		int totalChange = 0;
		for (Point point : pathPointsList) {
			totalChange=totalChange+point.cost;
		}
		return totalChange;
	}


	/**
	 * Draw the escape path from source towards East on top of the grayscale map such that it has the lowest elevation change.
	 */
	public void drawLowestElevationEscapePath(Graphics g, List<Point> pathPointsList){
		for (Point point : pathPointsList) {
			g.setColor(new Color	(252, 209, 42));
			g.fillRect(point.x, point.y, 1, 1);
		}
	}
	


}
