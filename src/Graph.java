import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class Graph {
	public HashMap<String, ArrayList<Vertex>> adjacencyList = new HashMap<String, ArrayList<Vertex>>();
    public HashMap<String, Vertex> vertices;
    public HashMap<String, Edge> edges;
    public int numVertices;
    public int numEdges;

    public Graph(HashMap<String, Vertex> vertices, HashMap<String, Edge> edges) {
        this.vertices = vertices;
        this.edges = edges;
        this.buildAdjacencyList();
        numVertices = 0;
        numEdges = 0;
    }

    public HashMap<String, Vertex> getVertices() {
        return vertices;
    }

    public HashMap<String, Edge> getEdges() {
        return edges;
    }

    public void addVertex(Vertex toAdd) {
        vertices.put(toAdd.getID(), toAdd);
    }

    //  Add edges
    public void addEdge(Edge toAdd) {	
    		edges.put(toAdd.getID(), toAdd);
    }

    
    
    public void buildAdjacencyList() {
	    	edges.forEach((key, value) -> {
	    		Edge e = edges.get(key);
	    		Vertex start = e.getStart();
	    		Vertex end = e.getEnd();
	    		
	    		if (adjacencyList.containsKey(start.getID()) && adjacencyList.containsKey(end.getID())) {
	    		
	    		
	    			ArrayList<Vertex> list1 = adjacencyList.get(start.getID());
	    			list1.add(end);
	    			adjacencyList.put(start.getID(), list1);
	    			ArrayList<Vertex> list2 = adjacencyList.get(end.getID());
	    			list2.add(start);
	    			adjacencyList.put(end.getID(), list2);
	    			
	    		}else if (adjacencyList.containsKey(start.getID())){
	    			ArrayList<Vertex> list1 = adjacencyList.get(start.getID());
	    			
	    			list1.add(end);
	    			adjacencyList.put(start.getID(), list1);
	    			
	    			
	    			ArrayList<Vertex> list2 = new ArrayList<Vertex>();
	    			list2.add(start);
	    			adjacencyList.put(end.getID(), list2);
	    		}else if (adjacencyList.containsKey(end.getID())) {
	    			ArrayList<Vertex> list2 = adjacencyList.get(end.getID());
	    			list2.add(start);
	    			adjacencyList.put(end.getID(), list2);
	    			
	    			ArrayList<Vertex> list1 = new ArrayList<Vertex>();
	    			list2.add(end);
	    			adjacencyList.put(start.getID(), list1);
	    		}
	    		else {
	    			
	    			ArrayList<Vertex> list1 = new ArrayList<Vertex>();
	    			ArrayList<Vertex> list2 = new ArrayList<Vertex>();

    				list1.add(end);
    				list2.add(start);
    				adjacencyList.put(start.getID(), list1);
    				adjacencyList.put(end.getID(), list2);
	    		}
	    		});
    		
    }
    
    
    public ArrayList<Vertex> shortestPath(String startID, String endID) {
    		PriorityQueue<Vertex> queue = new PriorityQueue<Vertex>();
    		Vertex start = vertices.get(startID);
    		start.setDistance(0);
    		queue.add(start);
    		
    		
    		while (!queue.isEmpty()) {
    			Vertex min = queue.poll();
			if (min.getID() == endID) {
				break;
			}
    			ArrayList<Vertex> adjacentVertices = getAdjacentVertices(vertices.get(min.getID()));
    			for (Vertex adjVertex: adjacentVertices){
	    				if (min.getDistance() + getWeight(min, adjVertex) < adjVertex.getDistance()) {
	    					queue.remove(adjVertex);
	    					adjVertex.setParent(min);
	    					adjVertex.setDistance(min.getDistance() + getWeight(min, adjVertex));
	    					queue.add(adjVertex);


	    				}
	    				
	    				
    				
    			}
    			

    		}
    		
    		ArrayList<Vertex> path = new ArrayList<Vertex>();
    		Vertex vertex = vertices.get(endID);
    		while (vertex != null) {
    			path.add(0, vertex);
    			vertex = vertex.getParent();
    			
    		}
         
    		return path;

    }
	
	private double getWeight(Vertex start2, Vertex end2) {
		final double R = 6372.8;
		double latDiff = Math.toRadians(end2.getLatitude() - start2.getLatitude());
		double longDiff = Math.toRadians(end2.getLongitude() - start2.getLongitude());
		double startLat = Math.toRadians(start2.getLatitude());
		double endLat = Math.toRadians(end2.getLatitude());
		
		double a = Math.pow(Math.sin(latDiff/2), 2) + Math.pow(Math.sin(longDiff)/2, 2) * Math.cos(startLat) * Math.cos(endLat);
		double c = 2 * Math.asin(Math.sqrt(a));
		return R * c;
	}

	private ArrayList<Vertex> getAdjacentVertices(Vertex start) {
		return adjacencyList.get(start.getID());
	}
}





