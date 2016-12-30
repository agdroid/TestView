package com.example.andre.testview.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.andre.testview.R;
import com.example.andre.testview.adapter.MyAdapter;
import com.example.andre.testview.model.DummyData;

import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private MyAdapter mMyAdpater;
    private List myDataset;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        myDataset = DummyData.getListData();

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mMyAdpater = new MyAdapter(this, myDataset);
        mRecyclerView.setAdapter(mMyAdpater);
    }
}
