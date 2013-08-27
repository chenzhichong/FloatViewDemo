package net.xsmile.fv;

import android.content.Context;
import android.graphics.PixelFormat;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.ImageView;

public class AnimationView extends ImageView {
	private WindowManager wm = (WindowManager) getContext()
			.getApplicationContext().getSystemService("window");

	// 此wmParams为获取的全局变量，用以保存悬浮窗口的属性
	private WindowManager.LayoutParams wmParams = ((MyApplication) getContext()
			.getApplicationContext()).getMywmParams();

	public AnimationView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		/*
		 * //获取相对屏幕的坐标，即以屏幕左上角为原点 x = event.getRawX(); y = event.getRawY()-25;
		 * //25是系统状态栏的高度 Log.i("currP", "currX"+x+"====currY"+y);
		 */
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			// 获取相对View的坐标，即以此View左上角为原点
			/*mTouchStartX = event.getX();
			mTouchStartY = event.getY();

			Log.i("startP", "startX" + mTouchStartX + "====startY"
					+ mTouchStartY);
			ScreenShot.shot();*/
			createAnimView();
			break;
		case MotionEvent.ACTION_MOVE:
			//updateViewPosition();
			break;

		case MotionEvent.ACTION_UP:
			//updateViewPosition();
			//mTouchStartX = mTouchStartY = 0;
			break;
		}
		return true;
	}
	
	private void createAnimView(){
    	myAV=new AnimationView(MyApplication.getContext());
    	myAV.setBackgroundResource(R.anim.myanim);
    	//获取WindowManager
    	wm=(WindowManager)getApplicationContext().getSystemService("window");
        //设置LayoutParams(全局变量）相关参数
    	wmParams = ((MyApplication)getApplication()).getMywmParams();

         /**
         *以下都是WindowManager.LayoutParams的相关属性
         * 具体用途可参考SDK文档
         */
        wmParams.type=LayoutParams.TYPE_PHONE;   //设置window type
        wmParams.format=PixelFormat.RGBA_8888;   //设置图片格式，效果为背景透明

        //设置Window flag
        wmParams.flags=LayoutParams.FLAG_NOT_TOUCH_MODAL
                              | LayoutParams.FLAG_NOT_FOCUSABLE;
        /*
         * 下面的flags属性的效果形同“锁定”。
         * 悬浮窗不可触摸，不接受任何事件,同时不影响后面的事件响应。
         wmParams.flags=LayoutParams.FLAG_NOT_TOUCH_MODAL 
                               | LayoutParams.FLAG_NOT_FOCUSABLE
                               | LayoutParams.FLAG_NOT_TOUCHABLE;
        */
        
        
        wmParams.gravity=Gravity.LEFT|Gravity.TOP;   //调整悬浮窗口至左上角
        //以屏幕左上角为原点，设置x、y初始值
        wmParams.x=4;
        wmParams.y=196;
        
        //设置悬浮窗口长宽数据
        wmParams.width=60;
        wmParams.height=120;
    
        //显示myFloatView图像
        wm.addView(myFV, wmParams);
    	
    }
}
