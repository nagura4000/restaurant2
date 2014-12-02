package com.arugan.restaurant.crawl.dto;

import java.awt.geom.Point2D;
import java.util.List;

public class TabelogDTO extends RestaurantDTO {

	private String name;
	private Double point;
	private Double pointLunch;
	private Double pointDinner;

	private Integer priceLunchMin;
	private Integer priceLunchMax;
	private Integer priceDinnerMin;
	private Integer priceDinnerMax;

//	private String[] breadcrumbs;
	//private String[] genres;
	private List<String> breadcrumbs;
	private List<String> genres;

	private List<String> addresses;
	private String tell;

	private String parking;
	private String noSmoking;

	private String url;
	private List<String> imageUrls;
	private String restaurantUrl;

	private Point2D location;

	private List<ReviewDTO> reviewList;

	/**
	 * @return reviewList
	 */
	public List<ReviewDTO> getReviewList() {
		return reviewList;
	}
	/**
	 * @param reviewList セットする reviewList
	 */
	public void setReviewList(List<ReviewDTO> reviewList) {
		this.reviewList = reviewList;
	}
	/**
	 * @return location
	 */
	public Point2D getLocation() {
		return location;
	}
	/**
	 * @param location セットする location
	 */
	public void setLocation(Point2D location) {
		this.location = location;
	}
	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name セットする name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return point
	 */
	public Double getPoint() {
		return point;
	}
	/**
	 * @param point セットする point
	 */
	public void setPoint(Double point) {
		this.point = point;
	}
	/**
	 * @return pointLunch
	 */
	public Double getPointLunch() {
		return pointLunch;
	}
	/**
	 * @param pointLunch セットする pointLunch
	 */
	public void setPointLunch(Double pointLunch) {
		this.pointLunch = pointLunch;
	}
	/**
	 * @return pointDinner
	 */
	public Double getPointDinner() {
		return pointDinner;
	}
	/**
	 * @param pointDinner セットする pointDinner
	 */
	public void setPointDinner(Double pointDinner) {
		this.pointDinner = pointDinner;
	}
	/**
	 * @return priceLunchMin
	 */
	public Integer getPriceLunchMin() {
		return priceLunchMin;
	}
	/**
	 * @param priceLunchMin セットする priceLunchMin
	 */
	public void setPriceLunchMin(Integer priceLunchMin) {
		this.priceLunchMin = priceLunchMin;
	}
	/**
	 * @return priceLunchMax
	 */
	public Integer getPriceLunchMax() {
		return priceLunchMax;
	}
	/**
	 * @param priceLunchMax セットする priceLunchMax
	 */
	public void setPriceLunchMax(Integer priceLunchMax) {
		this.priceLunchMax = priceLunchMax;
	}
	/**
	 * @return priceDinnerMin
	 */
	public Integer getPriceDinnerMin() {
		return priceDinnerMin;
	}
	/**
	 * @param priceDinnerMin セットする priceDinnerMin
	 */
	public void setPriceDinnerMin(Integer priceDinnerMin) {
		this.priceDinnerMin = priceDinnerMin;
	}
	/**
	 * @return priceDinnerMax
	 */
	public Integer getPriceDinnerMax() {
		return priceDinnerMax;
	}
	/**
	 * @param priceDinnerMax セットする priceDinnerMax
	 */
	public void setPriceDinnerMax(Integer priceDinnerMax) {
		this.priceDinnerMax = priceDinnerMax;
	}
	/**
	 * @return breadcrumbs
	 */
	public List<String> getBreadcrumbs() {
		return breadcrumbs;
	}
	/**
	 * @param breadcrumbs セットする breadcrumbs
	 */
	public void setBreadcrumbs(List<String> breadcrumbs) {
		this.breadcrumbs = breadcrumbs;
	}

	/**
	 * @return addresses
	 */
	public List<String> getAddresses() {
		return addresses;
	}
	/**
	 * @param addresses セットする addresses
	 */
	public void setAddresses(List<String> addresses) {
		this.addresses = addresses;
	}
	/**
	 * @return tell
	 */
	public String getTell() {
		return tell;
	}
	/**
	 * @param tell セットする tell
	 */
	public void setTell(String tell) {
		this.tell = tell;
	}
	/**
	 * @return parking
	 */
	public String getParking() {
		return parking;
	}
	/**
	 * @param parking セットする parking
	 */
	public void setParking(String parking) {
		this.parking = parking;
	}
	/**
	 * @return noSmoking
	 */
	public String getNoSmoking() {
		return noSmoking;
	}
	/**
	 * @param noSmoking セットする noSmoking
	 */
	public void setNoSmoking(String noSmoking) {
		this.noSmoking = noSmoking;
	}
	/**
	 * @return url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url セットする url
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * @return imageUrls
	 */
	public List<String> getImageUrls() {
		return imageUrls;
	}
	/**
	 * @param imageUrls セットする imageUrls
	 */
	public void setImageUrls(List<String> imageUrls) {
		this.imageUrls = imageUrls;
	}
	/**
	 * @return restaurantUrl
	 */
	public String getRestaurantUrl() {
		return restaurantUrl;
	}
	/**
	 * @param restaurantUrl セットする restaurantUrl
	 */
	public void setRestaurantUrl(String restaurantUrl) {
		this.restaurantUrl = restaurantUrl;
	}
	/**
	 * @return genres
	 */
	public List<String> getGenres() {
		return genres;
	}
	/**
	 * @param genres セットする genres
	 */
	public void setGenres(List<String> genres) {
		this.genres = genres;
	}

}
