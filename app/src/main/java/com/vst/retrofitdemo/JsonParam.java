package com.vst.retrofitdemo;

import android.support.v4.util.ArrayMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by user on 2017/4/1.
 */

public class JsonParam {
    private ArrayMap<String, String> params;

    public JsonParam() {
        params = new ArrayMap<>();
    }

    public void putInt(String k, int v) {
        params.put(k, String.valueOf(v));
    }

    public void putString(String k, String v) {
        params.put(k, v);
    }

    public JSONObject build() {
        JSONObject obj = new JSONObject();
        try {
            for (ArrayMap.Entry<String, String> entry : params.entrySet()) {
                String k = entry.getKey();
                String v = entry.getValue();
                if (v.startsWith("[") && v.endsWith("]")) {
                    JSONArray jsonArray = new JSONArray(v);
                    obj.put(k, jsonArray);
                } else if (v.startsWith("(") && v.endsWith(")")) {
                    JSONObject object = new JSONObject(v);
                    obj.put(k, object);
                } else {
                    obj.put(k, v);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }
}
