package com.example.jimmycarlson.halo5stats.Halo5Query;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

/**
 * Created by jimmycarlson on 9/9/16.
 */
public class PlayerSearcher {

    private final String oAuthKey = "927352f116114ea19470118dc791f91a";
    private RequestQueue queue;
    private Bitmap bitmapResponse;  // used to return values from inner classes

    public PlayerSearcher(Context context) {
        RequestQueue queue = Volley.newRequestQueue(context);
    }

    // Gets Bitmap response of spartan emblem
    public Bitmap getSpartanEmblem(String playerGamertag) {

        // Build search url
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https")
                .authority("www.haloapi.com")
                .appendPath("profile")
                .appendPath("h5")
                .appendPath("profiles")
                .appendPath("JCarlsonOwn5")
                .appendPath("emblem");
        String url = builder.build().toString();


        // Sends Image request
        ImageRequest request = new ImageRequest(url,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        // set bitmapResponse to spartan emblem
                        bitmapResponse = response;
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

        if (bitmapResponse == null) {
            System.out.println("NULL AT PLAYER SEARCHER");
        } else {
            System.out.println("NOT NULL AT PLAYER SEARCHER");
        }

        return bitmapResponse;
    }
}
