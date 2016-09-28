package com.example.jimmycarlson.halo5stats;

import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.example.jimmycarlson.halo5stats.Halo5Query.PlayerSearcher;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private ImageView spartanEmblem;

    private final String oAuthKey = "927352f116114ea19470118dc791f91a";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spartanEmblem = (ImageView) findViewById(R.id.spartanEmblem);

        // Setup PlayerSearcher Query
        RequestQueue queue = PlayerSearcher.getInstance(this.getApplicationContext())
                .getRequestQueue();

        // To add a request
        //PlayerSearcher.getInstance(this).addToRequestQueue(stringRequest);

        searchPlayerEmblem("JCarlsonOWN5");
    }

    public void searchPlayerEmblem(String gamertag) {
        // Build up url
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https")
                .authority("www.haloapi.com")
                .appendPath("profile")
                .appendPath("h5")
                .appendPath("profiles")
                .appendPath(gamertag)
                .appendPath("emblem");
        String url = builder.build().toString();

        // Sends Image request
        ImageRequest request = new ImageRequest(url,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        // Set spartan emblem to bitmap response
                        spartanEmblem.setImageBitmap(response);
                    }
                }, 0, 0, null,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("IMAGE REQUEST ERROR", "Bad request");
                    }
                }){
            @Override
            public Map<String,String> getHeaders() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                // add headers
                params.put("Ocp-Apim-Subscription-Key", oAuthKey);
                return params;
            }
        };

        PlayerSearcher.getInstance(this).addToRequestQueue(request);
    }
}
