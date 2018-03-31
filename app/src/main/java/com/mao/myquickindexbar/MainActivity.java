package com.mao.myquickindexbar;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.OvershootInterpolator;
import android.widget.ListView;
import android.widget.TextView;

import com.mao.myquickindexbar.adapter.MyAdapter;
import com.mao.myquickindexbar.bean.Contacts;
import com.mao.myquickindexbar.view.MyQuickIndexBar;
import com.nineoldandroids.view.ViewHelper;
import com.nineoldandroids.view.ViewPropertyAnimator;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    private MyQuickIndexBar my_quick_index_bar;

    private ListView listView;
    private TextView tv_text;
    private ArrayList<Contacts> contactsArrayList=new ArrayList<Contacts>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        //初始化ListView数据
        fillList();
        //对通讯录数据进行排序
        Collections.sort(contactsArrayList);
        //设置数据
        listView.setAdapter(new MyAdapter(this,contactsArrayList));

        my_quick_index_bar.setOnTouchIndexListener(new MyQuickIndexBar.onTouchIndexListener() {
            @Override
            public void onTouchIndex(String text) {
                //根据点击的字母与遍历集合获取的首字母相比较
                for (int i = 0; i < contactsArrayList.size(); i++) {
                    String firstWord = contactsArrayList.get(i).getPinYin().charAt(0) + "";
                    if(text.equals(firstWord)){//首字母相同
                       listView.setSelection(i);
                        break;//只要找到就直接显示就可以

                    }
                }
                //显示当前触摸的字母
                showCurrentWord(text);
            }
        });
        //开始隐藏控件
        ViewHelper.setScaleX(tv_text,0);
        ViewHelper.setScaleY(tv_text,0);
    }
    private Handler handler=new Handler();
    private boolean isScale=false;//动画是否缩放
    //显示当前触摸的字母
    private void showCurrentWord(String text) {
         tv_text.setText(text);
        if(!isScale){
            isScale=true;
            ViewPropertyAnimator.animate(tv_text).scaleX(1f).setInterpolator(new OvershootInterpolator()).setDuration(350).start();
            ViewPropertyAnimator.animate(tv_text).scaleY(1f).setInterpolator(new OvershootInterpolator()).setDuration(350).start();
        }

        //执行延迟前清除所有消息
        handler.removeCallbacksAndMessages(null);
        //执行延迟缩放动画，让TextView缩小至消失
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ViewPropertyAnimator.animate(tv_text).scaleX(0f).setInterpolator(new OvershootInterpolator()).setDuration(350).start();
                ViewPropertyAnimator.animate(tv_text).scaleY(0f).setInterpolator(new OvershootInterpolator()).setDuration(350).start();
                isScale=false;
            }
        },1000);
    }

    private void initView(){
        my_quick_index_bar= (MyQuickIndexBar) findViewById(R.id.my_quick_index_bar);
        listView= (ListView) findViewById(R.id.listview);
        tv_text= (TextView) findViewById(R.id.tv_text);
    }

    private void fillList() {
        // 虚拟数据
        contactsArrayList.add(new Contacts("路飞"));
        contactsArrayList.add(new Contacts("路飞1"));
        contactsArrayList.add(new Contacts("索隆"));
        contactsArrayList.add(new Contacts("索隆1"));
        contactsArrayList.add(new Contacts("娜美"));
        contactsArrayList.add(new Contacts("娜娜"));
        contactsArrayList.add(new Contacts("艾斯"));
        contactsArrayList.add(new Contacts("艾斯1"));
        contactsArrayList.add(new Contacts("乌索普"));
        contactsArrayList.add(new Contacts("桑吉士"));
        contactsArrayList.add(new Contacts("乔巴"));
        contactsArrayList.add(new Contacts("罗宾"));
        contactsArrayList.add(new Contacts("香克斯"));
        contactsArrayList.add(new Contacts("蒙奇·D·龙"));
        contactsArrayList.add(new Contacts("哥尔·D·罗杰"));
        contactsArrayList.add(new Contacts("雷利"));
        contactsArrayList.add(new Contacts("巴基"));
        contactsArrayList.add(new Contacts("萨博"));
        contactsArrayList.add(new Contacts("唐吉诃德·多弗朗明哥"));
        contactsArrayList.add(new Contacts("克洛克达尔"));
        contactsArrayList.add(new Contacts("弗兰奇"));
        contactsArrayList.add(new Contacts("藤虎"));
        contactsArrayList.add(new Contacts("波雅·汉库克"));
        contactsArrayList.add(new Contacts("鹰眼米霍克"));
        contactsArrayList.add(new Contacts("卡文迪许"));
        contactsArrayList.add(new Contacts("青雉"));
        contactsArrayList.add(new Contacts("凯多"));
        contactsArrayList.add(new Contacts("布鲁克"));
        contactsArrayList.add(new Contacts("布哥"));
    }
}
