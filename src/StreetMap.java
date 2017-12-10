import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class StreetMap extends JPanel {

	static ArrayList<Vertex> vertices = new ArrayList<>();
    static ArrayList<Edge> edges = new ArrayList<>();

    static ArrayList<Vertex> evaluatedNodes = new ArrayList<>();
    static ArrayList<Vertex> unevaluatedNodes = new ArrayList<>();

//    static HashMap<Vertex, Vertex> predecessors;
//    static HashMap<Vertex, Double> distances;

    static double minLongitude = Double.MAX_VALUE;
    static double minLatitude = Double.MAX_VALUE;
    static double maxLongitude = -1 * Double.MAX_VALUE;
    static double maxLatitude = -1 * Double.MAX_VALUE;
    static double maxDistance = -1 * Double.MAX_VALUE;
    static double width = 420;
    static double height = 400;
    static String input;
    
    @Override
    public void paintComponent(Graphics g)
    {
    	
    	
    		readInput();
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLACK);
        
        
        double latitudeScale = this.getWidth()/Math.abs(maxLatitude - minLatitude) ;
        double longitudeScale = this.getHeight()/Math.abs(maxLongitude - minLongitude) ;
       
        for (int i = 0; i<edges.size(); i++) {
			Edge e = edges.get(i);
			int startX = (int) ((e.getStart().getLongitude() - minLongitude) * longitudeScale);
			int startY = (int) (this.getHeight() - ((e.getStart().getLatitude() - minLatitude) * latitudeScale));
			
			
			int endX = (int) ((e.getEnd().getLongitude() - minLongitude) * longitudeScale);
			int endY = (int) (this.getHeight() - ((e.getEnd().getLatitude() - minLatitude) * latitudeScale));

			g2.drawLine(startX, startY, endX, endY);

        }

    }

    //  Driver method
    public static void calculate(Vertex start) {
        start.setDistance(0.0);
        unevaluatedNodes.add(start);

        while(unevaluatedNodes.size() > 0) {
            Vertex node = getMinimum(unevaluatedNodes);
            evaluatedNodes.add(node);
            unevaluatedNodes.remove(node);
            findShortestPath(node);
        }
    }

//    //  Evaluate predecessors list
//    public static LinkedList<Vertex> findPath(Vertex end) {
//        LinkedList<Vertex> path = new LinkedList<>();
//        Vertex temp = end;
//
//        if (predecessors.get(end) == null) {
//            return null;
//        }
//
//        path.add(temp);
//        while (predecessors.get(temp) != null) {
//            temp = predecessors.get(temp);
//            path.add(temp);
//        }
//
//        Collections.reverse(path);
//        return path;
//    }

//    public static List<Vertex> findPath(Vertex end) {
//        return end.getPath();
//    }


    //  Perform DJ Ikstra's algorithm
    private static void findShortestPath(Vertex node) {
        ArrayList<Vertex> adjacents = getAdjacent(node);
        for (Vertex v : adjacents) {
            if (closestDistance(v) > closestDistance(node) + getDistance(node, v)) {
 //               distances.put(v, closestDistance(node) + getDistance(node, v));
                v.setDistance(closestDistance(node) + getDistance(node, v));
//                predecessors.put(v,node);
                v.addToPath(node);
                unevaluatedNodes.add(v);
//                System.out.println(v.getID());
            }
        }
    }

    //  Return all adjacent, unevaluated nodes
    private static ArrayList<Vertex> getAdjacent(Vertex node) {
        ArrayList<Vertex> adjacent = new ArrayList<>();
        for (Edge e : edges) {
            if (e.getStart().equals(node) && !isEvaluated(e.getEnd())) {
                adjacent.add(e.getEnd());
            }
            else if (e.getEnd().equals(node) && !isEvaluated(e.getStart())) {
                adjacent.add(e.getStart());
            }
        }
        return adjacent;
    }

    //  Return weight of edge connecting two vertices
    private static double getDistance(Vertex source, Vertex target) {
        for (Edge e : edges) {
            if (e.getStart().equals(source) && e.getEnd().equals(target)
                    || e.getStart().equals(target) && e.getEnd().equals(source)) {
                return e.getWeight();
            }
        }
        throw new RuntimeException("Not actually adjacent.");
    }

    //  Returns the closest vertices
    private static Vertex getMinimum(ArrayList<Vertex> vertices) {
        Vertex min = null;
        for (Vertex v : vertices) {
            if (min == null) {
                min = v;
            }
            else {
                if (closestDistance(v) < closestDistance(min)) {
                    min = v;
                }
            }
        }
        return min;
    }

    //  Return distance of closest vertex to a vertex
    private static double closestDistance(Vertex destination) {

        Double d = destination.getDistance();

        //  If unconnected, return infinity
        if (d.equals(null)) {
            return Double.MAX_VALUE;
        }
        else {
            return d;
        }
    }

    private static boolean isEvaluated(Vertex node) {
        return evaluatedNodes.contains(node);
    }
    
	public static void main(String[] args) {

		input = "ur.txt";
		JFrame frame = new JFrame();
        frame.setSize(400, 420);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        StreetMap streetmap = new StreetMap();
        frame.setContentPane(streetmap);

        frame.setVisible(true);
        frame.invalidate();
		
		

	}



	private static void readInput() {
		BufferedReader br = null;
		try {
	        String currentLine;
	        br = new BufferedReader(new FileReader(input));
	        
	        
	        while ((currentLine = br.readLine()) != null) {
	               String[] strArr = currentLine.split("\t");
	               String roadOrIntersection = strArr[0];
	               
	               
	               if (roadOrIntersection.equals("i")) {
	            	   		Vertex v = new Vertex(strArr[1], Double.parseDouble(strArr[2]), Double.parseDouble(strArr[3]));
	            	   		if (v.getLatitude() < minLatitude) {
	            	   			minLatitude = v.getLatitude();
	            	   		}
	            	   		
	            	   		if (v.getLatitude() > maxLatitude) {
	            	   			maxLatitude = v.getLatitude();
	            	   		}
	            	   		
	            	   		if (v.getLongitude() < minLongitude) {
	            	   			minLongitude = v.getLongitude();
	            	   		}
	            	   		
	            	   		if (v.getLongitude() > maxLongitude) {
	            	   			maxLongitude = v.getLongitude();
	            	   		}
	            	   		
	            	   		
	            	   		vertices.add(v);
	            	   		
	               }else {
	            	   
	            	   		Edge e = new Edge(strArr[1], getVertexByID(vertices, strArr[2]), getVertexByID(vertices, strArr[3]));
	            	   		
	            	   		if (e.getWeight() > maxDistance) {
	            	   			maxDistance = e.getWeight();
	            	   		}
	            	   		edges.add(e);
	               }
	        }
	        
	        Graph g = new Graph(vertices, edges);

	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                if (br != null) {
	                	br.close();
	                }
	            } catch (IOException ex) {
	                ex.printStackTrace();
	            }
	        }

        Vertex start = vertices.get(0);
		Vertex end = vertices.get(15);

        calculate(start);

		List<Vertex> route = end.getPath();
		printList(route);

	}
	
	
	
	
	private static Vertex getVertexByID(ArrayList<Vertex> list, String id) {
		for (Vertex v: list) {
			if (v.getID().equals(id)) {
				return v;
			}
		}
		
		return null;
	}

	private static void printList(List<Vertex> route) {
        if (route.isEmpty()) {
            System.out.println("This is wrong");
        }
        for (Vertex v : route) {
            System.out.println(v.getID());
        }
    }

}
