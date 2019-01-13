package com.example.panagiotis.testtest;


import android.util.Log;

import java.util.ArrayList;
import java.util.List;

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
                    float priceNow=Integer.parseInt(this.price);
                    float priceWhenBuy = Float.valueOf(stock.getPrice());
                    gainOrLoss= priceNow - priceWhenBuy;

                }
            }

            return gainOrLoss;
        }




    }


