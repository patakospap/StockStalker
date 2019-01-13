package com.example.panagiotis.testtest;

import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.example.panagiotis.testtest.MainActivity.stocks;


public class DownLoadDataActivity extends AppCompatActivity {

    private List<String> symbols;
    private Button goToMain;

    private static final String TAG = "DOWNLOADATA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_down_load_data);

        symbols = new ArrayList<>();
        symbols.add("AAPL");
        symbols.add("MSFT");
        symbols.add("GOOG");
        symbols.add("AMD");
        symbols.add("GE");
       /* symbols.add("F");
        symbols.add("BAC");
        symbols.add("T");
        symbols.add("CHK");*/
      /*  symbols.add("UBNT");
        symbols.add("FNSR");
        symbols.add("FANDF");
        symbols.add("DJI");
        symbols.add("NDAQ");
        symbols.add("NFLX");*/




        for (String symbol : symbols) {
            DownloadData downloadData = new DownloadData();
            downloadData.execute("https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=" + symbol + "&apikey=624ADSPRSB8EWWB8");
        }

        goToMain = findViewById(R.id.gotoMain);
        goToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(DownLoadDataActivity.this, MainActivity.class));
            }
        });


    }

    private class DownloadData extends AsyncTask<String,Void,String> {

        private static final String TAG = "PapDownloadDataTask";

        @Override
        protected void onPostExecute(String jsonData) {
            super.onPostExecute(jsonData);

            Log.d(TAG, "onPostExecute: " + jsonData);

            JSONParser parser = new JSONParser();

            Stock stock = parser.parseJson(jsonData);
            stocks.add(stock);

        }

        @Override
        protected String doInBackground(String... strings) {
            Log.d(TAG, "doInBackground starts with: " + strings[0]);


            String stockData = downloadJSON(strings[0]);

            if (stockData == null) {
                Log.e(TAG, "doInBackground: Error downloading from url " + strings[0]);
            }
            return stockData;
        }

        private String downloadJSON(String urlPath) {
            StringBuilder stringBuilder = new StringBuilder();

            try {
                URL url = new URL(urlPath);

                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                int responseCode = connection.getResponseCode();
                Log.d(TAG, "downloadJSON: Response code was" + responseCode);

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                String line = reader.readLine();
                while (line != null) {
                    stringBuilder.append(line).append("\n");
                    line = reader.readLine();
                }

                reader.close();

            } catch (MalformedURLException e) {
                Log.e(TAG, "downloadJSON: not correct URL: " + urlPath, e);
            } catch (IOException e) {
                Log.e(TAG, "downloadJSON: io error ", e);
            }

            return stringBuilder.toString();
        }

    }}

