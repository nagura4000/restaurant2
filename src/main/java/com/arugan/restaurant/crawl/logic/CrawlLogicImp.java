package com.arugan.restaurant.crawl.logic;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

import com.arugan.restaurant.crawl.analyz.PageAnalyzer;
import com.arugan.restaurant.crawl.analyz.PageAnalyzerFactory;
import com.arugan.restaurant.crawl.dto.PageDTO;
import com.arugan.restaurant.crawl.dto.RestaurantDTO;
import com.arugan.restaurant.crawl.fetcher.Fetcher;
import com.arugan.restaurant.crawl.fetcher.FetcherImp;
import com.arugan.restaurant.crawl.parse.Parser;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class CrawlLogicImp implements CrawlLogic {

	private Fetcher fetcher;
	private PageAnalyzer analyzer;

	private ObjectMapper objectMapper = new ObjectMapper();

	private org.apache.log4j.Logger logger = Logger.getLogger(this.getClass());

	public CrawlLogicImp() {
		fetcher = FetcherImp.getInstance();

		//analyzer = PageAnalyzerFactory.createSitmapAnalyzer();
		analyzer = PageAnalyzerFactory.createRankingAnalyzer();
	}

	@Override
	public PageDTO getPageDTO(String url) throws FailingHttpStatusCodeException, MalformedURLException, IOException {

		logger.debug(url);

		PageAnalyzer targetAnalyzer = analyzer.getTargetAnalyzer(url);

		if (targetAnalyzer == null) {
			logger.debug("targetAnalyzer == null");
			return null;
		}

		HtmlPage page = fetcher.getPage(url);
		List<String> linkList = targetAnalyzer.getLinkList(page);
		List<String> targetLinkList = targetAnalyzer.doFilter(linkList);

		PageDTO pageDTO = new PageDTO();
		pageDTO.setLinkList(targetLinkList);

		Parser parser = targetAnalyzer.getParser();
		if (parser != null) {
			RestaurantDTO dto = parser.parse(page);
			String parseResult = objectMapper.writeValueAsString(dto);
			pageDTO.setParseResult(parseResult);
		}

		return pageDTO;
	}

//	@Override
//	public PageDTO getPageDTO(String url) throws FailingHttpStatusCodeException, MalformedURLException, IOException {
//
//		PageDTO pageDTO = new PageDTO();
//		List<String> targetLinkList = new ArrayList<String>();
//		targetLinkList.add(url);
//
//		pageDTO.setLinkList(targetLinkList);
//
//		pageDTO.setParseResult("AAAAA");
//		return pageDTO;
//	}

}
