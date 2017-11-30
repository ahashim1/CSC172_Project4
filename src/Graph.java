import java.util.ArrayList;

public class Graph {
	static ArrayList<Edge> adjacentEdges = new ArrayList<Edge>();
	static int numVertices;
	static int numEdges;
	
	public Graph() {
		numVertices = 0;
		numEdges = 0;
	}
	
	static void insertEdge(Edge e) {
		numEdges += 1;
		adjacentEdges.add(e);
	}
    
	static boolean removeEdge(Edge e) {
		return false;
	}
	
	static void insertVertex(Vertex v) {
	}
    
	static boolean removeVertex(Vertex v) {
		return false;
	}
	
	
	
}
