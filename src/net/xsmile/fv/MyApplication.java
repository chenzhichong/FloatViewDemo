package net.xsmile.fv;

import android.app.Application;
import android.content.Context;
import android.view.WindowManager;

public class MyApplication extends Application {
	
	/**
	 * ����ȫ�ֱ���
	 * ȫ�ֱ���һ�㶼�Ƚ������ڴ���һ���������������ļ�����ʹ��static��̬����
	 * 
	 * ����ʹ������Application��������ݵķ���ʵ��ȫ�ֱ���
	 * ע����AndroidManifest.xml�е�Application�ڵ����android:name=".MyApplication"����
	 * 
	 */
	
	/**
	 * ȫ�ֵ�������.
	 */
	private static Context mContext;
	
	/**��ȡContext.
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