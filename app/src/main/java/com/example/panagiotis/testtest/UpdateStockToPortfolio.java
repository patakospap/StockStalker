package com.example.panagiotis.testtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



public class UpdateStockToPortfolio extends AppCompatActivity {

    private static final String TAG = "PopUpdateActivity";


    DataBaseHelper myDbHelper;

    private Button updateButton;
    private EditText symbolTextUpdate;
    private EditText priceTextUpdate;
    private EditText numOfStocksTextUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_stock_t_portfolio);

        DisplayMetrics dn = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dn);

        int width = dn.widthPixels;
        int height = dn.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.6));

        myDbHelper = new DataBaseHelper(this);

        updateButton = findViewById(R.id.updatebtn);
        symbolTextUpdate = findViewById(R.id.symbolFieldUpdate);
        priceTextUpdate = findViewById(R.id.priceFieldUpdate);
        numOfStocksTextUpdate=findViewById(R.id.num_of_stocksField_Update);

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isUpdate = myDbHelper.updateData(symbolTextUpdate.getText().toString(),
                        priceTextUpdate.getText().toString(),
                        numOfStocksTextUpdate.getText().toString());
                if(isUpdate == true) {
                    Toast.makeText(UpdateStockToPortfolio.this, "Data Update", Toast.LENGTH_LONG).show();
                    finish();

                   startActivity(new Intent(UpdateStockToPortfolio.this, PortfolioActivity.class));

                }
                else
                    Toast.makeText(UpdateStockToPortfolio.this,"Data not Updated! Write the correct Symbol",Toast.LENGTH_LONG).show();
            }

        });

        }



}
