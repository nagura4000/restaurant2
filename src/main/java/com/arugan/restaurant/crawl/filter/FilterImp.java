package com.arugan.restaurant.crawl.filter;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

public class FilterImp implements Filter {
	private final String inputRegex;
	private final String outputRegex;
	private Logger logger = Logger.getLogger(this.getClass());

	public FilterImp(String inputRegex, String outputRegex) {
		this.inputRegex = inputRegex;
		this.outputRegex = outputRegex;
	}

	@Override
	public List<String> doFilter(List<String> linkList) {
		List<String> matchLinkList = new ArrayList<String>();
		if (StringUtils.isBlank(outputRegex)) {
			return matchLinkList;
		}

		for (String link : linkList) {
			if (isLink(outputRegex, link)) {
				matchLinkList.add(link);
			}
		}
		return matchLinkList;
	}

	private boolean isLink(String regex, String link) {
		Matcher matcher = createMatcher(regex, link);
		if (matcher.find()) {
			logger.debug("url matched.");
			logger.debug("\t regex = " + regex);
			logger.debug("\t url  = " + link);
			// マッチした
			return true;
		}
		logger.debug("url did not match. " + link);
		// マッチなし
		return false;
	}

	private Matcher createMatcher(String regex, String url) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(url);
		return matcher;
	}

	@Override
	public boolean isTargetFilter(String url) {
		return isLink(inputRegex, url);
	}
}
