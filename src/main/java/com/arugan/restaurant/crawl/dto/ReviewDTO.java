package com.arugan.restaurant.crawl.dto;

public class ReviewDTO {
	private String name;
	private String title;
	private String comment;
	private String url;
	private String date;
	private Double pointLunch;
	private Double pointDinner;
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
	 * @return title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title セットする title
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return comment
	 */
	public String getComment() {
		return comment;
	}
	/**
	 * @param comment セットする comment
	 */
	public void setComment(String comment) {
		this.comment = comment;
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
	 * @return date
	 */
	public String getDate() {
		return date;
	}
	/**
	 * @param date セットする date
	 */
	public void setDate(String date) {
		this.date = date;
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
}
