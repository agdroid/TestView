package com.example.andre.testview.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.TextView;

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
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mMyAdpater = new MyAdapter(this, myDataset);
        mRecyclerView.setAdapter(mMyAdpater);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(createHelperCallback());
        itemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    private ItemTouchHelper.Callback createHelperCallback() {
            ItemTouchHelper.SimpleCallback simpleItemTouchCallback =
                    new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(final RecyclerView.ViewHolder viewHolder, int direction) {
                final View undo = viewHolder.itemView.findViewById(R.id.undo);
                if (undo != null) {
                    TextView text_loeschen = (TextView) viewHolder.itemView.findViewById(R.id.undo_loeschen);
                    text_loeschen.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            deleteItem(mRecyclerView, viewHolder, viewHolder.getAdapterPosition());
                            clearView(mRecyclerView, viewHolder);
                        }
                    });

                    TextView text_rueckgaengig = (TextView) viewHolder.itemView.findViewById(R.id.undo_rueckgaengig);
                    text_rueckgaengig.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mRecyclerView.getAdapter().notifyItemChanged(viewHolder.getAdapterPosition());
                            clearView(mRecyclerView, viewHolder);
                            undo.setVisibility(View.GONE);
                        }
                    });

                    undo.setVisibility(View.VISIBLE);

                    //Hier automatisches Löschen nach xx Sekunden einbauen

                }
                //deleteItem(viewHolder.getAdapterPosition());
            }
        };
        return simpleItemTouchCallback;
    }

    //TODO: Gehört in MyAdapter!!! Aufruf mit mMyAdapter.deleteItem(..)
    private void deleteItem(RecyclerView rv, RecyclerView.ViewHolder vh, int pos) {
        myDataset.remove(pos);
        rv.getAdapter().notifyItemRemoved(pos);
    }
}
