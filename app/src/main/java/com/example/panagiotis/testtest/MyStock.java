package com.example.panagiotis.testtest;


import android.util.Log;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MyStock  {

    private static final String TAG = "GAINLOSS";


        private String symbol;
        private String price;
        private String numOfStocks;



        public MyStock(String symbol ,String price, String numOfStocks) {


            this.symbol = symbol;
            this.price = price;
            this.numOfStocks = numOfStocks;

        }



    public String getSymbol() {
            return symbol;
        }

        public void setSymbol(String symbol) {
            this.symbol = symbol;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getNumOfStocks() {
            return numOfStocks;
        }

        public void setNumOfStocks(String numOfStocks) {
            this.numOfStocks = numOfStocks;
        }



    public float getGainOrLoss(List<Stock> stocks){
            float gainOrLoss=0;
            for(Stock stock :stocks){
                if (stock.getSymbol().equals(symbol)){
                    Log.d(TAG, "getGainOrLoss:" +stock.getPrice());
                    float priceNow=Float.valueOf(stock.getPrice());
                    float priceWhenBuy = Float.valueOf( this.price);
                    gainOrLoss= (priceNow*Integer.parseInt(numOfStocks) - priceWhenBuy*Integer.parseInt(numOfStocks));




                }
            }

            return gainOrLoss;
        }




    }


