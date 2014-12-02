/**
 *
 */
package com.arugan.restaurant.crawl.fetcher;

import java.io.IOException;
import java.net.MalformedURLException;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * @author nagura
 *
 */
public interface Fetcher {

	HtmlPage getPage(String url) throws FailingHttpStatusCodeException, MalformedURLException, IOException;

}
