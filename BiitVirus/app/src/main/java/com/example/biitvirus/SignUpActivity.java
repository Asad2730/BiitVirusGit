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
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class SignUpActivity extends AppCompatActivity {

    private EditText email,password;
    private Button signup;
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        signup = findViewById(R.id.signup);

        queue = Volley.newRequestQueue(getApplicationContext());
        queue.start();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String em = email.getText().toString();
                    String pas = password.getText().toString();
                    if(!em.isEmpty() && !pas.isEmpty()) {
                        insertData(em,pas);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }


    void insertData(String em,String pas) throws JSONException {

        String url = Helper.ip+"signUp";

        JSONObject obj = new JSONObject();

        obj.put("email",em);
        obj.put("password",pas);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, obj,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(SignUpActivity.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(SignUpActivity.this, "Error:"+error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        queue.add(request);

    }


}