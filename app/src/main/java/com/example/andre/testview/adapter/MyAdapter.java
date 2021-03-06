package com.example.andre.testview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
    private static final String TAG = "MyAdapter";

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
        private View mUndoView;

        //Konstruktor des ViewHolder
        public MyViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.tv_title);
            //We'll need the container later on, when we add an View.OnClickListener
            container = itemView.findViewById(R.id.container_item_root2);
            mUndoView = itemView.findViewById(R.id.undo);
        }

        //Stackoverflow Use ItemTouchHelper for Swipe-To-Dismiss with another View displayed behind the swiped out
        public View getUndoView() {
            return mUndoView;
        }
    }

    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.row_layout2, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        View v;
        //Hier könnte der Fehler liegen
        //Grund: Es muss jedes mal die Original-View (XML-Datei) gelsen werden.
        //Durch den Verweis auf den Container bleibt offensichtlich immer der letzte View-Status
        // (GONE oder VISIBLE) erhalten
        v = holder.container.findViewById(R.id.undo);
        String vi = "nicht zugewiesen";
        switch  (v.getVisibility()) {
            case 0 :  vi = "View.VISIBLE";  break;
            case 8 :  vi = "View.GONE";     break;
        }
        ListItem item = mList.get(position);
        holder.title.setText(item.getTitle());

        Log.d(TAG, "onBindViewHolder():Element " + position + " set. -> Title=" + item.getTitle() + " -> Visibility = " + vi);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

}