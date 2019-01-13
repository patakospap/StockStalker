package com.example.panagiotis.testtest;

public class Stock {

    private String symbol;
    private String open;
    private String high;
    private String low;
    private String price;
    private String volume;
    private String latestTraidingDay;
    private String previousClose;
    private String change;
    private String chanePercent;

    public Stock(String symbol, String open, String high, String low, String price, String volume, String latestTraidingDay, String previousClose, String change, String chanePercent) {
        this.symbol = symbol;
        this.open = open;
        this.high = high;
        this.low = low;
        this.price = price;
        this.volume = volume;
        this.latestTraidingDay = latestTraidingDay;
        this.previousClose = previousClose;
        this.change = change;
        this.chanePercent = chanePercent;
    }



    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getLatestTraidingDay() {
        return latestTraidingDay;
    }

    public void setLatestTraidingDay(String latestTraidingDay) {
        this.latestTraidingDay = latestTraidingDay;
    }

    public String getPreviousClose() {
        return previousClose;
    }

    public void setPreviousClose(String previousClose) {
        this.previousClose = previousClose;
    }

    public String getChange() {
        return change;
    }

    public void setChange(String change) {
        this.change = change;
    }

    public String getChanePercent() {
        return chanePercent;
    }

    public void setChanePercent(String chanePercent) {
        this.chanePercent = chanePercent;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "symbol='" + symbol + '\'' +
                ", open='" + open + '\'' +
                ", high='" + high + '\'' +
                ", low='" + low + '\'' +
                ", price='" + price + '\'' +
                ", volume='" + volume + '\'' +
                ", latestTraidingDay='" + latestTraidingDay + '\'' +
                ", previousClose='" + previousClose + '\'' +
                ", change='" + change + '\'' +
                ", chanePercent='" + chanePercent + '\'' +
                '}';
    }
}
