public class Edge {

    private String id;
    private Vertex start;
    private Vertex end;
    private double weight;

    public Edge(String id_String, Vertex startVertex, Vertex endVertex) {
        id = id_String;
        start = startVertex;
        end = endVertex;
        weight = Helper.getWeight(start, end);
    }

    
    public String getID() {
    		return id;
    }
    
    public Vertex getStart() {
    		return start;
    }
    
    public Vertex getEnd() {
    		return end;
    }
    
    public double getWeight() {
    		return weight;
    }

    public boolean contains(Vertex test) {
        Vertex temp1 = start;
        Vertex temp2 = end;

        if (test == temp1 || test == temp2) {
            return true;
        }
        return false;
    }

	
}