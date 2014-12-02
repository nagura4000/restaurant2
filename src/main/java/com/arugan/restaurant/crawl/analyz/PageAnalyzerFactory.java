package com.arugan.restaurant.crawl.analyz;

import com.arugan.restaurant.crawl.filter.FilterImp;
import com.arugan.restaurant.crawl.link.ScoreLinkGetterImp;
import com.arugan.restaurant.crawl.link.RankingLinkGetterImp;
import com.arugan.restaurant.crawl.parse.Parser;
import com.arugan.restaurant.crawl.parse.TabelogParser;

public class PageAnalyzerFactory {

	public static PageAnalyzer createSitmapAnalyzer() {

		PageAnalyzer analyzer = new PageAnalyzerImp(new FilterImp("^http://tabelog.com/sitemap/$", "^http://tabelog.com/sitemap/[a-z]+/$"));

		// 都道府県
		PageAnalyzer prefecturalAnalyzer = new PageAnalyzerImp(new FilterImp("^http://tabelog.com/sitemap/[a-z]+/$", "^http://tabelog.com/sitemap/[a-z]+/[0-9A\\-]+/$"));

		// 市町村
		PageAnalyzer cityAnalyzer = new PageAnalyzerImp(new FilterImp("^http://tabelog.com/sitemap/[a-z]+/[0-9A\\-]+/$", "^http://tabelog.com/sitemap/[a-z]+/[0-9A\\-]+/[a-z]+"));

		// エリア
		PageAnalyzer areaAnalyzer = new PageAnalyzerImp(new FilterImp("^http://tabelog.com/sitemap/[a-z]+/[0-9A\\-]+/[a-z]+", "^http://tabelog.com/sitemap/[a-z]+/[0-9A\\-]+/[a-z]+/\\?PG=[0-9]+|^http://tabelog\\.com/[a-z]+/A\\d{4}/A\\d{6}/\\d{8}/$"));

		// ページャ
		PageAnalyzer pagerAnalyzer = new PageAnalyzerImp(new FilterImp("^http://tabelog.com/sitemap/[a-z]+/[0-9A\\-]+/[a-z]+/\\?PG=[0-9]+", "^http://tabelog.com/sitemap/[a-z]+/[0-9A\\-]+/[a-z]+/\\?PG=[0-9]+|^http://tabelog\\.com/[a-z]+/A\\d{4}/A\\d{6}/\\d{8}/$"));

		PageAnalyzer detailAnalyzer = new PageAnalyzerImp(new FilterImp("^http://tabelog\\.com/[a-z]+/A\\d{4}/A\\d{6}/\\d{8}/$", ""));
		Parser parser = new TabelogParser();
		detailAnalyzer.setParser(parser);

		analyzer.setNextAnalyzer(prefecturalAnalyzer)
				.setNextAnalyzer(cityAnalyzer)
				.setNextAnalyzer(areaAnalyzer)
				.setNextAnalyzer(pagerAnalyzer)
				.setNextAnalyzer(detailAnalyzer);

		return analyzer;
	}

	public static PageAnalyzer createRankingAnalyzer() {
		PageAnalyzer analyzer = new PageAnalyzerImp(new FilterImp("^http://tabelog.com/$", "^http://tabelog.com/[a-z]+/$"));

		// 都道府県
		PageAnalyzer prefecturalAnalyzer = new PageAnalyzerImp(new FilterImp("^http://tabelog.com/[a-z]+/$", "^http://tabelog.com/[a-z]+/A\\d{4}/$"));

		// 市町村
		PageAnalyzer cityAnalyzer = new PageAnalyzerImp(new FilterImp("^http://tabelog.com/[a-z]+/A\\d{4}/$", "^http://tabelog.com/[a-z]+/A\\d{4}/A\\d{6}/$"));

		// エリア
		PageAnalyzer areaAnalyzer = new PageAnalyzerImp(new FilterImp("^http://tabelog.com/[a-z]+/A\\d{4}/A\\d{6}/$", "^http://tabelog.com/[a-z]+/A\\d{4}/A\\d{6}/rstLst/[a-z]+/\\?Srt=D&SrtT=rt$"),
														new RankingLinkGetterImp());

		// ページャ
		PageAnalyzer pagerAnalyzer = new PageAnalyzerImp(new FilterImp("^http://tabelog.com/[a-z]+/A\\d{4}/A\\d{6}/rstLst/[a-z]+(/\\d+|)/\\?Srt=D&SrtT=rt$",
																		"^http://tabelog.com/[a-z]+/A\\d{4}/A\\d{6}/rstLst/[a-z]+/\\d+/\\?Srt=D&SrtT=rt$|^http://tabelog\\.com/[a-z]+/A\\d{4}/A\\d{6}/\\d{8}/$"),
															new ScoreLinkGetterImp());

		PageAnalyzer detailAnalyzer = new PageAnalyzerImp(new FilterImp("^http://tabelog\\.com/[a-z]+/A\\d{4}/A\\d{6}/\\d{8}/$", ""));
		Parser parser = new TabelogParser();
		detailAnalyzer.setParser(parser);

		analyzer.setNextAnalyzer(prefecturalAnalyzer)
				.setNextAnalyzer(cityAnalyzer)
				.setNextAnalyzer(areaAnalyzer)
				.setNextAnalyzer(pagerAnalyzer)
				.setNextAnalyzer(detailAnalyzer);

		return analyzer;
	}

}
