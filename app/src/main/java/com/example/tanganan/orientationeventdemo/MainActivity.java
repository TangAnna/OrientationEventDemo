package com.example.tanganan.orientationeventdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.OrientationEventListener;
import android.widget.TextView;

/**
 * @author TangAnna  2018/01/05
 *         重力感应，实时获取手机设备旋转角度（屏幕不做变化，根据角度判断处理横竖屏）
 */
public class MainActivity extends AppCompatActivity {


    private static final String TAG = "MainActivity";
    private TextView mTvOrientation, mTvAngle;
    private OrientationEventListener mOrientationEventListener;//方向事件监听器

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
    }

    private void initListener() {
        mOrientationEventListener = new OrientationEventListener(this) {
            @Override
            public void onOrientationChanged(int orientation) {//这个角度范围根据自己的需要给定（此处是20的误差范围）
                Log.d(TAG, "onOrientationChanged: " + orientation);
                if (orientation > 350 || orientation < 20) { //0度  90
                    mTvOrientation.setText("正竖屏");
                } else if (orientation > 70 && orientation < 110) { //90度
                    mTvOrientation.setText("右横屏");
                } else if (orientation > 160 && orientation < 200) { //180度
                    mTvOrientation.setText("倒竖屏");
                } else if (orientation > 250 && orientation < 290) { //270度
                    mTvOrientation.setText("左横屏");
                } else {
                    return;
                }
                mTvAngle.setText("当前角度:" + orientation + "");
            }
        };

        if (mOrientationEventListener.canDetectOrientation()) {//判断设备是否支持
            mOrientationEventListener.enable();
        } else {
            mOrientationEventListener.disable();//注销
            mTvAngle.setText("当前设备不支持手机旋转！");
        }
    }

    private void initView() {
        mTvAngle = (TextView) findViewById(R.id.tv_main_angle);
        mTvOrientation = (TextView) findViewById(R.id.tv_main_orientation);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //注销方向事件监听器
        if (mOrientationEventListener != null) {
            mOrientationEventListener.disable();
        }
    }
}
