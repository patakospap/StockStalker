package com.example.panagiotis.testtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;




public class AddStockToPortfolio extends AppCompatActivity {

    private static final String TAG = "PopActivity";

   DataBaseHelper myDbHelper;

    private Button addStockButton;
    private EditText symbolText;
    private EditText priceText;
    private EditText numOfStocksText;


    //protected MyStock myStock;


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.add_stock_to_portfolio);

        DisplayMetrics dn = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dn);

        int width = dn.widthPixels;
        int height = dn.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.6));

        myDbHelper = new DataBaseHelper(this);

        addStockButton = findViewById(R.id.addNewStockbtn);

        symbolText = findViewById(R.id.symbolFieldUpdate);
        priceText = findViewById(R.id.priceFieldUpdate);
        numOfStocksText = findViewById(R.id.num_of_stocksField_Update);

        addStockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean isInserted = myDbHelper.insertData(symbolText.getText().toString(),
                        priceText.getText().toString(),numOfStocksText.getText().toString());

                if (isInserted=true){

                    Toast.makeText(AddStockToPortfolio.this,"Stock added to your Portfolio",Toast.LENGTH_LONG).show();
                    finish();
                    startActivity(new Intent(AddStockToPortfolio.this,PortfolioActivity.class));
                }
                else
                    Toast.makeText(AddStockToPortfolio.this,"Not Inserted",Toast.LENGTH_LONG).show();
            }
        });










        }

}
