package com.example.panagiotis.testtest;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JSONParser {
    private static final String TAG = "JSONParser";

    public static final String ROOT_JEY = "Global Quote";
    public static final String SYMBOL_KEY = "01. symbol";
    public static final String OPEN_KEY = "02. open";
    public static final String HIGH_KEY = "03. high";
    public static final String LOW_KEY = "04. low";
    public static final String PRICE_KEY = "05. price";
    public static final String VOLUME_KEY = "06. volume";
    public static final String LATEST_TRADING_KEY = "07. latest trading day";
    public static final String PREVIOUS_KEY = "08. previous close";
    public static final String CHANGE_KEY = "09. change";
    public static final String CHANGE_PERCENT_KEY = "10. change percent";

    private Stock stock;

    public JSONParser(){
        this.stock=null;
    }

    public Stock parseJson(String jsonText){
        try{

            // jsonObject = new JSONObject(ROOT_JEY);
            //JSONObject jsonStock = jsonObject.getJSONObject(jsonText);
            //JSONArray jsonStockArray = new JSONArray(jsonText);
            //for(int i=0; i<jsonStockArray.length();i++){
            //JSONObject jsonStock = jsonStockArray.getJSONObject(i);
            JSONObject jsonObject = new JSONObject(jsonText);
            JSONObject jsonStock = jsonObject.getJSONObject(ROOT_JEY);


            Log.d(TAG, "parseJson:haha ");

            //for(int i=0; i<jsonObject.length();i++){
            //JSONObject jsonStock = jsonObject.getJSONObject(i);

            //JSONObject sy = jsonStock.getJSONObject(SYMBOL_KEY);
            String symbol = jsonStock.getString(SYMBOL_KEY);

            //JSONObject op = jsonStock.getJSONObject(OPEN_KEY);
            String o = jsonStock.getString(OPEN_KEY);
            //int open = Integer.parseInt(o);

            //JSONObject hi = jsonStock.getJSONObject(HIGH_KEY);
            String h = jsonStock.getString(HIGH_KEY);
            //int high = Integer.parseInt(h);

            //JSONObject lo = jsonStock.getJSONObject(LOW_KEY);
            String l = jsonStock.getString(LOW_KEY);
            //int low = Integer.parseInt(l);

            //JSONObject pr = jsonStock.getJSONObject(PRICE_KEY);
            String p = jsonStock.getString(PRICE_KEY);
            //int price = Integer.parseInt(p);

            //JSONObject vol = jsonStock.getJSONObject(VOLUME_KEY);
            String volume = jsonStock.getString(VOLUME_KEY);

            //JSONObject lt = jsonStock.getJSONObject(LATEST_TRADING_KEY);
            String latestTradingDay = jsonStock.getString(LATEST_TRADING_KEY);

            //JSONObject pre = jsonStock.getJSONObject(PREVIOUS_KEY);
            String prev = jsonStock.getString(PREVIOUS_KEY);
            //int previousClose = Integer.parseInt(prev);

            //JSONObject ch = jsonStock.getJSONObject(CHANGE_KEY);
            String c = jsonStock.getString(CHANGE_KEY);
            //int change = Integer.parseInt(c);

            //JSONObject cpk = jsonStock.getJSONObject(CHANGE_PERCENT_KEY);
            String cp = jsonStock.getString(CHANGE_PERCENT_KEY);
            //int change_percent = Integer.parseInt(cp);


            stock = new Stock(symbol,o,h,l,p,volume,latestTradingDay,prev,c,cp);


        } catch (JSONException e) {

            Log.e(TAG, "parseJson: PARSING ERROR " + e );
        }


        return  stock;

        //} catch (JSONException e) {
        //Log.e(TAG, "parseJson: PARSING ERROR " , e );
        // }


    }
}
