package com.arugan.restaurant.crawl.analyz;

import java.util.List;

import com.arugan.restaurant.crawl.filter.Filter;
import com.arugan.restaurant.crawl.link.LinkGetter;
import com.arugan.restaurant.crawl.link.LinkGetterImp;
import com.arugan.restaurant.crawl.parse.Parser;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class PageAnalyzerImp implements PageAnalyzer {

	private PageAnalyzer nextAnalyzer;
	private Parser parse;
	private Filter filter;
	private LinkGetter linkGetter;
//	private Logger logger = Logger.getLogger(this.getClass());

	public PageAnalyzerImp(Filter filter) {
		this.filter = filter;
		this.linkGetter = new LinkGetterImp();
	}

	public PageAnalyzerImp(Filter filter, LinkGetter linkGetter) {
		this.filter = filter;
		this.linkGetter = linkGetter;
	}

	@Override
	public PageAnalyzer setNextAnalyzer(PageAnalyzer analyzer) {
		this.nextAnalyzer = analyzer;
		return nextAnalyzer;
	}

	@Override
	public PageAnalyzer getTargetAnalyzer(String url) {
		if (filter.isTargetFilter(url)) {
			// マッチした
			return this;
		}

		if (nextAnalyzer != null) {
			// 次のAnalyzer
			return nextAnalyzer.getTargetAnalyzer(url);
		}
		// マッチなし
		return null;
	}

	public Parser getParse() {
		return parse;
	}

	public void setParse(Parser parse) {
		this.parse = parse;
	}

	@Override
	public List<String> doFilter(List<String> linkList) {
		return filter.doFilter(linkList);
	}

	@Override
	public Parser getParser() {
		return this.parse;
	}

	@Override
	public void setParser(Parser parser) {
		this.parse = parser;
	}

	@Override
	public List<String> getLinkList(HtmlPage page) {
		return linkGetter.getLinkList(page);
	}

}
