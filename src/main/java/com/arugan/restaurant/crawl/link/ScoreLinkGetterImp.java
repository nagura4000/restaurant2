package com.arugan.restaurant.crawl.link;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class ScoreLinkGetterImp implements LinkGetter {

	private List<String> linkList = new ArrayList<String>();

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getLinkList(HtmlPage page) {
		List<HtmlElement> elements = (List<HtmlElement>)page.getByXPath("//ul[@class='rstlist-info']/li");
		for (HtmlElement element : elements) {
			if (!element.getByXPath("div[3]/div[1]/p[1]/em[@class='score']").isEmpty()) {
				List<HtmlElement> linkElement = (List<HtmlElement>)element.getByXPath("div[1]/div[1]/div[@class='rstname-wrap']/strong/a");
				if (!linkElement.isEmpty()) {
					String value = linkElement.get(0).getAttribute("href");
					value = value.replaceAll("^http://.+\\*-", "");
					try {
						URL url = new URL(page.getUrl(), value);
						String link = url.toString();
						linkList.add(link);
					} catch (MalformedURLException e) {
					}
				}
			}
		}
		return linkList;
	}

}
