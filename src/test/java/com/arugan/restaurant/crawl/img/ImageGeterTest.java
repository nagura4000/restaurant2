package com.arugan.restaurant.crawl.img;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class ImageGeterTest {

	private ImageGeter logic;

	@Before
	public void init() {
		logic = new ImageGeter();
	}

	@Test
	public void test() {

		String imgUrl = "http://image1-3.tabelog.k-img.com/restaurant/images/Rvw/1624/1624632.jpg";
		String savePath = "/tmp/1624632.jpg";

		try {
			logic.saveImage(imgUrl, savePath);
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

	}

}
