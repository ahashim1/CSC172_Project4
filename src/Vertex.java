import java.util.LinkedList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class Vertex {

    private String id;
    private double latitude;
    private double longitude;

    private List<Vertex> path = new LinkedList<>();
    private double distance = Double.MAX_VALUE;

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

    public void addToPath(Vertex toAdd) {
        path.add(toAdd);
    }

    public List<Vertex> getPath() {
        return path;
    }
    
}
