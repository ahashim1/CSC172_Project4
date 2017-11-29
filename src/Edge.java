public class Edge {

    private static String id;
    private static Vertex start;
    private static Vertex end;
    private static int weight;

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