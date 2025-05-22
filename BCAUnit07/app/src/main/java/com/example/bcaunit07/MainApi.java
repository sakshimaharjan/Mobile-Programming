package com.example.bcaunit07;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class MainApi extends AppCompatActivity {

    EditText inputTitle, inputBody;
    Button btnSend, btnFetch;
    TextView textResult;

    String getUrl = "https://jsonplaceholder.typico de.com/posts/1";
    String postUrl = "https://jsonplaceholder.typicode.com/posts"; // fake API

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api);

        inputTitle = findViewById(R.id.inputTitle);
        inputBody = findViewById(R.id.inputBody);
        btnSend = findViewById(R.id.btnSend);
        btnFetch = findViewById(R.id.btnFetch);
        textResult = findViewById(R.id.textResult);

        btnFetch.setOnClickListener(v -> fetchData());
        btnSend.setOnClickListener(v -> sendData());
    }

    private void fetchData() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, getUrl,
                response -> textResult.setText("GET Response:\n" + response),
                error -> textResult.setText("GET Error: " + error.getMessage())
        );

        MySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }

    private void sendData() {
        String title = inputTitle.getText().toString();
        String body = inputBody.getText().toString();

        JSONObject postData = new JSONObject();
        try {
            postData.put("title", title);
            postData.put("body", body);
            postData.put("userId", 1);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, postUrl, postData,
                response -> textResult.setText("POST Response:\n" + response.toString()),
                error -> textResult.setText("POST Error: " + error.getMessage())
        );

        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
    }
}