package com.mao.myquickindexbar.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by 毛麒添 on 2017/2/26 0026.
 * 触摸View的时候，能快速定位获取触摸的是哪个字母
 */

public class MyQuickIndexBar extends View {

    private String[] indexArr = { "A", "B", "C", "D", "E", "F", "G", "H",
            "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
            "V", "W", "X", "Y", "Z" };

    private Paint paint;
    private int width;//控件宽度

    private float latticeHeight;//将控件等分每个部分的高度

    private int lastIndex=-1;//上一次点击字母记录的索引

    public MyQuickIndexBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyQuickIndexBar(Context context) {
        super(context);
        init();
    }

    public MyQuickIndexBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        //初始化画笔参数
        paint=new Paint(Paint.ANTI_ALIAS_FLAG);//设置抗锯齿
        paint.setTextSize(40);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setColor(Color.GRAY);
    }

    //初始化宽高
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = getMeasuredWidth();
        latticeHeight=getMeasuredHeight()*1f/indexArr.length;
    }

    //绘制文本
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < indexArr.length; i++) {
            float x=width/2;
            //每一个格子字母的摆放位置 控件格子高度一半+文本字母高度的一半+格子所处位置*每个格子的高度
            float y=latticeHeight/2+getTextHeight(indexArr[i])/2+i*latticeHeight;
            //如果索引相等，则改变字体的颜色
            paint.setColor(lastIndex==i?Color.BLACK:Color.GRAY);
            canvas.drawText(indexArr[i],x,y,paint);
        }
    }
    //触摸监听，需要有自己的处理，返回true
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                //获取按下的位置
                float y = event.getY();
                //获取按下位置与每个格子高度的余数，这个余数就是每个字母的索引
                int index= (int) (y/latticeHeight);
                if(lastIndex!=index){
                    //Log.w("毛麒添",indexArr[index]);
                    if(index>=0 && index<indexArr.length){//索引安全性检查
                        if(listener!=null){
                            listener.onTouchIndex(indexArr[index]);
                        }
                    }
                }
                lastIndex=index;
                break;
            case MotionEvent.ACTION_UP:
                //抬起手指的时候重置最后记录的位置，防止下次点击重复位置没有反应
                lastIndex=-1;
                break;
        }
        //引发重绘
        invalidate();
        return true;
    }

    /**
     * 获取文本的高度
     * @param text 需要获取高度的文本
     * @return 文本的高度
     */
    private int getTextHeight(String text) {
        Rect rect=new Rect();
        paint.getTextBounds(text,0,text.length(),rect);
        return rect.height();
    }


    //回调监听，把点击的字母暴露给使用者

    private onTouchIndexListener listener;
    public void setOnTouchIndexListener(onTouchIndexListener listener){
         this.listener=listener;
    }
    public interface onTouchIndexListener{
        void onTouchIndex(String text);
    }
}
