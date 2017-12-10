import java.util.LinkedList;
import java.util.List;

public class Vertex implements Comparable<Vertex> {

    private String id;
    private double latitude;
    private double longitude;
    /*
    Returns true if the current path is a better way to get to a node than
    its saved path
    Look at this if the method does not work
     */
    public int compareTo(Vertex toCompare) {
        Double dist1 = this.distance;
        Double dist2 = toCompare.distance;

        return Double.compare(dist2, dist1);
    }

    private double distance = Double.MAX_VALUE;
    private Vertex parent;

    public Vertex(String id, double latitude, double longitude) {
       this.id = id;
       this.latitude = latitude;
       this.longitude = longitude;
       this.parent = null;
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
