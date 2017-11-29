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
	               System.out.println(strArr[0]);
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
