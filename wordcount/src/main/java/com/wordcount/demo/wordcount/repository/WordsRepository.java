package com.wordcount.demo.wordcount.repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Repository;

import com.wordcount.demo.wordcount.beans.Words;
import com.wordcount.demo.wordcount.utils.Constants;


@Repository
public class WordsRepository {
	@Autowired
	ResourceLoader resourceLoader;
	@Autowired
	Environment environment;
	Map<String, Integer> wordCountsMap;
	
	@PostConstruct
	public void initializer()
	{
		String wordFromFile;
		try (Scanner scanner = new Scanner(resourceLoader.getResource("classpath:" + environment.getProperty("sample.file")).getInputStream())){
			scanner.useDelimiter(Constants.NON_WORD_CHAR_SEQUENCE);
			wordCountsMap = new HashMap<String, Integer>();
			while(scanner.hasNext()) {
				wordFromFile = scanner.next().toLowerCase();
				if(!wordCountsMap.containsKey(wordFromFile)) {
					wordCountsMap.put(wordFromFile, 1);
				}
				else {
					wordCountsMap.replace(wordFromFile, wordCountsMap.get(wordFromFile)+1);
				}
			}
    	}catch(Exception e) {
    		System.out.println("-------------------------------------------------------");
    		e.printStackTrace();
    	}
	}
	
	public Words wordSearchCount(Words words) throws IOException {
		System.out.println("WordCountsMap "+wordCountsMap.size());
		String searchWordToLower = null;
		List<Map<String, Integer>> finalList = new ArrayList<Map<String,Integer>>();
		
		for(String searchWord:words.getWordList()) {
			Map<String, Integer> wordsMap = new HashMap<String, Integer>();
			searchWordToLower = searchWord.toLowerCase();
			if(wordCountsMap.containsKey(searchWordToLower)) {
				System.out.println(searchWord+" Present");
				wordsMap.put(searchWord, wordCountsMap.get(searchWordToLower));
			}
			else {
				System.out.println(searchWord+" Not Present");
				wordsMap.put(searchWord,0);
			}
			finalList.add(wordsMap);
		}
		words.setMapList(finalList);
		return words;
	}
	
	public Map<String, Integer> WordsAndTheirCounts(int n) throws IOException{
		if(n <= 0) {
			return null;
		}
		
		Comparator<Entry<String,Integer>> compareByValue = new Comparator<Entry<String,Integer>>() {
			@Override
			public int compare(Entry<String, Integer> e1, Entry<String, Integer> e2) {
				int result;
				result = e2.getValue().compareTo(e1.getValue());
				if(result == 0) {
					result = e1.getKey().compareTo(e2.getKey());
				}
				return result;
			}		
		};
		
		Set<Entry<String,Integer>> wordCountsSortedSet = new TreeSet<Entry<String,Integer>>(compareByValue);
		wordCountsSortedSet.addAll(wordCountsMap.entrySet());

		int i=0;
		LinkedHashMap<String,Integer> wordsWithHighestCountsMap = new LinkedHashMap<String,Integer>();
		for(Entry<String,Integer> wordCount: wordCountsSortedSet) {
			wordsWithHighestCountsMap.put(wordCount.getKey(), wordCount.getValue());
			if(++i >= n) {
				break;
			}
		}
		return wordsWithHighestCountsMap;
	}
}
