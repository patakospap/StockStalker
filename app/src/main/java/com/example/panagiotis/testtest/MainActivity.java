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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
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




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        stockListView = findViewById(R.id.stockListView);
        StockAdapter stockAdapter = new StockAdapter(MainActivity.this,
                R.layout.list_record, stocks);
        stockListView.setAdapter(stockAdapter);

        }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);

        return  true;

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
