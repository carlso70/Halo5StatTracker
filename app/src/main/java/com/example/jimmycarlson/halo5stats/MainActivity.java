package com.example.jimmycarlson.halo5stats;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.jimmycarlson.halo5stats.Halo5Query.PlayerSearcher;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private ImageView spartanEmblem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spartanEmblem = (ImageView) findViewById(R.id.spartanEmblem);

        // Setup PlayerSearcher Query
        PlayerSearcher searcher = new PlayerSearcher(getApplicationContext());

        Bitmap emblemBitmap = searcher.getSpartanEmblem("JCarlsonOwn5");
        if (emblemBitmap != null) {
            spartanEmblem.setImageBitmap(emblemBitmap);
            System.out.println("NOT NULL BITMAP");
        } else {
            System.out.println("BITMAP IS NULL");
        }

    }
}
