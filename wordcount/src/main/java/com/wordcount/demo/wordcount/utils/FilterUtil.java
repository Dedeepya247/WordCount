package com.wordcount.demo.wordcount.utils;

import java.util.Set;

import org.springframework.http.converter.json.MappingJacksonValue;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.PropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;


public class FilterUtil {

	//Utility method to filter out bean properties from being serialized
	public static MappingJacksonValue getFilteredBean(Object bean, String filterId, Set<String> propertiesToRetain){
		
		PropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(propertiesToRetain);
		FilterProvider filters = new SimpleFilterProvider().addFilter(filterId, filter);
		MappingJacksonValue mapping = new MappingJacksonValue(bean);
		mapping.setFilters(filters);
		
		return mapping;
	}
}
