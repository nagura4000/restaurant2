package com.arugan.restaurant.crawl.parse;

import java.awt.geom.Point2D;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;

import com.arugan.restaurant.constant.Constant;
import com.arugan.restaurant.crawl.dto.RestaurantDTO;
import com.arugan.restaurant.crawl.dto.ReviewDTO;
import com.arugan.restaurant.crawl.dto.TabelogDTO;
import com.arugan.restaurant.crawl.fetcher.Fetcher;
import com.arugan.restaurant.crawl.fetcher.FetcherImp;
import com.arugan.restaurant.crawl.util.Util;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class TabelogParser implements Parser {

	private final String TARGET_URL_REGEX = "http://tabelog\\.com/[a-z]+/A\\d{4}/A\\d{6}/\\d{8}/";
	private final Pattern targetUrlPattern = Pattern.compile(TARGET_URL_REGEX);

	private final String LS = System.getProperty("line.separator");

	private Fetcher fetcher = FetcherImp.getInstance();
	private Pattern gooLocationPattern = Pattern.compile("(E\\d{3}\\.\\d{2}\\.\\d{2}\\.\\d{3})(N\\d{3}\\.\\d{2}\\.\\d{2}\\.\\d{3})");
	private Pattern googleLocationPattern = Pattern.compile("lat=(\\d{2}\\.\\d+)&lng=(\\d{3}\\.\\d+)");

	private Logger logger = Logger.getLogger(this.getClass());

	@Override
	public RestaurantDTO parse(HtmlPage page) throws FailingHttpStatusCodeException, MalformedURLException, IOException {

//		List<String> parseList = new ArrayList<String>();

		TabelogDTO dto = new TabelogDTO();

		HtmlElement nameElement = (HtmlElement)page.getByXPath("//*[@id='rdhead-name']/h2/a/span/span[1]").get(0);
		String name = nameElement.asText();

		dto.setName(name);

		dto.setBreadcrumbs(getBreadcrub(page));

		dto.setPoint(getPoint(page));

		Map<String, Double> pointMap = getPointMap(page);

		dto.setPointLunch(pointMap.get(Constant.LUNCH));
		dto.setPointDinner(pointMap.get(Constant.DINNER));

		Map<String, Integer> priceMap = getPriceMap(page);
		dto.setPriceLunchMin(priceMap.get(Constant.PRICE_LUNCH_MIN));
		dto.setPriceLunchMax(priceMap.get(Constant.PRICE_LUNCH_MAX));
		dto.setPriceDinnerMin(priceMap.get(Constant.PRICE_DINNER_MIN));
		dto.setPriceDinnerMax(priceMap.get(Constant.PRICE_DINNER_MAX));

		dto.setGenres(getGenre(page));
		dto.setAddresses(getAddresses(page));

		dto.setTell(getTel(page));
		dto.setParking(getParking(page));
		dto.setNoSmoking(getNoSmoking(page));

		dto.setUrl(getURL(page));
		dto.setRestaurantUrl(getRestrantURL(page));

		dto.setImageUrls(getImageURL(page));

		dto.setLocation(getLocation(page));

		dto.setReviewList(getReview(page));

		return dto;
	}

	@SuppressWarnings("unchecked")
	private List<ReviewDTO> getReview(HtmlPage page) {
		List<ReviewDTO> list = new ArrayList<ReviewDTO>();
		List<HtmlElement> elements = (List<HtmlElement>)page.getByXPath("//ul[@class='review-newlist']/li");
		for (HtmlElement element : elements) {

			ReviewDTO reviewDTO = new ReviewDTO();

			List<HtmlElement> reviewList = (List<HtmlElement>)element.getByXPath("div/div/h4[@class='subject']/a/strong");
			if (reviewList.isEmpty()) {
				logger.error("Xpath error. div/div/h4[@class='subject']/a/strong");
			} else {
				String title = reviewList.get(0).asText();
				reviewDTO.setTitle(title);
			}

			reviewList = (List<HtmlElement>)element.getByXPath("div/div/div/p[@class='comment']");
			if (reviewList.isEmpty()) {
				reviewList = (List<HtmlElement>)element.getByXPath("div/div/p[@class='comment']");
			}
			if (!reviewList.isEmpty()) {
				String comment = reviewList.get(0).asText();
				comment = comment.replaceAll(LS, " ");
				reviewDTO.setComment(comment);
			}

			reviewList = (List<HtmlElement>)element.getByXPath("div/div/div/p/span[@class='more']/a");
			if (reviewList.isEmpty()) {
				reviewList = (List<HtmlElement>)element.getByXPath("div/div/p/span[@class='more']/a");
			}
			if (!reviewList.isEmpty()) {
				String url = reviewList.get(0).getAttribute("href");
				reviewDTO.setUrl(url);
			}

			reviewList = (List<HtmlElement>)element.getByXPath("div/div/div/p[@class='visit-date']");
			if (reviewList.isEmpty()) {
				logger.error("Xpath error.");
			} else {
				String date = reviewList.get(0).asText();
				date = date.replaceAll("訪問：'", "");
				reviewDTO.setDate(date);
			}

			reviewList = (List<HtmlElement>)element.getByXPath("div/div/div/p[@class='rating lunch']/img");
			String pointLunchValue = reviewList.get(1).getAttribute("alt");
			if (NumberUtils.isNumber(pointLunchValue)) {
				Double pointLunch = Double.parseDouble(pointLunchValue);
				reviewDTO.setPointLunch(pointLunch);
			}

			reviewList = (List<HtmlElement>)element.getByXPath("div/div/div/p[@class='rating dinner']/img");
			String pointDinnerValue = reviewList.get(1).getAttribute("alt");
			if (NumberUtils.isNumber(pointDinnerValue)) {
				Double pointDinner = Double.parseDouble(pointDinnerValue);
				reviewDTO.setPointDinner(pointDinner);
			}

			reviewList = (List<HtmlElement>)element.getByXPath("div/p[@class='reviewer-name auth-mobile' or @class='reviewer-name']/a/span");
			if (reviewList.isEmpty()) {
				logger.error("Xpath error. " + page.getUrl());
			} else {
				String name = reviewList.get(0).getTextContent();
				name = name.replaceAll(" .*$", "");
				reviewDTO.setName(name);
			}

			list.add(reviewDTO);
		}
		return list;
	}

	private Point2D getLocation(HtmlPage page) throws FailingHttpStatusCodeException, MalformedURLException, IOException {

		Point2D location = null;

		String url = page.getUrl().toExternalForm() + "dtlmap/";
		HtmlPage mapPage = fetcher.getPage(url);

		location = getLocationGoogle(mapPage);
		if (location == null) {
			location = getLocationGOO(mapPage);
		}
		return location;
	}

	@SuppressWarnings("unchecked")
	private Point2D getLocationGoogle(HtmlPage mapPage) {
		List<HtmlElement> elements = (List<HtmlElement>)mapPage.getByXPath("//div[@id='for_print_pop']");
		if (!elements.isEmpty()) {
			String mapValue = elements.get(0).getAttribute("data-url");
			Matcher matcher = googleLocationPattern.matcher(mapValue);
			String latValue = "";
			String lonValue = "";
			if (matcher.find()) {
				latValue = matcher.group(1);
				lonValue = matcher.group(2);

				Double latitude = Double.parseDouble(latValue);
				Double longitude = Double.parseDouble(lonValue);

				return new Point2D.Double(longitude, latitude);
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	private Point2D getLocationGOO(HtmlPage mapPage) {
		List<HtmlElement> elements = (List<HtmlElement>)mapPage.getByXPath("//frame[@id='areamatch-goo-frame']");
		if (!elements.isEmpty()) {
			String mapValue = elements.get(0).getAttribute("src");
			Matcher matcher = gooLocationPattern.matcher(mapValue);
			String latValue = "";
			String lonValue = "";
			if (matcher.find()) {
				latValue = matcher.group(1);
				lonValue = matcher.group(2);

				Double longitude = getPoint(lonValue);
				Double latitude = getPoint(latValue);

				return Util.tokyoToWGS(longitude, latitude);
			}
		}
		return null;
	}

	private Double getPoint(String pointValue) {
		pointValue = pointValue.replaceAll("E|N", "");

		String pointValues[] = pointValue.split(".");

		Double degrees =Double.parseDouble(pointValues[0]);
		Double minutes = Double.parseDouble(pointValues[1]);
		Double seconds = Double.parseDouble(pointValues[2] + "." + pointValues[3]);

		Double point = degrees + (minutes * 60 + seconds) / 3600;

		return point;
	}

	@SuppressWarnings("unchecked")
	private List<String> getImageURL(HtmlPage page) {
		List<String> list = new ArrayList<String>();
		List<HtmlElement> elements = (List<HtmlElement>)page.getByXPath("//div[@id='contents-photo']/ul/li/div/p/a");
		for (HtmlElement element : elements) {
			String genre = element.getAttribute("href");
			list.add(genre);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	private String getRestrantURL(HtmlPage page) {
		String value = "";
		List<HtmlElement> elements = (List<HtmlElement>)page.getByXPath("//th[text()='ホームページ']/../td/p/a");
		if (!elements.isEmpty()) {
			value = elements.get(0).getAttribute("href");
		}
		return value;
	}

	@SuppressWarnings("unchecked")
	private String getURL(HtmlPage page) {
		String value = "";
		List<HtmlElement> elements = (List<HtmlElement>)page.getByXPath("//link[@rel='canonical']");
		if (!elements.isEmpty()) {
			value = elements.get(0).getAttribute("href");
		}
		return value;
	}

	@SuppressWarnings("unchecked")
	private String getNoSmoking(HtmlPage page) {
		String smoking = "";
		List<HtmlElement> elements = (List<HtmlElement>)page.getByXPath("//th[text()='禁煙・喫煙']/../td");
		if (!elements.isEmpty()) {
			smoking = elements.get(0).asText();
			smoking = smoking.replaceAll(LS, "");
		}
		return smoking;
	}

	@SuppressWarnings("unchecked")
	private String getParking(HtmlPage page) {
		String parking = "";
		List<HtmlElement> elements = (List<HtmlElement>)page.getByXPath("//th[text()='駐車場']/../td");
		if (!elements.isEmpty()) {
			parking = elements.get(0).asText();
			parking = parking.replaceAll(LS, "");
		}
		return parking;
	}

	@SuppressWarnings("unchecked")
	private String getTel(HtmlPage page) {
		String tel = "";
		List<HtmlElement> elements = (List<HtmlElement>)page.getByXPath("//strong[@property='v:tel']");
		if (!elements.isEmpty()) {
			tel = elements.get(0).asText();
		}
		return tel;
	}

	@SuppressWarnings("unchecked")
	private List<String> getAddresses(HtmlPage page) {
		List<String> list = new ArrayList<String>();
		List<HtmlElement> elements = (List<HtmlElement>)page.getByXPath("//p[@rel='v:addr']");
		if (!elements.isEmpty()) {
			list.add(elements.get(0).asText());
		}

		elements = (List<HtmlElement>)page.getByXPath("//p[@rel='v:addr']/span/a");
		for (HtmlElement element : elements) {
			String address = element.asText();
			list.add(address);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	private List<String> getGenre(HtmlPage page) {
		List<String> genreList = new ArrayList<String>();
		List<HtmlElement> elements = (List<HtmlElement>)page.getByXPath("//span[@property='v:category']");
		for (HtmlElement element : elements) {
			String genre = element.asText();
			genreList.add(genre);
		}
		return genreList;
	}


	@SuppressWarnings("unchecked")
	private Map<String, Integer> getPriceMap(HtmlPage page) {
		Map<String, Integer> priceMap = new HashMap<String, Integer>();

		List<HtmlElement> elements = (List<HtmlElement>)page.getByXPath("//th[text()='平均利用金額']/../td/p/span");
		for (int i = 0; i < elements.size(); i++) {
			HtmlElement element = elements.get(i);
			String pointClass = element.getAttribute("class");
			if (pointClass.equals(Constant.LUNCH)) {
				String value = elements.get(i + 1).asText();
				setPriceMap(priceMap, value, Constant.PRICE_LUNCH_MAX, Constant.PRICE_LUNCH_MAX);
			} else if (pointClass.equals(Constant.DINNER)) {
				String value = elements.get(i + 1).asText();
				setPriceMap(priceMap, value, Constant.PRICE_DINNER_MIN, Constant.PRICE_DINNER_MAX);
			}
		}

		return priceMap;
	}


	private void setPriceMap(Map<String, Integer> pointMap, String value, String minKey, String maxKey) {
		String[] values = value.split("～", -1);
		String min = values[0];
		String max = values[1];
		min = min.replaceAll("[^0-9]", "");
		max = max.replaceAll("[^0-9]", "");
		if (NumberUtils.isNumber(min)) {
			pointMap.put(minKey, Integer.parseInt(min));
		}
		if (NumberUtils.isNumber(max)) {
			pointMap.put(maxKey, Integer.parseInt(max));
		}
	}


	@SuppressWarnings("unchecked")
	private Map<String, Double> getPointMap(HtmlPage page) {

		Map<String, Double> pointMap = new HashMap<String, Double>();

		List<HtmlElement> elements = (List<HtmlElement>)page.getByXPath("//div[@class='score-s']/span");
		List<HtmlElement> pointElement = (List<HtmlElement>)page.getByXPath("//div[@class='score-s']/em");
		for (int i = 0; i < elements.size(); i++) {
			HtmlElement element = elements.get(i);
			String pointClass = element.getAttribute("class");
			if (pointClass.equals(Constant.LUNCH)) {
				String value = pointElement.get(i).asText();
				if (NumberUtils.isNumber(value)) {
					pointMap.put(Constant.LUNCH, Double.parseDouble(value));
				}
			} else {
				String value = pointElement.get(i).asText();
				if (NumberUtils.isNumber(value)) {
					pointMap.put(Constant.DINNER, Double.parseDouble(value));
				}
			}
		}

		return pointMap;
	}

	@SuppressWarnings("unchecked")
	private Double getPoint(HtmlPage page) {
		Double point = null;
		List<HtmlElement> elements = (List<HtmlElement>)page.getByXPath("//span[@property='v:average']");
		if (!elements.isEmpty()) {
			String pointValue = elements.get(0).asText();
			if (NumberUtils.isNumber(pointValue)) {
				point = Double.parseDouble(pointValue);
			}
		}
		return point;
	}

	@SuppressWarnings("unchecked")
	private List<String> getBreadcrub(HtmlPage page) {
		List<String> list = new ArrayList<String>();
		List<HtmlElement> breadcrumbsElement = (List<HtmlElement>)page.getByXPath("//div[@id='location-breadcrumbs-wrap']");
		//breadcrumbsElement.remove(0);
		for (HtmlElement breadcrub : breadcrumbsElement) {
			String tmp = breadcrub.asText();
			tmp = tmp.replaceAll("グルメ", "");
			list.add(tmp);
		}

		return list;
	}

	@Override
	public boolean isTargetUrl(String url) {
		Matcher matcher = targetUrlPattern.matcher(url);
		return matcher.find();
	}

}
