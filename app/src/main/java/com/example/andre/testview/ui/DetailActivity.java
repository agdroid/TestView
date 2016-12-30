package com.example.andre.testview.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.andre.testview.R;

public class DetailActivity extends AppCompatActivity {
    private static final String EXTRA_VISIBILITY = "extraVisibility";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        View undoView = findViewById(R.id.undo);
        String sExtra = getIntent().getExtras().getString(EXTRA_VISIBILITY);

        switch (sExtra) {
            case "visible" :
                findViewById(R.id.undo).setVisibility(View.VISIBLE);
                break;
            case "gone":
                findViewById(R.id.undo).setVisibility(View.GONE);
                break;
            default:
                break;
       }
     }
}
