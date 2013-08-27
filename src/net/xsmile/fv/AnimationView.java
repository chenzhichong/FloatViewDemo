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

	// ��wmParamsΪ��ȡ��ȫ�ֱ��������Ա����������ڵ�����
	private WindowManager.LayoutParams wmParams = ((MyApplication) getContext()
			.getApplicationContext()).getMywmParams();

	public AnimationView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		/*
		 * //��ȡ�����Ļ�����꣬������Ļ���Ͻ�Ϊԭ�� x = event.getRawX(); y = event.getRawY()-25;
		 * //25��ϵͳ״̬���ĸ߶� Log.i("currP", "currX"+x+"====currY"+y);
		 */
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			// ��ȡ���View�����꣬���Դ�View���Ͻ�Ϊԭ��
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
    	//��ȡWindowManager
    	wm=(WindowManager)getApplicationContext().getSystemService("window");
        //����LayoutParams(ȫ�ֱ�������ز���
    	wmParams = ((MyApplication)getApplication()).getMywmParams();

         /**
         *���¶���WindowManager.LayoutParams���������
         * ������;�ɲο�SDK�ĵ�
         */
        wmParams.type=LayoutParams.TYPE_PHONE;   //����window type
        wmParams.format=PixelFormat.RGBA_8888;   //����ͼƬ��ʽ��Ч��Ϊ����͸��

        //����Window flag
        wmParams.flags=LayoutParams.FLAG_NOT_TOUCH_MODAL
                              | LayoutParams.FLAG_NOT_FOCUSABLE;
        /*
         * �����flags���Ե�Ч����ͬ����������
         * ���������ɴ������������κ��¼�,ͬʱ��Ӱ�������¼���Ӧ��
         wmParams.flags=LayoutParams.FLAG_NOT_TOUCH_MODAL 
                               | LayoutParams.FLAG_NOT_FOCUSABLE
                               | LayoutParams.FLAG_NOT_TOUCHABLE;
        */
        
        
        wmParams.gravity=Gravity.LEFT|Gravity.TOP;   //�����������������Ͻ�
        //����Ļ���Ͻ�Ϊԭ�㣬����x��y��ʼֵ
        wmParams.x=4;
        wmParams.y=196;
        
        //�����������ڳ�������
        wmParams.width=60;
        wmParams.height=120;
    
        //��ʾmyFloatViewͼ��
        wm.addView(myFV, wmParams);
    	
    }
}
