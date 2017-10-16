/**
 * Created by PENGFEI XU on 2017.
 */
package com.example.android.aurin_android_pengfeixu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ViewFlipper;

public class StepthroughActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {

    private ViewFlipper viewFlipper;
    //定义手势检测器实例
    GestureDetector detector;
    //定义动画
    private Animation enter_left_to_right;
    private Animation exit_left_to_right;

    private Animation enter_right_to_left;
    private Animation exit_right_to_left;
    //定义手势动作两点之间的最小距离
    final int FLIP_DISTANCE=50;

    private ImageButton skip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //请求全屏
        getWindow().requestFeature(Window.FEATURE_NO_TITLE); //请求没有标题栏
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stepthrough);

        detector=new GestureDetector(this);
        //获得ViewFlipper实例
        viewFlipper = (ViewFlipper) this.findViewById(R.id.flipper);
        /** 从左到右的两个动画 */
        enter_left_to_right = AnimationUtils.loadAnimation(this,
                R.anim.enter_left_to_right);
        exit_left_to_right = AnimationUtils.loadAnimation(this,
                R.anim.exit_left_to_right);
        /** 从右到左的两个动画 */
        enter_right_to_left = AnimationUtils.loadAnimation(this,
                R.anim.enter_right_to_left);
        exit_right_to_left = AnimationUtils.loadAnimation(this,
                R.anim.exit_right_to_left);

        skip = (ImageButton) findViewById(R.id.skip_button);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StepthroughActivity.this,StartActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // 将该Activity上的触碰时间交个 GestureDetector处理
        return detector.onTouchEvent(event);
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        /**
         * 如果第1个触点事件的x坐标大于第2个触点事件的x坐标超过 FLIP_DISTANCE
         * 也就是手势从右向左滑动
         */
        if (e1.getX()-e2.getX()>FLIP_DISTANCE) {
            showPreviousView();
            return true;
        }
        /**
         * 如果第2个触点事件的x坐标大于第1个触点事件的x坐标超过 FLIP_DISTANCE
         * 也就是手势从左向右滑动
         */
        else if (e2.getX()-e1.getX()>FLIP_DISTANCE) {
            showNextView();
            return true;
        }
        return false;
    }

    /**
     * 显示前一页
     */
    private void showNextView() {
        // 动画效果
        viewFlipper.setInAnimation(enter_left_to_right);
        viewFlipper.setOutAnimation(enter_left_to_right);
        viewFlipper.showPrevious();
    }

    /**
     * 显示下一页
     */
    private void showPreviousView() {
        // 动画效果
        viewFlipper.setInAnimation(enter_right_to_left);
        viewFlipper.setOutAnimation(exit_right_to_left);
        viewFlipper.showNext();
    }



}
