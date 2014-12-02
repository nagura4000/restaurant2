package com.arugan.restaurant.crawl.fetcher;

import java.io.IOException;
import java.net.MalformedURLException;

import org.junit.Before;
import org.junit.Test;

import com.arugan.restaurant.crawl.link.LinkGetter;
import com.arugan.restaurant.crawl.link.LinkGetterImp;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class FetcherTest {

	private Fetcher fetcher;

	private LinkGetter linkGetter;

	@Before
	public void init() {
		fetcher = FetcherImp.getInstance();
		linkGetter = new LinkGetterImp();
	}

	@Test
	public void test() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		String url = "http://tabelog.com/washoku/kanagawa/A1405/0/0/COND-0-0-2-0-0-0-0-0/D-trend/40/";
		HtmlPage page = fetcher.getPage(url);

		for (String link :linkGetter.getLinkList(page)) {
			System.out.println(link);
		}
	}

}
