package v;
 
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

public class SentiScorer {
	public static Logger log = Logger.getLogger("SENTI");
	private String[] sentences;
	List<List<Integer>> fullStatList = new LinkedList<List<Integer>>();

	public void process(String searchToString) {
		searchToString = searchToString.toUpperCase();
		searchToString = searchToString.replace("N'T", " NOT");		
		sentences = searchToString.split("\\.");

		for (String s : sentences) {
			List<Integer> eachSentenceStat = processEachSentence(s);

			if (eachSentenceStat != null) {
				fullStatList.add(eachSentenceStat);
			}
		}
		SentiUtil.printStat(fullStatList);
	}

	private List<Integer> processEachSentence(String eachSentence) {
		List<Integer> eachSentenceStat = null;

		if (!hasCompoundWord(eachSentence)) {
			eachSentenceStat = new WordCounter().getCounts(eachSentence);
		} else {
			eachSentenceStat = processCompoundSentence(eachSentence);
		}
		return eachSentenceStat;
	}

	private List<Integer> processCompoundSentence(String eachSentence) {
		List<List<Integer>> etat = new LinkedList<List<Integer>>();

		for (String cw : WordDictionary.getCompoundList()) {

			String[] sa = eachSentence.split(cw);

			for (String s : sa) {
				List<Integer> eachSentenceStat = new WordCounter().getCounts(s);
				if (eachSentenceStat != null) {
					etat.add(0, eachSentenceStat);
				}
			}
		}
		return etat.get(0);
	}

	private boolean hasCompoundWord(String inString) {
		boolean hasCompoundWord = false;

		for (String s : WordDictionary.getCompoundList()) {
			if (inString.contains(s)) {
				hasCompoundWord = true;
				break;
			}
		}
		return hasCompoundWord;
	}

}
