import java.io.*;

public class GameHelper{
	public String getUserInput(String prompt){
		String inputLine = null;
		System.out.print(prompt+" ");
		try{

			BufferedReader is = new BufferedReader(new InputStreamReader(System.in));
			inputLine = is.readLine();
			if(inputLine.length() == 0)
				return null;
		}catch(IOException e){
			System.out.println("IOException"+e);
		}
		return inputLine;
	}

	public ArrayList<String> placeDotCom(int comSize){
		ArrayList<String> alphaCells = new ArrayList<String>();
		String [] alphacoords = new String[comSize];
		String temp = null;
		int [] coords 
	}
}