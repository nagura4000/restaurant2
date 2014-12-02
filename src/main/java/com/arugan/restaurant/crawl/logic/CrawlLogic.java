package com.arugan.restaurant.crawl.logic;

import java.io.IOException;
import java.net.MalformedURLException;

import com.arugan.restaurant.crawl.dto.PageDTO;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;

public interface CrawlLogic {

	PageDTO getPageDTO(String url) throws FailingHttpStatusCodeException, MalformedURLException, IOException;

}
