
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;


public class Kingdom {
	 public ArrayList<ArrayList<Integer>> graph=new ArrayList<ArrayList<Integer>>();
	 public ArrayList<ArrayList<Integer>> connections=new ArrayList<ArrayList<Integer>>();
     public Set<Integer> cities = new HashSet<>();
	 // TODO: You should add appropriate instance variables.
	 public void initializeKingdom(String filename) {
    	File file = new File(filename);
    	Scanner sc1 = null;
		try {
			sc1 = new Scanner(file);
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}    	
    	int row=0;
    	while (sc1.hasNextLine()) {
    		String[] line=sc1.nextLine().split(" ");
    		if(row==0) {
    			initializeArray(line.length+1);
    		}
    		for (int i = 0; i < line.length; i++) {
    			 if (Integer.parseInt(line[i])==1) {		
					graph.get(i+1).add(row+1);
					graph.get(row+1).add(i+1);
					connections.get(row+1).add(i+1);
				}				
			}
    		cities.add(row+1);	
    		row=row+1;
		}
    	sc1.close();  
    }
    public List<Colony> getColonies() {
        List<Colony> colonies = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        for (int city : cities) {
            if (!visited.contains(city)) {
                
                Colony c=new Colony();
                dfs(city, graph, visited, c.cities);
                for (int i = 0; i < c.cities.size(); i++) {
                	
					c.roadNetwork.put(c.cities.get(i), connections.get(c.cities.get(i)));
				}
                colonies.add(c);
            }
        }     
        return colonies;
    }
    public void printColonies(List<Colony> discoveredColonies) {
    	System.out.println("Discovered colonies are:");
    	int i = 1;
    	for (Colony colony : discoveredColonies) {
    		Collections.sort(colony.cities);
    		System.out.print("Colony " +i+ ": [");	
    		for (int j = 0; j < colony.cities.size(); j++) {
    			if (j==colony.cities.size()-1) {
					System.out.println(colony.cities.get(j)+"]");
				}
    			else {
					System.out.print(colony.cities.get(j)+", ");
				}		
			}
    		i=i+1;
		}    
    }
    public void dfs(int city, ArrayList<ArrayList<Integer>> graph, Set<Integer> visited, List<Integer> colony) {
        colony.add(city);
        visited.add(city);      
        for (Integer adj :graph.get(city)) {
			if(!visited.contains(adj)) {
				dfs(adj, graph, visited, colony);
			}
        }
    }
    private void initializeArray(int n) {
    	for (int i = 0; i < n; i++) {
			graph.add(new ArrayList<Integer>());
			connections.add(new ArrayList<Integer>());
		}
    }  
}
