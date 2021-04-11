package nl.krebos.poc.wordcount;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordFrequencyAnalyzerImpl implements WordFrequencyAnalyzer{
	Map <String, WordFrequencyImpl> wordMap = new HashMap<>();
	private String text;

	/**
	 * CalculateHighestFrequency should return the highest frequency in the text (several
	 * words might have this frequency)
	 */
	public int calculateHighestFrequency(String text) {
		int freq = 0;
		if (text != null) {		
			convertTextToWords(text);

			// Find word with highest frequency
			if (! wordMap.isEmpty() ) {
				int highest = 0;
				for (WordFrequencyImpl wordFrq : wordMap.values()) {
					if (wordFrq.getFrequency() > highest) {
						highest = wordFrq.getFrequency();
					}
				}
				freq = highest;
			}
		}
		return freq;
	}

	/**
	 * CalculateFrequencyForWord should return the frequency of the specified word
	 */
	public int calculateFrequencyForWord(String text, String word) {
		int freq = 0;
		if (text != null && word != null) {		
			convertTextToWords(text);
			
			// Find word with highest frequency
			if (! wordMap.isEmpty() ) {
				WordFrequencyImpl wordFreq = wordMap.get(word);
				if (wordFreq !=  null) {
					freq = wordFreq.getFrequency();
				}
			}			
		}
		return freq;			
	}

	/**
	 * CalculateMostFrequentNWords should return a list of the most frequent „n‟ words inthe input text, 
	 * all the words returned in lower case. If several words have the samefrequency, this method should 
	 * return them in ascendant alphabetical order (for inputtext “The sun shines over the lake” and n = 3, 
	 * it should return the list {(“the”, 2),(“lake”, 1), (“over”, 1) }
	 */
	public List<WordFrequency> calculateMostFrequentNWords(String text, int n) {
		List<WordFrequency> wordList= null;
		if (text != null && n > 0) {		
			convertTextToWords(text);
			
			if (! wordMap.isEmpty() ) {
				wordList = new ArrayList<>(); 
				for (WordFrequencyImpl wordFrq : wordMap.values()) {
					wordList.add(wordFrq);
				}
				wordList.sort(WordFrequencyImpl.WordFreqComparator);
				//Collections.sort(wordList, WordFrequencyImpl.WordFreqComparator);
				if (wordList.size() > n) {
					List<WordFrequency> shortWordList= new ArrayList<>();
					for (int i = 0 ; i < n; i++) {
						shortWordList.add(wordList.get(i));
					}
					wordList = shortWordList;
				}
			}
		}
		return wordList;
	}	
	/**
	 * Process the incoming text and construct a List/Map with 
	 * the words and frequencies. This methods adds also a
	 * kind of 'caching' if a next call contains the same text
 	 * we can use the same wordList.
	 * @param text2
	 */
	private void convertTextToWords(String text2) {	
		boolean process = false;
		if (text == null) {
			process = true;
		} else if (! text2.trim().toLowerCase().equals(text)) {
			process = true;
		}	

		if (process) {
			System.out.println("Process incoming text");
			text = text2.trim().toLowerCase();
	
			int startIndex = 0;
			boolean wordFound = false;
			if (text.length() > 0) {
				WordFrequencyImpl wordFreq = null;			
				for (int i=0; i < text.length(); i++) {
					if (validChar(text.charAt(i))) {
						if (! wordFound) {
							wordFound = true;
							startIndex = i;
							wordFreq = new WordFrequencyImpl();						
						}
					} else {
						// We found an invalid word character, so the word is complete
						if (wordFound) {
							wordFound = false;
							String word = text.substring(startIndex, i);
							storeWord(word);
							startIndex = 0;
						}
					}
				}
				
				// Ensure the last word is also stored
				if (wordFound) {
					String word = text.substring(startIndex);
					storeWord(word);
				}
			}				
		}
	}		

	/**
	 * Only characters between a and z are valid.  
	 * @param c
	 * @return
	 */
	private boolean validChar(char c) {
		if (c >= 'a' && c <= 'z') {
			return true;
		} else {
			return false;
		}
	}

	
	/**
	 * Check if word already present, if not add word.
	 * @param word
	 */
	private void storeWord(String word) {
		WordFrequencyImpl wordFreq = wordMap.get(word);
		if (wordFreq == null) {
			wordFreq = new WordFrequencyImpl();
			wordFreq.setWord(word);
			wordMap.put(word, wordFreq);
		} else {
			wordFreq.increaseFrequency();
		}						
	}	
}
