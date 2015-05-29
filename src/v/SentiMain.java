package v;
 
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

public class SentiMain {
	public static Logger log = Logger.getLogger("SENTI");
	//static String sentiStrings = "I haven't been talking to 5 agents so far, still no end to my pain... phone can't be dead for 10 days!!!  ";
	
	private static StringBuilder currentInput = new StringBuilder();
	
	public static void main(String[] args) {	
		//currentInput.append(sentiStrings);
		//new SentiScorer().process(sentiStrings);
		
		List<String> inputList = readInputFile();
		for(String sentiString: inputList){
			currentInput.delete(0, currentInput.length());
			currentInput.append(sentiString);
			new SentiScorer().process(sentiString);
		}
		System.out.println("Done !!");
	}
	
	
	public static String getCurrentInput(){
		return currentInput.toString();
	}
	
	private static List<String> readInputFile(){
		String  thisLine = null;
		List<String> inputList = new LinkedList<String>();
		try {
			File file = new File("./src/HackathonInput.txt");
			//File file = new File("./src/PositiveExample.txt");
			FileReader reader = new FileReader(file);
			 BufferedReader br = new BufferedReader(reader);
	         while ((thisLine = br.readLine()) != null) {
	           // System.out.println(thisLine);
	            inputList.add(thisLine);
	         }  
		} catch (Exception e) {
			e.printStackTrace();
		}
		return inputList;
		
	}
	

}
