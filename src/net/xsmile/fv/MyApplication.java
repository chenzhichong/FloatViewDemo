package net.xsmile.fv;

import android.app.Application;
import android.content.Context;
import android.view.WindowManager;

public class MyApplication extends Application {
	
	/**
	 * 创建全局变量
	 * 全局变量一般都比较倾向于创建一个单独的数据类文件，并使用static静态变量
	 * 
	 * 这里使用了在Application中添加数据的方法实现全局变量
	 * 注意在AndroidManifest.xml中的Application节点添加android:name=".MyApplication"属性
	 * 
	 */
	
	/**
	 * 全局的上下文.
	 */
	private static Context mContext;
	
	/**获取Context.
	 * @return
	 */
	public static Context getContext(){
		return mContext;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		mContext = getApplicationContext();
	}

	private WindowManager.LayoutParams wmParams=new WindowManager.LayoutParams();

	public WindowManager.LayoutParams getMywmParams(){
		return wmParams;
	}
}