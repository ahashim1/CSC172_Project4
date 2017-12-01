public class Edge {

    public static String id;
    public static Vertex start;
    public static Vertex end;
    public static double weight;

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

    public static double getWeight() {
        return weight;
    }

    public static boolean contains(Vertex test) {
        Vertex temp1 = start;
        Vertex temp2 = end;

        if (test == temp1 || test == temp2) {
            return true;
        }
        return false;
    }
}