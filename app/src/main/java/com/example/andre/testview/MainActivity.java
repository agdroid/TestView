package com.example.andre.testview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.example.andre.testview.ui.DetailActivity;
import com.example.andre.testview.ui.RecyclerViewActivity;


public class MainActivity extends AppCompatActivity {
    private static final String EXTRA_VISIBILITY = "extraVisibility";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button_visible).setOnClickListener(mGlobal_OnClickListener);
        findViewById(R.id.button_gone).setOnClickListener(mGlobal_OnClickListener);
        findViewById(R.id.button_recyclerview).setOnClickListener(mGlobal_OnClickListener);

    }

    //Global On click listener for all views
    final OnClickListener mGlobal_OnClickListener = new OnClickListener() {
        private Intent intent;

        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.button_visible:
                    intent = new Intent(MainActivity.this, DetailActivity.class);
                    intent.putExtra(EXTRA_VISIBILITY, "visible");
                    break;
                case R.id.button_gone:
                    intent = new Intent(MainActivity.this, DetailActivity.class);
                    intent.putExtra(EXTRA_VISIBILITY, "gone");
                    break;
                case R.id.button_recyclerview:
                    intent = new Intent(MainActivity.this, RecyclerViewActivity.class);
                    break;
                default:

                    break;
            }
            startActivity(intent);
        }
    };

}
