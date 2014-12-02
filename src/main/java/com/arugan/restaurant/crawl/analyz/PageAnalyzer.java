package com.arugan.restaurant.crawl.analyz;

import java.util.List;

import com.arugan.restaurant.crawl.parse.Parser;
import com.gargoylesoftware.htmlunit.html.HtmlPage;


public interface PageAnalyzer {
	PageAnalyzer setNextAnalyzer(PageAnalyzer analyzer);

	PageAnalyzer getTargetAnalyzer(String url);

	List<String> doFilter(List<String> linkList);

	List<String> getLinkList(HtmlPage page);

	Parser getParser();
	void setParser(Parser parser);
}
