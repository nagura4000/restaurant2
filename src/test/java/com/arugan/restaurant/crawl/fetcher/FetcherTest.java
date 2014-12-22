package com.arugan.restaurant.crawl.fetcher;

import java.io.IOException;
import java.net.MalformedURLException;

import com.arugan.restaurant.crawl.link.LinkGetter;
import com.arugan.restaurant.crawl.link.LinkGetterImp;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class FetcherTest {

	private Fetcher fetcher;

	private LinkGetter linkGetter;

//	@Before
//	public void init() {
//		fetcher = FetcherImp.getInstance();
//		linkGetter = new LinkGetterImp();
//	}
//
//	@Test
//	public void test() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
//		String url = "http://tabelog.com/washoku/kanagawa/A1405/0/0/COND-0-0-2-0-0-0-0-0/D-trend/40/";
//		HtmlPage page = fetcher.getPage(url);
//
//		for (String link :linkGetter.getLinkList(page)) {
//			System.out.println(link);
//		}
//	}

	private void start() {

		fetcher = FetcherImp.getInstance();
		linkGetter = new LinkGetterImp();

		String url = "http://tabelog.com/kanagawa/A1402/A140204/";
		HtmlPage page = null;
		try {
			page = fetcher.getPage(url);
		} catch (FailingHttpStatusCodeException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		for (String link :linkGetter.getLinkList(page)) {
			System.out.println(link);
		}
	}
	public static void main(String[] args) {

		FetcherTest test = new FetcherTest();
		test.start();


	}

}
