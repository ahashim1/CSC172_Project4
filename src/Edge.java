public class Edge {

    private String id;
    private Vertex start;
    private Vertex end;
    private double weight;

    public Edge(String id_String, Vertex startVertex, Vertex endVertex) {
        id = id_String;
        start = startVertex;
        end = endVertex;
        weight = getWeightFromVertices(start, end);
    }

    private double getWeightFromVertices(Vertex start2, Vertex end2) {
    		double weight = Math.sqrt(Math.pow(end2.getLatitude() - start2.getLatitude(), 2) + Math.pow(end2.getLongitude() - start2.getLongitude(), 2));
		return weight;
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