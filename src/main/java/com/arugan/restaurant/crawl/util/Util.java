package com.arugan.restaurant.crawl.util;

import java.awt.geom.Point2D;

public class Util {

	/**
     * 日本測地系を世界測地系に変換します。
     * @param longitude 日本測地系の経度
     * @param latitude 日本測地系の緯度
     * @return 世界測地系の経度（x）、緯度（y）
     */
    public static Point2D tokyoToWGS(final double longitude, final double latitude) {
		final double pi = Math.PI;
		final double rd = pi / 180;
		double b = latitude; // 緯度 [度]
		double l = longitude; // 経度 [度]
		final double h = 697.681000; // 高さ [m]
		final double a = 6377397.155;
		final double f = 1 / 299.152813;
		final int a_ = 6378137; // 赤道半径
		final double f_ = 1 / 298.257223; // 扁平率

		// 並行移動量 [m]
		// e.g. $x_ = $x + $dx etc.
		final int dx = -148;
		final int dy = +507;
		final int dz = +681;
		b *= rd;
		l *= rd;

		final double e2 = 2 * f - f * f; // 離心率 e^2
		final double bda = 1 - f; // 極半径 / 赤道半径 b/a
		final double da = a_ - a;
		final double df = f_ - f;
		final double sb = Math.sin(b);
		final double cb = Math.cos(b);
		final double sl = Math.sin(l);
		final double cl = Math.cos(l);

		double rn = 1 / Math.sqrt(1 - e2 * sb * sb);
		final double rm = a * (1 - e2) * rn * rn * rn;
		rn *= a;

		// ずれの計算
		double db = -dx * sb * cl - dy * sb * sl + dz * cb + da * rn * e2 * sb
		                * cb / a + df * (rm / bda + rn * bda) * sb * cb;
		db /= rm + h;
		double dl = -dx * sl + dy * cl;
		dl /= (rn + h) * cb;
		//final double dh = dx * cb * cl + dy * cb * sl + dz * sb - da * a / rn
		//              + df * bda * rn * sb * sb;

		final double retY = (b + db) / rd;
		final double retX = (l + dl) / rd;
		//final double height = h + dh;
		return new Point2D.Double(retX, retY);
    }

}
