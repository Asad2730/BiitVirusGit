package com.example.biitvirus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class AddLabActivity extends AppCompatActivity {

    private EditText name;
    private Button add;
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lab);

        name = findViewById(R.id.lab_name);
        add = findViewById(R.id.add_lab_btn);
        queue = Volley.newRequestQueue(this);
        queue.start();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!name.getText().toString().isEmpty()){
                    try {
                        insertData();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void insertData() throws JSONException {
        String url = Helper.ip+"insertLab";
        JSONObject obj = new JSONObject();
        obj.put("uid",Helper.uid);
        obj.put("name",name.getText().toString());
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, obj,
                new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(getApplicationContext(), "Data inserted!", Toast.LENGTH_SHORT).show();
                finish();
                startActivity(new Intent(getApplicationContext(),LabsActivity.class));
            }
        },null);

        queue.add(request);
    }
}