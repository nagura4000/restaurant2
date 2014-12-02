package com.arugan.restaurant.crawl.link;

import java.util.List;

import com.gargoylesoftware.htmlunit.html.HtmlPage;

public interface LinkGetter {

	List<String> getLinkList(HtmlPage page);

}
