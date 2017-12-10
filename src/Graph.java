import java.util.List;
import java.util.ArrayList;

public class Graph {
	public ArrayList<ArrayList<Vertex>> adjacencyList;
    public List<Vertex> vertices;
    public List<Edge> edges;
    public int numVertices;
    public int numEdges;

    public Graph(List<Vertex> vertices, List<Edge> edges) {
        this.vertices = vertices;
        this.edges = edges;

        numVertices = 0;
        numEdges = 0;
    }

    public List<Vertex> getVertices() {
        return vertices;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public boolean addVertex(Vertex toAdd) {
        return vertices.add(toAdd);
    }

    //  Add edges
    public boolean addEdge(Edge toAdd) {
        if (!vertices.contains(toAdd.getStart())) {
            return vertices.add(toAdd.getStart());
        }
        if (!vertices.contains(toAdd.getEnd())) {
            return vertices.add(toAdd.getEnd());
        }

        return edges.add(toAdd);
    }

    public List<Vertex> getAdjacent(Vertex node) {
        List<Vertex> adjacent = new ArrayList<>();
        //
        for (Edge i : edges) {
            //  Get all edges that contains the node, return a list of nodes in the edges that aren't the original
            if (i.contains(node)) {
                if (i.getStart() != node) {
                    adjacent.add(i.getStart());
                }
                else {
                    adjacent.add(i.getEnd());
                }
            }
        }
        return adjacent;
    }

    public boolean isConnected(Vertex node1, Vertex node2) {
        for (Edge i : edges) {
            if (i.getStart() == node1 && i.getEnd() == node2) {
                return true;
            }
            else if (i.getStart() == node2 && i.getEnd() == node1) {
                return true;
            }
        }
        return false;
    }
}





