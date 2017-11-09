package com.example.davidma.tab.data;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by davidma on 11/10/17.
 */

public class DataApi {
    private final Context context;

    public DataApi(Context context){
        this.context = context;
    }

    public List<Contact> getContacts(String fileName){
        String json = getString(fileName);
        if(json != null && !json.isEmpty()) {
            Type type = new TypeToken<List<Contact>>() {
            }.getType();

            Gson gson = new Gson();
            return gson.fromJson(json, type);
        }
        return null;

    }

    private String getString(String fileName) {
        String json = null;
        try {
            InputStream is = this.context.getAssets().open(fileName);
            int size = is.available();
            byte[] data = new byte[size];
            is.read(data);
            json = new String(data,"UTF-8");
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }
}
