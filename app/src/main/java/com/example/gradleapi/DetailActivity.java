package com.example.gradleapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView tv_name = findViewById(R.id.tv_name_detail);
        TextView tv_desc = findViewById(R.id.tv_desc_detail);
        ImageView img_team = findViewById(R.id.img_team_detail);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String name = bundle.getString("name");
            String desc = bundle.getString("desc");
            String image = bundle.getString("image");

            tv_name.setText(name);
            tv_desc.setText(desc);
            Picasso.get().load(image).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher_round).into(img_team);
        }
    }
}