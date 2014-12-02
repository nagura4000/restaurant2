package com.arugan.restaurant.crawl.parse;

import java.io.IOException;
import java.net.MalformedURLException;

import com.arugan.restaurant.crawl.dto.RestaurantDTO;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public interface Parser {
	RestaurantDTO parse(HtmlPage page) throws FailingHttpStatusCodeException, MalformedURLException, IOException;

	boolean isTargetUrl(String url);
}
