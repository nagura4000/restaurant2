/**
 *
 */
package com.arugan.restaurant.crawl.filter;

import java.util.List;

/**
 * @author nagura
 *
 */
public interface Filter {
	List<String> doFilter(List<String> linkList);

//	void setNextFilter(Filter filter);
//
//	Filter getTargetFilter(String url);
	
	boolean isTargetFilter(String url);
}
