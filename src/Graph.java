import java.util.List;
import java.util.ArrayList;

public class Graph {

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

    //  Add edges and
    public boolean addEdge(Edge toAdd) {
        if (!vertices.contains(toAdd.start)) {
            return vertices.add(toAdd.start);
        }
        if (!vertices.contains(toAdd.end)) {
            return vertices.add(toAdd.end);
        }

        return edges.add(toAdd);
    }

    public List<Vertex> getAdjacent(Vertex node) {
        List<Vertex> adjacent = new ArrayList<>();
        //  TODO: find all adjacent vertices (that is, the vertices that share an edge)
        return adjacent;
    }
}