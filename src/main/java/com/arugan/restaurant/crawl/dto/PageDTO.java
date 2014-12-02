package com.arugan.restaurant.crawl.dto;

import java.util.ArrayList;
import java.util.List;

public class PageDTO {

	private List<String> linkList = new ArrayList<String>();

	private String parseResult;

	public List<String> getLinkList() {
		return linkList;
	}

	public void setLinkList(List<String> linkList) {
		this.linkList = linkList;
	}

	public String getParseResult() {
		return parseResult;
	}

	public void setParseResult(String parseResult) {
		this.parseResult = parseResult;
	}
}
