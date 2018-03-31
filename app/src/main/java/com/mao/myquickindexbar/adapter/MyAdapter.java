package com.mao.myquickindexbar.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mao.myquickindexbar.R;
import com.mao.myquickindexbar.bean.Contacts;

import java.util.ArrayList;

/**
 * Created by 毛麒添 on 2017/2/27 0027.
 * 联系人数据适配器
 */

public class MyAdapter extends BaseAdapter {
    private ArrayList<Contacts> contactsArrayList=new ArrayList<Contacts>();
    private Context context;
    public MyAdapter(Context context, ArrayList<Contacts> contactsArrayList){
        this.context=context;
        this.contactsArrayList=contactsArrayList;
    }
    @Override
    public int getCount() {
        return contactsArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        ViewHolder viewHolder=null;
        if(convertView==null){
            convertView=View.inflate(context, R.layout.layout_listview_item,null);
           /* viewHolder=new ViewHolder();
            viewHolder.tv_index= (TextView) convertView.findViewById(R.id.tv_index);
            viewHolder.tv_name= (TextView) convertView.findViewById(R.id.tv_name);
            convertView.setTag(viewHolder);*/
        }/*else {
            viewHolder= (ViewHolder) convertView.getTag();
        }*/
        ViewHolder viewHolder=ViewHolder.getHolder(convertView);
        Contacts contacts = contactsArrayList.get(position);
        viewHolder.tv_name.setText(contacts.getName());
        //当前汉字的首字母
        String currentWord=contacts.getPinYin().charAt(0)+"";

        if(position>0){
            //获取上一个汉字的首字母
            String lastcurrentWord = contactsArrayList.get(position - 1).getPinYin().charAt(0) + "";
            if(lastcurrentWord.equals(currentWord)){
                //如果首字母相同，将其头部隐藏
                viewHolder.tv_index.setVisibility(View.GONE);
            }else {//不一样则显示(ListView的复用，所以需要设置显示)
                viewHolder.tv_index.setVisibility(View.VISIBLE);
                viewHolder.tv_index.setText(currentWord);
            }
        }else {
            viewHolder.tv_index.setVisibility(View.VISIBLE);
            viewHolder.tv_index.setText(currentWord);
        }

        return convertView;
    }

    static class ViewHolder{
        TextView tv_index;
        TextView tv_name;
        //重新封装ViewHolder
        public ViewHolder(View convertView){
            tv_index= (TextView) convertView.findViewById(R.id.tv_index);
            tv_name= (TextView) convertView.findViewById(R.id.tv_name);
        }
        public static ViewHolder getHolder(View convertView){
            ViewHolder viewHolder = (ViewHolder) convertView.getTag();
            if(viewHolder==null){
               viewHolder=new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            }
            return viewHolder;
        }
    }
}
