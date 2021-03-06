import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.JPanel;

// Authors: Ali Hashim and James Emery
public class StreetMap extends JPanel {
	// Initializing variables 
	static HashMap<String, Vertex> vertices = new HashMap<String, Vertex>();
    static HashMap<String, Edge> edges = new HashMap<String, Edge>();
    static boolean showMap = false;
    static boolean showDirections = false;
    static String startPointID = null;
    static String endPointID = null;
    static double minLongitude = Double.MAX_VALUE;
    static double minLatitude = Double.MAX_VALUE;
    static double maxLongitude = -1 * Double.MAX_VALUE;
    static double maxLatitude = -1 * Double.MAX_VALUE;
    static double maxDistance = -1 * Double.MAX_VALUE;
    static double width = 420;
    static double height = 400;
    static String input;
    static ArrayList<Vertex> path;
    
    @Override
    public void paintComponent(Graphics g)
    {
    	
    	
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLACK);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        double latitudeScale = this.getHeight()/Math.abs(maxLatitude - minLatitude) ;
        double longitudeScale = this.getWidth()/Math.abs(maxLongitude - minLongitude) ;
        edges.forEach((key, value) -> {
        		Edge e = edges.get(key);
        		int startX = (int) ((e.getStart().getLongitude() - minLongitude) * longitudeScale);
    			int startY = (int) (this.getHeight() - ((e.getStart().getLatitude() - minLatitude) * latitudeScale));
    			
    			
    			int endX = (int) ((e.getEnd().getLongitude() - minLongitude) * longitudeScale);
    			int endY = (int) (this.getHeight() - ((e.getEnd().getLatitude() - minLatitude) * latitudeScale));
    
    			g2.drawLine(startX, startY, endX, endY);
        		
        });
        
        if (showDirections) {
        g2.setColor(Color.RED);
        g2.setStroke(new BasicStroke(5));
        for (int i=0; i<path.size()-1; i++) {
        		int startX = (int) ((path.get(i).getLongitude() - minLongitude) * longitudeScale);
    			int startY = (int) (this.getHeight() - ((path.get(i).getLatitude() - minLatitude) * latitudeScale));
    			
    			
    			int endX = (int) ((path.get(i+1).getLongitude() - minLongitude) * longitudeScale);
    			int endY = (int) (this.getHeight() - ((path.get(i+1).getLatitude() - minLatitude) * latitudeScale));
    
    			g2.drawLine(startX, startY, endX, endY);
        }
        }

        


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
	            	   		
	            	   		vertices.put(v.getID(), v);
	            	   		
	            	   		
	               }else {
	            	   
	            	   		Edge e = new Edge(strArr[1], vertices.get(strArr[2]), vertices.get(strArr[3]));
	            	   		
	            	   		if (e.getWeight() > maxDistance) {
	            	   			maxDistance = e.getWeight();
	            	   		}
	            	   		edges.put(e.getID(), e);
	               }
	        }
	        
	        if (showDirections) {
	        		Graph g = new Graph(vertices, edges);
	        		path = g.shortestPath(startPointID, endPointID);
	        		printPath();
	        }
	        

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

    

	}
	
	
	
	private static void printPath() {
        double pathDistance = 0;
		for (int i = 0; i < path.size() - 1; i++) {
			System.out.println(path.get(i).getID());
			pathDistance += Helper.getWeight(path.get(i), path.get(i+1));
		}

        System.out.println(path.get(path.size() - 1).getID());
        System.out.print("Path distance: ");
        double distMiles = pathDistance * 0.621371;
        double roundedMiles =  Math.floor(distMiles * 1000) / 1000;

		System.out.print(roundedMiles);
		System.out.println(" miles");
		
	}

	public static void main(String[] args) {
		if (args.length == 0){
			System.err.println("Need command line arguments");
			return;
		}
		input = args[0];

		for (int i = 1; i<args.length; i++) {
			if (args[i].equals("--show")) {
				showMap = true;
			}else if (args[i].equals("--directions")) {
				showDirections = true;
				if (args.length == i+1) {
					System.err.println("Need two points after '--directions'");
					return;
				}else if (args.length == i + 2) {
					System.err.println("Need two points after '--directions'");
					return;
				}
				startPointID = args[i+1];
				endPointID = args[i+2];
			}
		}
		
		if (!showMap && !showDirections) {
			System.err.println("You need to specify to show directions via '--directions startID endID' or to show the map via '--show'");
			
		}

		readInput();

		if (showMap) {
			JFrame frame = new JFrame();
	        frame.setSize(400, 420);
	        frame.setLocationRelativeTo(null);
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        
	        StreetMap streetmap = new StreetMap();
	        frame.setContentPane(streetmap);
	
	        frame.setVisible(true);
	        frame.invalidate();
		}
	}

}
