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

public class PcActivity extends AppCompatActivity {


    private Button add;
    private ListView lv;
    private List<PcModel> list;
    private PcAdaptor adaptor;
    private PcModel model;
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pc);


        add = findViewById(R.id.add);
        lv = findViewById(R.id.listView);
        list = new ArrayList<>();
        queue = Volley.newRequestQueue(this);
        queue.start();

        loadList();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),AddPcActivity.class);
                startActivity(intent);
            }
        });


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(),PcInformationActivity.class);
                Helper.selected_pc = list.get(i);
                startActivity(intent);
            }
        });
    }

    private void loadList() {
        list = new ArrayList<>();
        String url = Helper.ip + "getPcs?id=" + Helper.selected_lab.id;
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            model = new PcModel();
                            try {
                                model.id = response.getJSONObject(i).getInt("Id");
                                model.name = response.getJSONObject(i).getString("name");
                                model.lid = Helper.selected_lab.id;
                                list.add(model);
                                adaptor = new PcAdaptor(getApplicationContext(), list);
                                lv.setAdapter(adaptor);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                }, null);
        queue.add(request);
    }
}