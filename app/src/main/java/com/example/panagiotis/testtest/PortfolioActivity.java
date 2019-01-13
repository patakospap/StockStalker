package com.example.panagiotis.testtest;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.facebook.login.LoginManager;

import java.util.ArrayList;
import java.util.List;

public class PortfolioActivity extends AppCompatActivity {
    private static final String TAG = "PortfolioActivity";

    private Button newStockbutton;
    private ListView portfolioListView;

    private DataBaseHelper myDbHelper;

    private List<MyStock> myStocks = new ArrayList<MyStock>();
    private MyStockAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portfolio);

        myDbHelper = new DataBaseHelper(this);


        portfolioListView = findViewById(R.id.portfolioListView);
        newStockbutton = findViewById(R.id.newStockbtn);
        newStockbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PortfolioActivity.this, AddStockToPortfolio.class));
            }
        });




        Cursor data = myDbHelper.getAllData();


        if (data.getCount() == 0) {

            showMessage("Error", "Nothing found");
            return;
        }

       StringBuffer buffer = new StringBuffer();
        while (data.moveToNext()) {
            buffer.append("Id :" + data.getString(0) + "\n");
            buffer.append("Symbol :" + data.getString(1) + "\n");
            buffer.append("Price :" + data.getString(2) + "\n");
            buffer.append("NumofStocks :" + data.getString(3) + "\n\n");


                MyStock myStock = new MyStock(data.getString(1), data.getString(2), data.getString(3));
                if (!myStocks.contains(myStock)){
                    myStocks.add(myStock);
                }

                listAdapter = new MyStockAdapter(PortfolioActivity.this, R.layout.list_record_portfolio, myStocks);
                portfolioListView.setAdapter(listAdapter);
        }


            registerForContextMenu(portfolioListView);


    }



    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.list_context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {


        AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.delete_id:
                Integer deletedRows = myDbHelper.deleteData(myStocks.get(info.position).getSymbol());
                if (deletedRows > 0) {
                    Toast.makeText(PortfolioActivity.this, "Data Deleted", Toast.LENGTH_LONG).show();
                    myStocks.remove(info.position);
                    listAdapter.notifyDataSetChanged();

                } else
                    Toast.makeText(PortfolioActivity.this, "Data not Deleted", Toast.LENGTH_LONG).show();
                return true;

                case R.id.update_id:
                finish();
                startActivity(new Intent(PortfolioActivity.this, UpdateStockToPortfolio.class));
                return true;


        }
        return super.onContextItemSelected(item);

    }

    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);


        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.main_acitvity:
                finish();
                Log.d(TAG, "Go to MainActivity ");
                return true;
            case R.id.portfolio_activity:
                Toast.makeText(PortfolioActivity.this, "You are already watching your portfolio", Toast.LENGTH_SHORT).show();
            case R.id.logout:
                showLogoutMessage("Logout","Are you sure you want to Logout??");
                return true;
        }
        return super.onOptionsItemSelected(item);
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
                LoginManager.getInstance().logOut();
            }
        });
        builder.show();
    }
}


