package com.wordcount.demo.wordcount.controllers;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wordcount.demo.wordcount.beans.Words;
import com.wordcount.demo.wordcount.services.WordsService;
import com.wordcount.demo.wordcount.utils.Constants;
import com.wordcount.demo.wordcount.utils.CsvUtil;
import com.wordcount.demo.wordcount.utils.FilterUtil;

@RestController
public class WordController {
	
	@Autowired
	WordsService wordsService;
	
	@PostMapping(value="/counter-api/search",consumes = "application/json")
	public MappingJacksonValue wordSearch(@RequestBody Words words) throws IOException {
		wordsService.wordSearchCount(words);
		Set<String> propertiesToRetain = new HashSet<String>();
		propertiesToRetain.add(Constants.WORD_COUNTS);		
		return FilterUtil.getFilteredBean(words, Constants.FILTER_WORDS, propertiesToRetain);
	}
	
	@GetMapping(value = "/counter-api/top/{n}", produces = "text/csv")
	public void topNWordsCount(HttpServletResponse response, @PathVariable int n) throws IOException {
		Map<String, Integer> wordCountMap = wordsService.WordsAndTheirCounts(n);
		CsvUtil.writeMapToCsv(response.getWriter(), wordCountMap);
	}
}
