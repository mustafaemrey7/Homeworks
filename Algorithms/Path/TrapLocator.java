import java.util.*;

public class TrapLocator {
    public List<Colony> colonies;

    public TrapLocator(List<Colony> colonies) {
        this.colonies = colonies;
    }

    public List<List<Integer>> revealTraps() {
        List<List<Integer>> traps = new ArrayList<>();
        for (Colony c : colonies) {
        	List<Integer> list=new ArrayList<>();
			Set<Integer> temp=checkCycle(c);
			for (int val:temp) {
				list.add(val);
			}	
			if (list.size()==c.cities.size()) {
				traps.add(new ArrayList<Integer>());
			}
			else {
				traps.add(list);
			}
		}
        return traps;
    }
    public void printTraps(List<List<Integer>> traps) {       
    	System.out.println("Danger exploration conclusions:");
    	for (int i = 0; i <traps.size(); i++) {
    		Collections.sort(traps.get(i));
    		if (traps.get(i).size()==0) {
    			System.out.println("Colony "+(i+1)+": Safe");
			}
    		else {
				System.out.println("Colony "+(i+1)+": Dangerous. Cities on the dangerous path: "+traps.get(i));
			}			
		}
    }
    public boolean check(Colony colony,int city,Set<Integer> visited,Set<Integer> inStack,Set<Integer> s) {
    	if (inStack.contains(city)) {
			return true;
		}
    	if (visited.contains(city)) {
			return false;
		}	
    	visited.add(city);
    	inStack.add(city); 	
    	for (Integer adj : colony.roadNetwork.get(city)) {
			if (check(colony, adj, visited, inStack,s)) { 	
				if (!s.contains(adj)) {
					s.add(adj);
				}
				else {
					break;
				}
				return true;
			}	
		}
    	inStack.remove(city);
    	return false;
	}
    public Set<Integer> checkCycle(Colony graph) {
    	Set<Integer> visited=new HashSet<>();
    	Set<Integer> stack=new HashSet<>();
    	Set<Integer> path=new HashSet<>();
		for (Integer city : graph.cities) {
			if (!visited.contains(city)) {
				if (check(graph, city, visited, stack,path)) {	
					return stack;
				}
			}
		}
		return stack;
    }

}
