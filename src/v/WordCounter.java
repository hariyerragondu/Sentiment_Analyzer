package v;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

public class WordCounter {
	static Logger log = Logger.getLogger("SENTI");

	private int positiveWordCount = 0;
	private int negativeWordCount = 0;
	private int neutralWordCount = 0;

	private boolean checkPos = true;

	private int pIndex = 0;
	private List<String> list = null;

	private boolean tempDomainFlag = false;

	public List<Integer> getCounts(String searchToString) {
		list = WordDictionary.getPositiveList();
		int pi = list.size();

		// does it contain domain words?

		for (String dw : WordDictionary.getDomainWordList()) {
			if (searchToString.contains(dw))
				tempDomainFlag = true;
		}

		for (int in = 0; in < pi; in++) {
			pIndex = in;
			if (checkPos) {
				searchPositiveCount(searchToString, list.get(in));
			}
		}

		for (String s : WordDictionary.getNegativeList()) {
			if (searchToString.contains(s)) {
				negativeWordCount++;				
			}
		}

		for (String s : WordDictionary.getNeutralList()) {
			if (searchToString.contains(s)) {
				neutralWordCount++;
			}
		}

		if (!tempDomainFlag) {			
			if( (positiveWordCount - negativeWordCount) > 0 ) {
				log.debug("Not Found Domain Word: Pre " + neutralWordCount);
				neutralWordCount = (positiveWordCount + negativeWordCount)/3;
				log.debug("Not Found Domain Word: Post " + neutralWordCount);
			} else {
				log.debug("Not Found Domain Word: Pre " + neutralWordCount);
				neutralWordCount = (positiveWordCount + negativeWordCount)/-3;
				log.debug("Not Found Domain Word: Post " + neutralWordCount);
			}
		}
		
		List<Integer> resultList = Arrays.asList(positiveWordCount, negativeWordCount, neutralWordCount);
		log.debug(resultList);
		return resultList;
	}

	private void searchPositiveCount(String searchToString, String p) {
		//System.out.println("Recd searchPositiveCount(): " + searchToString
		//		+ " >>> " + p);
		for (int index = searchToString.indexOf(p); index >= 0; index = searchToString
				.indexOf(p, index + 1)) {
			String[] ss = searchToString.split(p, 2);

			String preCheck = ss[0];

			if (preCheck.contains("NOT") && preCheck.trim().endsWith("NOT")) {
				//System.out.println("2: " + preCheck);
				// if(preCheck.trim().endsWith("NOT")){
				log.debug("Contains NOT: " + preCheck);
				negativeWordCount++;
				int lio = preCheck.lastIndexOf("NOT");
				preCheck = preCheck.substring(0, lio);
				preCheck = preCheck.concat(ss[1]);

				for (int in = pIndex; in < list.size(); in++) {
					searchPositiveCount(preCheck, list.get(in));
				}
				checkPos = false;
			} else {
				positiveWordCount++;
			}
		}
	}
}
