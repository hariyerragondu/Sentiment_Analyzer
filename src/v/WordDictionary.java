package v;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

public class WordDictionary {
	static Logger log = Logger.getLogger("SENTI");
	private static List<String> positiveList = new ArrayList<String>();
	private static List<String> negativeList = new ArrayList<String>();
	private static List<String> neutralList = new ArrayList<String>();
	private static List<String> compoundList = new ArrayList<String>();

	static{
		setPositiveList();
		setNegativeList();
		setNeutralList();
		setCompoundList();
	}
	
	public static List<String> getPositiveList() {
		return positiveList;
	}
	public static void setPositiveList() {
		String  thisLine = null;
		try {
			File file = new File("./src/positive-words.txt");
			FileReader reader = new FileReader(file);
			BufferedReader br = new BufferedReader(reader);
	         while ((thisLine = br.readLine()) != null) {
	            positiveList.add(thisLine.toUpperCase());
	         }  
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static List<String> getNegativeList() {
		return negativeList;
	}
	public static void setNegativeList() {
		String  thisLine = null;
		try {
			File file = new File("./src/negative-words.txt");
			FileReader reader = new FileReader(file);
			BufferedReader br = new BufferedReader(reader);
	         while ((thisLine = br.readLine()) != null) {
	        	 negativeList.add(thisLine.toUpperCase());
	         }  
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static List<String> getNeutralList() {
		return neutralList;
	}
	public static void setNeutralList() {
		neutralList.add("OK");
		neutralList.add("SATISFACTORY");
		neutralList.add("SO SO");
	}
	public static List<String> getCompoundList() {
		return compoundList;
	}
	public static void setCompoundList() {
		compoundList.add("BUT");
		compoundList.add("HOWEVER");
	}
	
	private static List<String> readInputFile(){
		String  thisLine = null;
		List<String> inputList = new LinkedList<String>();
		try {
			File file = new File("./src/positive-words.txt");
			FileReader reader = new FileReader(file);
			 BufferedReader br = new BufferedReader(reader);
	         while ((thisLine = br.readLine()) != null) {
	            System.out.println(thisLine);
	            inputList.add(thisLine);
	         }  
		} catch (Exception e) {
			e.printStackTrace();
		}
		return inputList;
		
	}
	
}
