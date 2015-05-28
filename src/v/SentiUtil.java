package v;

import java.util.List;

import org.apache.log4j.Logger;

public class SentiUtil {
	public static Logger log = Logger.getLogger("SENTI");
	public static Logger logr = Logger.getLogger("SENTU");
	
	public static void printStat(List<List<Integer>> stat){
		log.debug(" **** **** **** ");
		
		for(List<Integer> list : stat){			
			logr.debug(list.get(0) + "|"+  list.get(1) + "|" + list.get(2));
			System.out.println(list.get(0) + "|"+  list.get(1) + "|" + list.get(2));
		}
		
		normalizeStats(stat);
	}
	
	private static void normalizeStats(List<List<Integer>> stat){		
		float t1 = 0;
		float t2 = 0;
		float t3 = 0;
		
		for(List<Integer> list : stat){			
			t1 += list.get(0); 
			t2 += list.get(1); 
			t3 += list.get(2);
		}		
		logr.debug("Normalized Stat = " + t1 + "|" + t2 + "|" + t3);		
		System.out.println("Normalized Stat = " + t1 + "|" + t2 + "|" + t3);
		
		float result = (t1 - t2 - t3)/(t1 + t2 + t3);
		
		System.out.println(result);
		
		String disposition = getDisposition(result);
		
		logr.debug(SentiMain.getCurrentInput());
		logr.debug(result);
		logr.debug(disposition);
		
		logr.debug(" *** ");
		
		logr.debug("");
		logr.debug("");
		logr.debug("");
		
	}
	
	
	private static String getDisposition(float result){			
		if(result <= 0.2){
			return "NEGATIVE";
		}
		else if(result > 0.2 && result < 0.5){
				return "NEUTRAL";
		}else if(result >= 0.5){
			return "POSITIVE";
		}		
		return "NEUTRAL>>>>>>>>>>>"+result;
	}
	
	
	public static void printStatX(List<List<Integer>> stat){
		log.debug(" **** **** **** ");
		
		float positives = 0;
		float negatives = 0;
		float neutrals = 0;
		float result = 0;
		
		for(List<Integer> list : stat){			
			log.debug(list.get(0) + "|"+  list.get(1) + "|" + list.get(2));
			System.out.println(list.get(0) + "|"+  list.get(1) + "|" + list.get(2));
			positives += list.get(0);
			negatives += list.get(1);
			neutrals += list.get(2);	
		}
		result = (positives-negatives)/(positives+negatives+neutrals);
	}


}
