package com.example.dell.bmob_demo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import cn.ittiger.recyclerview.CommonRecyclerViewAdapter;
import cn.ittiger.recyclerview.CommonViewHolder;

/*
 * Created by JohnnyTan on 2017/9/13.
 */

public class ContentAdapter extends CommonRecyclerViewAdapter {
    private Context mContext;

    public ContentAdapter(Context context, List<String> list) {

        super(list);
        mContext = context;
    }

    @Override
    public CommonViewHolder onCreateItemViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(android.R.layout.simple_list_item_1, parent, false);
        return new ContentViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(CommonViewHolder holder, int position, Object item) {

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position, List payloads) {
        super.onBindViewHolder(holder, position, payloads);
        ContentViewHolder viewHolder = (ContentViewHolder) holder;
        viewHolder.mTextView.setText((CharSequence) payloads.get(position));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    class ContentViewHolder extends CommonViewHolder {
        TextView mTextView;

        public ContentViewHolder(View itemView) {

            super(itemView);
            mTextView = (TextView) itemView;
        }
    }
}
