package com.example.andre.testview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.andre.testview.R;
import com.example.andre.testview.model.ListItem;

import java.util.List;

/**
 * Created by andre on 30.12.2016.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<ListItem> mList;
    private LayoutInflater mLayoutInflater;
    private Context mContext; // Store the context for easy access

    //Konstruktor des Adapter
    public MyAdapter(Context context, List<ListItem> list) {
        mContext = context;
        mList = list;
        mLayoutInflater = LayoutInflater.from(context);
    }

    // Easy access to the context object in the recyclerview
    private Context getContext() {
        return mContext;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private View container;

        //Konstruktor des ViewHolder
        public MyViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.tv_title);
            //We'll need the container later on, when we add an View.OnClickListener
            container = itemView.findViewById(R.id.container_item_root2);
        }
    }

    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.row_layout2, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ListItem item = mList.get(position);
        holder.title.setText(item.getTitle());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

}