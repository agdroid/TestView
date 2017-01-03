package com.example.andre.testview.ui;

import android.graphics.Canvas;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.andre.testview.R;
import com.example.andre.testview.adapter.MyAdapter;
import com.example.andre.testview.model.DummyData;

import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {
    private static final String TAG = "RecyclerViewActivity";

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

                       //Stackoverflow Use ItemTouchHelper for Swipe-To-Dismiss with another View displayed behind the swiped out
 /*    TODO: Die nächsten Zeilen müssen wieder raus

                        //Besser raus. Die movementFlags können bleicben wie sie sind
                        @Override
                        public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                            if (viewHolder instanceof MyAdapter.MyViewHolder) {
                                int swipeFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
                                return makeMovementFlags(0, swipeFlags);
                            } else {
                                return 0;
                            }
                         }

                        @Override
                        public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                            getDefaultUIUtil().clearView(((MyAdapter.MyViewHolder) viewHolder).getUndoView());
                        }

                        @Override
                        public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
                            if (viewHolder != null) {
                                getDefaultUIUtil().onSelected(((MyAdapter.MyViewHolder) viewHolder).getUndoView());
                            }
                        }

                        @Override
                        public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                            getDefaultUIUtil().onDraw(c, recyclerView, ((MyAdapter.MyViewHolder) viewHolder).getUndoView(), dX, dY,    actionState, isCurrentlyActive);
                        }

                        @Override
                        public void onChildDrawOver(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                            getDefaultUIUtil().onDrawOver(c, recyclerView, ((MyAdapter.MyViewHolder) viewHolder).getUndoView(), dX, dY,    actionState, isCurrentlyActive);
                        }
*/

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(final RecyclerView.ViewHolder viewHolder, int direction) {
                final View undo = viewHolder.itemView.findViewById(R.id.undo);

                //Start: block for debug
                int p = viewHolder.getAdapterPosition();
                String vi = "nicht zugewiesen";
                switch  (undo.getVisibility()) {
                    case 0 :  vi = "View.VISIBLE";  break;
                    case 8 :  vi = "View.GONE";     break;
                }
                Log.d(TAG, "BEGINN onSwiped(): Pos=" + p + " -> direction=" + direction + " -> Visibility = " + vi);
                //End: block for debug


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
                            undo.setVisibility(View.GONE);
                            clearView(mRecyclerView, viewHolder);
                            mRecyclerView.getAdapter().notifyItemChanged(viewHolder.getAdapterPosition());
                        }
                    });

                    undo.setVisibility(View.VISIBLE);

                    //Start: block for debug
                    int p2 = viewHolder.getAdapterPosition();
                    String vi2 = "nicht zugewiesen";
                    switch  (undo.getVisibility()) {
                        case 0 :  vi2 = "View.VISIBLE";  break;
                        case 8 :  vi2 = "View.GONE";     break;
                    }
                    Log.d(TAG, "END onSwiped(): Pos=" + p2 + " -> direction=" + direction + " -> Visibility = " + vi2);
                    //End: block for debug

                    //Sonst wird Wechsel der Sichtbarkeit nicht angezeigt
                    clearView(mRecyclerView, viewHolder);
                    //mRecyclerView.getAdapter().notifyItemChanged(viewHolder.getAdapterPosition());

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
