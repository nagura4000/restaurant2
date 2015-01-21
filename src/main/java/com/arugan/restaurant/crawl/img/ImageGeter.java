package com.arugan.restaurant.crawl.img;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;


public class ImageGeter {

	public void saveImage(String imgUrl, String savePath) throws IOException {
		URL url = new URL(imgUrl);
		URLConnection urlCon = url.openConnection();
		urlCon.setRequestProperty("User-Agent","Mozilla/5.0");
		InputStream in = url.openStream();
		OutputStream out = new FileOutputStream(savePath);

		try {
			byte[] buf = new byte[1024];
			int len = 0;

			while ((len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
			out.flush();
		} finally {
			out.close();
			in.close();
		}
	}

}
