import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.io.File;
import java.util.*;

public class TravelMap {

    public Map<Integer, Location> locationMap = new HashMap<>();
    public List<Location> locations = new ArrayList<>();

    
    public List<Trail> trails = new ArrayList<>();
  

    public void initializeMap(String filename) {
    	File file = new File(filename);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = null;
		try {
			db = dbf.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Document doc = null;
		try {
			doc = db.parse(file);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        doc.getDocumentElement().normalize();       
        NodeList locationNodes = doc.getElementsByTagName("Location");
        for (int i = 0; i < locationNodes.getLength(); i++) {
        	Node node = locationNodes.item(i);
        	if (node.getNodeType()==Node.ELEMENT_NODE) {
				Element element=(Element) node;
				int id = Integer.parseInt(element.getElementsByTagName("Id").item(0).getTextContent());
				 String name = element.getElementsByTagName("Name").item(0).getTextContent();
				 Location location = new Location(name, id);
				 locationMap.put(id, location);
				 locations.add(location);
            }    
        }
        NodeList trailNodes = doc.getElementsByTagName("Trail");
        for (int i = 0; i < trailNodes.getLength(); i++) {
        	Node node = trailNodes.item(i);
        	if (node.getNodeType()==Node.ELEMENT_NODE) {
        		 Element trailElement = (Element) trailNodes.item(i);
                 int source = Integer.parseInt(trailElement.getElementsByTagName("Source").item(0).getTextContent());
                 int destination = Integer.parseInt(trailElement.getElementsByTagName("Destination").item(0).getTextContent());
                 int danger = Integer.parseInt(trailElement.getElementsByTagName("Danger").item(0).getTextContent());
                 
                 Trail trail = new Trail(locationMap.get(source), locationMap.get(destination), danger); 
                 trails.add(trail);
			}
        }  
    } 
    public List<Trail> getSafestTrails() {
        List<Trail> safestTrails = new ArrayList<>();
        List<Location> unVisitedLocations = locations;
        Collections.sort(trails);
        PriorityQueue<Trail> pq = new PriorityQueue<>();
        // add all trails to the priority queue
        for (Trail trail : trails) {
			pq.add(trail);	
		}     
        while (!unVisitedLocations.isEmpty()) {
       	
			Trail minTrail=pq.poll();
			if (minTrail==null) {
				break;
			}
			else {
				if (!isCycle(safestTrails, minTrail)) {
					safestTrails.add(minTrail);
					delLoc(unVisitedLocations, minTrail);
				}
			}
		}   
        return safestTrails;
    }
    public static void delLoc(List<Location> unVisitList,Trail t) {  	
		for (int i = 0; i < unVisitList.size(); i++) {
			if (unVisitList.get(i).id==t.source.id||unVisitList.get(i).id==t.destination.id) {
				unVisitList.remove(i);
			}	
		}
	}
    public boolean isCycle(List<Trail> list, Trail addTrail) {	 
        Location start = addTrail.source;   
        Set<Location> visited = new HashSet<>();
        Stack<Location> stack = new Stack<>();
        stack.push(start);
        while (!stack.isEmpty()) {
            Location current = stack.pop();
            visited.add(current); 
            if (current == addTrail.destination) {
                return true;
            } 
            for (Trail t : list) {
                if (t.source == current && !visited.contains(t.destination)) {
                    stack.push(t.destination);
                } else if (t.destination == current && !visited.contains(t.source)) {
                    stack.push(t.source);
                }
            }
        } 
        return false; 
    }

    public void printSafestTrails(List<Trail> safestTrails) {
    	System.out.println("Safest trails are:");
    	int totalDanger=0;
    	for (Trail trail : safestTrails) {
			System.out.println("The trail from "+trail.source.name+" to "+trail.destination.name+" with danger "+trail.danger);
			totalDanger=totalDanger+trail.danger;
    	}
    	System.out.println("Total Danger: "+totalDanger);
        // Print the given list of safest trails conforming to the given output format.
        // TODO: Your code here
    }
    
}
