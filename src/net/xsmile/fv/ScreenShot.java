package net.xsmile.fv;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

public class ScreenShot {
	private static WindowManager wm;
	public static void shot() {
		// 获取屏幕大小：
		DisplayMetrics metrics = new DisplayMetrics();
		//WindowManager WM = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		wm = (WindowManager)MyApplication.getContext().getSystemService(Context.WINDOW_SERVICE);
		//metrics = MyApplication.getContext().getResources().getDisplayMetrics();
		Display display = wm.getDefaultDisplay();
		display.getMetrics(metrics);
		Log.i("ss" + "  getDefaultDisplay", metrics.toString());
		int height = metrics.heightPixels; // 屏幕高
		int width = metrics.widthPixels; // 屏幕的宽
		//int height = 800; // 屏幕高
		//int width = 480; // 屏幕的宽
		Log.i("ss" + "  getDefaultDisplay", "screenWidth=" + width
				+ "; screenHeight=" + height);
		// 获取显示方式
		int pixelformat = display.getPixelFormat();
		PixelFormat localPixelFormat1 = new PixelFormat();
		PixelFormat.getPixelFormatInfo(pixelformat, localPixelFormat1);
		int deepth = localPixelFormat1.bytesPerPixel;// 位深
		Log.i("ss" + "  getDefaultDisplay", "deepth=" + deepth);
		//int deepth = 4;

		byte[] arrayOfByte = new byte[height * width * deepth];
		long tmp = System.currentTimeMillis();
		try {
			BufferedInputStream bis=new BufferedInputStream(new FileInputStream(
					"/dev/graphics/fb0"));
			Log.i("ss", "-----read start-------");
			bis.read(arrayOfByte);
			Log.i("ss", "-----read end-------time = "
					+ (System.currentTimeMillis() - tmp));
			bis.close();

			BufferedOutputStream bos=new BufferedOutputStream(new FileOutputStream(
					"/data/local/tmp/fb0.png"));
			int[] tmpColor = new int[width * height];
			int r, g, b;
			tmp = System.currentTimeMillis();
			Log.i("ss", "-----bitmap start-------");
			for (int j = 0; j < width * height * deepth; j += deepth) {
				r = arrayOfByte[j] & 0xff;
				g = arrayOfByte[j + 1] & 0xff;
				b = arrayOfByte[j + 2] & 0xff;
				tmpColor[j / deepth] = (r << 16) | (g << 8) | b | (0xff000000);
			}
			Bitmap tmpMap = Bitmap.createBitmap(tmpColor, width, height,
					Bitmap.Config.ARGB_8888);
			Log.i("ss", "-----bitmap end-------time = "
					+ (System.currentTimeMillis() - tmp));

			tmp = System.currentTimeMillis();
			Log.i("ss", "-----compress start-------");
			tmpMap.compress(Bitmap.CompressFormat.PNG, 100, bos);
			Log.i("ss", "-----compress end-------time = "
					+ (System.currentTimeMillis() - tmp));
			tmp = System.currentTimeMillis();
			Log.i("ss", "-----flush start-------");
			bos.flush();
			bos.close();
			Log.i("ss", "-----flush end-------time = "
					+ (System.currentTimeMillis() - tmp));

		} catch (Exception e) {
			Log.i("ss", "Exception");
			e.printStackTrace();
		}
	}
}
