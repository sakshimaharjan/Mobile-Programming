package com.example.bcaunit07;

import android.icu.util.Output;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;

public class ApiTestActivity extends AppCompatActivity {

//    Button btnFetch;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_api_test);
//        btnFetch = findViewById(R.id.fetch);
//        btnFetch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                volleyRequest();
//            }
//        });
//    }
//    public void volleyRequest(){
//        // Instantiate the RequestQueue.
//        RequestQueue queue = Volley.newRequestQueue(this);
//        //url for localhost
//        String url ="https://jsonplaceholder.typicode.com/posts";
//        // Request a string response from the provided URL.
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        //displaying response string in logcat
//                        Log.d("result",response);
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                //displaying error response message
//                Log.d("exception",error.toString());
//            }
//        });
//        // Add the request to the RequestQueue.
//        queue.add(stringRequest);
//    }


    Button btnFetch;
    TextView txtResult;

    String url = "https://jsonplaceholder.typicode.com/posts";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api_test);

        btnFetch = findViewById(R.id.fetch);
        txtResult = findViewById(R.id.txtResult);

        btnFetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchData();
            }
        });
    }

    private void fetchData() {
        RequestQueue queue = Volley.newRequestQueue(this);

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        StringBuilder resultBuilder = new StringBuilder();
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject obj = response.getJSONObject(i);
                                String title = obj.getString("title");
                                String body = obj.getString("body");

                                resultBuilder
                                        .append("📌 Title: ").append(title).append("\n")
                                        .append("📝 Body: ").append(body).append("\n\n");
                            }
                            txtResult.setText(resultBuilder.toString());
                        } catch (JSONException e) {
                            txtResult.setText("Parsing error: " + e.getMessage());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        txtResult.setText("Error: " + error.getMessage());
                    }
                });

        queue.add(request);
    }
}
