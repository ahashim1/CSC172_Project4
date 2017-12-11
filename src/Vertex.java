import java.util.LinkedList;
import java.util.List;

public class Vertex implements Comparable<Vertex> {

    private String id;
    private double latitude;
    private double longitude;
    public int compareTo(Vertex toCompare) {
        

        return Double.compare(distance, toCompare.getDistance());
    }

    private double distance = Double.POSITIVE_INFINITY;
    private Vertex parent;

    public Vertex(String id, double latitude, double longitude) {
       this.id = id;
       this.latitude = latitude;
       this.longitude = longitude;
    }
    
    public String getID() {
    		return id;
    }
    
    public double getLatitude() {
    		return latitude;
    }
    
    public double getLongitude() {
    		return longitude;
    }

	public void setDistance(double d) {
        distance = d;
    }

    public double getDistance() {
        return distance;
    }
    
    public void setParent(Vertex v) {
    		parent = v;
    }
    
    public Vertex getParent() {
    		return parent;
    }
    


    
   
    
}
