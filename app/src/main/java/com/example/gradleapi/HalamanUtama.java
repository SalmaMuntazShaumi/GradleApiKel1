package com.example.gradleapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;

public class HalamanUtama extends AppCompatActivity {

    private String URL = "https://www.thesportsdb.com/api/v1/json/1/all_sports.php";
    private RecyclerView recyclerView;
    ArrayList<TeamModel> arrayList;
    TeamAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_utama);

        recyclerView = findViewById(R.id.list);
        arrayList = new ArrayList<>();

        getData();
    }

    private void getData(){

        AndroidNetworking.get(URL)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                       try {
                           JSONArray teamsArray = response.getJSONArray("sports");
                           for (int i = 0; i < teamsArray.length(); i++){
                               JSONObject teamObject = teamsArray.getJSONObject(i);
                               String sport = teamObject.getString("strSport");
                               String desc = teamObject.getString("strSportDescription");
                               String image = teamObject.getString("strSportThumb");
                               arrayList.add(new TeamModel(image, sport, desc));

                               adapter = new TeamAdapter(getApplicationContext(), arrayList);
                               RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                               recyclerView.setLayoutManager(layoutManager);
                               recyclerView.setAdapter(adapter);

                               adapter.setOnItemClickListener(new TeamAdapter.OnItemClickListener() {
                                   @Override
                                   public void onItemClick(int position) {
                                        Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                                        intent.putExtra("name", arrayList.get(position).getSport());
                                        intent.putExtra("desc", arrayList.get(position).getDesc());
                                        intent.putExtra("image", arrayList.get(position).getImage());
                                        startActivity(intent);
                                   }
                               });
                           }
                       } catch (Exception e) {
                           e.printStackTrace();
                       }
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });
    }
}