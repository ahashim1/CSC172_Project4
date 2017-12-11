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
    		final double R = 6372.8;
    		double latDiff = Math.toRadians(end2.getLatitude() - start2.getLatitude());
    		double longDiff = Math.toRadians(end2.getLongitude() - start2.getLongitude());
    		double startLat = Math.toRadians(start2.getLatitude());
    		double endLat = Math.toRadians(end2.getLatitude());
    		
    		double a = Math.pow(Math.sin(latDiff/2), 2) + Math.pow(Math.sin(longDiff)/2, 2) * Math.cos(startLat) * Math.cos(endLat);
    		double c = 2 * Math.asin(Math.sqrt(a));
    		return R * c;
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