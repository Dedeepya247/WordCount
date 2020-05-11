package com.wordcount.demo.wordcount.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

public class CsvUtil {

	public static void writeMapToCsv(PrintWriter writer, Map<String, Integer> wordCountMap) throws IOException {
		try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withDelimiter('|'))) {
		      for (Entry<String, Integer> wordCount : wordCountMap.entrySet()) {
		        List<String> wordList = Arrays.asList(
		        	wordCount.getKey(),
		            wordCount.getValue().toString()
		          );
		        
		        csvPrinter.printRecord(wordList);
		      }
		      csvPrinter.flush();
		    }
	}
}
