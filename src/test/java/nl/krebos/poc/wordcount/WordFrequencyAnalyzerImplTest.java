package nl.krebos.poc.wordcount;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class WordFrequencyAnalyzerImplTest {

	@Test
	public void calculateHighestFrequency_with_null_value() {
		WordFrequencyAnalyzerImpl wfa = new WordFrequencyAnalyzerImpl();
		int freq = wfa.calculateHighestFrequency(null);
		assertTrue(freq == 0);
	}

	@Test
	public void calculateHighestFrequency_with_empty_string() {
		WordFrequencyAnalyzerImpl wfa = new WordFrequencyAnalyzerImpl();
		int freq = wfa.calculateHighestFrequency("");
		assertTrue(freq == 0);
	}	
	
	@Test
	public void calculateHighestFrequency_with_filled_1() {
		WordFrequencyAnalyzerImpl wfa = new WordFrequencyAnalyzerImpl();
		int freq = wfa.calculateHighestFrequency("A");
		assertTrue(freq == 1);
	}
	
	@Test
	public void calculateHighestFrequency_with_filled_2() {
		WordFrequencyAnalyzerImpl wfa = new WordFrequencyAnalyzerImpl();
		int freq = wfa.calculateHighestFrequency("  -a A, aA ");
		assertTrue(freq == 2);
		// the program should use the cached data/wordlist
		freq = wfa.calculateHighestFrequency("  -a A, aA ");
		assertTrue(freq == 2);		
	}	
	
	@Test
	public void calculateHighestFrequency_with_filled_3() {
		WordFrequencyAnalyzerImpl wfa = new WordFrequencyAnalyzerImpl();
		int freq = wfa.calculateHighestFrequency("The sun shines over the lake");
		assertTrue(freq == 2);
	}	
	
	@Test
	public void calculateFrequencyForWord_with_null_value_1() {
		WordFrequencyAnalyzerImpl wfa = new WordFrequencyAnalyzerImpl();
		int freq = wfa.calculateFrequencyForWord(null, null);
		assertTrue(freq == 0);
	}	
	
	@Test
	public void calculateFrequencyForWord_with_null_value_2() {
		WordFrequencyAnalyzerImpl wfa = new WordFrequencyAnalyzerImpl();
		int freq = wfa.calculateFrequencyForWord("The sun shines over the lake", null);
		assertTrue(freq == 0);
	}
	
	@Test
	public void calculateFrequencyForWord_with_null_value_3() {
		WordFrequencyAnalyzerImpl wfa = new WordFrequencyAnalyzerImpl();
		int freq = wfa.calculateFrequencyForWord(null, "the");
		assertTrue(freq == 0);
	}	
	
	@Test
	public void calculateFrequencyForWord_filled_1() {
		WordFrequencyAnalyzerImpl wfa = new WordFrequencyAnalyzerImpl();
		int freq = wfa.calculateFrequencyForWord("The sun shines over the lake", "the");
		assertTrue(freq == 2);
	}	
	
	@Test
	public void calculateFrequencyForWord_filled_2() {
		WordFrequencyAnalyzerImpl wfa = new WordFrequencyAnalyzerImpl();
		int freq = wfa.calculateFrequencyForWord("The sun shines over the lake", "piet");
		assertTrue(freq == 0);
	}	
	
	@Test
	public void calculateMostFrequencyNWords_with_null_value() {
		WordFrequencyAnalyzerImpl wfa = new WordFrequencyAnalyzerImpl();
		List<WordFrequency> wordList = wfa.calculateMostFrequentNWords(null, 0);
		assertTrue(wordList == null);
	}	
	
	@Test
	public void calculateMostFrequencyNWords_filled_0() {
		WordFrequencyAnalyzerImpl wfa = new WordFrequencyAnalyzerImpl();
		List<WordFrequency> wordList = wfa.calculateMostFrequentNWords("The sun shines over the lake", 0);
		assertTrue(wordList == null);
	}	

	@Test
	public void calculateMostFrequencyNWords_filled_1() {
		WordFrequencyAnalyzerImpl wfa = new WordFrequencyAnalyzerImpl();
		List<WordFrequency> wordList = wfa.calculateMostFrequentNWords("The sun shines over the lake", 3);
		System.out.println(Arrays.toString(wordList.toArray()));
		for (WordFrequency wordFreq : wordList) {
			System.out.println("wordList: " + wordFreq);	
		}		
		assertTrue(wordList != null);
		assertTrue(wordList.size() == 3);
	}
	
	@Test
	public void calculateMostFrequencyNWords_filled_2() {
		WordFrequencyAnalyzerImpl wfa = new WordFrequencyAnalyzerImpl();
		List<WordFrequency> wordList = wfa.calculateMostFrequentNWords("The sun shines over the lake", 9);
		for (WordFrequency wordFreq : wordList) {
			System.out.println("wordList: " + wordFreq);	
		}		
		assertTrue(wordList != null);
		assertTrue(wordList.size() == 5);
	}
}
