package com.wordcount.demo.wordcount.beans;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.wordcount.demo.wordcount.utils.Constants;

@JsonFilter(Constants.FILTER_WORDS)
public class Words {

	private List<String> wordList;
	
	private List<Map<String, Integer>> mapList;

	public List<String> getWordList() {
		return wordList;
	}

	public void setWordList(List<String> wordList) {
		this.wordList = wordList;
	}

	public List<Map<String, Integer>> getMapList() {
		return mapList;
	}

	public void setMapList(List<Map<String, Integer>> mapList) {
		this.mapList = mapList;
	}

	@Override
	public String toString() {
		return "Words [wordList=" + wordList + ", mapList=" + mapList + "]";
	}
	
}
