public class Edge {

    public static String id;
    public static Vertex start;
    public static Vertex end;
    public static int weight;

    public Edge(String id, Vertex start, Vertex end, int weight) {
        this.id = id;
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    public static String getID() {
        return id;
    }

    public static Vertex getStart() {
        return start;
    }

    public static Vertex getEnd() {
        return end;
    }

    public static int getWeight() {
        return weight;
    }
}