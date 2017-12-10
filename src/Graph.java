import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class Graph {
	public HashMap<String, ArrayList<Vertex>> adjacencyList;
    public HashMap<String, Vertex> vertices;
    public HashMap<String, Edge> edges;
    public int numVertices;
    public int numEdges;

    public Graph(HashMap<String, Vertex> vertices, HashMap<String, Edge> edges) {
        this.vertices = vertices;
        this.edges = edges;

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
	    			adjacencyList.put(start.getID(), list2);
	    			
	    		}else if (adjacencyList.containsKey(start.getID())){
	    			ArrayList<Vertex> list1 = adjacencyList.get(start.getID());
	    			list1.add(end);
	    			adjacencyList.put(start.getID(), list1);
	    		}else if (adjacencyList.containsKey(end.getID())) {
	    			ArrayList<Vertex> list2 = adjacencyList.get(end.getID());
	    			list2.add(start);
	    			adjacencyList.put(start.getID(), list2);
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
//    public List<Vertex> getAdjacent(Vertex node) {
//        List<Vertex> adjacent = new ArrayList<>();
//        //
//        
//        
//        for (Edge i : edges) {
//            //  Get all edges that contains the node, return a list of nodes in the edges that aren't the original
//            if (i.contains(node)) {
//                if (i.getStart() != node) {
//                    adjacent.add(i.getStart());
//                }
//                else {
//                    adjacent.add(i.getEnd());
//                }
//            }
//        }
//        return adjacent;
//    }
//
//    public boolean isConnected(Vertex node1, Vertex node2) {
//        for (Edge i : edges) {
//            if (i.getStart() == node1 && i.getEnd() == node2) {
//                return true;
//            }
//            else if (i.getStart() == node2 && i.getEnd() == node1) {
//                return true;
//            }
//        }
//        return false;
//    }
}





