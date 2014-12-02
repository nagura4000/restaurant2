package com.arugan.restaurant.crawl.mapreduce;
import java.io.IOException;
import java.net.UnknownHostException;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import com.arugan.restaurant.crawl.dto.PageDTO;
import com.arugan.restaurant.crawl.logic.CrawlLogic;
import com.arugan.restaurant.crawl.logic.CrawlLogicImp;
import com.arugan.restaurant.mongodb.MongoDB;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

public class CrawlMapper extends Mapper<LongWritable, Text, Text, Text> {
	private Text linkText = new Text();
	private CrawlLogic logic = new CrawlLogicImp();

	private DBCollection target;
	private DBCollection restaurant;

	public CrawlMapper() throws UnknownHostException {
		target = MongoDB.getInstance().getTargetDBCollection();
		restaurant = MongoDB.getInstance().getRestaurantDBCollection();
	}

	@Override
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

		String url = value.toString();

		BasicDBObject targetUrl = new BasicDBObject();
		targetUrl.put("url", url);
		if (target.findOne(targetUrl) != null) {
			return;
		}
		target.insert(targetUrl);

		PageDTO pageDTO = logic.getPageDTO(url);
		if (pageDTO == null) {
			return;
		}

		for (String link : pageDTO.getLinkList()) {
			linkText.set(link);
			context.write(linkText, linkText);
		}

		String parsResult = pageDTO.getParseResult();
		if (!StringUtils.isBlank(parsResult)) {
			DBObject restrantObje = (DBObject)JSON.parse(parsResult);
			if (restrantObje.get("point") != null) {

				BasicDBObject query = new BasicDBObject();
				query.put("url", url);
				DBCursor cursor = restaurant.find(query);
				if (!cursor.hasNext()) {
					restaurant.insert(restrantObje);
				} else {
					restaurant.update(targetUrl, restrantObje);
				}
			}
		}
	}
}
