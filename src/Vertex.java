
public class Vertex {

    public static String id;
    public static float latitude;
    public static float longitude;

    public Vertex(String id, float latitude, float longitude) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public static String getid() {
        return id;
    }

    public static float getLatitude() {
        return latitude;
    }

    public static float getLongitude() {
        return longitude;
    }
}
