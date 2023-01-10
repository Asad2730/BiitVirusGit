package com.example.biitvirus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.lang.ref.ReferenceQueue;

public class PcInformationActivity extends AppCompatActivity {

    private TextView name,result,last;
    private Button scan;
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pc_information);


        name = findViewById(R.id.name);
        result = findViewById(R.id.result);
        last = findViewById(R.id.last);
        scan = findViewById(R.id.scan);
        queue = Volley.newRequestQueue(this);
        queue.start();

        name.setText(Helper.selected_pc.name);
        result.setText("");
        last.setText("");

        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 scanRs();
            }
        });

    }


    void scanRs(){

        String url = Helper.ip+"checkInfected";
        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                result.setText(response);
                last.setText(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error:"+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        queue.add(request);
    }
}