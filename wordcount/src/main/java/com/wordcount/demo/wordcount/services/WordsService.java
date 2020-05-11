package com.wordcount.demo.wordcount.services;

import java.io.IOException;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wordcount.demo.wordcount.beans.Words;
import com.wordcount.demo.wordcount.repository.WordsRepository;

@Service
public class WordsService {

	@Autowired
	WordsRepository wordsRepository;
	
	public Words wordSearchCount(Words words) throws IOException {
		return wordsRepository.wordSearchCount(words);
	}
	
	public Map<String, Integer> WordsAndTheirCounts(int n) throws IOException{
		return wordsRepository.WordsAndTheirCounts(n);
	}
}
