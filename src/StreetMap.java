import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class StreetMap {

	public static void main(String[] args) {
		String input = args[0];
		BufferedReader br = null;
		try {
	        String currentLine;
	        br = new BufferedReader(new FileReader(input));
	        while ((currentLine = br.readLine()) != null) {
	               String[] strArr = currentLine.split("\t");
	               String roadOrIntersection = strArr[0];
	               String ID = strArr[1];
	               String secondCol = strArr[2];
	               String thirdCol = strArr[3];
	               
	               
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

}
