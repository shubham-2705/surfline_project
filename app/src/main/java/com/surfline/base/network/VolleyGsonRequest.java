package com.surfline.base.network;

import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.surfline.BuildConfig;
import com.surfline.base.listeners.UpdateGsonListener;


import java.io.UnsupportedEncodingException;


public class VolleyGsonRequest<T> extends Request<T> {

    private static final String TAG = VolleyGsonRequest.class.getSimpleName();
    private Gson mGson;
    private UpdateGsonListener<T> listener;
    private Class classz;
    private final String PROTOCOL_CHARSET = "utf-8";
    private String requestBody;

    public VolleyGsonRequest(int method, String url, UpdateGsonListener<T> listener, Class classz, String body) {
        super(method, url, listener);
        this.listener = listener;
        mGson = new Gson();
        this.classz = classz;
        this.requestBody = body;
        Log.i(TAG, "requestBody ------- " + requestBody);
    }

    public static <T> VolleyGsonRequest doPost(String url, UpdateGsonListener<T> updateListener, Class clasz, String body) {
        if (BuildConfig.DEBUG) {
            Log.i(TAG, url + "");
            Log.i(TAG, body + "");
        }
        return new VolleyGsonRequest(Method.POST, url, updateListener, clasz, body);
    }

    public static <T> VolleyGsonRequest doGet(String url, UpdateGsonListener<T> updateListener, Class clasz) {
        if (BuildConfig.DEBUG) {
            Log.i(TAG, url);
        }
        return new VolleyGsonRequest(Method.GET, url, updateListener, clasz, "");
    }

    public String getBodyContentType() {
        return "application/json";
    }

    @Override
    public byte[] getBody() {
        try {
            return requestBody == null ? null : requestBody.getBytes(PROTOCOL_CHARSET);
        } catch (UnsupportedEncodingException uee) {
            Log.e(TAG, "Unsupported Encoding while trying to get the bytes of %s using %s" + "---requestBody---" + requestBody);
            return null;
        }
    }

    @Override
    protected Response parseNetworkResponse(NetworkResponse response) {
        try {
            String json = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            if (BuildConfig.DEBUG)
                Log.d(TAG, "Response :: " + (response == null || response.data == null ? null : new String(response.data)));
            return Response.success(mGson.fromJson(json, classz), HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
            return Response.error(new ParseError(ex));
        } catch (JsonSyntaxException ex) {
            ex.printStackTrace();
            return Response.error(new ParseError(ex));
        } catch (Exception e) {
            e.printStackTrace();
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(Object response) {
        if (listener != null) {
            listener.onResponse(response);
        }
    }

}