package com.app.viewdraghelpdemo;

import android.content.Context;
import android.graphics.Point;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * VDHLayout Created on 2016/7/19-13:59
 * Description:
 * Created by DongHao
 */
public class VDHLayout extends LinearLayout {

    private ViewDragHelper mViewDragHelper;
    private View mDragView;//自由移动
    private View mAutoBackView;//移动后自动归位
    private View mEdgeTrackerView;//边界控制移动
    private Point mAutoBackOriginPos=new Point();

    public VDHLayout(Context context) {
        this(context,null);
    }

    public VDHLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public VDHLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mViewDragHelper=ViewDragHelper.create(this, 1.0f, new ViewDragHelper.Callback() {
            @Override
            public boolean tryCaptureView(View child, int pointerId) {
                return child==mDragView||child==mAutoBackView;
            }

            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx) {
//                final int leftBround=getPaddingLeft();
//                final int rightBround=getWidth()-100-leftBround;
//
//                final int newLeft=Math.min(Math.max(left,leftBround),rightBround);
//                return newLeft;
                 return left;
            }

            @Override
            public int clampViewPositionVertical(View child, int top, int dy) {
                return top;
            }

            //手指释放时回调
            @Override
            public void onViewReleased(View releasedChild, float xvel, float yvel) {
                //mAutoBackView手指释放时自动退回原来的地方
                if(releasedChild==mAutoBackView){
                    mViewDragHelper.settleCapturedViewAt(mAutoBackOriginPos.x,mAutoBackOriginPos.y);
                    invalidate();
                }
            }
            //在边界拖动时调用
            @Override
            public void onEdgeDragStarted(int edgeFlags, int pointerId) {
                mViewDragHelper.captureChildView(mEdgeTrackerView,pointerId);//该方法可以跳过tryCaptureView，虽然没有返回true，却并不影响
            }
        });

        mViewDragHelper.setEdgeTrackingEnabled(ViewDragHelper.EDGE_LEFT);//边界检测需要添加这行代码
    }



    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return  mViewDragHelper.shouldInterceptTouchEvent(ev);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mViewDragHelper.processTouchEvent(event);
        return true;
    }

    @Override
    public void computeScroll() {
        if(mViewDragHelper.continueSettling(true)){
            invalidate();
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        mAutoBackOriginPos.x=mAutoBackView.getLeft();
        mAutoBackOriginPos.y=mAutoBackView.getTop();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mDragView=getChildAt(0);
        mAutoBackView=getChildAt(1);
        mEdgeTrackerView=getChildAt(2);
    }
}
