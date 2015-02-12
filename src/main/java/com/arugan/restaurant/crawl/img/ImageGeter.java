package com.arugan.restaurant.crawl.img;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.codec.binary.Base64;


public class ImageGeter {

	public String saveImage(String imgUrl, String savePath) throws IOException {
		URL url = new URL(imgUrl);
		URLConnection urlCon = url.openConnection();
		urlCon.setRequestProperty("User-Agent","Mozilla/5.0");
		InputStream in = url.openStream();
		OutputStream out = new FileOutputStream(savePath);

		String imageString = "";
		try {
			byte[] buf = new byte[1024];
			int len = 0;

			while ((len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
			out.flush();
		} finally {

			File file = new File(savePath);
			int fileLen = (int)file.length();
			byte[] data = new byte[fileLen];
			@SuppressWarnings("resource")
			FileInputStream fis = new FileInputStream(file);
			fis.read(data);
			imageString = Base64.encodeBase64String(data);

//			File file = new File(savePath);
//			BufferedImage image = null;
//
//			try {
//				image = ImageIO.read(file);
//			} catch (IOException e) {
//			}
//
//			ByteArrayOutputStream baos = new ByteArrayOutputStream();
//			BufferedOutputStream bos = new BufferedOutputStream(baos);
//			image.flush();
//			try {
//				ImageIO.write(image, "jpg", bos);
//				bos.flush();
//				bos.close();
//			} catch (IOException e) {
//			}
//
//			byte[] bImage = baos.toByteArray();
//
//			//imageString = Base64.encode(bImage);
//			imageString = Base64.encodeBase64String(bImage);

			out.close();
			in.close();
		}

		return imageString;
	}

}
