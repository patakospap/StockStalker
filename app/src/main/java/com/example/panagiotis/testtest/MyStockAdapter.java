package com.example.panagiotis.testtest;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Locale;

import static com.example.panagiotis.testtest.MainActivity.stocks;

public class MyStockAdapter extends ArrayAdapter {
    private static final String TAG = "MyStockAdapter";

    private final LayoutInflater inflater;
    private final int layoutResource;
    private final List<MyStock> myPortfstocks;

    public MyStockAdapter(Context context,
                        int resource,
                        List<MyStock> objects) {
        super(context, resource, objects);

        inflater = LayoutInflater.from(context);
        layoutResource = resource;
        myPortfstocks = objects;
    }

    @Override
    public int getCount() {
        return myPortfstocks.size();
    }

    @NonNull

    @Override
    public View getView(int position,
                        @Nullable View convertView,
                        @NonNull ViewGroup parent) {

        View view = inflater.inflate(layoutResource,
                parent,
                false);

        TextView symbol = view.findViewById(R.id.symbolPortfTxt);
        TextView price = view.findViewById(R.id.pricePortfTxt);
        TextView numOfStocks = view.findViewById(R.id.numOfStocksPortfTxt);
        TextView gainLoss = view.findViewById(R.id.gainOrLosstxt);

        MyStock mystock = myPortfstocks.get(position);
        double GainOrLoss = mystock.getGainOrLoss(stocks);


        symbol.setText(mystock.getSymbol());
        price.setText(mystock.getPrice());
        numOfStocks.setText(mystock.getNumOfStocks());

        if (GainOrLoss<0){
         gainLoss.setTextColor(Color.RED);
         gainLoss.setText(String.format(Locale.US,"%.2f",GainOrLoss));

        }
        else{
            gainLoss.setTextColor(Color.GREEN);
            //gainLoss.setText(String.valueOf(GainOrLoss));
            gainLoss.setText(String.format(Locale.US,"%.2f",GainOrLoss));

        }


        return view;
    }
}
