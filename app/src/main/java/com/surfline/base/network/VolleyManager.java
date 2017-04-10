package com.surfline.base.network;

import android.content.Context;
import android.text.TextUtils;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;


public class VolleyManager {

    private static final String TAG = "VolleyManager";
    private static VolleyManager mVolleyManager;
    private final RequestQueue mRequestQueue;
//    private Context mContext;

    private VolleyManager(Context context) {
//        mContext = context;
        mRequestQueue = Volley.newRequestQueue(context);
    }

    public static VolleyManager getInstance(Context context) {
        if (mVolleyManager == null) {
            mVolleyManager = new VolleyManager(context);
        }
        return mVolleyManager;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {

//        tag = "";

        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        req.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 24, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        mRequestQueue.add(req);
    }

//	public void clearCache() {
//		mRequestQueue.getCache().clear();
//	}
//
//	public void clearCacheEntry(String uRL) {
//		((DiskBasedCache) mRequestQueue.getCache()).removeForUrl(uRL);
//	}
//
//	public void invalidateEntry(String uRl) {
//		((DiskBasedCache) mRequestQueue.getCache()).removeForUrl(uRl);
//	}

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

}
