package com.example.jimmycarlson.halo5stats.Halo5Query;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.util.Log;
import android.util.LruCache;
import android.widget.ImageView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

import javax.xml.transform.Templates;

/**
 * Created by jimmycarlson on 9/9/16.
 *
 * This is a singleton class for the volley searches
 *
 */
public class PlayerSearcher {
    private static PlayerSearcher mInstance;
    private ImageLoader mImageLoader;
    private RequestQueue mReqeustQueue;
    private static Context mContext;


    public PlayerSearcher(Context context) {
        mContext = context;
        mReqeustQueue = getRequestQueue();

        mImageLoader = new ImageLoader(mReqeustQueue,
                new ImageLoader.ImageCache() {
                    private final LruCache<String, Bitmap>
                            cache = new LruCache<String, Bitmap>(20);

                    @Override
                    public Bitmap getBitmap(String url) {
                        return cache.get(url);
                    }

                    @Override
                    public void putBitmap(String url, Bitmap bitmap) {
                        cache.put(url, bitmap);
                    }
                });
    }

    public static synchronized PlayerSearcher getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new PlayerSearcher(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mReqeustQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            mReqeustQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        }
        return mReqeustQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

    public ImageLoader getImageLoader() {
        return mImageLoader;
    }

}

    /*
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

*/
