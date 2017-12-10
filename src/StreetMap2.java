import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class StreetMap2 extends JPanel {
	static HashMap<String, Vertex> vertices = new HashMap<String, Vertex>();
    static HashMap<String, Edge> edges = new HashMap<String, Edge>();
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
	        
//	        Graph g = new Graph(vertices, edges);

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
	
	
	
	public static void main(String[] args) {
		
		input = "monroe.txt";
		JFrame frame = new JFrame();
        frame.setSize(400, 420);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        StreetMap2 streetmap = new StreetMap2();
        frame.setContentPane(streetmap);

        frame.setVisible(true);
        frame.invalidate();
	}

}
