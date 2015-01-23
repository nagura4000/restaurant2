package com.arugan.restaurant.crawl.fetcher;

import java.io.IOException;
import java.net.MalformedURLException;

import org.apache.log4j.Logger;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class FetcherImp implements Fetcher {

	private static WebClient webClient;

	private static Fetcher fetcher;

	private Logger log = Logger.getLogger(this.getClass());

	private FetcherImp() {
		webClient = new WebClient(BrowserVersion.INTERNET_EXPLORER_8);
		webClient.setJavaScriptEnabled(false);
		webClient.setThrowExceptionOnScriptError(false);

//		webClient = new WebClient(BrowserVersion.FIREFOX_24);
//		webClient.getOptions().setJavaScriptEnabled(false);
//		webClient.getOptions().setThrowExceptionOnScriptError(false);

	}

	public static Fetcher getInstance() {
		if (fetcher == null) {
			fetcher = new FetcherImp();
		}
		return fetcher;
	}

	public HtmlPage getPage(String url) {
        System.out.println("target url=" + url);
        HtmlPage page = null;
        try {
                log.info("target url:" + url);
                page = webClient.getPage(url);
                log.info("fetch OK.");
        } catch (FailingHttpStatusCodeException e) {
                log.error(e.getMessage());
        } catch (MalformedURLException e) {
                log.error(e.getMessage());
        } catch (IOException e) {
                log.error(e.getMessage());
        } catch (Exception e) {
                log.error(e.getMessage());
        }
        return page;

	}

}
