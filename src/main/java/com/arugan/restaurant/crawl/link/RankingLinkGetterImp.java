package com.arugan.restaurant.crawl.link;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class RankingLinkGetterImp implements LinkGetter {

	private List<String> linkList = new ArrayList<String>();
//	private Logger logger = Logger.getLogger(this.getClass());

	private Logger logger = Logger.getLogger(this.getClass());

	@Override
	public List<String> getLinkList(HtmlPage page) {
		List<HtmlAnchor> anchorList =  page.getAnchors();
		for (HtmlAnchor anchor : anchorList) {
			String value = anchor.getAttribute("href");

			StringBuffer buff = new StringBuffer();
			buff.append(value)
				.append("?Srt=D&SrtT=rt");
			value = buff.toString();
			try {
				URL url = new URL(page.getUrl(), value);
				String link = url.toString();
				linkList.add(link);
				logger.debug(link);
			} catch (MalformedURLException e) {
			}
		}

		return linkList;
	}

}
