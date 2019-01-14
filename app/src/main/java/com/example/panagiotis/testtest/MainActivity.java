package com.example.panagiotis.testtest;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "PapMainActivity";



    protected static List<Stock> stocks = new ArrayList<>();
    private ListView stockListView;

    private TextView symbolTextview;
    private TextView openTextview;
    private TextView highTextview;
    private TextView lowTextview;
    private TextView priceTextview;private TextView volumeTextview;private TextView lastestTrafingTextview;
    private TextView prevCloseTextview;
    private TextView changeTextview;
    private TextView changePercTextview;
    private Button backbutton;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        stockListView = findViewById(R.id.stockListView);
        StockAdapter stockAdapter = new StockAdapter(MainActivity.this,
                R.layout.list_record, stocks);
        stockListView.setAdapter(stockAdapter);
        stockListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                setContentView(R.layout.item_of_listview_details);
                symbolTextview = findViewById(R.id.symboF);
                openTextview = findViewById(R.id.open);
                highTextview = findViewById(R.id.high);
                lowTextview = findViewById(R.id.low);
                priceTextview = findViewById(R.id.price);
                volumeTextview = findViewById(R.id.volume);
                lastestTrafingTextview = findViewById(R.id.latesttrading);
                prevCloseTextview = findViewById(R.id.prevclose);
                changeTextview = findViewById(R.id.change);
                changePercTextview = findViewById(R.id.changeperc);
                backbutton = findViewById(R.id.backbtn);


                symbolTextview.setText(stocks.get(position).getSymbol());

                openTextview.setText(stocks.get(position).getOpen());
                highTextview.setText(stocks.get(position).getHigh());
                lowTextview.setText(stocks.get(position).getLow());
                priceTextview.setText(stocks.get(position).getLow());
                volumeTextview.setText(stocks.get(position).getVolume());
                lastestTrafingTextview.setText(stocks.get(position).getLatestTraidingDay());
                prevCloseTextview.setText(stocks.get(position).getPreviousClose());
                changeTextview.setText(stocks.get(position).getChange());
                changePercTextview.setText(stocks.get(position).getChanePercent());

                backbutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       recreate();
                    }
                });


            }
        });

        }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);

        return  true;

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.main_acitvity:
                Toast.makeText(MainActivity.this,"You already watch the stocks",Toast.LENGTH_SHORT).show();

                return true;
            case R.id.portfolio_activity:
                Intent intent=new Intent(this,PortfolioActivity.class);
                startActivity(intent);
                 return true;

            case R.id.logout:
                showLogoutMessage("Logout","Are you sure you want to Logout??");
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }



    }

    public void showLogoutMessage(String title, String Message) {
        AlertDialog  builder = new AlertDialog.Builder(this).create();
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);



        builder.setButton(Dialog.BUTTON_POSITIVE,"NO",new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.setButton(Dialog.BUTTON_NEGATIVE,"YES",new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
                System.exit(0);
            }
        });
        builder.show();
    }
}
