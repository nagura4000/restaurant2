package com.arugan.restaurant.crawl.logic;

import org.junit.Before;

public class CrawlLogicImpTest {
	private CrawlLogic logic;

	@Before
	public void init() {
		logic = new CrawlLogicImp();
	}

//	@Test
//	public void test() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
//		PageDTO pageDTO = logic.getPageDTO("http://tabelog.com/");
//		for (String link : pageDTO.getLinkList()) {
//			System.out.println(link);
//		}
//	}
//
//	@Test
//	public void testランキング１() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
//		PageDTO pageDTO = logic.getPageDTO("http://tabelog.com/kanagawa/");
//		for (String link : pageDTO.getLinkList()) {
//			System.out.println(link);
//		}
//	}
//
//	@Test
//	public void testランキング2() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
//		PageDTO pageDTO = logic.getPageDTO("http://tabelog.com/kanagawa/A1401/");
//		for (String link : pageDTO.getLinkList()) {
//			System.out.println(link);
//		}
//	}
//
//	@Test
//	public void testランキング3() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
//		PageDTO pageDTO = logic.getPageDTO("http://tabelog.com/kanagawa/A1402/A140204/");
//		for (String link : pageDTO.getLinkList()) {
//			System.out.println(link);
//		}
//	}
//
//	@Test
//	public void testランキング4() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
//		PageDTO pageDTO = logic.getPageDTO("http://tabelog.com/kanagawa/A1402/A140204/rstLst/washoku/?Srt=D&SrtT=rt");
//		for (String link : pageDTO.getLinkList()) {
//			System.out.println(link);
//		}
//	}
//
//	@Test
//	public void testランキング5() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
//		PageDTO pageDTO = logic.getPageDTO("http://tabelog.com/kanagawa/A1402/A140204/rstLst/washoku/11/?Srt=D&SrtT=rt");
//		for (String link : pageDTO.getLinkList()) {
//			System.out.println(link);
//		}
//	}
//
//
//	@Test
//	public void testサイトマップ() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
//		PageDTO pageDTO = logic.getPageDTO("http://tabelog.com/sitemap/");
//		for (String link : pageDTO.getLinkList()) {
//			System.out.println(link);
//		}
//	}
//
//	@Test
//	public void test都道府県() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
//		PageDTO pageDTO = logic.getPageDTO("http://tabelog.com/sitemap/kanagawa/");
//		for (String link : pageDTO.getLinkList()) {
//			System.out.println(link);
//		}
//	}
//
//	@Test
//	public void test市町村() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
//		PageDTO pageDTO = logic.getPageDTO("http://tabelog.com/sitemap/kanagawa/A1406-A140601/");
//		for (String link : pageDTO.getLinkList()) {
//			System.out.println(link);
//		}
//	}
//
//	@Test
//	public void testエリア() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
//		PageDTO pageDTO = logic.getPageDTO("http://tabelog.com/sitemap/tokyo/A1301-A130101/gi/");
//		for (String link : pageDTO.getLinkList()) {
//			System.out.println(link);
//		}
//	}
//
//	@Test
//	public void testページャ() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
//		PageDTO pageDTO = logic.getPageDTO("http://tabelog.com/sitemap/tokyo/A1301-A130101/gi/?PG=2");
//		for (String link : pageDTO.getLinkList()) {
//			System.out.println(link);
//		}
//	}
//
//
//
//	@Test
//	public void testTEMPO() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
//		PageDTO pageDTO = logic.getPageDTO("http://tabelog.com/hyogo/A2808/A280801/28026711/");
//		System.out.println(pageDTO.getParseResult());
//	}
//
//
//	@Test
//	public void testTEMPO2() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
//		PageDTO pageDTO = logic.getPageDTO("http://tabelog.com/kanagawa/A1401/A140101/14051195/");
//		System.out.println(pageDTO.getParseResult());
//	}
//
//
//	@Test
//	public void testTEMPOさわやか() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
//		PageDTO pageDTO = logic.getPageDTO("http://tabelog.com/shizuoka/A2202/A220203/22000392/");
//		System.out.println(pageDTO.getParseResult());
//	}
//
//	@Test
//	public void testTEMPO3() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
//		PageDTO pageDTO = logic.getPageDTO("http://tabelog.com/shizuoka/A2202/A220203/22022788/");
//		System.out.println(pageDTO.getParseResult());
//	}
//
//	@Test
//	public void testTEMPO4() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
//		PageDTO pageDTO = logic.getPageDTO("http://tabelog.com/hyogo/A2808/A280801/28026711/");
//		System.out.println(pageDTO.getParseResult());
//	}

//	@Test
//	public void testMongoDB() throws UnknownHostException {
//		MongoClient mongoClient = new MongoClient("localhost", 27017);
//		DB db= mongoClient.getDB("testDB");
//
//		DBCollection member = db.getCollection("member");
//
//		String yahoo = "http://www.yahoo.co.jp";
//		BasicDBObject document = new BasicDBObject("url", yahoo);
//		member.insert(document);
//
//		BasicDBObject searchObj = new BasicDBObject();
//		searchObj.put("url", yahoo);
//		DBObject newSerizawa = member.findOne(searchObj);
////		DBObject newSerizawa = member.findOne();
//		System.out.println(newSerizawa.get("url"));
//	}
}
