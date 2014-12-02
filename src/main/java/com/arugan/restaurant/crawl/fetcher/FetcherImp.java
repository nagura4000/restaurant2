package com.arugan.restaurant.crawl.fetcher;

import java.io.IOException;
import java.net.MalformedURLException;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class FetcherImp implements Fetcher {

	private static WebClient webClient;

	private static Fetcher fetcher;

	private FetcherImp() {
		webClient = new WebClient(BrowserVersion.INTERNET_EXPLORER_9);
		webClient.getOptions().setJavaScriptEnabled(false);
		webClient.getOptions().setThrowExceptionOnScriptError(false);

	}

	public static Fetcher getInstance() {
		if (fetcher == null) {
			fetcher = new FetcherImp();
		}
		return fetcher;
	}

	public HtmlPage getPage(String url) throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		HtmlPage page = webClient.getPage(url);
		return page;
	}

}
