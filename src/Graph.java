import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

// Authors: Ali Hashim and James Emery
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

    public void addEdge(Edge toAdd) {	
    		edges.put(toAdd.getID(), toAdd);
    }

    
    // Builds the adjacency list
    public void buildAdjacencyList() {
	    	edges.forEach((key, value) -> {
	    		Edge e = edges.get(key);
	    		Vertex start = e.getStart();
	    		Vertex end = e.getEnd();
	    		
	    		// If both keys already exist in the adjacency list, just add to the list
	    		if (adjacencyList.containsKey(start.getID()) && adjacencyList.containsKey(end.getID())) {
	    		
	    		
	    			ArrayList<Vertex> list1 = adjacencyList.get(start.getID());
	    			list1.add(end);
	    			adjacencyList.put(start.getID(), list1);
	    			ArrayList<Vertex> list2 = adjacencyList.get(end.getID());
	    			list2.add(start);
	    			adjacencyList.put(end.getID(), list2);
	    		// If just the startID key exists, add to the start list and create a list for the end point
	    		}else if (adjacencyList.containsKey(start.getID())){
	    			ArrayList<Vertex> list1 = adjacencyList.get(start.getID());
	    			
	    			list1.add(end);
	    			adjacencyList.put(start.getID(), list1);
	    			
	    			
	    			ArrayList<Vertex> list2 = new ArrayList<Vertex>();
	    			list2.add(start);
	    			adjacencyList.put(end.getID(), list2);
	    			
	    			// If just the endID exists
	    		}else if (adjacencyList.containsKey(end.getID())) {
	    			ArrayList<Vertex> list2 = adjacencyList.get(end.getID());
	    			list2.add(start);
	    			adjacencyList.put(end.getID(), list2);
	    			
	    			ArrayList<Vertex> list1 = new ArrayList<Vertex>();
	    			list2.add(end);
	    			adjacencyList.put(start.getID(), list1);
	    		}
	    		// If none of the IDs are in the HashMap yet.
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
    
    // Implementing Dijkstra
    public ArrayList<Vertex> shortestPath(String startID, String endID) {
		ArrayList<Vertex> path = new ArrayList<Vertex>();

		
		if (startID == endID) {
			System.err.println("These are the same two points");
			path.add(vertices.get(startID));
			return path;
		}
		
		if (!vertices.containsKey(startID)) {
			System.err.println("The start point does not exist");
		}
		
		if (!vertices.containsKey(endID)) {
			System.err.println("The end point does not exist");
		}
		
		// Adds the start vertex to a priority queue with the distance of 0
    		PriorityQueue<Vertex> queue = new PriorityQueue<Vertex>();
    		Vertex start = vertices.get(startID);
    		start.setDistance(0);
    		queue.add(start);
    		
    		// While the queue is empty, retrieve the minimum vertex in the queue, find its adjacent vertices, 
    		// update the priority queue with an updated weight if needed. It breaks if it reaches the end point
    		while (!queue.isEmpty()) {
    			Vertex min = queue.poll();
			if (min.getID() == endID) {
				break;
			}
    			ArrayList<Vertex> adjacentVertices = getAdjacentVertices(vertices.get(min.getID()));
    			for (Vertex adjVertex: adjacentVertices){
	    				if (min.getDistance() + Helper.getWeight(min, adjVertex) < adjVertex.getDistance()) {
	    					queue.remove(adjVertex);
	    					adjVertex.setParent(min);
	    					adjVertex.setDistance(min.getDistance() + Helper.getWeight(min, adjVertex));
	    					queue.add(adjVertex);
	    					

	    				}
    			}
    		}
    		
    		
//    		Iterates through the end vertex's parents until its parent is null to get the path in reverse order
    		Vertex vertex = vertices.get(endID);
    		while (vertex != null) {
    			path.add(0, vertex);
    			vertex = vertex.getParent();
    		}
    		
    		// If there is no path to the endID then the start and end are not connected
    		if (path.size() == 1) {
    			System.err.println("These intersections are not connected");
    		}
         
    		return path;

    }
	


	private ArrayList<Vertex> getAdjacentVertices(Vertex start) {
		return adjacencyList.get(start.getID());
	}
}





