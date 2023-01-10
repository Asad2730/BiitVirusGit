package com.example.biitvirus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class LabsActivity extends AppCompatActivity {

    private Button add;
    private ListView lv;
    private int ids[] = {1,2,3};
    private String names[]= {"Lab 1","Lab 2","Lab 3"};
    private List<LabModel> list;
    private LabAdaptor adaptor;
    private LabModel model;

    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_labs);

        add = findViewById(R.id.add);
        lv = findViewById(R.id.listView);
        queue = Volley.newRequestQueue(this);
        queue.start();
        loadList();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              startActivity(new Intent(getApplicationContext(),AddLabActivity.class));
            }
        });


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                  Intent intent = new Intent(getApplicationContext(),PcActivity.class);
                  Helper.selected_lab = list.get(i);
                  startActivity(intent);
            }
        });
    }

    private void loadList(){
        list = new ArrayList<>();
        String url = Helper.ip+"getLabs?id="+Helper.uid;
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i=0;i<response.length();i++){
                    model = new LabModel();
                    try {
                        model.id = response.getJSONObject(i).getInt("Id");
                        model.name = response.getJSONObject(i).getString("name");
                        list.add(model);
                        adaptor = new LabAdaptor(getApplicationContext(),list);
                        lv.setAdapter(adaptor);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        },null);
        queue.add(request);

    }
}